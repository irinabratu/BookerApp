package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static api.BookingApi.*;
import static api.BookingApi.getBookings;
import static api.HeaderApi.headerBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetBookingTests {


    /**
     * get booking by id - positive scenarios
     **/

    @Test
    public void getBookingAllValid() {
        String id = retrieveBookingIds().get(0);
        Response response = getBooking(id,headerBuilder("missing","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    /**
     * get booking by id - negative scenarios - headers
     **/

    @Test
    public void getBookingMissingAccept() {
        String id = retrieveBookingIds().get(0);
        Response response = getBooking(id,headerBuilder("missing","missing","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingInvalidAccept() {
        String id = retrieveBookingIds().get(0);
        Response response = getBooking(id,headerBuilder("missing","invalidAtom","missing",null));
        assertThat(response.getStatusCode(), equalTo(418));
    }

    /**
     * get booking by id - negative scenarios - url
     **/

    @Test
    public void getBookingMissingIdParam() {
        Response response = getBooking("");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingInvalidIdParam() {
        Response response = getBooking("test");
        assertThat(response.getStatusCode(), equalTo(404));
    }

    /**
     * get bookings - positive scenarios
     **/

    @Test
    public void getBookingsAllValidNoParams() {
        Response response = getBookings();
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsAllValidNameFilter() {
        HashMap<String, Object> paramBuilder = paramBuilder("Jim","Ericsson",null,null);
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsAllValidDateFilter() {
        HashMap<String, Object> paramBuilder = paramBuilder(null,null,"2015-03-03","2023-11-07");
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    /**
     * get bookings - negative scenarios
     **/

    @Test
    public void getBookingsMissingFirstName() {
        HashMap<String, Object> paramBuilder = paramBuilder(null,"Ericsson",null,null);
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsMissingLastName() {
        HashMap<String, Object> paramBuilder = paramBuilder("Jim",null,null,null);
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsMissingCheckinDate() {
        HashMap<String, Object> paramBuilder = paramBuilder(null,null,null,"2023-11-07");
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsMissingCheckoutDate() {
        HashMap<String, Object> paramBuilder = paramBuilder(null,null,"2015-03-03",null);
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsInvalidFirstName() {
        HashMap<String, Object> paramBuilder = paramBuilder("//","Ericsson","2015-03-03","2023-11-07");
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsInvalidLastName() {
        HashMap<String, Object> paramBuilder = paramBuilder(null,"//","2015-03-03","2023-11-07");
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingsInvalidCheckinDate() {
        HashMap<String, Object> paramBuilder = paramBuilder(null,null,"20150303","2023-11-07");
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void getBookingsInvalidCheckoutDate() {
        HashMap<String, Object> paramBuilder = paramBuilder(null,null,"2015-03-03","20231107");
        Response response = getBookings(paramBuilder);
        assertThat(response.getStatusCode(), equalTo(500));
    }
}
