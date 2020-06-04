package abika.sinau.appanggota

import abika.sinau.appanggota.R
import abika.sinau.appanggota.config.NetworkModule
import abika.sinau.appanggota.model.insertData.ResponseInsert
import abika.sinau.appanggota.model.updateData.ResponseUpdate
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val nama = inputNama.text.toString()
        val nohp = inputNoHp.text.toString()
        val alamat = inputAlamat.text.toString()

        btnSave.setOnClickListener {
//            inputData(nama, nohp, alamat)
            inputData(inputNama.text.toString(), inputNoHp.text.toString(), inputAlamat.text.toString())
        }

        btnBatal.setOnClickListener {
            finish()
        }

    }

    private fun inputData(nama: String?, nohp: String?, alamat: String?){

        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseInsert> {

            override fun onResponse(
                call: Call<ResponseInsert>,
                response: Response<ResponseInsert>
            ) {

                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()

            }

            override fun onFailure(call: Call<ResponseInsert>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }

//    private fun inputData(nama: String?, nohp: String?, alamat: String?){
//        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
//        input.enqueue(object : Callback<ResponseInsert>{
//            override fun onResponse(
//                call: Call<ResponseInsert>,
//                response: Response<ResponseInsert>
//            ) {
//                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
//                finish()
//            }
//
//            override fun onFailure(call: Call<ResponseInsert>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

    private fun updateData(id: String?, nama: String?, nohp: String?, alamat: String?){

        val input = NetworkModule.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseUpdate> {

            override fun onResponse(
                call: Call<ResponseUpdate>,
                response: Response<ResponseUpdate>
            ) {

                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                finish()

            }

            override fun onFailure(call: Call<ResponseUpdate>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }
}
