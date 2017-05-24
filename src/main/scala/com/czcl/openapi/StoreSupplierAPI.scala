package com.czcl.openapi

import scala.collection.mutable.ListBuffer

/**
  * Created by ZYC on 2017/5/19.
  */
class StoreSupplierAPI extends BaseAPI{
  /**
    * 门店的所有供应商
    */
  def suppliersOfStore(storeId:Long): Unit ={
    val url = "/stores/" + storeId + "/suppliers"
    get(url)
  }

  /**
    * 供应商在门店的所有供应物料
    */
  def storeMaterials(storeId:Long, supplierId:Long, pageNum:Int, pageSize:Int): Unit ={
    val url = "/stores/" + storeId + "/suppliers/" + supplierId + "/materials"
    val params:ListBuffer[(String, Any)] = new ListBuffer()
    params.append(("pagenum", pageNum))
    params.append(("pagesize", pageSize))
    get(url, params)
  }

}
object StoreSupplierAPI {
  def main(args: Array[String]): Unit = {
    val api = new StoreSupplierAPI
//    api.suppliersOfStore(1)
    api.storeMaterials(1,2, 1, 20)//服务端必须分页，要不然会超过grpc帧页面大小(4M)
  }
}
