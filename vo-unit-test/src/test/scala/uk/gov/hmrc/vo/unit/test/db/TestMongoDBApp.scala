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

import com.mongodb.client.model.ReturnDocument
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.{Filters, FindOneAndReplaceOptions}
import org.mongodb.scala.{ReadPreference, SingleObservableFuture}

/**
  * @author Yuriy Tumakha
  */
class TestMongoDBApp extends MongoDBAppSpec[DbEntity, DbRepository]:

  private def byId(id: ObjectId): Bson =
    Filters.equal("_id", id)

  "DbRepository" should {
    "save entity to DB and read it back" in {
      val entity = DbEntity(name = "test_entity")

      val saveResult = mongoRepository
        .collection
        .findOneAndReplace(byId(entity._id), entity, FindOneAndReplaceOptions().upsert(true).returnDocument(ReturnDocument.AFTER))
        .toFutureOption().futureValue

      saveResult shouldBe Some(entity)

      val entityFromDb = mongoRepository
        .collection
        .withReadPreference(ReadPreference.primaryPreferred())
        .find(byId(entity._id))
        .first()
        .toFutureOption().futureValue

      entityFromDb shouldBe Some(entity)
    }
  }
