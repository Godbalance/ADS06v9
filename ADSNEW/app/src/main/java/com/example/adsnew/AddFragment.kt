package com.example.adsnew

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import kotlinx.android.synthetic.main.fragment_add.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//@Suppress("DEPRECATION")
class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onStart() {
        super.onStart()

        startUiOptions()
        obtn_add_fdb.setOnClickListener(this::doAddCheckFields)
        otf_input_add_barcode.setEndIconOnClickListener(this::doScanner)
    }

    private fun doScanner(view: View) {
//        otf_input_add_barcode.editText?.setText("adding")
        val scanner = IntentIntegrator(activity)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        scanner.setBeepEnabled(false)
        scanner.initiateScan()
    }

    private fun startUiOptions() {

        val items = resources.getStringArray(R.array.array_adres_value)
        val adapter = ArrayAdapter(layoutInflater.context, R.layout.list_item, items)
        (actv_shoose_adres.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        actv_shoose_adres.editText?.doOnTextChanged { inputText, _, _, _ ->
            // Ответить на изменение вводимого текста
            actv_shoose_adres.error = null
        }

    }

    @SuppressLint("RestrictedApi")
    private fun doAddCheckFields(view: View) {
        val chooseAdres = actv_shoose_adres.editText?.text.toString()
        val choosePK = arguments?.getString("choosePK").toString()
        val inputBarcode = otf_input_add_barcode.editText?.text.toString()
        val inputStock = otf_input_add_stock.editText?.text.toString()
        val dateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE).toString().trim()
        val hoursTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).toString().trim()

        otf_input_add_barcode.editText?.doOnTextChanged { inputText, _, _, _ ->
            otf_input_add_barcode.error = null

        }
        otf_input_add_stock.editText?.doOnTextChanged { inputText, _, _, _ ->
            otf_input_add_stock.error = null

        }
        if ((chooseAdres.isEmpty())||(inputBarcode.isEmpty())||(inputStock.isEmpty())){

            if (chooseAdres.isEmpty()){

                actv_shoose_adres.error = "Не выбран адрес!"
            }

            if (inputBarcode.isEmpty()){

                otf_input_add_barcode.error = "Не указан ШК"
            }

            if (inputStock.isEmpty()){

                otf_input_add_stock.error = "Не указано количество!"
            }
        }
        else{

            try {

                val nameProduct = getString(
                    resources.getIdentifier(
                        "NAME$inputBarcode",
                        "string",
                        "com.example.adsnew"
                    )
                ).trim()
                val lmProduct = getString(
                    resources.getIdentifier(
                        "LM$inputBarcode",
                        "string",
                        "com.example.adsnew"
                    )
                ).trim()

                MaterialAlertDialogBuilder(layoutInflater.context)
                        .setTitle("Внимание!")
                        .setMessage(
                            "Разместить:" +
                                    "\n$nameProduct" +
                                    "\nна адресе: $chooseAdres" +
                                    "\nв количестве: $inputStock шт"
                        )
                        .setNegativeButton("Отменить") { dialog, which ->
                            // Respond to negative button press
                        }
                        .setPositiveButton("Подтвердить") { dialog, which ->
                            // Respond to positive button press
                            doAddFDB(
                                chooseAdres,
                                inputBarcode,
                                inputStock,
                                choosePK,
                                nameProduct,
                                lmProduct,
                                dateTime,
                                hoursTime
                            )
                        }
                        .show()
            }catch (e: Resources.NotFoundException){
                val inputErrorLM = EditText(layoutInflater.context)
                inputErrorLM.hint = "Введите ЛМ"
                MaterialAlertDialogBuilder(layoutInflater.context)
                        .setTitle("Товара нет в базе!")
                        .setMessage(
                            "Отправить информацию Денису?" +
                                    "\nШК: $inputBarcode"
                        )
                        .setView(
                            inputErrorLM,
                            60,
                            32,
                            60,
                            0
                        )
                        .setPositiveButton("Отправить") { dialog, which ->

                            val sendIntent: Intent = Intent().apply {

                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Денис привет, у нас ошибка при размещении товара! " +
                                            "\nШК: $inputBarcode" +
                                            "\nЛМ: ${inputErrorLM.text.toString().trim()}"
                                )
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            startActivity(shareIntent)
                        }
                        .show()
            }
        }
    }
    private fun  doAddFDB(
        chooseAdres: String,
        inputBarcode: String,
        inputStock: String,
        choosePK: String,
        nameProduct: String,
        lmProduct: String,
        dateTime: String,
        hoursTime: String
    ){
        val reff = FirebaseDatabase.getInstance().getReference("FDB06")
        val result = FDB(
            chooseAdres,
            inputBarcode,
            inputStock,
            choosePK,
            nameProduct,
            lmProduct,
            hoursTime,
            dateTime
        )
        reff.push().setValue(result).addOnCompleteListener{

            Toast.makeText(activity, "Размещено", Toast.LENGTH_SHORT).show()
            actv_shoose_adres.editText?.text = null
            otf_input_add_barcode.editText?.text = null
            otf_input_add_stock.editText?.text = null
        }
    }

}
























