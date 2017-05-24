package com.czcl.openapi

import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.util.EntityUtils

import scala.collection.mutable.ListBuffer

/**
  * Created by ZYC on 2017/5/22.
  */
class BaseAPI {
  val baseParams:ListBuffer[(String, Any)] = ListBuffer()
  val timestamp = System.currentTimeMillis()/1000
  val nonce = md5("" + System.currentTimeMillis() + "5ea0ac4f-90f5-4136-81ab-615cbca49f34")
  baseParams.append(("accessToken", "93f37d1bcf5b2f022cebc0bc3efd9342"))
  baseParams.append(("client_id", "ae674b84-f266-4785-b37b-228c044be967"))
  baseParams.append(("nonce", nonce))
  baseParams.append(("timestamp", timestamp))
  val httpSchema:String = "http://localhost:8092"
  val appSecret:String = "5ea0ac4f-90f5-4136-81ab-615cbca49f34"

  def md5(text: String) = {
    java.security.MessageDigest.getInstance("SHA-256").digest(text.getBytes()).map(0xFF & _).map{"%02x".format(_)}.foldLeft(""){_+_}.toUpperCase
  }

  def get(url:String, params:ListBuffer[(String, Any)]): Unit = {
    for(element <- baseParams){
      params.append(element)
    }

    val sorted = params.sortWith{case(object1, object2) => object1._1 < object2._1}
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val pairs:ListBuffer[String] = new ListBuffer()
    for(element <- sorted)
      pairs.append(element._1 + "=" + element._2)
    val httpUrl = url + "?" + pairs.mkString("&")
    println(httpUrl)
    val secretUrl = httpUrl + appSecret
    val sign = md5(secretUrl)
//    println(sign)
    val httpGet:HttpGet = new HttpGet(httpSchema + httpUrl + "&sign=" + sign)
    httpGet.setHeader("Content-Type", "application/json;charset:utf-8;")
    httpGet.addHeader("Content-Encoding", "UTF-8")
    val httpResponse:CloseableHttpResponse = client.execute(httpGet)
    try{
      val entity:HttpEntity = httpResponse.getEntity
      println("status:" + httpResponse.getStatusLine)
      if(entity != null){
        println("response content:" + EntityUtils.toString(entity))
      }
    }finally {
      if(httpResponse != null)
        httpResponse.close()
    }
  }

  def get(url:String): Unit = {
    val params:ListBuffer[(String, Any)] = new ListBuffer()
    get(url, params)
  }

  def post(url:String, jsonBody:String): Unit = {
    val params:ListBuffer[(String, Any)] = new ListBuffer()
    for(element <- baseParams)
      params.append(element)
    val sorted = params.sortWith{case(object1, object2) => object1._1 < object2._1}
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val pairs:ListBuffer[String] = new ListBuffer()
    for(element <- sorted)
      pairs.append(element._1 + "=" + element._2)
    val httpUrl = url + "?" + pairs.mkString("&")
    println(httpUrl)
    val secretUrl = httpUrl + appSecret
    val sign = md5(secretUrl)

    val httpPost:HttpPost = new HttpPost(httpUrl)
    httpPost.addHeader("Content-Encoding", "UTF-8")
    httpPost.addHeader("Content-type", "application/json")
    var postEntity:StringEntity = new StringEntity(jsonBody, "UTF-8")
    httpPost.setEntity(postEntity)
    val httpResponse:CloseableHttpResponse = client.execute(httpPost)
    try{
      val entity:HttpEntity = httpResponse.getEntity
      println("status:" + httpResponse.getStatusLine)
      if(entity != null){
        println("response content:" + EntityUtils.toString(entity))
      }
    }finally {
      if(httpResponse != null)
        httpResponse.close()
    }
  }
}
