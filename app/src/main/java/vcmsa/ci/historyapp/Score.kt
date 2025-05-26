package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat

        //Declaring the GUI variables
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnReviewAnswers = findViewById<Button>(R.id.btnReviewAnswers)

        //AI was used
        //Prompt used: "Please fix the error in this code to pass the history questions and answers array from the flashcards screen to the review screen and display it on the review screen.
        //80% of my code was used.
        val historyQuestions = intent.getStringArrayExtra("historyQuestions")
        val historyAnswers = intent.getStringArrayExtra("historyAnswers")

        //Intent is created to pass and display the users score and name from the flashcard screen on to the score page
        //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
        val score = intent.getIntExtra("score", 0)
        tvScore.text = "Your score is $score out of 5"
        val intent: Intent = intent
        val name = intent.getStringExtra("name")

        //AI was used
        //Prompt used: help to change this code in the score screen to display 'great job name!' on score ranging between 3-5 and to display 'keep practising 'name'' on scores lower than 3.
        //70% of my code was used.
        if(score in 3..5) {
            tvMessage.text = "Great Job ${name ?: "User"}!"
        }
        else{
            tvMessage.text = "Keep practising ${name ?: "User"}."
        }

        //Declaring the exit button to terminate the app once clicked
        btnExit.setOnClickListener {
            finish()
        }

        //Declaring the review answers button to navigate to the review screen once clicked
        btnReviewAnswers.setOnClickListener {
            //Intent code is used to navigate to the Review screen if the review button is clicked
            val intent = Intent(this, Review::class.java)
                //Carries the data declared to the review screen
                .putExtra("historyQuestions", historyQuestions)
                .putExtra("historyAnswers", historyAnswers)
            startActivity(intent)
            finish()
        }
    }//end of onCreate
}//end of Score Activity