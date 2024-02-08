package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.TokenGenerationCredentials;

import static api.BookingApi.deleteBooking;
import static api.BookingApi.retrieveBookingIds;
import static api.HeaderApi.headerBuilder;
import static api.TokenApi.retrieveToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteBookingTests {

    /**
     * delete booking by id - positive scenarios
     **/

    @Test
    public void deleteBookingAllValid() {
        String id = retrieveBookingIds().get(0);
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Response response = deleteBooking(id,headerBuilder("missing","missing","valid",token));
        assertThat(response.getStatusCode(), equalTo(201));
    }

    @Test
    public void deleteBookingAuthValid() {
        String id = retrieveBookingIds().get(0);
        Response response = deleteBooking(id,headerBuilder("missing","missing","valid",null));
        assertThat(response.getStatusCode(), equalTo(201));
    }

    @Test
    public void deleteBookingCookieValid() {
        String id = retrieveBookingIds().get(0);
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Response response = deleteBooking(id,headerBuilder("missing","missing","missing",token));
        assertThat(response.getStatusCode(), equalTo(201));
    }

    /**
     * delete booking by id - negative scenarios - headers
     **/

    @Test
    public void deleteBookingInvalidAuth() {
        String id = retrieveBookingIds().get(0);
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Response response = deleteBooking(id,headerBuilder("missing","missing","invalid",token));
        assertThat(response.getStatusCode(), equalTo(201));
    }

    @Test
    public void deleteBookingInvalidToken() {
        String id = retrieveBookingIds().get(0);
        Response response = deleteBooking(id,headerBuilder("missing","missing","missing","123"));
        assertThat(response.getStatusCode(), equalTo(403));
    }

    @Test
    public void deleteBookingAllInvalid() {
        String id = retrieveBookingIds().get(0);
        Response response = deleteBooking(id,headerBuilder("missing","missing","invalid","123"));
        assertThat(response.getStatusCode(), equalTo(403));
    }

    @Test
    public void deleteBookingMissingAuth() {
        String id = retrieveBookingIds().get(0);
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Response response = deleteBooking(id,headerBuilder("missing","missing","missing",token));
        assertThat(response.getStatusCode(), equalTo(201));
    }

    @Test
    public void deleteBookingMissingToken() {
        String id = retrieveBookingIds().get(0);
        Response response = deleteBooking(id,headerBuilder("missing","missing","valid",null));
        assertThat(response.getStatusCode(), equalTo(201));
    }

    @Test
    public void deleteBookingAllMissing() {
        String id = retrieveBookingIds().get(0);
        Response response = deleteBooking(id,headerBuilder("missing","missing","missing",null));
        assertThat(response.getStatusCode(), equalTo(403));
    }

    /**
     * delete booking by id - negative scenarios - url
     **/

    @Test
    public void deleteBookingMissingIdParam() {
        Response response = deleteBooking("");
        assertThat(response.getStatusCode(), equalTo(404));
    }

    @Test
    public void deleteBookingInvalidIdParam() {
        Response response = deleteBooking("test");
        assertThat(response.getStatusCode(), equalTo(403));
    }
}
