package com.roman.testmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.roman.testmvvm.R
import com.roman.testmvvm.model.Employee

class EmployeesListAdapter (private var employees:List<Employee>): RecyclerView.Adapter<EmployeesListAdapter.MViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_employee, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        val employee= employees[position]

        //render
        vh.textViewName.text= employee.full_name
        vh.
        Glide.with(vh.imageView.context).load(employee.avatar).into(vh.imageView)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun update(data:List<Employee>){
        employees= data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewName: TextView = view.findViewById(R.id.tvName)
        val imageView: ImageView = view.findViewById(R.id.imageView)

    }
}