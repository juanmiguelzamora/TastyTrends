package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.roydev.tastytrends.URL_Constants
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSignIn = findViewById<Button>(R.id.btnsignin)
        val sEmail = findViewById<EditText>(R.id.login_email) // Add EditText for email
        val sPassword = findViewById<EditText>(R.id.login_password) // Add EditText for password

        btnSignIn.setOnClickListener {
            val email = sEmail.text.toString()
            val password = sPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }

        val btnSignUp = findViewById<Button>(R.id.btnreturn)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SigUpActivity::class.java)
            startActivity(intent)
        }

        val tvForgotBtn = findViewById<TextView>(R.id.tvforgotbtn)
        tvForgotBtn.setOnClickListener {
            val intent = Intent(this, ForgotPassActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        val url = URL_Constants().getLoginUrl() // Use your URL_Constants class

        val jsonObject = JSONObject().apply {
            put("email", email)
            put("password", password)
        }

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            Response.Listener { response ->
                // Handle successful response
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                // Redirect to the main activity or dashboard
                startActivity(Intent(this, JFCActivity::class.java))
            },
            Response.ErrorListener { error ->
                var errorMessage = "Login failed"

                error.networkResponse?.let { networkResponse ->
                    val statusCode = networkResponse.statusCode
                    val responseData = String(networkResponse.data)

                    if (networkResponse.data.isNotEmpty()) {
                        try {
                            val errorResponse = JSONObject(responseData)
                            errorMessage = errorResponse.optString("message", "Unknown error")
                        } catch (e: Exception) {
                            errorMessage = "Server returned non-JSON response: $responseData"
                        }
                    } else {
                        errorMessage = "Error code: $statusCode"
                    }
                } ?: run {
                    errorMessage = error.message ?: "Unknown error"
                }

                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        )

        // Add the request to the RequestQueue
        Volley.newRequestQueue(this).add(jsonRequest)
    }
}
