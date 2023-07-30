package com.aj.hideandshowpassword

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.SpannableStringBuilder
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.View
import com.aj.hideandshowpassword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var isEyeClosed = false // Variable to keep track of the eye state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lavEye.progress = 0f
        binding.lavEye.playAnimation()
        main()
        binding.lavEye.setOnClickListener {
            togglePasswordVisibility()
        }

    }


    private fun togglePasswordVisibility() {
        if (binding.edtPassword.transformationMethod is PasswordTransformationMethod) {
            binding.edtPassword.transformationMethod = null
            isEyeClosed = true

        } else {
            isEyeClosed = false
            binding.lavEye.pauseAnimation()
            binding.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        if (isEyeClosed){
            binding.lavEye.progress = 0f
            binding.lavEye.playAnimation()
            main()
        }

        binding.edtPassword.setSelection(binding.edtPassword.text.length)
    }


    fun main() {
        val handler = Handler()
        handler.postDelayed({
            binding.lavEye.pauseAnimation()
        }, 500) // 1000 milliseconds = 1 second
    }

}