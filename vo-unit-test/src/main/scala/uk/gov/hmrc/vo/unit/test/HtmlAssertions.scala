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

import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements
import org.scalatest.AppendedClues.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.{Assertion, Succeeded}

import scala.language.implicitConversions

/**
  * HTML assertions implemented using Jsoup and ScalaTest.
  *
  * @author Yuriy Tumakha
  */
trait HtmlAssertions:

  this: Matchers =>

  private def getElementById(elementId: String)(using doc: Document): Option[Element] =
    Option(doc.body().getElementById(elementId))

  private def getElementsByClass(className: String)(using doc: Document): Elements =
    doc.body().getElementsByClass(className)

  implicit def parsePage(html: String): Document = Jsoup.parse(html)

  def select(cssQuery: String)(using doc: Document): Elements =
    doc.select(cssQuery)

  def selectFirst(cssQuery: String, errorMessage: String = "")(using doc: Document): Element =
    val errorClue = if errorMessage.nonEmpty then errorMessage else s". Element could not be found using CSS query: $cssQuery"

    val elementOpt = Option(doc.selectFirst(cssQuery))
    elementOpt should not be empty withClue errorClue
    elementOpt.get

  def assertNoElement(cssQuery: String, errorMessage: String = "")(using doc: Document): Assertion =
    val errorClue = if errorMessage.nonEmpty then errorMessage else s". Element matching CSS query '$cssQuery' was not expected to be present on the page."

    Option(doc.selectFirst(cssQuery)) shouldBe empty withClue errorClue

  def assertH1Text(h1Text: String)(using doc: Document): Assertion =
    val headerH1 = selectFirst("h1", ". The <h1> element could not be found.")

    val h1Count = select("h1").size
    h1Count shouldBe 1 withClue s". Page contains $h1Count <h1> elements."

    headerH1.text shouldBe h1Text withClue ". H1 text does not match the expected value."

  def assertPageContainsElement(html: String, elementId: String): Assertion =
    assertPageContainsElement(elementId)(using html)

  def assertPageDoesNotContainElement(html: String, elementId: String): Assertion =
    assertPageDoesNotContainElement(elementId)(using html)

  def assetPageContainsSummaryErrors(html: String, expectedErrors: List[String]): Assertion =
    assetPageContainsSummaryErrors(expectedErrors)(using html)

  def assertPageContainsElement(elementId: String)(using doc: Document): Assertion =
    getElementById(elementId) should not be empty withClue s"The element with id '$elementId' could not be found."

  def assertPageDoesNotContainElement(elementId: String)(using doc: Document): Assertion =
    getElementById(elementId) shouldBe empty withClue s"The element with id '$elementId' was not expected to be present on the page."

  def assetPageContainsSummaryErrors(expectedErrors: List[String])(using doc: Document): Assertion =
    val errorsSummary = getElementsByClass("govuk-error-summary__list").first()
    withClue("An unexpected number of errors was detected in the Errors Summary.") {
      errorsSummary.select("li").size shouldEqual expectedErrors.length
    }
    expectedErrors.zipWithIndex
      .map { case (expectedError, i) =>
        errorsSummary.select(s"li:nth-child(${i + 1}) > a").text shouldEqual expectedError
      }
      .forall(_ == Succeeded) shouldEqual true
