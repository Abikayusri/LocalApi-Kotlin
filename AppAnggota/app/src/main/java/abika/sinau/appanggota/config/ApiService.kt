package abika.sinau.appanggota.config

import abika.sinau.appanggota.model.deleteData.ResponseDelete
import abika.sinau.appanggota.model.getData.ResponseGetData
import abika.sinau.appanggota.model.insertData.ResponseInsert
import abika.sinau.appanggota.model.updateData.ResponseUpdate
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Abika Chairul Yusri
 * on Wednesday, 03 June 2020
 * Bismillahirrahmanirrahim
 */
interface ApiService {

    // method getData
    @GET("getData.php")
    fun getData(): Call<ResponseGetData>

    // method getDataById
    @GET("getData.php")
    fun getDataById(
        @Query("id") id: String): Call<ResponseGetData>

    // insert
    @FormUrlEncoded
    @POST("insertData.php")
    fun insertData(
        @Field("nama") nama: String,
        @Field("nohp") nohp: String,
        @Field("alamat") alamat: String): Call<ResponseInsert>

    // update
    @FormUrlEncoded
    @POST("updateData.php")
    fun updateData(
        @Field("id") id: String,
        @Field("nama") nama: String,
        @Field("nohp") nohp: String,
        @Field("alamat") alamat: String): Call<ResponseUpdate>

    // delete Data
    @POST("deleteData.php")
    fun deleteData(
        @Field("id") id: String): Call<ResponseDelete>
}