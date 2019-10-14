package com.example.helloapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_data.*


class UserDataActivity : AppCompatActivity() {

  val USER_NAME_KEY = "username"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_data)
  }

  fun onSignUp(view: View) {
      if (isDataValid()) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(USER_NAME_KEY, userNameEditText.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
      }
  }

  private fun isDataValid(): Boolean {
    // get data
    val username = userNameEditText.text.toString()
    val userSurname = userSurnameEditText.text.toString()
    val birthDate = birthDateEditText.text.toString()
    val password1 = passwordEditText1.text.toString()
    val password2 = passwordEditText2.text.toString()


    if (username.length < 3)
      Toast.makeText(this, "Name is too short", Toast.LENGTH_SHORT).show()
    else if (userSurname.length < 3)
      Toast.makeText(this, "Surname is too short", Toast.LENGTH_SHORT).show()
    else if (birthDate.isEmpty())
      Toast.makeText(this, "Birth date is invalid", Toast.LENGTH_SHORT).show()
    else if (!password1.equals(password2))
      Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
    else if (password1.length < 8)
      Toast.makeText(this, "Use at least 8 characters for password", Toast.LENGTH_SHORT).show()
    else
      return true

    return false
  }

  override fun onBackPressed() {
    Toast.makeText(this, "Fill the fields, please", Toast.LENGTH_SHORT).show()
  }
}
