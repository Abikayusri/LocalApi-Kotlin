package abika.sinau.appanggota

import abika.sinau.appanggota.adapter.AnggotaAdapter
import abika.sinau.appanggota.config.NetworkModule
import abika.sinau.appanggota.model.getData.DataItem
import abika.sinau.appanggota.model.getData.ResponseGetData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

                    val adapter = AnggotaAdapter(item, object : AnggotaAdapter.OnClickListener{
                        override fun detail(item: DataItem?) {

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

    override fun onResume() {
        super.onResume()
        showData()
    }
}