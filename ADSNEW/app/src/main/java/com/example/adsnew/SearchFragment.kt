package com.example.adsnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onStart() {
        super.onStart()

        startUiOptions()
        otf_input_search_value.setEndIconOnClickListener(this::onIconScanClick)
    }

    private fun startUiOptions() {

        val items = resources.getStringArray(R.array.array_arg_search)
        val adapter = ArrayAdapter(layoutInflater.context, R.layout.list_item, items)
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

    private fun onIconScanClick(view: View) {
        val items = resources.getStringArray(R.array.array_arg_search)
        val choosedArgSearch = actv_choose_search_value.editText?.text.toString()
        when(items.indexOf(choosedArgSearch.trim())){

            0->{
                Toast.makeText(
                    activity,
                    "Нельзя сканировать $choosedArgSearch!",
                    Toast.LENGTH_SHORT
                ).show()}

            1->{
//                doScann()
                Toast.makeText(activity, "Начать сканирование!", Toast.LENGTH_SHORT).show()
            }

            2->{
                Toast.makeText(
                    activity,
                    "Нельзя сканировать ${choosedArgSearch.substring(0, 5)}!",
                    Toast.LENGTH_SHORT
                ).show()}

            3->{
                Toast.makeText(
                    activity,
                    "Нельзя сканировать ${choosedArgSearch.replace("ю", "е")}!",
                    Toast.LENGTH_SHORT
                ).show()}

            else-> {
                Toast.makeText(activity, "Нельзя сканировать пустоту!", Toast.LENGTH_SHORT).show()}
        }
    }

//    private fun doScann() {
//        otf_input_search_value.editText?.setText("search")
//        val scanner = IntentIntegrator(activity)
//        scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
//        scanner.setBeepEnabled(false)
//        scanner.initiateScan()
//    }


}
