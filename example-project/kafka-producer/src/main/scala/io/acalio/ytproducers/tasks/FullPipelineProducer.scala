package io.acalio.ytproducers.tasks

import io.acalio.ytproducers.utility._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.acalio.dm.api.YoutubeDataManager
import org.acalio.dm.model.avro.{YLike,YVideo, YComment, YChannel, YSubscription}
import java.{util => ju}
import scala.collection.mutable.HashSet
import scala.collection.JavaConversions._
import com.google.api.client.googleapis.json.GoogleJsonResponseException

class FullPipelineProducer (
  val producerProperties: ju.Properties,
  val pipelineProperties: ju.Properties
) extends KafkaTask {


  def execute() {
    val kafkaVideoProducer = new KafkaProducer[String, YVideo] (producerProperties)
    val kafkaCommentProducer = new KafkaProducer[String, YComment] (producerProperties)
    val kafkaChannelProducer = new KafkaProducer[String, YChannel] (producerProperties)
    val kafkaLikeProducer = new KafkaProducer[String, YLike] (producerProperties)
    val kafkaSubscriptionsProducer = new KafkaProducer[String, YSubscription] (producerProperties)

    val topic: String = pipelineProperties.get(Keys.TOPIC_NAME).asInstanceOf[String]

    //get the query
    val query = pipelineProperties.get(Keys.QUERY).toString

    //execute the query ovia the api
    println(s"Start crawling from youtube\nPublishing to: $topic \nQuery: $query")
    val relatedVideo: ju.List[YVideo] = YoutubeDataManager
      .executeQuery(query, pipelineProperties.get(Keys.LIMIT_VIDEO).asInstanceOf[Long])

    val processedUsers = new HashSet[String]
    //add the unk user so that it will never be processed
    processedUsers += "unk"

    val vit = relatedVideo.iterator
    while(vit.hasNext) {
      //send to the producer
      val video: YVideo = vit.next
      println(s"=====================\n")
      val title: String = video.getTitle().toString()
      println(s"Get comment for: $title" )
      try {
        val cList =  YoutubeDataManager
          .commentThread(video.getId().toString(),
                pipelineProperties.get(Keys.LIMIT_COMMENT).asInstanceOf[Long])

        val cit = cList.iterator
        while(cit.hasNext) {
          val comment = cit.next
          val authorChannelId = comment.getAuthorChannelId.toString()
          if(!processedUsers(authorChannelId)){
            //the user has not been processed yet
            processedUsers += authorChannelId
            //get author subscriptions
            print(s"Getting Subcriptions for user: ${comment.getAuthorChannelId()} - ")
            var subscriptions: Option[ju.List[YSubscription]] = None
            try {
              subscriptions = Option[ju.List[YSubscription]](YoutubeDataManager
                .getSubscriptions(authorChannelId, pipelineProperties.get(Keys.LIMIT_SUBSCRIPTION).asInstanceOf[Long]))
            } catch {
              case e: GoogleJsonResponseException => {
                //try to get the subscritpions via the acvitiy list
                print("unalbe to donwload subscriptions. Trying via the user activities\r")
                subscriptions = Option[ju.List[YSubscription]](YoutubeDataManager
                  .getSubscriptionsAlternative(authorChannelId, pipelineProperties.get(Keys.LIMIT_SUBSCRIPTION).asInstanceOf[Long]))              }
            }

            if(subscriptions isDefined)
              println(s"got ${subscriptions.get.size}")

            println(s"Getting Likes for user: ${comment.getAuthorChannelId()}")
            //get the author channel likes
            val likes: ju.List[YLike] = YoutubeDataManager
              .getLikes(authorChannelId, pipelineProperties.get(Keys.LIMIT_LIKE).asInstanceOf[Long])

            // likes.foreach(println)
            //get the author channel info
            val channel : YChannel = YoutubeDataManager
              .getChannel(authorChannelId)

            //send subscriptions to kafka
            subscriptions.getOrElse(new ju.LinkedList[YSubscription]).iterator.foreach(
              sub => kafkaSubscriptionsProducer.send(
                new ProducerRecord[String, YSubscription]
                  (Keys.PREFIX_SUBSCRIPTION+topic, sub)
              )
            )

            //send likes to kafka
            likes.iterator.foreach(
              l => kafkaLikeProducer.send(
                new ProducerRecord[String, YLike](Keys.PREFIX_LIKE+topic, l))
            )

            //send channel to kafka
            kafkaChannelProducer.send(
              new ProducerRecord[String, YChannel](Keys.PREFIX_CHANNEL+topic, channel)
            )
          }

          //send comment to kafka
          kafkaCommentProducer.send(
            new ProducerRecord[String, YComment](Keys.PREFIX_COMMENT+topic, comment)
          )
        }
      } catch{
        case e: GoogleJsonResponseException => {}
      } 

      //send video to kafka
      //first the the details of the video
      val videoDetails : YVideo  = YoutubeDataManager.getVideo(video.getId().toString)     
      kafkaVideoProducer.send(
        new ProducerRecord[String, YVideo](Keys.PREFIX_VIDEO+topic, videoDetails)
      )
    }
  }
}


