package com.roman.testmvp.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roman.testmvp.R
import com.roman.testmvp.model.Employee

class EmployeesListAdapter (private var employees:List<Employee>): RecyclerView.Adapter<EmployeesListAdapter.MViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_employee, parent, false)
        return MViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        val employee= employees[position]

        //render
        vh.textViewName.text = """${employee.first_name} ${employee.last_name}"""
        vh.textViewEmail.text = employee.email
        vh.textViewId.text = "(" + employee.id.toString() + ")"
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
        val textViewEmail: TextView = view.findViewById(R.id.tvMail)
        val textViewId: TextView = view.findViewById(R.id.tvId)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }
}