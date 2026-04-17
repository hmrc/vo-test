import play.core.PlayVersion
import sbt.*

private object LibDependencies {

  val bootstrapVersion               = "10.7.0"
  val scalaTestVersion               = "3.2.20"
  val scalaTestPlusScalaCheckVersion = "3.2.20.0"
  val scalaTestPlusMockitoVersion    = "3.2.20.0"

  val unitTestDependencies: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-30" % bootstrapVersion % Provided,
    "org.apache.pekko"  %% "pekko-testkit"          % PlayVersion.pekkoVersion,
    "org.scalatest"     %% "scalatest"              % scalaTestVersion,
    "org.scalatestplus" %% "scalacheck-1-19"        % scalaTestPlusScalaCheckVersion,
    "org.scalatestplus" %% "mockito-5-23"           % scalaTestPlusMockitoVersion
  )

  val integrationTestDependencies: Seq[ModuleID] = unitTestDependencies

}
