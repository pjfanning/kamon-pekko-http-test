# kamon-pekko-http-test

Run with `sbt run`.

What does it do?
* It starts Kamon metrics gathering
* Sends a message to a Pekko actor and gets a response
* Leaves a Prometheus endpoint available at http://localhost:9095/metrics
* Eventually terminates the actor system after a Thread sleep
