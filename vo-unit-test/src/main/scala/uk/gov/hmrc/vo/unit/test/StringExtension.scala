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
trait StringExtension:

  extension (string: String)

    /**
     * Replace multiple whitespace characters (spaces, tabs, newlines) with a single space and trim leading/trailing whitespace.
     */
    def normalizeWhitespace: String =
      string.replaceAll("\\s+", " ").trim

    def trimEmptyLines: String =
      string.linesIterator.filter(_.trim.nonEmpty).mkString("\n")

    def replaceEndLinesWith(replaceWith: String = " "): String =
      string.replace("\r\n", replaceWith).replace("\r", replaceWith).replace("\n", replaceWith)
