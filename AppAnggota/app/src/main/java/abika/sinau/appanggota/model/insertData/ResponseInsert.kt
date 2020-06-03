package abika.sinau.appanggota.model.insertData

import com.google.gson.annotations.SerializedName

data class ResponseInsert(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
