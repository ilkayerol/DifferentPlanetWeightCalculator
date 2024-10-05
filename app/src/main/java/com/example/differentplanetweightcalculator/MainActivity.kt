package com.example.differentplanetweightcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextClock
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(),OnClickListener {

    val KG_TO_POUND = 2.2045
    val POUND_TO_KG = 0.45359237
    val MARS = 0.38
    val JUPITER = 2.34
    val VENUS = 0.91

    var etWeight : EditText? = null
    var tvResult : TextView? = null
    var cbMars : CheckBox? = null
    var cbVenus : CheckBox? = null
    var cbJupiter : CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(R.drawable.plats).into(findViewById(R.id.imageView))

        cbVenus = findViewById(R.id.cbVenus)
        cbMars = findViewById(R.id.cbMars)
        cbJupiter = findViewById(R.id.cbJupiter)
        cbVenus!!.setOnClickListener(this)
        cbMars!!.setOnClickListener(this)
        cbJupiter!!.setOnClickListener(this)

        etWeight = findViewById(R.id.etWeight)
        tvResult = findViewById(R.id.tvResult)
        // var btnCalculate : Button = findViewById(R.id.btnCalculate)

    }


    fun kgToPound (weight : Double) : Double {
        return weight * KG_TO_POUND
    }

    fun poundToKg ( weight: Double) : Double {
        return weight * POUND_TO_KG
    }

    // Formats double value to desired decimal point
    fun Double.Format (decimal : Int) = java.lang.String.format("%.${decimal}f",this)

    fun calculateWeight (pound: Double, checkbox: CheckBox){
        var resultKG : Double = 0.0
        when(checkbox.id){
            R.id.cbMars -> resultKG = pound * MARS
            R.id.cbJupiter -> resultKG = pound * JUPITER
            R.id.cbVenus -> resultKG = pound * VENUS
            else -> resultKG = 0.0
        }
        var resultToKg = poundToKg(resultKG)
        tvResult!!.text=resultKG.Format(2).toString()

    }

    override fun onClick(p0: View?) {
        // p0 as CheckBox -> if we use like that all onClicks will be listen on checkboxes so we can write var isChecked : Boolean = p0.ischecked
        var isChecked : Boolean = (p0 as CheckBox).isChecked
        if(etWeight!!.text.isNotEmpty()){
            var userWeightKG = etWeight!!.text.toString().toDouble()
            var userWeightPound = kgToPound(userWeightKG)

            when (p0.id){
                R.id.cbMars -> if(isChecked){ //if etWeight is not empty and cbMars is checked
                    cbVenus!!.isChecked = false
                    cbJupiter!!.isChecked = false
                    calculateWeight(userWeightPound,p0)
                }
                R.id.cbVenus -> if(isChecked){ //if etWeight is not empty and cbMars is checked
                    cbMars!!.isChecked = false
                    cbJupiter!!.isChecked = false
                    calculateWeight(userWeightPound,p0)
                }
                R.id.cbJupiter -> if(isChecked){ //if etWeight is not empty and cbMars is checked
                    cbMars!!.isChecked = false
                    cbVenus!!.isChecked = false
                    calculateWeight(userWeightPound,p0)
                }
            }
        }


    }


}