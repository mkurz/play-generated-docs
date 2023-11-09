//
// Copyright (C) Lightbend Inc. <https://www.lightbend.com>
//

//#akka-update
// The newer Akka version you want to use.
val akkaVersion = "2.6.21"

// Akka dependencies used by Play
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j"  % akkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson"  % akkaVersion,
  // Only if you are using Akka Testkit
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion
)
//#akka-update

//#akka-http-update
// The newer Akka HTTP version you want to use.
val akkaHTTPVersion = "10.2.10"

// Akka HTTP dependencies used by Play
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core" % akkaHTTPVersion,
  // Add this one if you are using HTTP/2
  // (e.g. with enabled PlayAkkaHttp2Support sbt plugin in build.sbt)
  "com.typesafe.akka" %% "akka-http2-support" % akkaHTTPVersion
)
//#akka-http-update
