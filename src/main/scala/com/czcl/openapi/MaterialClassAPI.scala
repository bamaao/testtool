package com.czcl.openapi

/**
  * Created by ZYC on 2017/5/18.
  */
class MaterialClassAPI extends BaseAPI{
  /**
    * 租户的物料分类树
    */
  def tree(): Unit ={
    val url = "/materialclasses/tree"
    get(url)
  }

  /**
    * 某个具体的分类(不包含子分类)
    */
  def oneMaterialClass(classId:Long): Unit ={
    val url = "/materialclasses/" + classId
    get(url)
  }

}

object MaterialClassAPI {
  def main(args: Array[String]): Unit = {
    val api = new MaterialClassAPI
//    api.tree()
    api.oneMaterialClass(500195)
  }
}
