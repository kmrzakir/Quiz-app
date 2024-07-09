package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {

    private lateinit var binding:ActivityLoginScreenBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)



        binding.loginBtn.setOnClickListener {
           var bool:Boolean = checkForEmailPassword(binding)
            if(!bool){
                return@setOnClickListener
            }else{
                loginUserWithEmailAndPassword(binding.emailView.text.toString(),binding.passwordView.text.toString())
            }
        }
        binding.signup.setOnClickListener {
            goToSignInActivity()

        }

    }

    private fun goToSignInActivity() {
        startActivity(Intent(this,signUp_activity::class.java))
    }

    private fun checkForEmailPassword( binding: ActivityLoginScreenBinding):Boolean {
       var email = binding.emailView.text.toString()
       var password = binding.passwordView.text.toString()
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please enter email and password",Toast.LENGTH_SHORT).show()
            return false
        }else{

            return true
        }
    }
    private fun loginUserWithEmailAndPassword(email:String,password:String){
        var auth = FirebaseAuth.getInstance()
       auth.signInWithEmailAndPassword(email,password)
           .addOnCompleteListener(this){task->
               if(task.isSuccessful){
                   Toast.makeText(this,"Login successfully",Toast.LENGTH_SHORT).show()
                   startActivity(Intent(this,MainActivity::class.java))
                   finish()
               }else{
                   Toast.makeText(this,"Login failed this user does not exist ${task.exception?.message}",Toast.LENGTH_SHORT).show()
               }

           }
    }


}