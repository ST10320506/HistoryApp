package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Review : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat

        //Declaring GUI variables
        val tvReview = findViewById<TextView>(R.id.tvReview)
        val btnExitApp = findViewById<Button>(R.id.btnExitApp)

        //Intent passes the data from the score screen to the review screen
        val intent: Intent = intent
        val historyQuestions = intent.getStringArrayExtra("historyQuestions")
        val historyAnswers = intent.getStringArrayExtra("historyAnswers")

        //AI used**
        if (historyQuestions != null && historyAnswers != null) {
            val combinedArray = buildString {
                for (i in historyQuestions.indices) {
                    append("${historyQuestions[i]} \nAnswer: ${historyAnswers[i]}\n\n")
                }
            }
            //displays array of history questions and answers on the review screen
            tvReview.text = combinedArray
        } else {
            //displays 'no data given' if the array is empty
            tvReview.text = "No data given"
        }
        //Declaring the exit button to terminate the app when clicked
        btnExitApp.setOnClickListener {
            finish()
        }
    }//end of onCreate
}//end of Review Activity