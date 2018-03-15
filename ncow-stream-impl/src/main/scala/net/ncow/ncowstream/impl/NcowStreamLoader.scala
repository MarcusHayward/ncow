package net.ncow.ncowstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import net.ncow.ncowstream.api.NcowStreamService
import net.ncow.ncow.api.NcowService
import com.softwaremill.macwire._

class NcowStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new NcowStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new NcowStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[NcowStreamService])
}

abstract class NcowStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[NcowStreamService](wire[NcowStreamServiceImpl])

  // Bind the NcowService client
  lazy val ncowService = serviceClient.implement[NcowService]
}
