package net.ncow.ncow.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

trait NcowService extends Service {

  def registerBot: ServiceCall[RegisterBotRequest, RegisterBotResponse]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("ncow")
      .withCalls(
        restCall(Method.POST, "/bot/register/", registerBot _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}

case class RegisterBotRequest(name: String)

object RegisterBotRequest {
  implicit val format: Format[RegisterBotRequest] = Json.format[RegisterBotRequest]
}

case class RegisterBotResponse(key: String)
object RegisterBotResponse {
  implicit  val format: Format[RegisterBotResponse] = Json.format[RegisterBotResponse]
}