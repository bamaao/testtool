package com.czcl.openapi

import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.util.EntityUtils

/**
  * Created by ZYC on 2017/5/16.
  */
class ApiSign {
  /**
    * 所有门店信息
    */
  def stores(): Unit ={
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val url = "/stores?accessToken=93f37d1bcf5b2f022cebc0bc3efd9342&client_id=ae674b84-f266-4785-b37b-228c044be967"+"5ea0ac4f-90f5-4136-81ab-615cbca49f34"
    val sign = md5(url)
    println(sign)
    val httpGet:HttpGet = new HttpGet("http://localhost:8092/stores?accessToken=93f37d1bcf5b2f022cebc0bc3efd9342&client_id=ae674b84-f266-4785-b37b-228c044be967&sign=" + sign)
    httpGet.addHeader("Content-Encoding", "UTF-8")
    val httpResponse:CloseableHttpResponse = client.execute(httpGet)
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

object ApiSign {
  def main(args: Array[String]): Unit = {
    val apiSign = new ApiSign()
    apiSign.stores()
  }
}
