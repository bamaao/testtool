package com.czcl.openapi

/**
  * Created by ZYC on 2017/5/19.
  */
class SupplierPriceAgreementAPI extends BaseAPI{
  /**
    * 供应商的供应价格协议
    */
  def priceAgreement(supplierId:Long): Unit ={
    val url = "/suppliers/" + supplierId + "/priceagreements"
    get(url)
  }

}
object SupplierPriceAgreementAPI {
  def main(args: Array[String]): Unit = {
    val api = new SupplierPriceAgreementAPI
    api.priceAgreement(2)
  }
}