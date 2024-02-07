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

public class UpdateBookingTests {

    /**
     * update booking - positive scenario
     **/

    @Test
    public void updateBookingAllValid(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.path("firstname"), equalTo("test"));
    }

    /**
     * update booking - negative scenarios - headers
     **/

    @Test
    public void updateBookingNoContentTypeValidAcceptCookieAuth(){
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("missing","valid","valid",token),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingNoAcceptValidContentTypeCookieAuth(){
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","missing","valid",token),"1");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void updateBookingNoCookieValidContentTypeAcceptAuth(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void updateBookingNoAuthValidContentTypeAcceptCookie(){
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","missing",token),"1");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void updateBookingNoHeaders(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("missing","missing","missing",null),"1");
        assertThat(response.getStatusCode(), equalTo(403));
    }

    @Test
    public void updateBookingInvalidContentTypeValidAcceptCookieAuth(){
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Response response = updateBooking(validUpdateBookingBody,headerBuilder("invalid","valid","valid",token),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingInvalidAcceptValidContentTypeCookieAuth(){
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","invalid","valid",token),"1");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void updateBookingInvalidAuthValidContentTypeAcceptCookie(){
        TokenGenerationCredentials validCredentials = new TokenGenerationCredentials("admin", "password123");
        String token = retrieveToken(validCredentials, headerBuilder("valid", "missing", "missing", null));
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","invalid",token),"1");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void updateBookingInvalidCookieValidContentTypeAcceptAuth(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","invalid","12345"),"1");
        assertThat(response.getStatusCode(), equalTo(403));
    }

    @Test
    public void updateBookingAllInvalid(){
        Response response = updateBooking(validUpdateBookingBody,headerBuilder("invalid","invalid","invalid","12345"),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingMissingIdParam(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),null);
        assertThat(response.getStatusCode(), equalTo(404));
    }

    @Test
    public void updateBookingInvalidIdParam(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"red");
        assertThat(response.getStatusCode(), equalTo(405));
    }

    /**
     * update booking - negative scenarios - missing body parts
     **/

    @Test
    public void updateBookingMissingFirstName(){
        Booking booking = bookingBuilder(null,"bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingMissingLastName(){
        Booking booking = bookingBuilder("irina",null,23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }


    @Test
    public void updateBookingMissingTotalPrice(){
        Booking booking = bookingBuilder("test","bratu",null,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingMissingDepositPaid(){
        Booking booking = bookingBuilder("test","bratu",23,null,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingMissingCheckin(){
        Booking booking = bookingBuilder("test","bratu",23,true,null,"2013-02-04", "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingMissingCheckout(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02",null, "needs");
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void updateBookingMissingAdditionalNeeds(){
        Booking booking = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", null);
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void updateBookingMissingAll(){
        Booking booking = bookingBuilder(null,null,null,null,null,null, null);
        Response response = updateBooking(booking,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(400));
    }

    /**
     * update booking - negative scenarios - invalid body parts
     **/
//
//    @Test
//    public void updateBookingInvalidFirstName(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        invalidBooking.put("test",invalidBooking.remove("firstname"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(400));
//    }
//
//    @Test
//    public void updateBookingInvalidLastName(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        invalidBooking.put("test",invalidBooking.remove("lastname"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(400));
//    }
//
//    @Test
//    public void updateBookingInvalidTotalPrice(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        invalidBooking.put("test",invalidBooking.remove("totalprice"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(400));
//    }
//
//    @Test
//    public void updateBookingInvalidDepositPaid(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        invalidBooking.put("test",invalidBooking.remove("depositpaid"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(400));
//    }
//
//    @Test
//    public void updateBookingInvalidCheckin(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBooking.get("bookingdates");
//        invalidBookingDates.put("test",invalidBookingDates.remove("checkin"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(400));
//    }
//
//    @Test
//    public void updateBookingInvalidCheckout(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBooking.get("bookingdates");
//        invalidBookingDates.put("test",invalidBookingDates.remove("checkout"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(400));
//    }
//
//    @Test
//    public void updateBookingInvalidAdditionalNeeds(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        invalidBooking.put("test",invalidBooking.remove("additionalneeds"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(200));
//    }
//
//    @Test
//    public void updateBookingInvalidAll(){
//        HashMap<String, Object> invalidBooking = generateBodyMapForBooking();
//        invalidBooking.put("test",invalidBooking.remove("firstname"));
//        invalidBooking.put("test",invalidBooking.remove("lastname"));
//        invalidBooking.put("test",invalidBooking.remove("totalprice"));
//        invalidBooking.put("test",invalidBooking.remove("depositpaid"));
//        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBooking.get("bookingdates");
//        invalidBookingDates.put("test",invalidBookingDates.remove("checkin"));
//        invalidBookingDates.put("test",invalidBookingDates.remove("checkout"));
//        invalidBooking.put("test",invalidBooking.remove("additionalneeds"));
//        Response response = updateBooking(invalidBooking,headerBuilder("valid","valid","valid",null),"1");
//        assertThat(response.getStatusCode(), equalTo(400));
//    }
}
