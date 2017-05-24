package com.czcl.openapi

import java.util.Calendar

import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}

import scala.collection.mutable.ListBuffer

/**
  * Created by ZYC on 2017/5/23.
  */
class PurchaseReturnAPI extends BaseAPI{
  /**
    * 门店的收货单
    */
  def purchaseReturnsOfStore(storeId:Long, beginTimestamp:Long, endTimestamp:Long, pageNum:Int, pageSize:Int, supplierId:Long): Unit ={
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val listBuffer:ListBuffer[(java.lang.String, Any)] = new ListBuffer()
    listBuffer.append(("beginTimestamp", beginTimestamp))
    listBuffer.append(("endTimestamp", endTimestamp))
    listBuffer.append(("pagenum", pageNum))
    listBuffer.append(("pagesize", pageSize))

    if(supplierId > 0) {
      listBuffer.append(("supplierId", supplierId))
    }
    val url = "/stores/" + storeId + "/returns"
    get(url, listBuffer)
  }
}

object PurchaseReturnAPI {
  def main(args: Array[String]): Unit = {
    val api = new PurchaseReturnAPI
    //    val current = System.currentTimeMillis()/1000
    val cal: Calendar = Calendar.getInstance()
    cal.set(Calendar.YEAR, 2017)
    cal.set(Calendar.MONTH, 1)
    cal.set(Calendar.DATE, 1)
    val begin = cal.getTimeInMillis/1000
//    println(begin)
    val end = begin + 90 * 24 * 60 * 60
    api.purchaseReturnsOfStore(1, begin, end, 1, 20, 0)
  }
}
