
name := "akka-http-example"

version := "0.1"

scalaVersion := "2.12.4"

val akkaHTTPVersion = "10.1.0-RC1"
val akkaVersion = "2.5.9"
val json4sVersion = "3.6.0-M2"


libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHTTPVersion,
    "de.heikoseeberger" %% "akka-http-json4s" % "1.20.0-RC1",
    "org.json4s" %% "json4s-jackson" % json4sVersion
  )
}