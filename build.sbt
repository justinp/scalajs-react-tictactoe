version := "0.1"

scalaVersion := "2.12.10"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions", "-language:postfixOps")

scalaJSUseMainModuleInitializer := true

npmDependencies in Compile ++= Seq(
  "react" -> "16.7.0",
  "react-dom" -> "16.7.0"
)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "1.0.0",
  "com.github.japgolly.scalajs-react" %%% "core" % "1.6.0",
)

enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)
