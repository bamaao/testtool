package com.bamaao.testtool.base

import java.lang.reflect.Type
import java.net.URI
import java.util.Date

import com.google.gson._
import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost}
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.util.EntityUtils

/**
  * Created by ZYC on 2016/12/29.
  */
class TestToolBase {

  //schema,host,port
  val HttpUrl = "http://test.mealcome.cn/meallinkapi/"
//  val HttpUrl = "http://api.mealcome.cn/"
  var sessionId:String = ""

  //GET 带sessionId
  def getWithSession(urlPath:String){
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val httpGet:HttpGet = new HttpGet(HttpUrl + urlPath)
    httpGet.addHeader("sessionId", sessionId)
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

  //url不带参数，参数放到parameters中，统一封装到url路径中
  def getWithSessionAndParamaters(urlPath:String, paramaters:Map[String, Object]): Unit ={
    val uriBuilder:URIBuilder = new URIBuilder(HttpUrl + urlPath)
    paramaters.foreach(entry => {
      uriBuilder.addParameter(entry._1, "" + entry._2)
    })
    val uri:URI = uriBuilder.build()
    println("url:" + uri.toString)

    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val httpGet:HttpGet = new HttpGet(uri)
    httpGet.addHeader("sessionId", sessionId)
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

  def getWithSessionBody(urlPath:String, paramaters:java.util.Map[String, Object]): Unit ={

  }

  def postWithSessionBody(urlPath:String, paramaters:java.util.Map[String, Object]): Unit ={
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val httpPost:HttpPost = new HttpPost(HttpUrl + urlPath)
    httpPost.addHeader("sessionId", sessionId)
    httpPost.addHeader("Content-Encoding", "UTF-8")
    httpPost.addHeader("Content-type", "application/json")

    //日期格式数据
    var builder:GsonBuilder = new GsonBuilder()
    val gson:Gson = builder.setDateFormat("yyyy:MM:dd HH:mm:ss").create()

    var postEntity:StringEntity = new StringEntity(gson.toJson(paramaters), "UTF-8")
    httpPost.setEntity(postEntity)
    val httpResponse:CloseableHttpResponse = client.execute(httpPost)
    try{
      val entity:HttpEntity = httpResponse.getEntity
      println("status:" + httpResponse.getStatusLine)
      if(entity != null){
        //println("contentEncoding:" + entity.getContentEncoding)
        println("response content:" + EntityUtils.toString(entity))
      }
    }finally {
      if(httpResponse != null)
        httpResponse.close()
    }
  }
}
