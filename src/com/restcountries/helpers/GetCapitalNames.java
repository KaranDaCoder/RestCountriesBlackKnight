package com.restcountries.helpers;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.restcountries.rest.RestCountriesRunner;

public class GetCapitalNames {
	private String capitalName;

	public String getCapitalNameWithAlphaCode(String alphaCode) throws IOException {

		if (alphaCode.length() == 0 || alphaCode.equals("")) {
			capitalName = "Alpha Code cannot be blank or empty";
			return capitalName;
		}

		JSONArray getJsonResponse = RestCountriesRunner.getResponse();

		for (int i = 0; i < getJsonResponse.length(); i++) {
			JSONObject countryObject = getJsonResponse.getJSONObject(i);
			String alpha2Code = countryObject.getString("alpha2Code");
			String alpha3Code = countryObject.getString("alpha3Code");
			if (alpha2Code.equals(alphaCode) || alpha3Code.equals(alphaCode)) {
				capitalName = countryObject.getString("capital");
				if (capitalName.equals("")) {
					capitalName = "Capital name does not exist currently for " + alphaCode;
				}
				break;
			} else {
				capitalName = "No Country exists with inputted alpha code";
			}
		}
		return capitalName;
	}

	public String getCapitalNameWithName(String countryName) throws IOException {

		if (countryName.length() == 0 || countryName.equals(null)) {
			capitalName = "Country Name cannot be blank or empty";
			return capitalName;
		}

		JSONArray getJsonResponse = RestCountriesRunner.getResponse();

		for (int i = 0; i < getJsonResponse.length(); i++) {
			JSONObject countryObject = getJsonResponse.getJSONObject(i);
			if (countryObject.get("name").equals(countryName)) {
				capitalName = countryObject.getString("capital");
				if (capitalName.equals("")) {
					capitalName = "Capital name does not exist currently for " + countryName;
				}
				break;
			} else {
				capitalName = "Invalid name entered, cannot retrieve capital name";
			}
		}
		return capitalName;
	}

}
