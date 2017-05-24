package com.czcl.openapi

/**
  * Created by ZYC on 2017/5/19.
  */
class SupplierAPI extends BaseAPI{
  /**
    * 所有供应商
    */
  def suppliers(): Unit ={
    val url = "/suppliers"
    get(url)
  }

  /**
    * 某个供应商
    */
  def oneSupplier(supplierId:Long): Unit ={
    val url = "/suppliers/" + supplierId
    get(url)
  }

}

object SupplierAPI {
  def main(args: Array[String]): Unit = {
    val api = new SupplierAPI
//    api.suppliers()
    api.oneSupplier(2)
  }
}
