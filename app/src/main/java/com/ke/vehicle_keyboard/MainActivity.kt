package com.ke.vehicle_keyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.parkingwang.keyboard.KeyboardInputController
import com.parkingwang.keyboard.OnInputChangedListener
import com.parkingwang.keyboard.PopupKeyboard
import com.parkingwang.keyboard.view.InputView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        plate1()

    }

    private fun plate1() {
        val view = findViewById<InputView>(R.id.view)

        val popupKeyboard = PopupKeyboard(this)
        popupKeyboard.attach(view, this)

        popupKeyboard.controller.apply {
            setSwitchVerify(false)
            bindLockTypeProxy(object : KeyboardInputController.LockNewEnergyProxy {
                override fun setOnClickListener(listener: View.OnClickListener) {
                    listener.onClick(view)
                }

                override fun onNumberTypeChanged(isNewEnergyType: Boolean) {

                }

            })
            addOnInputChangedListener(object : OnInputChangedListener {
                override fun onChanged(number: String, isCompleted: Boolean) {
                    //                    if (isCompleted) {
                    //                        popupKeyboard.dismiss(this@MainActivity)
                    //                    }
                    Log.d("TAG", "onChanged $number $isCompleted")
                }

                override fun onCompleted(number: String, isAutoCompleted: Boolean) {
                    Log.d("TAG", "onCompleted $number $isAutoCompleted , number = ${view.number}")


                    popupKeyboard.dismiss(this@MainActivity)

                }

            })

        }
    }
}