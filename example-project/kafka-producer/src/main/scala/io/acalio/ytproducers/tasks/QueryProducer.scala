package io.acalio.ytproducers.tasks

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, ProducerConfig}
import org.acalio.dm.api.YoutubeDataManager
import org.acalio.dm.model.avro.{YVideo,YComment}
import java.util.List
import java.{util => ju}
import io.acalio.ytproducers.utility.Keys
import com.google.api.client.googleapis.json.GoogleJsonResponseException

class QueryProducers (
  val producerProperties: ju.Properties,
  val config: ju.Properties
) extends KafkaTask {

  def execute() {
    try{
      val producerVideo = new KafkaProducer[String, YVideo](producerProperties)
      val producerComment = new KafkaProducer[String, YComment](producerProperties)

      val topic: String = config.get(Keys.TOPIC_NAME).asInstanceOf[String]
      val topicVideo: String = s"${Keys.PREFIX_VIDEO}$topic"
      val topicComment: String = s"${Keys.PREFIX_COMMENT}$topic"

      val query: String = config.get(Keys.QUERY).asInstanceOf[String]

      val videoLimit: Long = config.get(Keys.LIMIT_VIDEO).asInstanceOf[Long]
      val commentLimit: Long = config.get(Keys.LIMIT_COMMENT).asInstanceOf[Long]


      println(s"Start crawling from youtube\nPublishing to: $topic \nQuery: $query")
      val relatedVideos : List[YVideo] = YoutubeDataManager.executeQuery(query, videoLimit)
      val videoIt = relatedVideos.iterator
      while(videoIt.hasNext){
        val video: YVideo = videoIt.next
        //get video full inforamtion
        val videoFull  = YoutubeDataManager.getVideo(video.getId().toString)

        val title: String = video.getTitle().toString()
        println(s"Get comment for: $title")

        try {
          //comments might be disabled on the current video
          val cList: List[YComment] = YoutubeDataManager.commentThread(video.getId().toString(),commentLimit);
          val it = cList.iterator
          while(it.hasNext)
            producerComment.send(new ProducerRecord[String, YComment](topicComment, it.next))
        }catch{
          case e : GoogleJsonResponseException => {
            videoFull.setDisabledComments(false)
          }
        }
        println(videoFull)
        producerVideo.send(new ProducerRecord[String, YVideo](topicVideo,videoFull))
      }
      } catch {
        case e : Exception => e.printStackTrace()
      }
    }

}

