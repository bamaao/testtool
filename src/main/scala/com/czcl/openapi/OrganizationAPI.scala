package com.czcl.openapi

/**
  * Created by ZYC on 2017/5/16.
  */
class OrganizationAPI extends BaseAPI{
  /**
    * 所有门店信息
    */
  def stores(): Unit ={
    val url = "/stores"
    get(url)
  }

  /**
    * 某个门店信息
    */
  def oneStore(id:Long): Unit ={
    val url = "/stores/" + id
    get(url)
  }

  /**
    * 租户信息
    */
  def tenant(): Unit ={
    val url = "/tenant"
    get(url)
  }

  /**
    * 某个门店的所有部门
    */
  def depts(storeId:Long): Unit ={
    val url = "/stores/" + storeId + "/depts"
    get(url)
  }

  /**
    * 某个门店的某个部门
    */
  def dept(deptId:Long, storeId:Long): Unit ={
    val url = "/stores/" + storeId + "/depts/" + deptId
    get(url)
  }

  /**
    * 某个门店的直属员工
    */
  def employeesOfStore(storeId:Long): Unit ={
    val url = "/stores/" + storeId + "/employees"
    get(url)
  }

  /**
    * 某个门店的部门直属员工
    */
  def employeesOfDept(deptId:Long, storeId:Long): Unit ={
    val url = "/stores/" + storeId + "/depts/" + deptId + "/employees"
    get(url)
  }

  /**
    * 某个门店的某个员工
    */
  def oneEmployee(employeeId:Long, storeId:Long): Unit ={
    val url = "/stores/" + storeId + "/employees/" + employeeId
    get(url)
  }

}

object OrganizationAPI {
  def main(args: Array[String]): Unit = {
    val apiSign = new OrganizationAPI()
//    apiSign.stores()
//    apiSign.tenant()
//    apiSign.oneStore(50002)
//    apiSign.depts(50002)
//    apiSign.dept(500057, 50002)
    //获取门店直属员工信息
//    apiSign.employeesOfStore(50002)
//    apiSign.employeesOfDept(500063, 50002)
    apiSign.oneEmployee(6000009, 50002)
  }
}
