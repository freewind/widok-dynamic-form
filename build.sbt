enablePlugins(ScalaJSPlugin)

name := "widok-dynamic-form"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq(
  "-unchecked", "-deprecation", 
  "-encoding", "utf8", 
  "-Xelide-below", annotation.elidable.ALL.toString
)

persistLauncher := true

libraryDependencies ++= Seq(
  "io.github.widok" %%% "widok" % "0.2.1"
)
