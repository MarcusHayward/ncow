package net.ncow.ncow.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}
import com.lightbend.lagom.scaladsl.api.transport.Method

trait NcowService extends Service {

  def registerBot(botRegistrationRequest: BotRegistrationRequest): ServiceCall[BotRegistrationRequest, BotRegistrationResponse]

  override final def descriptor = {
    import Service._
    named("ncow")
      .withCalls(
        restCall(Method.POST, "/bot/register", registerBot _)
      )
      .withAutoAcl(true)
  }
}

case class BotRegistrationRequest(name: String)

object BotRegistrationRequest {
  implicit val format: Format[BotRegistrationRequest] = Json.format[BotRegistrationRequest]
}

case class BotRegistrationResponse(key: String)

object BotRegistrationResponse {
  implicit val format: Format[BotRegistrationResponse] = Json.format[BotRegistrationResponse]
}