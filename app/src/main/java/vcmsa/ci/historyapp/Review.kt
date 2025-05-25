package vcmsa.ci.historyapp

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

        //Declaring the exit button to exit the app
        btnExitApp.setOnClickListener {
            finish()
        }
    }//end of onCreate
}//end of Review Activity