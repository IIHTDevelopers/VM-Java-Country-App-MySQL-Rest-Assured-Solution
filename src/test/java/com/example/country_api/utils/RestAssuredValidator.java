package com.example.country_api.utils;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class RestAssuredValidator {

	// Generic method to validate a GET request with a specific endpoint and
	// expected body content
	public static void validateGetRequest(String endpoint, int expectedStatusCode, String expectedBody) {
		ValidatableResponse response = RestAssured.given().when().get(endpoint).then().statusCode(expectedStatusCode)
				.body(equalTo(expectedBody));

		System.out.println("GET request to " + endpoint + " passed with status code " + expectedStatusCode
				+ " and body: " + expectedBody);
	}

	// Generic method to validate a POST request with a body and expected response
	public static void validatePostRequest(String endpoint, String requestBody, int expectedStatusCode,
			String expectedResponseBody) {
		ValidatableResponse response = RestAssured.given().contentType("application/json").body(requestBody).when()
				.post(endpoint).then().statusCode(expectedStatusCode).body(equalTo(expectedResponseBody));

		System.out.println("POST request to " + endpoint + " passed with status code " + expectedStatusCode
				+ " and response body: " + expectedResponseBody);
	}

	// Generic method to validate a PUT request with a body and expected response
	public static void validatePutRequest(String endpoint, String requestBody, int expectedStatusCode,
			String expectedResponseBody) {
		ValidatableResponse response = RestAssured.given().contentType("application/json").body(requestBody).when()
				.put(endpoint).then().statusCode(expectedStatusCode).body(equalTo(expectedResponseBody));

		System.out.println("PUT request to " + endpoint + " passed with status code " + expectedStatusCode
				+ " and response body: " + expectedResponseBody);
	}

	// Generic method to validate a DELETE request
	public static void validateDeleteRequest(String endpoint, int expectedStatusCode) {
		ValidatableResponse response = RestAssured.given().when().delete(endpoint).then()
				.statusCode(expectedStatusCode);

		System.out.println("DELETE request to " + endpoint + " passed with status code " + expectedStatusCode);
	}

	// Generic method to validate a PATCH request with a body and expected response
	public static void validatePatchRequest(String endpoint, String requestBody, int expectedStatusCode,
			String expectedResponseBody) {
		ValidatableResponse response = RestAssured.given().contentType("application/json").body(requestBody).when()
				.patch(endpoint).then().statusCode(expectedStatusCode).body(equalTo(expectedResponseBody));

		System.out.println("PATCH request to " + endpoint + " passed with status code " + expectedStatusCode
				+ " and response body: " + expectedResponseBody);
	}
}

//public class RestAssuredValidator {
//
//	public static void validateRestAssuredUsage() {
//		ValidatableResponse response = RestAssured.given().when().get("/country").then().statusCode(200)
//				.body(equalTo("India"));
//
//		System.out.println("API validation passed with correct status code and response.");
//	}
//}
