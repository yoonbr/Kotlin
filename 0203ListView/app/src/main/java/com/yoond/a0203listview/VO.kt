package com.yoond.a0203listview

import java.io.Serializable

class VO:Serializable{
    var icon = 0
    var name : String?=null
    override fun toString(): String {
        return "VO[icon=${icon}, name=${name}"
    }
}