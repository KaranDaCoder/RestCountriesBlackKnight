package com.restcountries.testcases;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.restcountries.helpers.GetCapitalNames;
import com.restcountries.rest.RestCountriesRunner;


public class RestCountriesTestCases {

	@Test
	public void testStatusCodeAndJsonResponse() throws IOException {
		RestCountriesRunner.getResponse();
		assertEquals(RestCountriesRunner.getResponseCode(), 200);
	}

	@Test
	public void testEnteringValid3DigitsAlphaCodeToRetrieveCapitalCity() throws IOException {
		String testCapitalNamesWithAplhaCode = new GetCapitalNames().getCapitalNameWithAlphaCode("IND");
		assertEquals(testCapitalNamesWithAplhaCode, "New Delhi");
	}

	@Test
	public void testEnterringValid2DigitsAlphaCodeToRetrieveCapitalCity() throws IOException {
		String testCapitalNamesWithAplhaCode = new GetCapitalNames().getCapitalNameWithAlphaCode("IN");
		assertEquals(testCapitalNamesWithAplhaCode, "New Delhi");
	}

	@Test
	public void testProvidingEmptyAlphaCodeAndCountryCode() throws IOException {
		String testProvidingEmptyAlphaCodeAndCountryCode = new GetCapitalNames().getCapitalNameWithAlphaCode("");
		assertEquals(testProvidingEmptyAlphaCodeAndCountryCode, "Alpha Code cannot be blank or empty");
	}

	/*
	 * should fail: negative scenario wherein entering case-sensitive alpha code
	 * gives unexpected output.
	 */
	@Test
	public void testEnteringCaseSensitiveAlphaCode() throws IOException {
		String testEnteringCaseSensitiveAlphaCode = new GetCapitalNames().getCapitalNameWithAlphaCode("usa");
		assertEquals(testEnteringCaseSensitiveAlphaCode, "Washington, D.C.");
		// No Country exists with inputted alpha code
	}

	/*
	 * should fail: negative scenario wherein entering case-sensitive country name
	 * gives unexpected output.
	 */
	@Test
	public void testEnteringCaseSensitiveCountryName() throws IOException {
		String testEnteringCaseSensitiveCountryName = new GetCapitalNames().getCapitalNameWithName("CAYMAN ISLANDS");
		assertEquals(testEnteringCaseSensitiveCountryName, "George Town");
		// Invalid name entered, cannot retrieve capital name
	}

	@Test
	public void testEnteringInvalidAlphaCode() throws IOException {
		String testEnteringInvalidAlphaCode = new GetCapitalNames().getCapitalNameWithAlphaCode("@#!");
		assertEquals(testEnteringInvalidAlphaCode, "No Country exists with inputted alpha code");
	}

	@Test
	public void testEnteringInvalidCountryName() throws IOException {
		String testEnteringInvalidCountryName = new GetCapitalNames().getCapitalNameWithName("ASWA*&&@");
		assertEquals(testEnteringInvalidCountryName, "Invalid name entered, cannot retrieve capital name");

	}
	
	@Test
	public void testFetchingCountriesWithNoCapitals() throws Exception {
		String testFetchingCountriesWithNoCapitals = new GetCapitalNames().getCapitalNameWithName("Macao");
		assertEquals(testFetchingCountriesWithNoCapitals, "Capital name does not exist currently for Macao");
	}

}
