package AllureReport;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@Listeners(AllureTestNg.class)

public class AllureReportAPI {
    private static final String BASE_URL = "http://localhost:8000";

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Test Login")
    @Description("Test to verify login functionality")
    public void testLogin() {
        attachText("Starting testLogin");
        String username = "Marshal";
        String password = "parshal47";

        Response response = RestAssured.given()
                .contentType(ContentType.MULTIPART)
                .multiPart("username", username)
                .multiPart("password", password)
                .post("/login/");

        attachResponse(response);
        Assert.assertEquals(302, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Edit")
    @Description("Test to verify edit functionality")
    public void testEdit() {
        attachText("Starting testEdit");

        Response response = RestAssured.given()
                .contentType(ContentType.MULTIPART)
                .multiPart("host", 1)
                .multiPart("name", "DMBxQyAZGI")
                .multiPart("description", "DMBxQyAZGI2")
                .multiPart("producer", 3)
                .multiPart("guarantee", 4)
                .post("/update-product/35/");

        attachResponse(response);
        Assert.assertEquals(302, response.getStatusCode());
    }

    @Test
    @DisplayName("Page Load Test")
    @Description("Test to verify page load time")
    public void pageLoadTest() {
        attachText("Starting pageLoadTest");

        long startTime = System.currentTimeMillis();
        Response response = RestAssured.get("/update-product/35/");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        int statusCode = response.getStatusCode();

        attachResponse(response);
        Assert.assertEquals(200, statusCode, "Invalid response status code");
        Assert.assertTrue(executionTime < 1500, "Response time is more than 1s");
    }

    @Test
    @DisplayName("Test Add Comment to Product Page")
    @Description("Test to verify adding a comment to a product page")
    public void testAddCommentToProductPage() {
        attachText("Starting testAddCommentToProductPage");
        String comment = "This is a test comment";

        Response postResponse = RestAssured.given()
                .multiPart("body", comment)
                .post("http://127.0.0.1:8000/product_page/35/");

        attachResponse(postResponse);
        String responseBody = postResponse.getBody().asString();
        Assert.assertTrue(responseBody.contains(comment));
    }

    @Test
    @DisplayName("Bad Data Registration Test")
    @Description("Test to verify registration with invalid data")
    public void badDataRegistrationTest() {
        attachText("Starting badDataRegistrationTest");
        String username = "hshsh";
        String password1 = "hshsh";
        String password2 = "hshsh";

        Response postResponse = RestAssured.given()
                .multiPart("username", username)
                .multiPart("password1", password1)
                .multiPart("password2", password2)
                .post("http://127.0.0.1:8000/registerUser/");

        attachResponse(postResponse);
        Assert.assertEquals(400, postResponse.getStatusCode());
    }

    @Attachment(value = "Text Attachment", type = "text/plain")
    private String attachText(String text) {
        return text;
    }

    @Attachment(value = "Response Attachment", type = "application/json")
    private byte[] attachResponse(Response response) {
        return response.getBody().asByteArray();
    }

    @Attachment(value = "File Attachment", type = "image/png")
    private byte[] attachFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Attachment(value = "File Attachment", type = "image/png")
    private byte[] attachFile(byte[] fileBytes) {
        return fileBytes;
    }
}