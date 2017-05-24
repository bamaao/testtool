package com.czcl.openapi

import java.util.Calendar

import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}

import scala.collection.mutable.ListBuffer

/**
  * Created by ZYC on 2017/5/22.
  */
class ReceiptAPI extends  BaseAPI{
  /**
    * 门店的收货单
    */
  def receiptsOfStore(storeId:Long, beginTimestamp:Long, endTimestamp:Long, pageNum:Int, pageSize:Int, supplierId:Long): Unit ={
    val client:CloseableHttpClient = HttpClientBuilder.create().build()
    val listBuffer:ListBuffer[(java.lang.String, Any)] = new ListBuffer()
    listBuffer.append(("beginTimestamp", beginTimestamp))
    listBuffer.append(("endTimestamp", endTimestamp))
    listBuffer.append(("pagenum", pageNum))
    listBuffer.append(("pagesize", pageSize))

    if(supplierId > 0) {
      listBuffer.append(("supplierId", supplierId))
    }

    val url = "/stores/" + storeId + "/receipts"
    get(url, listBuffer)
  }

}

object ReceiptAPI {
  def main(args: Array[String]): Unit = {
    val api = new ReceiptAPI
//    val current = System.currentTimeMillis()/1000
    val cal: Calendar = Calendar.getInstance()
    cal.set(Calendar.YEAR, 2016)
    cal.set(Calendar.MONTH, 2)
    val begin = cal.getTimeInMillis/1000
//    println(begin)
    val end = begin + 30 * 24 * 60 * 60
    api.receiptsOfStore(1, begin, end, 1, 20, 12)
  }
}
