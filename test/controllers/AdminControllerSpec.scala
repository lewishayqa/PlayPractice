package controllers

import common.SessionKeys
import play.api.http.Status
import play.api.test.Helpers._

class AdminControllerSpec extends ControllerBaseSpec {

  val controller = new AdminController(cc, mockAppConfig)

  "Calling the admin action" when {

    "user is logged in as an admin" should {

      val adminRequest = fakeRequest.withSession(SessionKeys.adminStatus -> "true")
      val result = controller.admin(adminRequest)

      "return 200" in {
        status(result) shouldBe Status.OK
      }

      "return HTML" in {
        contentType(result) shouldBe Some("text/html")
        charset(result) shouldBe Some("utf-8")
      }
    }

    "user is not logged in" should {

      val result = controller.admin(fakeRequest)

      "return 401" in {
        status(result) shouldBe Status.SEE_OTHER
      }

      "redirect the user to the login page" in {
        redirectLocation(result) shouldBe Some("/login")
      }
    }
  }

  "Calling the loginShow action" should {

    val result = controller.loginShow(fakeRequest)

    "return 200" in {
      status(result) shouldBe Status.OK
    }

    "return HTML" in {
      contentType(result) shouldBe Some("text/html")
      charset(result) shouldBe Some("utf-8")
    }
  }

  "Calling the login action" when {

    "there are no errors in the form" should {

      val result = controller.login(fakeRequest.withFormUrlEncodedBody("username" -> "admin", "password" -> "cactus"))

      "return 200" in {
        status(result) shouldBe Status.OK
      }

      "return HTML" in {
        contentType(result) shouldBe Some("text/html")
        charset(result) shouldBe Some("utf-8")
      }
    }

    "there are errors in the form" should {

      val result = controller.login(fakeRequest.withFormUrlEncodedBody("username" -> "", "password" -> ""))

      "return 400" in {
        status(result) shouldBe Status.BAD_REQUEST
      }

      "return HTML" in {
        contentType(result) shouldBe Some("text/html")
        charset(result) shouldBe Some("utf-8")
      }
    }
  }
}
