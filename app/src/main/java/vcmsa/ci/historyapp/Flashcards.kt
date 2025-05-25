package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Flashcards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcards)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat

        //Declaring GUI variables
        val tvHistoryQuestions = findViewById<TextView>(R.id.tvHistoryQuestions)
        val radioGroupAnswers = findViewById<RadioGroup>(R.id.rdgrpAnswers)
        val btnNextQuestion = findViewById<Button>(R.id.btnNextQuestion)
        val intent: Intent = intent
        val name = intent.getStringExtra("name")

        //Array created for the history questions and answers
        //Reference: https://github.com/liehanels/QuizzApp - Liehan Els, 2025. Liehan had written code to show how to create an array. [online] Available at: <https://github.com/liehanels/QuizzApp> [Accessed 21 May 2025].
        //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].

        //Array of history questions based on the South African Apartheid
        val historyQuestions = arrayOf(
            "1. Apartheid was a policy or system of segregation or discrimination on the grounds of race.",
            "2. Apartheid ended in 1974.",
            "3. Pass laws required black South Africans to carry passbooks to enter white areas.",
            "4. Nelson Mandela was South Africa's first black president.",
            "5. Nelson Mandela was imprisoned for 30 years before becoming the president of South Africa."
        )

        //Array of True and False answers user can choose from using radio buttons
        val historyAnswerChoices = arrayOf(
            arrayOf("True", "False"),
            arrayOf("True", "False"),
            arrayOf("True", "False"),
            arrayOf("True", "False"),
            arrayOf("True", "False")
        )

        //Array to store the users answers
        var userAnswers = arrayOfNulls<String>(5)

        //Array of the correct answers to the history questions
        val historyAnswers = arrayOf(
            "True",
            "False",
            "True",
            "True",
            "False"
        )

        //Counter variable to track the questions the user answered correctly
        var counter = 0
        //Score variable to track the users score as they answer the questions
        var score = 0
        //Check variable to display 'correct/incorrect' to the user when selecting true/false
        var check = 0

        //For loop to display the history questions and True/False choices to the user
        //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
        tvHistoryQuestions.text = historyQuestions[counter]
        for (i in 0 until radioGroupAnswers.childCount) {
            val radioButton = radioGroupAnswers.getChildAt(i) as RadioButton
            radioButton.text = historyAnswerChoices[counter][i]
        }

        //Declaring the next question button function so when it's clicked it will go through and display each question
        btnNextQuestion.setOnClickListener {
            if (counter < 5) {

                var selectedAnswer = radioGroupAnswers.checkedRadioButtonId
                //If...else statement to check if the user has selected an answer
                //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
                if (selectedAnswer != -1) {
                    val selectedRbtn = findViewById<RadioButton>(selectedAnswer)
                    userAnswers[counter] = selectedRbtn.text.toString()
                    Log.d("userAnswer", userAnswers[counter].toString())
                    counter++
                } else {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                //If...else statement to check if the user has answered all the questions
                //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
                if (counter < 5) {
                    tvHistoryQuestions.text = historyQuestions[counter]
                    for (i in 0 until radioGroupAnswers.childCount) {
                        val radioButton = radioGroupAnswers.getChildAt(i) as RadioButton
                        radioButton.text = historyAnswerChoices[counter][i]
                    }
                    radioGroupAnswers.clearCheck()
                } else {
                    for (i in 0 until 5) {
                        if (userAnswers[i] == historyAnswers[i]) {
                            //score is used to check if the users answers match the history answers
                            score++
                        }
                    }
                    //Log statement used to check the users score
                    Log.d("score", score.toString())

                    //Intent code used to navigate from the flashcard screen to the score screen once all the questions have been answered
                    //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
                    val intent = Intent(this, Score::class.java)
                        .putExtra("score", score)
                        .putExtra("name", name)
                        .putExtra("historyQuestions", historyQuestions)
                        .putExtra("historyAnswers", historyAnswers)
                    startActivity(intent)
                    finish()
                }

                //If...else statement to check if the user has selected the correct answer
                //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
                if (userAnswers[check] == historyAnswers[check]) {
                    //Toast message displays correct if the user chooses the correct answer
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                    //check is used to check if the users answers match the history answers
                    check++
                } else {
                    //Toast message displays incorrect if the user chooses the incorrect answer
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                    check++
                }
            }
        }
    }// end of onCreate
}//end of Flashcards Activity