package com.example.mdtest1

import android.content.Intent
import android.icu.lang.UCharacter.LineBreak.SPACE
import android.icu.text.Collator.ReorderCodes.SPACE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.actv_choose_search_value

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        startUiOptions()
        startButtonOptions()
    }

    private fun startUiOptions() {

        val items = resources.getStringArray(R.array.array_pk)
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (actv_choose_pk.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        actv_choose_pk.editText?.doOnTextChanged { inputText, _, _, _ ->

            // Ответить на изменение вводимого текста
            actv_choose_pk.hint = "Войти как"
            otf_input_pass.hint = getString(R.string.input_pass) + inputText
            actv_choose_pk.error = null
        }
    }

    private fun startButtonOptions() {

        obtn_login.setOnClickListener {
            doLoginCheckField()
        }
    }

    private fun doLoginCheckField() {

        val choosePK = actv_choose_pk.editText?.text.toString()
        val inputPass = otf_input_pass.editText?.text.toString()

        otf_input_pass.editText?.doOnTextChanged { inputText, _, _, _ ->
            otf_input_pass.error = null

        }

        if ((choosePK.isEmpty())||(inputPass.isEmpty())){

            if (choosePK.isEmpty()){
                actv_choose_pk.error = "Не выбран сотрудник!"

            }

            if (inputPass.isEmpty()){
                otf_input_pass.error = "Не введен пароль!"

            }
        }
        else{
            doLoginCheckUser(choosePK,inputPass)

        }
    }

    private fun doLoginCheckUser(choosePK:String,inputPass:String) {
        val getPass = getString(resources.getIdentifier(choosePK,"string", packageName)).trim()

        if (inputPass == getPass){

            Toast.makeText(this, "Здравствуйте $choosePK", Toast.LENGTH_SHORT).show()
            intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
        else{

            otf_input_pass.error = "Неверно указан пароль!"
        }
    }
}