package dev.lepshee.whop.blocking

import kotlinx.coroutines.runBlocking

internal fun <T> runBlockingWhop(block: suspend () -> T): T = runBlocking { block() }
