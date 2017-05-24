package com.czcl.openapi

import scala.collection.mutable.ListBuffer

/**
  * Created by ZYC on 2017/5/18.
  */
class MaterialAPI extends BaseAPI{
  /**
    * 物料分页查询
    */
  def materials(pageNum:Int, pageSize:Int): Unit ={
    val params:ListBuffer[(String, Any)] = new ListBuffer()
    params.append(("pagenum", pageNum))
    params.append(("pagesize", pageSize))
    val url = "/materials"
    get(url, params)
  }

  /**
    * 物料按照分类查询
    */
  def materialsOfClass(classId:Long): Unit ={
    val url = "/materialclasses/" + classId + "/materials"
    get(url)
  }

  /**
    * 某个物料
    */
  def oneMaterial(materialId:Long): Unit ={
    val url = "/materials/" + materialId
    get(url)
  }

  /**
    * 平台物料单位
    * @param pageNum
    * @param pageSize
    */
  def units(pageNum:Int, pageSize:Int): Unit = {
    val params:ListBuffer[(String, Any)] = new ListBuffer()
    params.append(("pagenum", pageNum))
    params.append(("pagesize", pageSize))
    val url = "/units"
    get(url, params)
  }

}

object MaterialAPI {
  def main(args: Array[String]): Unit = {
    val api = new MaterialAPI
//    api.materials(1, 20)
//    api.materialsOfClass(2)
//    api.oneMaterial(1)
    api.units(1, 20)
  }
}
