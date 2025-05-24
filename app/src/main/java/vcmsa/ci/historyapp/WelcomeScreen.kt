package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat

        //declaring variable used from main activity
        val intent: Intent = intent
        val name = intent.getStringExtra("name")

        //Declaring GUI variables
        val tvWelcomeName = findViewById<TextView>(R.id.tvWelcomeName)
        val btnStartQuiz = findViewById<Button>(R.id.btnStartQuiz)

        //displays personalized welcome message to the user
        tvWelcomeName.text = "Welcome $name !"

        //navigate to the next page
        btnStartQuiz.setOnClickListener {
            val intent = Intent(this, Flashcards::class.java)
            startActivity(intent)
            finish()
        }
    }//end of onCreate
}//end of WelcomeScreen