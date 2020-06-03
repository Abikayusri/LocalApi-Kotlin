package abika.sinau.appanggota.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abika Chairul Yusri
 * on Wednesday, 03 June 2020
 * Bismillahirrahmanirrahim
 */
object NetworkModule {

    fun getRetrofit(): Retrofit {
        // jika emulator, ganti IP dengan IP Default -> 10.0.2.2
        // jika device, pastikann IPnya sama dengan yg digunakan laptop
//        return Retrofit.Builder().build().baseUrl("http://localhost/mentoring-kotlin/")
        return Retrofit.Builder().baseUrl("http://192.168.100.226/mentoring-kotlin/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)
}