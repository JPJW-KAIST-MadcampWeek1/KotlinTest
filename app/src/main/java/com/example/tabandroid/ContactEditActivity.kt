package com.example.tabandroid
import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.tabandroid.R
import org.json.JSONObject
import java.io.FileNotFoundException

class ContactEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_edit)
        setTitle("EditContact")

        val btn_editComplete = findViewById<ImageButton>(R.id.btn_editComplete)
        val userName = findViewById<EditText>(R.id.tv_editName)
        val userNumber = findViewById<EditText>(R.id.tv_editNumber)

        userName.setText(intent.getStringExtra("user_name"))
        userNumber.setText(intent.getStringExtra("user_number"))

        btn_editComplete.setOnClickListener(View.OnClickListener() {

//            val intent = Intent()
//            intent.putExtra("user_name", userName.text.toString())
//            intent.putExtra("user_number", userNumber.text.toString())
//            setResult(RESULT_OK, intent)
            val updatedName = userName.text.toString()
            val updatedNumber = userNumber.text.toString()

            // Update the JSON data
            updateContactInJson(updatedName, updatedNumber)
            finish()
        })
    }

    private fun updateContactInJson(name: String, number: String) {
        val fileName = "contacts.json"

        // Read the existing contacts JSON from internal storage or from assets if not exist
        val contactsJsonString = try {
            applicationContext.openFileInput(fileName).bufferedReader().use { it.readText() }
        } catch (e: FileNotFoundException) {
            // Read from assets and save to internal storage for future use
            applicationContext.assets.open(fileName).bufferedReader().use { it.readText() }.also {
                applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                    output.write(it.toByteArray())
                }
            }
        }

        try {
            val jsonObject = JSONObject(contactsJsonString)
            val contactsArray = jsonObject.getJSONArray("contacts")

            // Find and update the specific contact
            for (i in 0 until contactsArray.length()) {
                val contact = contactsArray.getJSONObject(i)
                if (contact.getString("name") == name) {
                    contact.put("number", number)
                    break
                }
            }

            // Save the updated contacts JSON back to internal storage
            applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                output.write(jsonObject.toString().toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the exception properly in your app
            Toast.makeText(this, "Failed to update the contact.", Toast.LENGTH_SHORT).show()
        }
    }

}