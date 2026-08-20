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

package uk.gov.hmrc.vo.unit.test.db

import org.bson.codecs.ObjectIdCodec
import org.mongodb.scala.model.*
import uk.gov.hmrc.mongo.MongoComponent
import uk.gov.hmrc.mongo.play.json.formats.MongoJavatimeFormats
import uk.gov.hmrc.mongo.play.json.{Codecs, PlayMongoRepository}

import java.time.Instant
import java.util.concurrent.TimeUnit
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class DbRepository @Inject() (
  mongo: MongoComponent
)(using ec: ExecutionContext
) extends PlayMongoRepository[DbEntity](
    collectionName = "dbCollection",
    mongoComponent = mongo,
    domainFormat = DbEntity.format,
    indexes = Seq(
      IndexModel(
        Indexes.ascending("createdAt"),
        IndexOptions().name("dbCollectionTTL")
          .expireAfter(30, TimeUnit.DAYS)
      )
    ),
    extraCodecs = Seq(
      ObjectIdCodec(),
      Codecs.playFormatCodec(MongoJavatimeFormats.instantFormat)
    )
  )
