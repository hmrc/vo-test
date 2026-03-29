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

package uk.gov.hmrc.vo.integration.test

import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, get, getRequestedFor, urlEqualTo}
import play.api.test.Helpers.OK
import uk.gov.hmrc.vo.unit.test.BaseSpec

import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}

/**
  * @author Yuriy Tumakha
  */
class HttpWireMockSpec extends BaseSpec with HttpWireMock:

  private val testEndpoint = "/test/endpoint"

  "WireMock endpoint" should {
    "respond with 200 status" in {
      wireMockServer.stubFor(
        get(urlEqualTo(testEndpoint))
          .willReturn(aResponse().withStatus(OK).withBody("OK"))
      )

      val request  = HttpRequest.newBuilder.uri(URI(s"$wireMockBaseUrl$testEndpoint")).GET.build
      val response = HttpClient.newHttpClient.send(request, HttpResponse.BodyHandlers.ofString)

      response.statusCode shouldBe OK
      response.body       shouldBe "OK"

      wireMockServer.verify(getRequestedFor(urlEqualTo(testEndpoint)))
    }
  }
