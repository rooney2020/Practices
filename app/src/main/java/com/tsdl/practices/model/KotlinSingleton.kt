package com.tsdl.practices.model

import com.tsdl.common.util.LogUtils

object KotlinSingleton {
    fun singletonTest() {
        LogUtils.logD(KotlinSingleton.javaClass.simpleName, "singletonTest");
    }
}
