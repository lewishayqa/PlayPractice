/*
 * Copyright 2017 HM Revenue & Customs
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

package controllers

import javax.inject.Inject

import models.Word
import play.api.i18n.I18nSupport
import play.api.mvc._

class WordSquareController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def exampleWordSquare: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.wordSquare("example", Word.wordForm))
  }

  def wordSquare: Action[AnyContent] = Action { implicit request =>
    val formResult = Word.wordForm.bindFromRequest
    formResult.fold({ formWithErrors =>
      BadRequest(views.html.wordSquare("error", formWithErrors))
    }, { result =>
      Ok(views.html.wordSquare(result.word, Word.wordForm))
    })
  }
}