package com.prashant.mapincompose.model

data class Response(
	val response: List<ResponseItem?>? = null
)

data class ResponseItem(
	val distance: Any? = null,
	val latitude: Double? = null,
	val name: String? = null,
	val time: Any? = null,
	val fareStage: String? = null,
	val seq: Int? = null,
	val longitude: Double? = null
)

