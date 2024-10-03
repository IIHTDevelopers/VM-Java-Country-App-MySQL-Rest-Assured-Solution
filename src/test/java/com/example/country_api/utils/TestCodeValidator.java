package com.example.country_api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class TestCodeValidator {
	public static void validateTestClass(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        Pattern givenPattern = Pattern.compile("\\bgiven\\s*\\(");
        Pattern whenPattern = Pattern.compile("\\bwhen\\s*\\(");
        Pattern thenPattern = Pattern.compile("\\bthen\\s*\\(");
        if (!givenPattern.matcher(fileContent).find()) {
            throw new AssertionError("'given()' is missing in the student's code.");
        } else {
            System.out.println("'given()' is correctly used.");
        }
        if (!whenPattern.matcher(fileContent).find()) {
            throw new AssertionError("'when()' is missing in the student's code.");
        } else {
            System.out.println("'when()' is correctly used.");
        }
        if (!thenPattern.matcher(fileContent).find()) {
            throw new AssertionError("'then()' is missing in the student's code.");
        } else {
            System.out.println("'then()' is correctly used.");
        }
    }
}
