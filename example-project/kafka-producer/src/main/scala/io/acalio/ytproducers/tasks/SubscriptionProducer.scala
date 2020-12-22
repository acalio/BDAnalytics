package io.acalio.ytproducers.tasks

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.acalio.dm.model.avro.YSubscription
import org.acalio.dm.api.YoutubeDataManager
import java.{util => ju}
import io.acalio.ytproducers.utility.Keys


class SubscriptionProducer (
  val produerProperties: ju.Properties,
  val config: ju.Properties
) extends KafkaTask {

  def execute() {
    try {
      val producer = new KafkaProducer[String, YSubscription](produerProperties)
      val topic: String = s"${Keys.LIMIT_SUBSCRIPTION}${config.get(Keys.TOPIC_NAME).asInstanceOf[String]}"

      val channelId: String = config.get(Keys.CHANNEL_ID).asInstanceOf[String]
      val subLimit: Long = config.get(Keys.LIMIT_SUBSCRIPTION).asInstanceOf[Long]

      val subList: ju.List[YSubscription] = YoutubeDataManager.getSubscriptions(channelId, subLimit);
      val it = subList.iterator
      while(it.hasNext)
        producer.send(new ProducerRecord[String, YSubscription](topic, it.next))

    } catch {
      case e : Exception => e.printStackTrace()
    }

  }



}
