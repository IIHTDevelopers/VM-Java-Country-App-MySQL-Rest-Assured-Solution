package com.example.country_api.boundary;

import static com.example.country_api.utils.TestUtils.boundaryTestFile;
import static com.example.country_api.utils.TestUtils.currentTest;
import static com.example.country_api.utils.TestUtils.testReport;
import static com.example.country_api.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.example.country_api.utils.RestUtils;
import com.example.country_api.utils.TestCodeValidator;

import io.restassured.response.Response;

public class CountryApiBoundaryTest {

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	private final RestUtils restUtils = new RestUtils();

	private final String filePath = "src/main/java/com/example/country_api/utils/RestUtils.java";

	// Test case for adding a new country (POST request)
	@Test
	public void testAddCountry() throws IOException {
		Response response = restUtils.addCountry("Canada");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "addCountry",
				List.of("given", "when", "body", "post", "then", "extract", "response"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful && response.getStatusCode() == 201
					&& response.getBody().asString().contains("Country added with ID"), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

//	 Test case for getting a country by ID (GET request with content negotiation)
	@Test
	public void testGetCountryByIdAsJson() throws IOException {
		Response response = restUtils.getCountryById(1, "application/json");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "getCountryById",
				List.of("given", "when", "header", "get", "response", "then", "extract"));
		try {
			yakshaAssert(currentTest(),
					isValidationSuccessful && response.getStatusCode() == 200
							&& response.getContentType().equalsIgnoreCase("application/json")
							&& response.getBody().asString().contains("India"),
					boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for getting a country by ID (GET request with XML)
	@Test
	public void testGetCountryByIdAsXml() throws IOException {
		Response response = restUtils.getCountryById(1, "application/xml");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "getCountryById",
				List.of("given", "when", "header", "get", "response", "then", "extract"));
		try {
			yakshaAssert(currentTest(),
					isValidationSuccessful && response.getStatusCode() == 200
							&& (response.getContentType()).equalsIgnoreCase("application/xml")
							&& (response.getBody().asString()).contains("<name>India</name>"),
					boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for updating an existing country (PUT request)
	@Test
	public void testUpdateCountry() throws IOException {
		Response response = restUtils.updateCountry(1, "India Updated");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "updateCountry",
				List.of("given", "when", "body", "put", "response", "then", "extract", "contentType"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful && response.getStatusCode() == 200
					&& (response.getBody().asString()).contains("Country updated"), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for deleting a country by ID (DELETE request)
	@Test
	public void testDeleteCountry() throws IOException {
		Response response = restUtils.deleteCountry(2);
		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "deleteCountry",
				List.of("given", "when", "delete", "response", "then", "extract"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful && response.getStatusCode() == 204, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for partially updating a country name by ID (PATCH request)
	@Test
	public void testPatchCountryName() throws IOException {
		Response response = restUtils.patchCountryName(1, "India Patched");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "patchCountryName",
				List.of("given", "contentType", "body", "when", "patch", "extract", "response", "then"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful && response.getStatusCode() == 200
					&& (response.getBody().asString()).contains("Country name updated"), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for searching a country by name (GET request with query parameter)
	@Test
	public void testSearchCountryByName() throws IOException {
		Response response = restUtils.searchCountryByName("India");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "searchCountryByName",
				List.of("given", "queryParam", "when", "get", "extract", "response", "then"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful && response.getStatusCode() == 200
					&& (response.getBody().asString()).contains("India"), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for retrieving a country by code (GET request with URI template)
	@Test
	public void testGetCountryByCode() throws IOException {
		Response response = restUtils.getCountryByCode("IN");
		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "getCountryByCode",
				List.of("given", "when", "get", "extract", "response", "then"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful && response.getStatusCode() == 200
					&& (response.getBody().asString()).contains("India"), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for bulk adding multiple countries (POST request)
	@Test
	public void testAddMultipleCountries() throws IOException {
		Map<Integer, String> countries = Map.of(4, "France", 5, "Germany");
		Response response = restUtils.addMultipleCountries(countries);

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath, "addMultipleCountries",
				List.of("given", "contentType", "body", "when", "post", "extract", "response", "then"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful
					&& (response.getBody().asString()).contains("Countries added") && response.getStatusCode() == 201,
					boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for handling different response body types (GET request for JSON)
	@Test
	public void testGetCountryResponseTypeAsJson() throws IOException {
		Response response = restUtils.getCountryByIdWithResponseType(1, "application/json");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath,
				"getCountryByIdWithResponseType",
				List.of("given", "header", "get", "when", "extract", "response", "then"));
		try {
			yakshaAssert(currentTest(),
					isValidationSuccessful && response.getStatusCode() == 200
							&& (response.getContentType()).equalsIgnoreCase("application/json")
							&& (response.getBody().asString()).contains("India"),
					boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for handling different response body types (GET request for XML)
	@Test
	public void testGetCountryResponseTypeAsXml() throws IOException {
		Response response = restUtils.getCountryByIdWithResponseType(1, "application/xml");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath,
				"getCountryByIdWithResponseType",
				List.of("given", "header", "get", "when", "extract", "response", "then"));
		try {
			yakshaAssert(currentTest(), isValidationSuccessful && response.getStatusCode() == 200
					&& (response.getContentType()).equalsIgnoreCase("application/xml"), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for handling different response body types (GET request for text)
	@Test
	public void testGetCountryResponseTypeAsText() throws IOException {
		Response response = restUtils.getCountryByIdWithResponseType(1, "text/plain");

		// Validate if the necessary keywords are present in the method's body
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(filePath,
				"getCountryByIdWithResponseType",
				List.of("given", "header", "get", "when", "extract", "response", "then"));
		try {
			yakshaAssert(currentTest(),
					isValidationSuccessful && response.getStatusCode() == 200
							&& (response.getContentType()).contains("text/plain")
							&& (response.getBody().asString()).contains("Country: India"),
					boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
