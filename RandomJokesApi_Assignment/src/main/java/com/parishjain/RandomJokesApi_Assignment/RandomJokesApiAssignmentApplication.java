package com.parishjain.RandomJokesApi_Assignment;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class RandomJokesApiAssignmentApplication {

	public static void main(String[] args) throws Exception{

		// The URL class is used to represent a URL address and provides various methods for working with URLs
		URL getUrl = new URL("https://api.chucknorris.io/jokes/random");

		// Opening the connection to the URL by using HttpURLConnection Class
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

		// Setting up the Request Method
		connection.setRequestMethod("GET");

		// Here we are fetching the response status code like 404,200
		int responseCode = connection.getResponseCode();

		if(responseCode == 200){

			// Getting the Input Stream from the connection
			InputStream inputStream = connection.getInputStream();
			/*
			   InputStreamReader is a class in Java that is used to bridge between byte-oriented input streams and
			   character-oriented readers. It is often used to convert raw bytes from an InputStream into character
			   data that can be easily processed in Java as strings.

				The main purpose of InputStreamReader is to provide a way to read text data from an input stream,
				such as reading text files or reading data from a network connection, where the data is represented
				as a sequence of bytes. InputStreamReader converts these bytes into characters using a specified
				character encoding, allowing the data to be processed as text in Java.
			 */
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			// This allows data to read efficiently
			/*
			In other words, InputStreamReader is responsible for converting the bytes from the input stream into characters
			using a specific character encoding, and BufferedReader is responsible for efficiently reading and buffering the
			characters from the InputStreamReader in larger chunks for more efficient processing. By using BufferedReader in
			combination with InputStreamReader, you can efficiently read and process text data from an input stream, while also
			properly decoding the bytes into characters using the specified character encoding. This ensures that the text data
			is correctly interpreted and processed in your Java code.
			 */
			BufferedReader reader = new BufferedReader(inputStreamReader);

			//  Now Reading Response Data Line By Line
			String line;
			StringBuilder response = new StringBuilder();
			while((line = reader.readLine()) != null){
				response.append(line);
			}
			// Close the BufferedReader and input stream
			reader.close();
			inputStream.close();
			// Get the complete response as a String
			String responseString = response.toString();
			JSONObject jsonObject = new JSONObject(responseString);
			System.out.println(jsonObject.getString("value"));
			System.out.println();
			// indexFactor 4 which specifies the number of spaces to use for indentation.
			String prettyJson = jsonObject.toString(4);
			System.out.println(prettyJson);

		}
	}

}
