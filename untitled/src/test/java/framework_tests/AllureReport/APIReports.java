package framework_tests.AllureReport;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class APIReports {
    private static final String BASE_URL = "http://localhost:8000"; // Замените на актуальный URL вашего Django-сервера

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testLogin() {
        String username = "Marshal";
        String password = "parshal47";

        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.MULTIPART)
                .multiPart("username", username)
                .multiPart("password", password);

        Response response = request.post("/login/");

        Allure.addAttachment("Login Request", getHttpRequestAttachment(request));
        Allure.addAttachment("Login Response", getHttpResponseAttachment(response));

        Assert.assertEquals(302, response.getStatusCode());
    }

    @Test
    public void testEdit() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("host", 1);
        parameters.put("name", "DMBxQyAZGI");
        parameters.put("description", "DMBxQyAZGI2");
        parameters.put("producer", 3);
        parameters.put("guarantee", 4);

        EncoderConfig encoderConfig = new EncoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT);

        RequestSpecification request = RestAssured.given()
                .config(RestAssured.config().encoderConfig(encoderConfig))
                .contentType(ContentType.MULTIPART)
                .formParams(parameters);

        Response response = request.post("/update-product/35/");

        Allure.addAttachment("Edit Request", getHttpRequestAttachment(request));
        Allure.addAttachment("Edit Response", getHttpResponseAttachment(response));

        Assert.assertEquals(302, response.getStatusCode());
    }

    @Test
    public void testPageLoad() {
        long startTime = System.currentTimeMillis();
        Response response = RestAssured.get("/update-product/35/");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        int statusCode = response.getStatusCode();

        RequestSpecification request = RestAssured.given();

        Allure.addAttachment("Page Load Request", getHttpRequestAttachment(request));
        Allure.addAttachment("Page Load Response", getHttpResponseAttachment(response));

        Assert.assertEquals(200, statusCode, "Invalid response status code");
        Assert.assertTrue(executionTime < 1500, "Response time is more than 1s");
    }

    @Test
    public void testAddCommentToProductPage() {
        String comment = "This is a test comment";

        RequestSpecification request = RestAssured.given()
                .formParam("body", comment);

        Response postResponse = request.post("/product_page/35/");

        Allure.addAttachment("Add Comment Request", getHttpRequestAttachment(request));
        Allure.addAttachment("Add Comment Response", getHttpResponseAttachment(postResponse));

        String responseBody = postResponse.getBody().asString();
        Assert.assertTrue(responseBody.contains(comment));
    }

    @Test
    public void testBadDataReg() {
        String username = "hshsh";
        String password1 = "hshsh";
        String password2 = "hshsh";

        RequestSpecification request = RestAssured.given()
                .formParam("username", username)
                .formParam("password1", password1)
                .formParam("password2", password2);

        Response postResponse = request.post("/registerUser/");

        Allure.addAttachment("Bad Data Registration Request", getHttpRequestAttachment(request));
        Allure.addAttachment("Bad Data Registration Response", getHttpResponseAttachment(postResponse));

        Assert.assertEquals(400, postResponse.getStatusCode());
    }

    private String getHttpRequestAttachment(RequestSpecification request) {
        RequestSpecificationImpl specificationImpl = (RequestSpecificationImpl) request;
        StringBuilder attachmentBuilder = new StringBuilder();
        attachmentBuilder.append("HTTP Request\n");
        attachmentBuilder.append("Method: ").append(((RequestSpecificationImpl) request).getMethod()).append("\n");
        attachmentBuilder.append("URI: ").append(((RequestSpecificationImpl) request).getURI()).append("\n");
        attachmentBuilder.append("Headers: ").append(((RequestSpecificationImpl) request).getHeaders()).append("\n");
        attachmentBuilder.append("Parameters: ").append(((RequestSpecificationImpl) request).getFormParams()).append("\n");
        attachmentBuilder.append("Body: ").append(specificationImpl.getBody()).append("\n");
        return attachmentBuilder.toString();
    }

    private ByteArrayInputStream getHttpResponseAttachment(Response response) {
        String responseString = response.getBody().asString();
        return new ByteArrayInputStream(responseString.getBytes());
    }
}
