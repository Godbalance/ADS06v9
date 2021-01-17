package com.example.mdtest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.text.trimmedLength
import androidx.core.view.get
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        startButtonOptions()
        startUiOptions()

    }

    private fun startUiOptions() {

        val items = resources.getStringArray(R.array.array_arg_search)
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (actv_choose_search_value.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        actv_choose_search_value.editText?.doOnTextChanged { inputText, _, _, _ ->
            // Ответить на изменение вводимого текста

            //Эта строка обращается к выбранному значению в TextView MD
//            val argumentHintSearch = actv_choose_search_value.editText?.text.toString()

            val search_by = getString(R.string.search_by)
            val input_ = getString(R.string.input_)

            if ((inputText.toString() == items[2])||(inputText.toString() == items[3])){
                if (inputText.toString() == items[2]) {
                    otf_input_search_value.hint =
                        input_ + inputText.toString().substring(0, 5)
                    actv_choose_search_value.hint = search_by
                    otf_input_search_value.error = null
                }
                if (inputText.toString() == items[3]) {
                    otf_input_search_value.hint =
                        input_ + inputText.toString().replace("ю", "е")
                    otf_input_search_value.error = "Функция в разработке"
                }
            }else{
                actv_choose_search_value.hint = search_by
                otf_input_search_value.hint = input_ + inputText
                otf_input_search_value.error = null
            }
        }
    }

    private fun startButtonOptions() {

        obtn_add_activity.setOnClickListener{

            intent = Intent(this,AddActivity::class.java)
            startActivity(intent)

        }

        otf_input_search_value.setEndIconOnClickListener {

            val items = resources.getStringArray(R.array.array_arg_search)
            val choosedArgSearch = actv_choose_search_value.editText?.text.toString()

            if ((choosedArgSearch == items[0])||(choosedArgSearch == items[2])||(choosedArgSearch == items[3])){
                Toast.makeText(this,"Нельзя сканировать $choosedArgSearch", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this,"Начать сканирование!", Toast.LENGTH_SHORT).show()
            }



        }

    }

}