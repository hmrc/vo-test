import play.core.PlayVersion
import sbt.*

private object LibDependencies {

  val bootstrapVersion               = "10.7.0"
  val scalaTestPlusScalaCheckVersion = "3.2.19.0"
  val scalaTestPlusMockitoVersion    = "3.2.19.0"

  val unitTestDependencies: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-30" % bootstrapVersion % Provided,
    "org.apache.pekko"  %% "pekko-testkit"          % PlayVersion.pekkoVersion,
    "org.scalatestplus" %% "scalacheck-1-18"        % scalaTestPlusScalaCheckVersion,
    "org.scalatestplus" %% "mockito-5-12"           % scalaTestPlusMockitoVersion
  )

  val integrationTestDependencies: Seq[ModuleID] = unitTestDependencies

}
