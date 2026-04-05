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

/**
  * @author Yuriy Tumakha
  */
class StringExtensionSpec extends BaseSpec:

  private val stringWithEmptyLines = s"""
                                        |<link href="stylesheet/extra-cool.css" media="all" rel="stylesheet" type="text/css" />
                                        |
                                        |
                                        |<p>Some text </p>
                                        |""".stripMargin

  private val trimmedString            = s"""<link href="stylesheet/extra-cool.css" media="all" rel="stylesheet" type="text/css" />\n<p>Some text </p>"""
  private val replacedEndLineWithSpace = """<link href="stylesheet/extra-cool.css" media="all" rel="stylesheet" type="text/css" /><p>Some text </p>"""

  "String extension" should {
    "have a method .trimEmptyLines that trims empty lines from generated strings" in
      forAll {
        (str: String) =>
          str.trimEmptyLines shouldBe str.linesIterator.map(_.trim).filter(_.nonEmpty).mkString("\n")
      }

    "have a method .trimEmptyLines that trims empty lines from a given String" in {
      stringWithEmptyLines.trimEmptyLines shouldBe trimmedString
    }

    "have a method .replaceEndLinesWith that replace end line chars with specified String" in {
      "aaa\n<bbb>\r\nccc\n".replaceEndLinesWith()   shouldBe "aaa <bbb> ccc "
      "aaa\n<bbb>\r\nccc\n".replaceEndLinesWith("") shouldBe "aaa<bbb>ccc"
      stringWithEmptyLines.replaceEndLinesWith("")  shouldBe replacedEndLineWithSpace
    }

  }
