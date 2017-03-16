@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package cn.codetector.vertx.jdbc.coroutine

import io.vertx.core.json.JsonArray
import io.vertx.ext.sql.ResultSet
import io.vertx.ext.sql.SQLConnection
import io.vertx.ext.sql.UpdateResult
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by codetector on 2017/3/15.
 * Kotlin Vert.x JDBC (SQLConnection) Coroutine Extension functions
 */
suspend fun SQLConnection.setAutoCommit(autoCommit: Boolean): Boolean = suspendCoroutine { cont ->
    this.setAutoCommit(autoCommit) {
        cont.resume(it.succeeded())
    }
}

suspend fun SQLConnection.execute(query: String): Boolean = suspendCoroutine { cont ->
    this.execute(query) {
        cont.resume(it.succeeded())
    }
}

suspend fun SQLConnection.query(query: String): ResultSet = suspendCoroutine { cont ->
    this.query(query) {
        if (it.succeeded()) {
            cont.resume(it.result())
        } else {
            cont.resumeWithException(it.cause())
        }
    }
}

suspend fun SQLConnection.queryWithParams(query: String, args: JsonArray): ResultSet = suspendCoroutine { cont ->
    this.queryWithParams(query, args) {
        if (it.succeeded()) {
            cont.resume(it.result())
        } else {
            cont.resumeWithException(it.cause())
        }
    }
}

suspend fun SQLConnection.update(query: String): UpdateResult = suspendCoroutine { cont ->
    this.update(query) {
        if (it.succeeded()) {
            cont.resume(it.result())
        } else {
            cont.resumeWithException(it.cause())
        }
    }
}

suspend fun SQLConnection.updateWithParams(query: String, args: JsonArray): UpdateResult = suspendCoroutine { cont ->
    this.updateWithParams(query, args) {
        if (it.succeeded()) {
            cont.resume(it.result())
        } else {
            cont.resumeWithException(it.cause())
        }
    }
}

suspend fun SQLConnection.close(): Boolean = suspendCoroutine { cont ->
    this.close {
        cont.resume(it.succeeded())
    }
}