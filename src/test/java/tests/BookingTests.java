package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Booking;

import java.util.HashMap;

import static api.BookingApi.*;
import static api.HeaderApi.headerBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BookingTests {


    /**
     * create booking - positive scenarios
     **/

    @Test
    public void createBookingAllValid() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    /**
     * create booking - negative scenarios - headers
     **/

    @Test
    public void createBookingNoContentTypeInvalidAccept() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid, headerBuilder("missing","invalid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidContentTypeNoAccept() {
        Response response = createBooking(validCreateBookingBody, headerBuilder("invalid","missing","missing",null));
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void createBookingNoContentTypeValidAccept() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid, headerBuilder("missing","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingValidContentTypeNoAccept() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid, headerBuilder("valid","missing","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingNoContentTypeNoAccept() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid);
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidContentTypeValidAccept() {
        Response response = createBooking(validCreateBookingBody,headerBuilder("invalid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void createBookingValidContentTypeInvalidAccept() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid,headerBuilder("valid","invalid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingInvalidContentTypeInvalidAccept() {
        Response response = createBooking(validCreateBookingBody,headerBuilder("invalid","invalid","missing",null));
        assertThat(response.getStatusCode(), equalTo(400));
    }

    /**
     * create booking - negative scenarios - missing body parts
     **/

    @Test
    public void createBookingMissingFirstName() {
        Booking bookingMissingFirstName = bookingBuilder(null,"bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingMissingFirstName,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingLastName() {
        Booking bookingMissingLastName = bookingBuilder("irina",null,23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingMissingLastName,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingTotalPrice() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",null,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingDepositPaid() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,null,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingAllValid,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingCheckin() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,null,"2013-02-04", "needs");
        Response response = createBooking(bookingAllValid,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingCheckout() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02",null, "needs");
        Response response = createBooking(bookingAllValid,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingAdditionalNeeds() {
        Booking bookingAllValid = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", null);
        Response response = createBooking(bookingAllValid,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingMissingAll() {
        Booking bookingAllValid = bookingBuilder(null,null,null,null,null,null, null);
        Response response = createBooking(bookingAllValid,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    /**
     * create booking - negative scenarios - invalid body parts
     **/

    @Test
    public void createBookingInvalidFirstName() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        invalidBody.put("test",invalidBody.remove("firstname"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidLastName() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        invalidBody.put("test",invalidBody.remove("lastname"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidTotalPrice() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        invalidBody.put("test",invalidBody.remove("totalprice"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidDepositPaid() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        invalidBody.put("test",invalidBody.remove("depositpaid"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidCheckin() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBody.get("bookingdates");
        invalidBookingDates.put("test",invalidBookingDates.remove("checkin"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidCheckout() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBody.get("bookingdates");
        invalidBookingDates.put("test",invalidBookingDates.remove("checkin"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidAdditionalNeeds() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        invalidBody.put("test",invalidBody.remove("additionalneeds"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingInvalidAll() {
        HashMap<String, Object> invalidBody = generateInvalidBodyForCreateBooking();
        invalidBody.put("test1",invalidBody.remove("firstname"));
        invalidBody.put("test2",invalidBody.remove("lastname"));
        invalidBody.put("test3",invalidBody.remove("totalprice"));
        invalidBody.put("test4",invalidBody.remove("depositpaid"));
        invalidBody.put("test5",invalidBody.remove("additionalneeds"));
        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBody.get("bookingdates");
        invalidBookingDates.put("test6",invalidBookingDates.remove("checkin"));
        invalidBookingDates.put("test7",invalidBookingDates.remove("checkout"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    /**
     * update booking - negative scenarios - missing body parts
     **/

    @Test
    public void updateBookingAllValid(){
        Booking bookingMissingFirstName = bookingBuilder("test","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = updateBooking(bookingMissingFirstName,headerBuilder("valid","valid","valid",null),"1");
        assertThat(response.getStatusCode(), equalTo(200));
    }
}
