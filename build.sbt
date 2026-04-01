import uk.gov.hmrc.DefaultBuildSettings.targetJvm

ThisBuild / majorVersion := 0
ThisBuild / scalaVersion := "3.8.3"
ThisBuild / targetJvm := "jvm-21"
ThisBuild / isPublicArtefact := false
ThisBuild / scalacOptions ++= Seq("-feature", "-Wconf:msg=Flag .* set repeatedly:s")

lazy val library = Project("vo-test", file("."))
  .settings(publish / skip := true)
  .aggregate(voUnitTest, voIntegrationTest)
  .disablePlugins(JUnitXmlReportPlugin)

lazy val voUnitTest = Project("vo-unit-test", file("vo-unit-test"))
  .settings(
    libraryDependencies ++= LibDependencies.unitTestDependencies
  )

lazy val voIntegrationTest = Project("vo-integration-test", file("vo-integration-test"))
  .settings(
    libraryDependencies ++= LibDependencies.integrationTestDependencies
  )
  .dependsOn(voUnitTest)

addCommandAlias("precommit", "scalafmtSbt;scalafmtAll")
