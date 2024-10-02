package example

import kamon.Kamon
import kamon.context.Context
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.actor.typed.{ActorRef, ActorSystem, Behavior}

object Main extends App {
  val UserID = Context.key[String]("userID", "undefined")
  final case class Greet(whom: String, replyTo: ActorRef[SayHello])
  final case class SayHello(name: String)

  object HelloWorld {
    def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
      context.log.info("Hello {}!", message.whom)
      val id = Kamon.currentContext().get(UserID)
      context.log.info("UserID {}!", id)
      message.replyTo ! SayHello(message.whom)
      Behaviors.same
    }
  }

  object HelloWorldMain {
    def apply(): Behavior[SayHello] =
      Behaviors.setup { context =>
        val greeter = context.spawn(HelloWorld(), "greeter")

        Behaviors.receiveMessage { message =>
          greeter ! Greet(message.name, context.self)
          Behaviors.same
        }
      }
  }

  val system: ActorSystem[SayHello] = ActorSystem(HelloWorldMain(), "hello")
  try {
    val kamonContext = Context.of(UserID, "12345")
    Kamon.runWithContext(kamonContext) {
      system ! SayHello("World")
    }
    Thread.sleep(5000)
  } finally {
    system.terminate()
  }
}
