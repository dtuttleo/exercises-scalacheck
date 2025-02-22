import com.jsuereth.sbtpgp.PgpKeys.publishSigned

ThisBuild / organization       := "org.scala-exercises"
ThisBuild / githubOrganization := "47degrees"
ThisBuild / scalaVersion       := "2.13.7"

// This is required by the exercises compiler:
publishLocal  := (publishLocal dependsOn compile).value
publishSigned := (publishSigned dependsOn compile).value

addCommandAlias("ci-test", "scalafmtCheckAll; scalafmtSbtCheck; test")
addCommandAlias("ci-docs", "github; documentation/mdoc; headerCreateAll")
addCommandAlias("ci-publish", "github; ci-release")

lazy val exercises = (project in file("."))
  .settings(name := "exercises-scalacheck")
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-exercises"        %% "exercise-compiler"           % "0.6.7",
      "org.scala-exercises"        %% "definitions"                 % "0.6.7",
      "joda-time"                   % "joda-time"                   % "2.10.13",
      "com.47deg"                  %% "scalacheck-toolbox-datetime" % "0.6.0",
      "com.chuusai"                %% "shapeless"                   % "2.3.7",
      "org.scalatest"              %% "scalatest"                   % "3.2.10",
      "org.scalacheck"             %% "scalacheck"                  % "1.15.4",
      "org.scalatestplus"          %% "scalacheck-1-14"             % "3.2.2.0",
      "com.github.alexarchambault" %% "scalacheck-shapeless_1.15"   % "1.3.0"
    )
  )
  .enablePlugins(ExerciseCompilerPlugin)

lazy val documentation = project
  .settings(mdocOut := file("."))
  .settings(publish / skip := true)
  .enablePlugins(MdocPlugin)
