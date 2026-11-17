package tests;

import org.junit.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class PostmanEchoTest {

    // 1. GET
    @Test
    public void testGetRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    // 2. POST с JSON
    @Test
    public void testPostJsonRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("{\"test\":\"value\"}")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data.test", equalTo("value"))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("json.test", equalTo("value"))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"));
    }

    // 3. POST с form-data
    @Test
    public void testPostFormDataRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .multiPart("foo1", "bar1")
                .multiPart("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data", equalTo(Collections.emptyMap()))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("json", nullValue())
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    // 4. PUT
    @Test
    public void testPutRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("{\"test\":\"value\"}")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data.test", equalTo("value"))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("json.test", equalTo("value"))
                .body("url", equalTo("https://postman-echo.com/put"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"));
    }

    // 5. PATCH
    @Test
    public void testPatchRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("{\"test\":\"value\"}")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data.test", equalTo("value"))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("json.test", equalTo("value"))
                .body("url", equalTo("https://postman-echo.com/patch"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"));
    }

    // 6. DELETE
    @Test
    public void testDeleteRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("{\"test\":\"value\"}")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data.test", equalTo("value"))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("json.test", equalTo("value"))
                .body("url", equalTo("https://postman-echo.com/delete"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"));
    }
}