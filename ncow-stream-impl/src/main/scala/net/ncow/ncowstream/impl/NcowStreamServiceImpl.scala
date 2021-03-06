package net.ncow.ncowstream.impl

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.ServiceCall
import net.ncow.ncowstream.api.NcowStreamService
import net.ncow.ncow.api.NcowService

import scala.concurrent.Future

/**
  * Implementation of the NcowStreamService.
  */
class NcowStreamServiceImpl(ncowService: NcowService) extends NcowStreamService {
//  def stream = ServiceCall { hellos =>
//    Future.successful(hellos.mapAsync(8)(ncowService.hello(_).invoke()))
//  }
  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]] = ???
}
