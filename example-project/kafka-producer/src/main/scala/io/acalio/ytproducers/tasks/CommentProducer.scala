package io.acalio.ytproducers.tasks
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, ProducerConfig}
import org.acalio.dm.api.YoutubeDataManager
import org.acalio.dm.model.avro.YComment
import java.util.List
import java.{util => ju}
import io.acalio.ytproducers.utility.Keys
import org.acalio.dm.model.avro.YChannel

class CommentProducer(
  val producerProperties: ju.Properties,
  val config: ju.Properties
    
) extends KafkaTask {

  def execute() {
    try {
      val producer = new KafkaProducer[String, YComment] (producerProperties)
      val topic = s"${Keys.PREFIX_VIDEO}${config.get(Keys.TOPIC_NAME).asInstanceOf[String]}"

      val videoId = config.get(Keys.VIDEO_ID).asInstanceOf[String]

      val commentLimit = config.get(Keys.LIMIT_COMMENT).asInstanceOf[Long]
      val cList =  YoutubeDataManager.commentThread(videoId, commentLimit)
      val it = cList.iterator
      while(it.hasNext)
        producer.send(new ProducerRecord[String, YComment](topic, it.next))

    } catch {
      case e : Exception => e.printStackTrace()
    }
  }



}

