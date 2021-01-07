package com.anto.tokobangunan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.anto.tokobangunan.databinding.ActivityEditTransaksiBinding
import com.anto.tokobangunan.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        BTlogin.setOnClickListener{

            with(binding){
                if (!isCredentialValid(etPassword.text!!, etUsername.text!!)) {
                    password.error = "Password Must Contain At Least 8 Character"
                }
                else {
                    password.error = null
                    //cara memindah antar layout
                    val intent= Intent(this@MainActivity, Home::class.java)
                    startActivityForResult(intent, 1)
                    finish()
                }
            }
        }
    }

    private fun isCredentialValid(password: Editable?, username: Editable?) : Boolean{
        return password != null && password.length >= 8 && username != null && username.toString().equals("12345678")
    }
}