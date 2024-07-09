package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityLoginScreenBinding
import com.example.quizapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class signUp_activity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            var bool:Boolean = checkForEmailPassword(binding)
            if(!bool){
                return@setOnClickListener
            }else{
                createUserWithEmailPassword(binding.emailViewsignin.text.toString(),binding.passwordViewsignin.text.toString())
            }
        }
        binding.loginin.setOnClickListener {
            goToLoginInActivity()
        }

    }

    private fun goToLoginInActivity() {
      startActivity(Intent(this,LoginScreen::class.java))
    }

    private fun checkForEmailPassword( binding: ActivitySignUpBinding):Boolean {
        var email = binding.emailViewsignin.text.toString()
        var password = binding.passwordViewsignin.text.toString()
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please enter email and password",Toast.LENGTH_SHORT).show()
            return false
        }else{

            return true
        }
    }
    private fun createUserWithEmailPassword(email:String,password:String){
        var auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->

                if(task.isSuccessful){
                    Toast.makeText(this,"signup successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,"signup failed ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                }

            }
    }
}