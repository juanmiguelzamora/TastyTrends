package com.roydev.tastytrends;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUp extends AsyncTask<String, Void, String> {
    private final URL_Constants url = new URL_Constants();
    String register = url.getRegisterUrl();
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder response = new StringBuilder();
        HttpURLConnection client = null;
        try {
            // Creating a URL to post the data.
            URL url = new URL(register);

            // Opening the connection.
            client = (HttpURLConnection) url.openConnection();

            // Setting method as POST.
            client.setRequestMethod("POST");

            // Setting content type and accept type.
            client.setRequestProperty("Content-Type", "application/json");
            client.setRequestProperty("Accept", "application/json");

            // Setting client to do output.
            client.setDoOutput(true);

            // Posting the data.
            try (OutputStream os = client.getOutputStream()) {
                byte[] input = strings[0].getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Checking response code.
            int responseCode = client.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return "Error: " + responseCode;
            }

            // Reading the response.
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(client.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return null; // Return null or a specific error message
        } finally {
            // Disconnect the client if necessary
            client.disconnect();
        }
        return response.toString(); // Return the response from the server
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Handle the result (success or error message) here
    }
}
