package com.example.mdtest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_login.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        imageButton2.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        startButtonOptions()
        startUiOptions()

    }

    private fun startUiOptions() {

        val items = resources.getStringArray(R.array.array_adres_value)
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (actv_shoose_adres.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        actv_shoose_adres.editText?.doOnTextChanged { inputText, _, _, _ ->

            // Ответить на изменение вводимого текста
            actv_shoose_adres.error = null
        }

    }

    private fun startButtonOptions() {

        obtn_add_fdb.setOnClickListener {
            doAddCheckFields()
        }

        obtn_search_activity.setOnClickListener{

            intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)

        }

        otf_input_add_tovar.setEndIconOnClickListener {

            Toast.makeText(this,"Нажата иконка сканера при размещении!", Toast.LENGTH_SHORT).show()

        }

    }

    private fun doAddCheckFields() {
        val chooseAdres = actv_shoose_adres.editText?.text.toString()
        val inputBarcode = otf_input_add_tovar.editText?.text.toString()
        val inputStock = otf_input_add_stock.editText?.text.toString()

        otf_input_add_tovar.editText?.doOnTextChanged { inputText, _, _, _ ->
            otf_input_add_tovar.error = null

        }
        otf_input_add_stock.editText?.doOnTextChanged { inputText, _, _, _ ->
            otf_input_add_stock.error = null

        }

        if ((chooseAdres.isEmpty())||(inputBarcode.isEmpty())||(inputStock.isEmpty())){
            if (chooseAdres.isEmpty()){
                actv_shoose_adres.error = "Не выбран адрес!"

            }

            if (inputBarcode.isEmpty()){
                otf_input_add_tovar.error = "Не указан ШК"

            }

            if (inputStock.isEmpty()){
                otf_input_add_stock.error = "Не указано количество!"

            }

        }
        else{
            doAddFDB(chooseAdres,inputBarcode,inputStock)

        }

    }

    private fun  doAddFDB(chooseAdres:String,inputBarcode:String,inputStock:String){
        Toast.makeText(this,chooseAdres + inputBarcode + inputStock, Toast.LENGTH_SHORT).show()

        AddFDB(chooseAdres,inputBarcode,inputStock)
    }

}