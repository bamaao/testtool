package com.czcl.openapi

import java.util

import com.bamaao.testtool.base.TestToolBase
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.{HttpEntity, NameValuePair}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

/**
  * Created by ZYC on 2017/4/13.
  */
class Oauth2 extends TestToolBase{
  //登录
  def accessToken(): Unit ={
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val httpPost:HttpPost = new HttpPost("http://localhost:8082/accessToken")//?client_secret=1&client_id=ae674b84-f266-4785-b37b-228c044be967&grant_type=authorization_code&code=6604f8f45deb0afa2044715ac1fa30c6&redirect_uri=http://localhost:8082/
    httpPost.addHeader("Content-Encoding", "UTF-8")
    httpPost.addHeader("Content-type", "application/x-www-form-urlencoded")

    var params: util.ArrayList[NameValuePair] = new util.ArrayList[NameValuePair]()
    params.add(new BasicNameValuePair("client_id", "ae674b84-f266-4785-b37b-228c044be967"))
    params.add(new BasicNameValuePair("client_secret", "1"))
    params.add(new BasicNameValuePair("grant_type", "authorization_code"))
    params.add(new BasicNameValuePair("code", "4b943f79a9972b2e3d29ff4e3deebb2d"))
    params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8082/"))

    val url = "/accessToken?client_id=ae674b84-f266-4785-b37b-228c044be967&client_secret=1&code=4b943f79a9972b2e3d29ff4e3deebb2d&grant_type=authorization_code&redirect_uri=http://localhost:8082/&secret=5ea0ac4f-90f5-4136-81ab-615cbca49f34"
    val sign = md5(url)
    println(sign)
    params.add(new BasicNameValuePair("sign", sign))

    var postEntity:UrlEncodedFormEntity = new UrlEncodedFormEntity(params)
    httpPost.setEntity(postEntity)
    val httpResponse:CloseableHttpResponse = client.execute(httpPost)
    try{
      val entity:HttpEntity = httpResponse.getEntity
      println("status:" + httpResponse.getStatusLine)
      if(entity != null){
        //        println("contentEncoding:" + entity.getContentEncoding)
        println("response content:" + EntityUtils.toString(entity))
      }
    }finally {
      if(httpResponse != null)
        httpResponse.close()
    }
  }

  def md5(text: String) = {
    java.security.MessageDigest.getInstance("SHA-256").digest(text.getBytes()).map(0xFF & _).map{"%02x".format(_)}.foldLeft(""){_+_}.toUpperCase
  }
}
object Oauth2{
  def main(args: Array[String]): Unit = {
    val oauth2 = new Oauth2()
    oauth2.accessToken()
//    val message = "/accessToken?client_id=ae674b84-f266-4785-b37b-228c044be967&client_secret=1&code=6604f8f45deb0afa2044715ac1fa30c6&grant_type=authorization_code&redirect_uri=http://localhost:8082/&secret=5ea0ac4f-90f5-4136-81ab-615cbca49f34"
//    println(oauth2.md5(message)) 8FDF4236F10845F765BDB56560085C6C8B8A20BD3C3A6F2E5F0ACBA7DF2C02F3
  }
}
