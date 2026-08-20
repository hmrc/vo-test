import play.core.PlayVersion
import sbt.*

private object LibDependencies {

  private val bootstrapVersion               = "10.8.0"
  private val hmrcMongoVersion               = "2.13.0"
  private val scalaTestVersion               = "3.2.20"
  private val scalaTestPlusScalaCheckVersion = "3.2.20.0"
  private val scalaTestPlusMockitoVersion    = "3.2.20.0"

  val unitTestDependencies: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-30"  % bootstrapVersion % Provided,
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-play-30"      % hmrcMongoVersion % Provided,
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-test-play-30" % hmrcMongoVersion,
    "org.apache.pekko"  %% "pekko-testkit"           % PlayVersion.pekkoVersion,
    "org.scalatest"     %% "scalatest"               % scalaTestVersion,
    "org.scalatestplus" %% "scalacheck-1-19"         % scalaTestPlusScalaCheckVersion,
    "org.scalatestplus" %% "mockito-5-23"            % scalaTestPlusMockitoVersion
  )

  val integrationTestDependencies: Seq[ModuleID] = unitTestDependencies

}
