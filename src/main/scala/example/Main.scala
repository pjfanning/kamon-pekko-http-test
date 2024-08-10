package example

import com.typesafe.config.ConfigFactory
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.client.RequestBuilding.Get
import org.apache.pekko.http.scaladsl.model.HttpResponse
import org.apache.pekko.http.scaladsl.server.Directives

import scala.concurrent.{Await, Future}

object Main extends App with Directives {
  val config = ConfigFactory.load()
  implicit val untyped: ActorSystem = ActorSystem.create("Test", config)
  try {
    val future: Future[HttpResponse] = Http().singleRequest(Get("https://www.google.com"))
    future.onComplete(rsp => println(rsp.map(_.status)))(untyped.dispatcher)
    Await.result(future, scala.concurrent.duration.Duration.Inf)
  } finally {
    untyped.terminate()
  }
}
