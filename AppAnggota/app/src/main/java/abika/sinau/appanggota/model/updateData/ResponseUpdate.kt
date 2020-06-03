package abika.sinau.appanggota.model.updateData

import com.google.gson.annotations.SerializedName

data class ResponseUpdate(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
