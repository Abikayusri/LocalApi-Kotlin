package abika.sinau.appanggota.model.deleteData

import com.google.gson.annotations.SerializedName

data class ResponseDelete(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
