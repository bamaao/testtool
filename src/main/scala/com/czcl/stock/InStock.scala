package com.czcl.stock

import java.util

import com.bamaao.testtool.base.TestToolBase
import com.google.gson.Gson
import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.util.EntityUtils

/**
  * Created by ZYC on 2016/12/29.
  */
class InStock extends TestToolBase{

  sessionId = "4156277f89d442fc804a3775fdd4ea8a"

  //登录
  def login(userName:String, password:String): Unit ={
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val httpPost:HttpPost = new HttpPost(HttpUrl + "base/users/login")
    httpPost.addHeader("Content-Encoding", "UTF-8")
    httpPost.addHeader("Content-type", "application/json")
    val gson:Gson = new Gson()
    var parameter:util.HashMap[String, Object] = new util.HashMap[String, Object]()
    parameter.put("code", userName)
    parameter.put("password", password)
    var postEntity:StringEntity = new StringEntity(gson.toJson(parameter), "UTF-8")
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

  //获取租户的门店信息
  def testGetStoresOfTenant(tenantId:Long): Unit ={
    getWithSession("openapi/tenant/branch/"+ tenantId)
  }

  //获取门店的部门信息
  def testGetDepartmentsOfStore(storeId:Long): Unit ={
    getWithSession("openapi/tenant/department/" + storeId)
  }

  //物料变更记录,时间全部是默认北京时间
  def testMaterialChangeRecords(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate"->beginDate, "endDate"->endDate)
    getWithSessionAndParamaters("openapi/material/changes", parameters)
  }

  //物料类别变更记录,时间全部是默认北京时间
  def testMaterialClassChangeRecords(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate"->beginDate, "endDate"->endDate)
    getWithSessionAndParamaters("openapi/materials/classes/changes", parameters)
  }

  //物料单位变更记录
  def testMaterialUnitChangeRecords(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate"->beginDate, "endDate"->endDate)
    getWithSessionAndParamaters("openapi/materials/units/changes", parameters)
  }

  //供应商信息变更记录
  def testSupplierChangeRecords(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate"->beginDate, "endDate"->endDate)
    getWithSessionAndParamaters("openapi/suppliers/changes", parameters)
  }

  //供应商供货价格变更记录
  def testSupplierPriceChangeRecords(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate"->beginDate, "endDate"->endDate)
    getWithSessionAndParamaters("openapi/suppliers/prices/changes", parameters)
  }

  //餐厅收货记录
  def testPurchaseChangeRecords(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate"->beginDate, "endDate"->endDate)
    getWithSessionAndParamaters("openapi/purchase/changes", parameters)
  }

  //申购单(已完成)
  def testRequisition(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate"->beginDate, "endDate"->endDate)
    getWithSessionAndParamaters("openapi/requisition/changes", parameters)
  }

  //采购订单
  def testOrder(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate" -> beginDate, "endDate" -> endDate)
    getWithSessionAndParamaters("openapi/purchase/order/changes", parameters)
  }

  //退货记录
  def testReturn(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate" -> beginDate, "endDate" -> endDate)
    getWithSessionAndParamaters("openapi/return/changes", parameters)
  }

  //调拨单
  def testAllocation(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate" -> beginDate, "endDate" -> endDate)
    getWithSessionAndParamaters("openapi/allocation/changes", parameters)
  }

  //盘点单
  def testInventory(beginDate:String, endDate:String): Unit = {
    val parameters = Map("beginDate" -> beginDate, "endDate" -> endDate)
    getWithSessionAndParamaters("openapi/inventory/changes", parameters)
  }

}

object InStock {
  def main(args: Array[String]): Unit = {
    val inStock = new InStock()
//    inStock.login("18975807256", "654321")
    inStock.login("szbyy008", "1")
//    inStock.testGetStoresOfTenant(1)
//    inStock.testGetDepartmentsOfStore(1)
//    inStock.testMaterialChangeRecords("2017-01-11 00:00:00", "2017-01-11 10:00:00")
//    inStock.testMaterialClassChangeRecords("2015-12-20 00:00:00", "2015-12-25 00:00:00")
//    inStock.testMaterialUnitChangeRecords("2015-12-01 00:00:00", "2017-01-23 00:00:00")
//    inStock.testSupplierChangeRecords("2015-12-01 00:00:00", "2017-01-23 00:00:00")
//      inStock.testSupplierPriceChangeRecords("2016-06-01 00:00:00", "2016-07-01 00:00:00")
//    inStock.testPurchaseChangeRecords("2017-03-07 00:00:00", "2017-03-07 12:00:00")
//    inStock.testRequisition("2017-01-01 00:00:00", "2017-01-23 00:00:00")
//    inStock.testOrder("2017-01-01 00:00:00", "2017-01-23 00:00:00")
//    inStock.testAllocation("2016-09-01 00:00:00", "2016-10-01 00:00:00")
//    inStock.testReturn("2016-12-01 00:00:00", "2017-01-01 00:00:00")
//    inStock.testInventory("2016-11-01 00:00:00", "2016-12-01 00:00:00")
  }
}


