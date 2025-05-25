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
            //Reference: coding up quiz app
            val name = edtName.text.toString()

            //check if name is empty
            if(name.isEmpty()){
                //Display a toast message if the user hasn't entered their name
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //start next page
            val intent = Intent(this, WelcomeScreen::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
            finish()
        }
    }//end of onCreate
}//end of MainActivity