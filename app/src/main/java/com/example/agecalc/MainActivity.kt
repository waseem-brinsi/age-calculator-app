package com.example.agecalc


import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat

import java.util.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnselectdate = findViewById<Button>(R.id.btn_selectdate)

        btnselectdate.setOnClickListener {View ->
            datePicker(View)


        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun datePicker(view: View){
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val dayofmonth = mycalendar.get(Calendar.DAY_OF_MONTH)

        val listnner = DatePickerDialog.OnDateSetListener {
                view, SelectedYear, SelectedMonth, SelectedDay ->
            Toast.makeText(this,"$SelectedDay/$SelectedMonth/$SelectedYear",Toast.LENGTH_LONG).show()
            //selected date
            val selectedDate = "$SelectedDay/$SelectedMonth/$SelectedYear"
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val selectedDateParses = sdf.parse(selectedDate)
            val selectedDateInMunite = selectedDateParses!!.time / 60000


            //the current date
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentInMunite = currentDate!!.time / 60000


            //operation
            val ageinMunite= currentInMunite - selectedDateInMunite


            //tvselectdateDisplay
            val tvselectdateDisplay = findViewById<TextView>(R.id.tvselectdateDisplay)
            tvselectdateDisplay.text = selectedDate
            val tvAgeInMuniteDisplay = findViewById<TextView>(R.id.TvAgeInMuniteDisplay)
            tvAgeInMuniteDisplay.text = "$ageinMunite"

        }
        DatePickerDialog(this,listnner,year,month,dayofmonth).show()
    }

}