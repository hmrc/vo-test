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
class TestBaseApp extends BaseAppSpec:

  "BaseAppSpec" should {
    "provide messagesApi by InjectedAppObjects" in {

      logger.info("\n" + messagesApi.messages)

      val messages = messagesApi.preferred(Seq.empty)
      messages.lang.language shouldBe "en"
      messages("error.date") shouldBe "Valid date required"
    }

    "provide stub Messages" in {
      val messages = stubMessages(
        "message.key.1"               -> "Value 1",
        "section1.page3.field1.label" -> "Field1 label"
      )
      messages.lang.language shouldBe "en"
      messages("message.key.1")               shouldBe "Value 1"
      messages("section1.page3.field1.label") shouldBe "Field1 label"
      messages.isDefinedAt("error.date")      shouldBe false
    }

  }
