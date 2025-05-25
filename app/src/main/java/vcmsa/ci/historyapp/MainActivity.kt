package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat

        //Declaring GUI variables
        val edtName = findViewById<EditText>(R.id.edtName)
        val btnNextPage = findViewById<Button>(R.id.btnNextPage)

        btnNextPage.setOnClickListener {
            //Getting data from the user
            //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
            val name = edtName.text.toString()

            //Checks if the name variable is empty
            //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
            if(name.isEmpty()){
                //Display a toast message if the user hasn't entered their name
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //Navigate to the Welcome page once the user has entered their name
            //Reference: https://youtu.be/K13fR2RHTak - Liehan Els, 2025. Coding up the Quiz app with lots of debugging. [video online] Available at: <https://youtu.be/K13fR2RHTak> [Accessed 21 May 2025].
            val intent = Intent(this, WelcomeScreen::class.java)
            //This is to carry the name variable to the next screen when navigating to the Welcome screen
            intent.putExtra("name", name)
            startActivity(intent)
            finish()
        }

    }//end of onCreate
}//end of MainActivity