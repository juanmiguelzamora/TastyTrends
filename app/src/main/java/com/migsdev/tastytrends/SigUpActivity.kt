package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.roydev.tastytrends.URL_Constants
import org.json.JSONObject

class SigUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sig_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sUsername = findViewById<EditText>(R.id.Signup_username)
        val sEmail = findViewById<EditText>(R.id.signup_email)
        val sPassword = findViewById<EditText>(R.id.signup_password)
        val sConPass = findViewById<EditText>(R.id.signup_confirmpass)

        val btnRegister = findViewById<Button>(R.id.btnregiter)
        btnRegister.setOnClickListener {
            if (sUsername.text.toString().isEmpty() || sEmail.text.toString().isEmpty() ||
                sPassword.text.toString().isEmpty() || sConPass.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (sPassword.text.toString() != sConPass.text.toString()) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(sUsername.text.toString(), sEmail.text.toString(), sPassword.text.toString())
            }
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        val url = URL_Constants().getRegisterUrl() // Using URL_Constants class

        val jsonObject = JSONObject().apply {
            put("username", username)
            put("email", email)
            put("password", password)
        }

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            Response.Listener { response ->
                // Handle successful response
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            },
            Response.ErrorListener { error ->
                val errorMessage = handleError(error)
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        )

        // Add the request to the RequestQueue
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleError(error: VolleyError): String {
        // Log the error for debugging
        Log.e("VolleyError", "Error: ${error.message}")

        return when {
            error.networkResponse != null -> {
                // Handle specific HTTP error codes
                val statusCode = error.networkResponse.statusCode
                val responseData = String(error.networkResponse.data)

                Log.e("VolleyError", "Status Code: $statusCode, Response: $responseData")

                when (statusCode) {
                    400 -> {
                        // Bad Request - usually validation errors
                        try {
                            val jsonError = JSONObject(responseData)
                            jsonError.optString("message", "Invalid input")
                        } catch (e: Exception) {
                            "Bad Request: Please check your input."
                        }
                    }
                    401 -> "Unauthorized: Please log in."
                    403 -> "Forbidden: You don't have permission."
                    404 -> "Not Found: API endpoint not found."
                    500 -> "Server Error: Please try again later."
                    else -> "Error code: $statusCode"
                }
            }
            error.message != null -> {
                // Handle network errors or other unexpected errors
                error.message ?: "Unknown error occurred."
            }
            else -> "An unexpected error occurred."
        }
    }
}
