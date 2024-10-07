package com.example.country_api.utils;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestUtils {

	private static String BASE_URL;

	// Dynamic URL Generation based on base URL and endpoint
	public RestUtils() {
		BASE_URL = loadGlobalVariable("baseUrl");
	}

	// Load shared global variables (e.g., Base URL) from a properties file
	private String loadGlobalVariable(String key) {
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("src/main/resources/application.properties")); // File storing global/environment
																					// variables
			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 1. HTTP GET request to retrieve a country by ID (with optional content
	// negotiation)
	public Response getCountryById(int id, String acceptHeader) {
		return given().header("Accept", acceptHeader)
				.when().get(BASE_URL + "/api/countries/get/" + id).then().extract().response();
	}

	// 2. HTTP POST request to add a new country
	public Response addCountry(String countryName) {
		return given().contentType(ContentType.JSON).body("{ \"name\": \"" + countryName + "\" }").when()
				.post(BASE_URL + "/api/countries/add").then().extract().response();
	}

	// 3. HTTP PUT request to update an existing country by ID
	public Response updateCountry(int id, String newCountryName) {
		return given().contentType(ContentType.JSON).body("{ \"name\": \"" + newCountryName + "\" }").when()
				.put(BASE_URL + "/api/countries/update/" + id).then().extract().response();
	}

	// 4. HTTP DELETE request to delete a country by ID
	public Response deleteCountry(int id) {
		return given().when().delete(BASE_URL + "/api/countries/delete/" + id).then().extract().response();
	}

	// 5. HTTP PATCH request to partially update a country's name by ID
	public Response patchCountryName(int id, String newCountryName) {
		return given().contentType(ContentType.JSON).body("{ \"name\": \"" + newCountryName + "\" }").when()
				.patch(BASE_URL + "/api/countries/patch/" + id).then().extract().response();
	}

	// 6. HTTP GET request to search for a country by name (query parameter)
	public Response searchCountryByName(String countryName) {
		return given().queryParam("name", countryName).when().get(BASE_URL + "/api/countries/search").then().extract()
				.response();
	}

	// 7. HTTP GET request to retrieve a country by code (URI template)
	public Response getCountryByCode(String code) {
		return given().when().get(BASE_URL + "/api/countries/code/" + code).then().extract().response();
	}

	// Dynamic URL generation with Base URL + Endpoint + Query/Path parameters
	public String buildURL(String endpoint, Map<String, String> queryParams) {
		StringBuilder urlBuilder = new StringBuilder(BASE_URL + endpoint);
		if (queryParams != null && !queryParams.isEmpty()) {
			urlBuilder.append("?");
			queryParams.forEach((key, value) -> urlBuilder.append(key).append("=").append(value).append("&"));
			urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove the trailing '&'
		}
		return urlBuilder.toString();
	}

	// GET request with dynamic URL generation and query parameters
	public Response getCountryById(int id) {
		String url = buildURL("/api/countries/get/" + id, null);
		return given().when().get(url).then().extract().response();
	}

	// GET request with dynamic query parameters
	public Response searchCountryByName2(String countryName) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("name", countryName);
		String url = buildURL("/api/countries/search", queryParams); // Dynamic query parameters
		return given().when().get(url).then().extract().response();
	}

	// POST request to add a new country (Example for CSV data)
	public Response addCountry2(String countryName) {
		return given().contentType(ContentType.JSON).body("{ \"name\": \"" + countryName + "\" }").when()
				.post(BASE_URL + "/api/countries/add").then().extract().response();
	}

	// DELETE request (Example with dynamic URL)
	public Response deleteCountry2(int id) {
		String url = buildURL("/api/countries/delete/" + id, null);
		return given().when().delete(url).then().extract().response();
	}

	// 10. POST request for adding a country (validating request body)
	public Response addCountry3(String countryName) {
		return given().contentType(ContentType.JSON).body("{ \"name\": \"" + countryName + "\" }").when()
				.post(BASE_URL + "/api/countries/add").then().extract().response();
	}

	// 11 & 12. GET request to retrieve a country by ID (validating response body
	// and status code)
	public Response getCountryById2(int id) {
		return given().when().get(BASE_URL + "/api/countries/get/" + id).then().extract().response();
	}

	// 13. POST request to handle bulk add of countries (resources & payloads)
	public Response addMultipleCountries(Map<Integer, String> countries) {
		return given().contentType(ContentType.JSON).body(countries).when().post(BASE_URL + "/api/countries/bulkAdd")
				.then().extract().response();
	}

	// 14. GET request to test different response body types (JSON, XML, Text)
	public Response getCountryByIdWithResponseType(int id, String acceptType) {
		return given().header("Accept", acceptType).when().get(BASE_URL + "/api/countries/responseType/" + id).then()
				.extract().response();
	}
}
