package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Booking;
import pojo.TokenGenerationCredentials;

import java.util.HashMap;

import static api.BookingApi.*;
import static api.HeaderApi.headerBuilder;
import static api.TokenApi.retrieveToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class E2ETests {

    @Test
    public void e2eJourney(){
        String id;
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response createBookingResponse = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(createBookingResponse.getStatusCode(), equalTo(200));
        id = createBookingResponse.path("bookingid").toString();

        HashMap<String, Object> partialBookingMap = new HashMap<>();
        partialBookingMap.put("firstname","irina2");
        Response patchBookingResponse = partiallyUpdateBooking(booking,headerBuilder("valid","valid","valid",null),id);
        assertThat(patchBookingResponse.getStatusCode(), equalTo(200));

        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Response deleteBookingResponse = deleteBooking(id,headerBuilder("missing","missing","valid",token));
        assertThat(deleteBookingResponse.getStatusCode(), equalTo(201));

        Response response = getBooking(id,headerBuilder("missing","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(404));
    }
}
