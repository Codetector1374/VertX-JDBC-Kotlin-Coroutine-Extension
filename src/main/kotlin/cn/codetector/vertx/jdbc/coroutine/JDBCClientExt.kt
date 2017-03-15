package cn.codetector.vertx.jdbc.coroutine

import io.vertx.ext.jdbc.JDBCClient
import io.vertx.ext.sql.SQLConnection
import kotlin.coroutines.experimental.suspendCoroutine

suspend fun JDBCClient.getConnection(): SQLConnection = suspendCoroutine { cont ->
    this.getConnection{ conn ->
        cont.resume(conn.result())
    }
}

