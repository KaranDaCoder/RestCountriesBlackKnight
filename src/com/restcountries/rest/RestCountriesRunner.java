package com.restcountries.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;

import com.restcountries.helpers.GetCapitalNames;

public class RestCountriesRunner {
	private static final String BASE_URL = "https://restcountries.eu/rest/v2/all?fields=name;capital;alpha2Code;alpha3Code";
	private static int responseCode;

	public static JSONArray getResponse() throws IOException {
		URL url = new URL(BASE_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		responseCode = httpURLConnection.getResponseCode();
		StringBuffer response = new StringBuffer();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		return new JSONArray(response.toString());
	}

	public static int getResponseCode() {
		return responseCode;
	}

	private static void getRestCountriesRunner() throws IOException {
		boolean isActive = true;
		Scanner getCapitals = new Scanner(System.in);
		while (isActive) {
			System.out.println(
					"Welcome! How would you like to search capitals? \n Press 1 to search with country name. \n Press 2 to search with alpha code name. \n Press 3 to exit");
			String getInput = getCapitals.nextLine();
			if (getInput.equals("1")) {
				System.out.println("Enter name of country to get it's capital...");
				String getCountryName = getCapitals.nextLine();
				System.out.println(new GetCapitalNames().getCapitalNameWithName(getCountryName));
			}

			else if (getInput.equals("2")) {
				System.out.println("Enter the country's alpha code to get it's capital...");
				String alphaCodeName = getCapitals.nextLine();
				System.out.println(new GetCapitalNames().getCapitalNameWithAlphaCode(alphaCodeName));
			}

			else if (getInput.equals("3")) {
				System.out.println("Exiting the app...");
				isActive = false;
			}

			else {
				System.out.println("Please provide valid inputs (1, 2 or 3)");
			}
		}

	}

	public static void main(String[] args) throws IOException {

		getRestCountriesRunner();
	}

}
