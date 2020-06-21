import Dependencies._
import plugins._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ovh.fandemonium"
ThisBuild / organizationName := "libbooru"

mainClass in Compile := Some("ovh.fandemonium.libbooru.Main")

lazy val root = (project in file("."))
  .settings(
    name := "libbooru",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
  )
