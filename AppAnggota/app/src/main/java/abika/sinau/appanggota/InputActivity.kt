package abika.sinau.appanggota

import abika.sinau.appanggota.config.NetworkModule
import abika.sinau.appanggota.model.actionData.ResponseAction
import abika.sinau.appanggota.model.getData.DataItem
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

//        aneh
        val nama = inputNama.text.toString()
        val nohp = inputNoHp.text.toString()
        val alamat = inputAlamat.text.toString()

        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null) {
            inputNama.setText(getData.nama)
            inputNoHp.setText(getData.nohp)
            inputAlamat.setText(getData.alamat)

            btnSave.text = "Update"
        }

        when (btnSave.text) {
            "Update" -> {
                btnSave.setOnClickListener {
                    updateData(
                        getData?.id,
                        inputNama.text.toString(),
                        inputNoHp.text.toString(),
                        inputAlamat.text.toString()
                    )
                }
            }
            else -> {
                btnSave.setOnClickListener {
//                    inputData(nama, nohp, alamat)
                    inputData(
                        inputNama.text.toString(),
                        inputNoHp.text.toString(),
                        inputAlamat.text.toString()
                    )
                }
            }
        }

        btnBatal.setOnClickListener {
            finish()
        }

    }

    private fun inputData(nama: String?, nohp: String?, alamat: String?) {

        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                    .show()
                finish()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }

    private fun updateData(id: String?, nama: String?, nohp: String?, alamat: String?) {

        val input =
            NetworkModule.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT)
                    .show()
                finish()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }
}
