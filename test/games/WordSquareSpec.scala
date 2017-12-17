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

package games

import org.scalatest.{Matchers, WordSpecLike}

class WordSquareSpec extends WordSpecLike with Matchers {

  "Calling the generateSquare function" should {

    "generate a valid word square from a single word with no spaces" in {

      val result = WordSquare.generateSquare("heLLo")

      val expectedResult = Array("H E L L O",
                                "E       L",
                                "L       L",
                                "L       E",
                                "O L L E H")

      result shouldBe expectedResult
    }

    "generate a valid word square from a sentence with multiple spaces" in {

      val result = WordSquare.generateSquare("hoW arE yOu")

      val expectedResult = Array("H O W   A R E   Y O U",
                                "O                   O",
                                "W                   Y",
                                "                     ",
                                "A                   E",
                                "R                   R",
                                "E                   A",
                                "                     ",
                                "Y                   W",
                                "O                   O",
                                "U O Y   E R A   W O H")

      result shouldBe expectedResult
    }

    "generate the word 'BLANK' when no word is entered" in {

      val result = WordSquare.generateSquare("")

      val expectedResult = Array("B L A N K",
                                "L       N",
                                "A       A",
                                "N       L",
                                "K N A L B")

      result shouldBe expectedResult
    }
  }
}
