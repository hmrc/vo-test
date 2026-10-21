/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.vo.unit.test

import org.scalatest.AppendedClues.convertToClueful
import org.scalatest.Assertion
import org.scalatest.Assertions.fail
import org.scalatest.matchers.should.Matchers.shouldBe
import play.api.libs.json.{JsObject, JsValue, Json}

import scala.io.Source
import scala.util.Using

/**
  * @author Yuriy Tumakha
  */
trait JsonAssertions(resourceDirectory: String):

  protected def loadJson(jsonFilename: String): JsObject =
    val resourcePath = s"/$resourceDirectory/$jsonFilename"
    val stream       = getClass.getResourceAsStream(resourcePath)

    if stream == null then fail(s"Test resource not found: $resourcePath")

    Using.resource(Source.fromInputStream(stream)) { source =>
      Json.parse(source.mkString).as[JsObject]
    }

  protected def compareJson(actualJson: JsValue, expectedJsonFilename: String): Assertion =
    val expectedJson = loadJson(expectedJsonFilename)

    actualJson shouldBe expectedJson withClue
      s"""\nExpected:
         |${Json.prettyPrint(expectedJson)}
         |
         |Actual:
         |${Json.prettyPrint(actualJson)}
         |""".stripMargin
