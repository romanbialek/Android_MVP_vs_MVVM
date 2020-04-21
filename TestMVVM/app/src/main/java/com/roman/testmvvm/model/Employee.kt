package com.roman.testmvvm.model

import java.io.Serializable

data class Employee(val id:Int, val email:String, val first_name:String, val last_name:String,  val  avatar:String): Serializable{
    val full_name = "$first_name $last_name"
}