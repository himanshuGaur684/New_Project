package com.example.newproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.databinding.MainViewHolderBinding
import com.example.newproject.room.model.Area
import com.example.newproject.room.model.Country
import com.example.newproject.room.model.Region
import com.example.newproject.room.model.Zone

class MainAdapter(private var onClick: (Zone) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    private var list: MutableList<Zone> = mutableListOf()

    fun setList(list: MutableList<Zone>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: MainViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainViewHolderBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textView.text = item.zone
        holder.binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }

}

class MainRegionAdapter(private var onClick: (Region) -> Unit) :
    RecyclerView.Adapter<MainRegionAdapter.MainViewHolder>() {


    private var list: MutableList<Region> = mutableListOf()

    fun setList(list: MutableList<Region>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: MainViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainViewHolderBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textView.text = item.region
        holder.binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }

}

class MainAreaAdapter(private var onClick: (Area) -> Unit) :
    RecyclerView.Adapter<MainAreaAdapter.MainViewHolder>() {


    private var list: MutableList<Area> = mutableListOf()

    fun setList(list: MutableList<Area>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: MainViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainViewHolderBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textView.text = item.area
        holder.binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }

}

class MainCountryAdapter(private var onClick: (Country) -> Unit) :
    RecyclerView.Adapter<MainCountryAdapter.MainViewHolder>() {


    private var list: MutableList<Country> = mutableListOf()

    fun setList(list: MutableList<Country>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: MainViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainViewHolderBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textView.text = item.country
        holder.binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }

}
