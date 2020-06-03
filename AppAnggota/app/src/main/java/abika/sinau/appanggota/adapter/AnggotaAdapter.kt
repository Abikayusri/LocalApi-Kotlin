package abika.sinau.appanggota.adapter

import abika.sinau.appanggota.model.getData.DataItem
import abika.sinau.appanggota.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Abika Chairul Yusri
 * on Wednesday, 03 June 2020
 * Bismillahirrahmanirrahim
 */
class AnggotaAdapter (val data: List<DataItem>?, val itemClick : OnClickListener): RecyclerView.Adapter<AnggotaAdapter.AnggotaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnggotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return AnggotaViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: AnggotaViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.nama
        holder.nohp.text = item?.nohp
        holder.alamat.text = item?.alamat

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }
    }

    class AnggotaViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

        val nama = view.itemNama
        val nohp = view.itemNoHp
        val alamat = view.itemAlamat
    }

    interface OnClickListener {
        fun detail(item: DataItem?)
    }
}