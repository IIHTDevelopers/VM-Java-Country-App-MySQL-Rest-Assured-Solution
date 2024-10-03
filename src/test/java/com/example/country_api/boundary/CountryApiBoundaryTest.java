package com.example.country_api.boundary;

import static com.example.country_api.utils.TestUtils.boundaryTestFile;
import static com.example.country_api.utils.TestUtils.currentTest;
import static com.example.country_api.utils.TestUtils.yakshaAssert;
import static com.example.country_api.utils.TestUtils.testReport;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.example.country_api.utils.RestUtils;

import io.restassured.response.Response;

public class CountryApiBoundaryTest {
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}

	private final RestUtils restUtils = new RestUtils();

	// Test case for adding a new country (POST request)
	@Test
	public void testAddCountry() throws IOException {
		Response response = restUtils.addCountry("Canada");
//		assertThat(response.getStatusCode()).isEqualTo(201); // Status code 201 for Created
//		assertThat(response.getBody().asString()).contains("Country added with ID");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 201, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for getting a country by ID (GET request with content negotiation)
	@Test
	public void testGetCountryByIdAsJson() throws IOException {
		Response response = restUtils.getCountryById(1, "application/json");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getContentType()).isEqualTo("application/json");
//		assertThat(response.getBody().asString()).contains("India");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for getting a country by ID (GET request with XML)
	@Test
	public void testGetCountryByIdAsXml() throws IOException {
		Response response = restUtils.getCountryById(1, "application/xml");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getContentType()).isEqualTo("application/xml");
//		assertThat(response.getBody().asString()).contains("<name>India</name>");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for updating an existing country (PUT request)
	@Test
	public void testUpdateCountry() throws IOException {
		Response response = restUtils.updateCountry(1, "India Updated");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getBody().asString()).contains("Country updated");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for deleting a country by ID (DELETE request)
	@Test
	public void testDeleteCountry() throws IOException {
		Response response = restUtils.deleteCountry(2);
//		assertThat(response.getStatusCode()).isEqualTo(204); // Status code 204 for No Content
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 204, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for partially updating a country name by ID (PATCH request)
	@Test
	public void testPatchCountryName() throws IOException {
		Response response = restUtils.patchCountryName(1, "India Patched");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getBody().asString()).contains("Country name updated");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for searching a country by name (GET request with query parameter)
	@Test
	public void testSearchCountryByName() throws IOException {
		Response response = restUtils.searchCountryByName("India");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getBody().asString()).contains("India");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for retrieving a country by code (GET request with URI template)
	@Test
	public void testGetCountryByCode() throws IOException {
		Response response = restUtils.getCountryByCode("IN");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getBody().asString()).contains("India");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for bulk adding multiple countries (POST request)
	@Test
	public void testAddMultipleCountries() throws IOException {
		Map<Integer, String> countries = Map.of(4, "France", 5, "Germany");
		Response response = restUtils.addMultipleCountries(countries);
//		assertThat(response.getStatusCode()).isEqualTo(201); // Status code 201 for Created
//		assertThat(response.getBody().asString()).contains("Countries added");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 201, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for handling different response body types (GET request for JSON)
	@Test
	public void testGetCountryResponseTypeAsJson() throws IOException {
		Response response = restUtils.getCountryByIdWithResponseType(1, "application/json");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getContentType()).isEqualTo("application/json");
//		assertThat(response.getBody().asString()).contains("India");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for handling different response body types (GET request for XML)
	@Test
	public void testGetCountryResponseTypeAsXml() throws IOException {
		Response response = restUtils.getCountryByIdWithResponseType(1, "application/xml");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getContentType()).isEqualTo("application/xml");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	// Test case for handling different response body types (GET request for text)
	@Test
	public void testGetCountryResponseTypeAsText() throws IOException {
		Response response = restUtils.getCountryByIdWithResponseType(1, "text/plain");
//		assertThat(response.getStatusCode()).isEqualTo(200); // Status code 200 for OK
//		assertThat(response.getContentType()).contains("text/plain");
//		assertThat(response.getBody().asString()).contains("Country: India");
		try {
			yakshaAssert(currentTest(), response.getStatusCode() == 200, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
