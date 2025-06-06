package rest;

import static io.restassured.RestAssured.given;

import java.util.Map;

import coreUtilities.utils.FileOperations;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredUtils {

	private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
	private static final String EXCEL_FILE_PATH = "src/main/resources/config.xlsx"; // Path to the Excel file
	private static final String SHEET_NAME = "PostData"; // Sheet name to read data from

	// FileOperations instance to read data from Excel
	FileOperations fileOperations = new FileOperations();

	/**
	 * @Test1 about this method getCommentsForPost()
	 * 
	 * @param : int postId - The ID of the post to retrieve comments for.
	 * @description : This method sends a GET request to retrieve comments
	 *              associated with a specific post.
	 * @return : Response - The response from the API containing the list of
	 *         comments.
	 */
	public Response getCommentsForPost(int postId) {
		return given().when().get(BASE_URL + "/posts/" + postId + "/comments").then().extract().response();
	}

	/**
	 * @Test2 about this method deletePost()
	 * 
	 * @param : int id - The ID of the post to delete.
	 * @description : This method sends a DELETE request to remove a specific post
	 *              by its ID.
	 * @return : Response - The response from the API after attempting to delete the
	 *         post.
	 */
	public Response deletePost(int id) {
		return given().when().delete(BASE_URL + "/posts/" + id).then().extract().response();
	}

	/**
	 * @Test3 about this method updatePost()
	 * 
	 * @param : int id - The ID of the post to update.
	 * @param : String newTitle - The new title to update the post with.
	 * @param : String newBody - The new body content to update the post with.
	 * @param : int userId - The user ID associated with the post.
	 * @description : This method sends a PUT request to update a post with new
	 *              title, body, and user ID.
	 * @return : Response - The response from the API after updating the post.
	 */
	public Response updatePost(int id, String newTitle, String newBody, int userId) {
		return given().contentType(ContentType.JSON)
				.body("{ \"title\": \"" + newTitle + "\", \"body\": \"" + newBody + "\", \"userId\": " + userId + " }")
				.when().put(BASE_URL + "/posts/" + id).then().extract().response();
	}

	/**
	 * @Test4 about this method addPost()
	 * 
	 * @param : Map<String, String> data - A map containing the post details (title,
	 *          body, userId).
	 * @description : This method sends a POST request to add a new post using the
	 *              data provided in the map.
	 * @return : Response - The response from the API after creating the new post.
	 * @throws : Exception - If there is an issue reading the data from the input
	 *           map.
	 */
	public Response addPost(Map<String, String> data) throws Exception {
		// Extract the values for title, body, and userId from the data map
		String title = data.get("title");
		String body = data.get("body");
		int userId = Integer.parseInt(data.get("userId"));

		// Perform POST request using the extracted data
		return given().contentType(ContentType.JSON)
				.body("{ \"title\": \"" + title + "\", \"body\": \"" + body + "\", \"userId\": " + userId + " }").when()
				.post(BASE_URL + "/posts").then().extract().response();
	}

	/**
	 * @Test5 about this method getPostById()
	 * 
	 * @param : int id - The ID of the post to retrieve.
	 * @description : This method sends a GET request to retrieve a specific post by
	 *              its ID.
	 * @return : Response - The response from the API containing the post details.
	 */
	public Response getPostById(int id) {
		return given().when().get(BASE_URL + "/posts/" + id).then().extract().response();
	}
}
