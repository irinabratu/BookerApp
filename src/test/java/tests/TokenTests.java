package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.TokenGenerationCredentials;

import static api.Helper.*;
import static api.TokenApi.generateToken;
import static api.TokenApi.validTokenRequestBody;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TokenTests {

    //positive scenario
    @Test
    public void generateTokenAllValid() {
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin","password123");
        Response response = generateToken(validCredentials,getValidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(200));
    }

    //negative scenarios - headers
    @Test
    public void generateTokenWithInvalidContentType() {
        Response response = generateToken(validTokenRequestBody, getInvalidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void generateTokenWithNoContentType() {
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin","password123");
        Response response = generateToken(validCredentials);
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("reason"), equalTo("Bad credentials"));
    }

    //negative scenarios - invalid body parts
    @Test
    public void generateTokenWithInvalidCredentials() {
        TokenGenerationCredentials invalidCredentials = new TokenGenerationCredentials("adminn","password1233");
        Response response = generateToken(invalidCredentials,getValidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("reason"), equalTo("Bad credentials"));
    }

    @Test
    public void generateTokenWithInvalidUsername() {
        TokenGenerationCredentials invalidCredentials = new TokenGenerationCredentials("adminn","password123");
        Response response = generateToken(invalidCredentials,getValidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("reason"), equalTo("Bad credentials"));
    }

    @Test
    public void generateTokenWithInvalidPassword() {
        TokenGenerationCredentials invalidCredentials = new TokenGenerationCredentials("admin","password1233");
        Response response = generateToken(invalidCredentials,getValidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("reason"), equalTo("Bad credentials"));
    }

    // negative scenarios - missing body parts
    @Test
    public void generateTokenWithMissingCredentials() {
        TokenGenerationCredentials missingCredentials = new TokenGenerationCredentials(null, null);
        Response response = generateToken(missingCredentials,getValidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("reason"), equalTo("Bad credentials"));
    }

    @Test
    public void generateTokenWithMissingUsername() {
        TokenGenerationCredentials missingCredentials = new TokenGenerationCredentials(null,"password123");
        Response response = generateToken(missingCredentials,getValidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("reason"), equalTo("Bad credentials"));    }

    @Test
    public void generateTokenWithMissingPassword() {
        TokenGenerationCredentials missingCredentials = new TokenGenerationCredentials("admin",null);
        Response response = generateToken(missingCredentials,getValidContentTypeHeaders());
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("reason"), equalTo("Bad credentials"));    }

}
