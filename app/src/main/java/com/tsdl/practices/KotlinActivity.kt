package com.tsdl.practices

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tsdl.practices.base.BaseActivity
import com.tsdl.practices.databinding.ActivityKotlinBinding
import com.tsdl.practices.model.KotlinData
import com.tsdl.practices.model.KotlinSingleton
import com.tsdl.practices.util.LogUtils

class KotlinActivity : BaseActivity() {

    private lateinit var binding : ActivityKotlinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kotlin)
        binding.btnTest.setOnClickListener {
            toast(KotlinData("brand", 10.0).toString())
            KotlinSingleton.singletonTest()
            val list = mutableListOf("list1", "list02", "list003")
            list.add("l4")
//            toast(list.toString())
//            toast(list.maxByOrNull { it.length })
//            toast(list.map { it.toUpperCase() }.toString())
//            toast(list.map { it.toUpperCase() }.toString())
//            toast(list.filter { it.length < 5 }.toString())
//            toast(list.filter { it.length < 5 }.map { it.toUpperCase() }.toString())
//            toast(list.any { it.length < 5 }.toString())
//            toast(list.all { it.length < 5 }.toString())
            val map = mutableMapOf("1" to "one", "2" to "two")
            map["3"] = "three"
//            toast(map.toString())
            log(1)
        }
    }

    private fun log(num : Int, name : String = "hello") {
        LogUtils.logD(TAG, "num is $num, name is $name")
    }
}