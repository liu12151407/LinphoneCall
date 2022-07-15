package com.hg.sfern.robot.plugin.generallibrary

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 切换到UI线程的扩展函数
 * @author: liuyinglong
 * @since: 2021-05-11
 * @update [日期YYYY-MM-DD] [更改人姓名][变更描述]   //其中@update默认不加，有更新才添加
 */
private val handler = Handler(Looper.getMainLooper())
private val coreSize = Runtime.getRuntime().availableProcessors() + 1

private val fix: ExecutorService = Executors.newFixedThreadPool(coreSize)
private val cache: ExecutorService = Executors.newCachedThreadPool()
private val single: ExecutorService = Executors.newSingleThreadExecutor()
private val scheduled: ExecutorService = Executors.newScheduledThreadPool(coreSize)
/**
 * 切换到主线程
 */
fun <T> T.ktxRunOnUi(block: T.() -> Unit) {
    handler.post {
        block()
    }
}

/**
 * 延迟delayMillis后切换到主线程
 */
fun <T> T.ktxRunOnUiDelay(delayMillis: Long, block: T.() -> Unit) {
    handler.postDelayed({
        block()
    }, delayMillis)
}

/**
 * 子线程执行。SingleThreadPool
 */
fun <T> T.ktxRunOnBgSingle(block: T.() -> Unit) {
    single.execute {
        block()
    }
}

/**
 * 子线程执行。FixedThreadPool
 */
fun <T> T.ktxRunOnBgFix(block: T.() -> Unit) {
    fix.execute {
        block()
    }
}

/**
 * 子线程执行。CachedThreadPool
 */
fun <T> T.ktxRunOnBgCache(block: T.() -> Unit) {
    cache.execute {
        block()
    }
}