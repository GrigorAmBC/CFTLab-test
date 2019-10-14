package com.example.helloapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    val USER_NAME_KEY = "username"
    val DEFAULT_USER_NAME_VALUE = "0"
    val REQUEST_CODE = 1
    private var userName: String? = "george"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check if data was entered
        val prefUserName = getPreferences(Context.MODE_PRIVATE).getString(USER_NAME_KEY, DEFAULT_USER_NAME_VALUE)
        if (prefUserName.equals(DEFAULT_USER_NAME_VALUE))
            requestData()
        else
            userName = prefUserName
    }

    private fun requestData() {
        val intent = Intent(this, UserDataActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
            if (requestCode == REQUEST_CODE) {
                // get data
                val resultUserName: String? = data?.getStringExtra(USER_NAME_KEY)

                // save data
                val sharedPref = getPreferences(Context.MODE_PRIVATE)
                sharedPref?: return
                with (sharedPref.edit()) {
                    putString(USER_NAME_KEY, resultUserName)
                    commit()
                }

                userName = resultUserName
            }
    }



    fun onHelloClicked(view: View) {
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle("Greetings")
        alertDialog.setMessage("Hello, " + userName + "!")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "Hey, honey"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }
}
