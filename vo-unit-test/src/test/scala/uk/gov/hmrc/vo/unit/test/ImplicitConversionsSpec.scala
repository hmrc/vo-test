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

import play.twirl.api.Html

import java.net.URL
import scala.language.implicitConversions
import uk.gov.hmrc.http.StringContextOps

/**
 * @author Yuriy Tumakha
 */
class ImplicitConversionsSpec extends BaseSpec with ImplicitConversions:

  "ImplicitConversions" should {
    "convert to Option" in {
      val opt: Option[Int] = 123
      opt shouldBe Some(123)

      forAll {
        (str: String) =>
          val opt: Option[String] = str
          opt shouldBe Some(str)
      }
    }

    "convert String to URL" in {
      val url: URL = "http://localhost:8080/path/page?param=value"
      url shouldBe url"http://localhost:8080/path/page?param=value"
    }

    "convert String to Option[URL]" in {
      val urlOpt: Option[URL] = "http://localhost:8080/path/page?param=value"
      urlOpt shouldBe Some(url"http://localhost:8080/path/page?param=value")
    }

    "convert String to Html" in {
      val content: String = "<h1>Page header</h1>"
      val html: Html = content
      html.body shouldBe content
    }

    "convert String to Option[Html]" in {
      val content: String = "<b>Bold text</b>"
      val htmlOpt: Option[Html] = content
      htmlOpt shouldBe Some(Html(content))
    }

    "convert Int to BigDecimal" in {
      val number: Int = 123456
      val bigDecimal: BigDecimal = number
      bigDecimal.intValue shouldBe number
    }

    "convert Int to Option[BigDecimal]" in {
      val number: Int = 777
      val bigDecimalOpt: Option[BigDecimal] = number
      bigDecimalOpt shouldBe Some(BigDecimal(number))
    }

  }
