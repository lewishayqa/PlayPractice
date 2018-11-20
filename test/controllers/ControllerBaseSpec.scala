package controllers

import mocks.MockAppConfig
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpecLike}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc._
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

trait ControllerBaseSpec extends WordSpecLike with Matchers with MockFactory with GuiceOneAppPerSuite {

  implicit val mockAppConfig: MockAppConfig = new MockAppConfig(app.configuration)
  implicit val cc: ControllerComponents = Helpers.stubControllerComponents()
  implicit val ec: ExecutionContext = ExecutionContext.global

  implicit val defaultTimeout: Duration = Duration(5, "seconds")

  def await[A](future: Future[A])(implicit timeout: Duration): A = Await.result(future, timeout)

  def status(of: Result): Int = of.header.status

  def status(of: Future[Result])(implicit timeout: Duration): Int = status(Await.result(of, timeout))

  val fakeRequest = FakeRequest()
}
