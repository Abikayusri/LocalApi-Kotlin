package abika.sinau.appanggota

import abika.sinau.appanggota.adapter.AnggotaAdapter
import abika.sinau.appanggota.config.NetworkModule
import abika.sinau.appanggota.model.actionData.ResponseAction
import abika.sinau.appanggota.model.getData.DataItem
import abika.sinau.appanggota.model.getData.ResponseGetData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, InputActivity::class.java))
        }

        showData()
    }

    private fun showData() {
        val listAnggota = NetworkModule.service().getData()
        listAnggota.enqueue(object : Callback<ResponseGetData> {
            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                if (response.isSuccessful) {

                    Log.d("Main Activity", "Data berhasil")
                    val item = response.body()?.data

                    val adapter = AnggotaAdapter(item, object : AnggotaAdapter.OnClickListener {
                        override fun detail(item: DataItem?) {
                            val intent = Intent(this@MainActivity, InputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun hapus(item: DataItem?) {
//                            hapusData(item?.id)

                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Yakin mau hapus data ?")
                                setPositiveButton("Hapus") { dialog, which ->
                                    hapusData(item?.id)
                                    dialog.dismiss()
                                }
                                setNegativeButton("Batal") { dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }

                    })

                    rvMain.adapter = adapter
                } else {
                    Log.e("Main Activity Error", "Harusnya berhasil")
                }
            }

            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Main Activity onFailure", t.message)
            }

        })
    }

    private fun hapusData(id: String?) {

        val hapus = NetworkModule.service().deleteData(id ?: "")
        hapus.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data Berhasil dihapus", Toast.LENGTH_SHORT)
                    .show()
                showData()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}