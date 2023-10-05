package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText=findViewById<EditText>(R.id.etWeight)
        val heightText=findViewById<EditText>(R.id.etHeight)
        val calButton=findViewById<Button>(R.id.btnCalculate)
        calButton.setOnClickListener {
            //input value into string
            val weight=weightText.text.toString()
            val height=heightText.text.toString()



            //use this function to validate data b4 calculate bmi and display result
            if(validateInput(weight,height)) {

                //calculate body mass
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                //get results with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }


    private  fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"height is empty",Toast.LENGTH_LONG).show()
                return false
        }
            else->{
                return true

            }            }
    }
    private fun displayResult(bmi:Float){
        val resultIndex=findViewById<TextView>(R.id.tvIndex)
        val resultDescription=findViewById<TextView>(R.id.tvResult)
        val info=findViewById<TextView>(R.id.tvInfo)


        resultIndex.text=bmi.toString()
        info.text="(normal range is 18.5-24.8)"

        var resultText=""
        var color=0

        when {
            bmi<18.50->{
                resultText="under weight"
                color=R.color.under_weight
            }

            bmi in 18.50..24.99->{
                resultText="healthly"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText="over weight"
                color=R.color.over_weight
            }
            bmi> 29.99->{
                resultText="obese"
                color=R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text=resultText
       /* calButton.setOnClickListener{
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }*/
    }
}