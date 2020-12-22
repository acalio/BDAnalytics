package io.acalio.ytproducers.tasks
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.acalio.dm.model.avro.YChannel
import org.acalio.dm.api.YoutubeDataManager
import java.{util => ju}
import io.acalio.ytproducers.utility.Keys


class ChannelProducer(
  val producerProperties: ju.Properties,
  val config: ju.Properties
) extends KafkaTask {

  def execute() {
    try {
      val producer = new KafkaProducer[String, YChannel] (producerProperties)
      val topic = s"${Keys.PREFIX_CHANNEL}${config.get(Keys.TOPIC_NAME).asInstanceOf[String]}"

      val channelId = config.get(Keys.CHANNEL_ID).asInstanceOf[String]
      val channelInfo: YChannel = YoutubeDataManager.getChannel(channelId);

      val record = new ProducerRecord[String, YChannel](topic, channelInfo)
      producer.send(record)
    } catch {
      case e : Exception => e.printStackTrace()
    }

  }



}
