package apiactions;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class UserApiActions {


    @Step("Send POST request to /api/auth/register to register new User")
    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    @Step("Send POST request to /api/auth/login to login via a registered User")
    public Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login");
    }


    @Step("Extract User's access token")
    public String extractToken(User user) {

        Gson gson = new Gson();

        Response response = loginUser(user);
        if (response.statusCode() != 200) {
            throw new AssertionError("Login failed");
        }

        String responseBody = response.getBody().asString();
        TokenResponse tokenResponse = gson.fromJson(responseBody, TokenResponse.class);

        if (tokenResponse == null || tokenResponse.getToken() == null) {
            throw new AssertionError("Token is missing in the response");
        }

        String token = tokenResponse.getToken();
        if (token.startsWith("Bearer ")) {
            token = token.substring("Bearer ".length());
        }
        return token;
    }

    @Step("Check if User exists by trying to login")
    public boolean isUserCreated(User user) {
        Response response = loginUser(user);
        return response.statusCode() == 200;
    }


    @Step("Send DELETE request to /api/auth/user to delete a User")
    public void deleteUser(String token) {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/api/auth/user");
    }

}

