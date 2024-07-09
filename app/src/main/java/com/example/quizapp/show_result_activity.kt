package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar

@Suppress("DEPRECATION")
class show_result_activity : AppCompatActivity() {

    private lateinit var progressbar:ProgressBar
    private lateinit var textviewCorrect:TextView
    private lateinit var textviewIncorrect:TextView
    private lateinit var play_again_btn: AppCompatButton
    private lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show_result)

        play_again_btn = findViewById(R.id.play_gain_btn)
        progressbar = findViewById(R.id.progress_bar)
        textviewCorrect = findViewById(R.id.textviewCorrect)
        textviewIncorrect = findViewById(R.id.textviewIncorrect)
        toolbar = findViewById(R.id.toolbar)

        //SETTING UP STATUS BAR COLOR TO Periwinkle_Blue HERE
        var windows = window
        windows.statusBarColor = resources.getColor(R.color.Periwinkle_Blue)

        //SETTING UP TOOLBAR HERE
        setSupportActionBar(toolbar)
        toolbar.setTitle("title text")
        toolbar.setSubtitle("subtitle text")





        //GETTING SCORE RESULT
        var intent = intent
        var numberOfCorrectOptions = intent.getIntExtra("numberOfCorrectOptions",0)
        var numberOfInCorrectOptions = intent.getIntExtra("numberOfInCorrectOptions",0)
        progressbar.progress = numberOfCorrectOptions
        textviewCorrect.text = "correct questions = $numberOfCorrectOptions"
        textviewIncorrect.text = "incorrect questions = $numberOfInCorrectOptions"

        //PLAY AGAIN GAME BY CLICK ON THE PLAY AGAIN BUTTON
        play_again_btn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
    //SETTING UP MENU ITEMS
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_for_toolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }
   //HANDLING MENU ITEM CLICK LISTENERS
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemId = item.itemId
        if(itemId == R.id.about_us){
            Toast.makeText(this,"Clicked on about us",Toast.LENGTH_SHORT).show()
        }else if(itemId == R.id.share){
            Toast.makeText(this,"Clicked on share",Toast.LENGTH_SHORT).show()
        }else if(itemId == android.R.id.home){
            Toast.makeText(this,"Clicked on back icon",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Clicked on exit",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}