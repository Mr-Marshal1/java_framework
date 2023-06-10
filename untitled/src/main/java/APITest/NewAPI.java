package APITest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.json.JSONObject;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.List;

public class NewAPI {
    private static final String BASE_URL = "http://localhost:8000";  // Замените на актуальный URL вашего Django-сервера

    @BeforeTest
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testLogin() {
        String username = "Marshal";
        String password = "parshal47";

        Response response = RestAssured.given()
                .contentType(ContentType.MULTIPART)
                .multiPart("username", username)
                .multiPart("password", password)
                .post("/login/");

        Assert.assertEquals(302, response.getStatusCode());
    }

    @Test
    public void TestEdit() {

        Response response = RestAssured.given()
                .contentType(ContentType.MULTIPART)
                .multiPart("host", 1)
                .multiPart("name", "DMBxQyAZGI")
                .multiPart("description", "DMBxQyAZGI2")
                .multiPart("producer", 3)
                .multiPart("guarantee", 4)
                .post("/update-product/35/");

        Assert.assertEquals(302, response.getStatusCode());
    }

    @Test
    public void PageLoad(){
        long startTime = System.currentTimeMillis();
        Response response = RestAssured.get("/update-product/35/");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode, "Invalid response status code");
        Assert.assertTrue(executionTime < 1500, "Response time is more than 1s");
    }

    @Test
    public void testAddCommentToProductPage() {
        String comment = "This is a test comment";

        Response postResponse = RestAssured.given()
                .multiPart("body", comment)
                .post("http://127.0.0.1:8000/product_page/35/");

        String responseBody = postResponse.getBody().asString();
        Assert.assertTrue(responseBody.contains(comment));
    }

    @Test
    public void BadDataReg(){
        String username = "hshsh";
        String password1 = "hshsh";
        String password2 = "hshsh";

        Response postResponse = RestAssured.given()
                .multiPart("username", username)
                .multiPart("password1", password1)
                .multiPart("password2", password2)
                .post("http://127.0.0.1:8000/registerUser/");

        Assert.assertEquals(400, postResponse.getStatusCode());
    }


    }

