package connectors

import javax.inject.Inject

import config.AppConfig
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}

class BattleNetConnector @Inject()(ws: WSClient, appConfig: AppConfig) {

  val apiKey: String = "9f88h2qzeu82vxxscpr54wuyy89cdzsa"

  private def bossUrl(bossID: String): String = s"${appConfig.battleNetService}/wow/boss/$bossID?locale=en_GBS&apikey=$apiKey"
  private def zoneUrl(zoneID: String): String = s"${appConfig.battleNetService}/wow/zone/$zoneID?locale=en_GB&apikey=$apiKey"

  def getBoss(bossID: Int, overrideUrl: Boolean = false)(implicit ec: ExecutionContext): Future[String] = {
    val request: WSRequest = if(overrideUrl) ws.url("http://ww7.fake.com") else ws.url(bossUrl(bossID.toString))
    request.get().map { response =>
      response.body
    }.recoverWith {
      case _ => Future.failed(new Exception("Request failed!"))
    }
  }

  def getZone(zoneID: Int, overrideUrl: Boolean = false)(implicit ec: ExecutionContext): Future[String] = {
    val request: WSRequest = if(overrideUrl) ws.url("http://ww7.fake.com") else ws.url(zoneUrl(zoneID.toString))
    request.get().map { response =>
      response.body
    }.recoverWith {
      case _ => Future.failed(new Exception("Request failed!"))
    }
  }
}
