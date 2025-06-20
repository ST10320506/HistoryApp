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

        //Declaring name variable passed from Main activity
        //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
        val intent: Intent = intent
        val name = intent.getStringExtra("name")

        //Declaring GUI variables
        val tvWelcomeName = findViewById<TextView>(R.id.tvWelcomeName)
        val btnStartQuiz = findViewById<Button>(R.id.btnStartQuiz)

        //Displays personalized welcome message to the user
        tvWelcomeName.text = "Welcome ${name ?: "User"}!"

        //Navigate to the Flashcards page
        //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
        btnStartQuiz.setOnClickListener {
            val intent = Intent(this, Flashcards::class.java)
            //Carries the name variable to the Flashcards screen when navigating to the Flashcards screen
            intent.putExtra("name", name)
            startActivity(intent)
            finish()
        }
    }//end of onCreate
}//end of WelcomeScreen Activity