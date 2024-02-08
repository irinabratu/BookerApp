package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static api.Helper.tryPingBooker;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HealthCheckBookingTest {

    @Test
    public void pingBooker() {
        Response response = tryPingBooker();
        assertThat(response.getStatusCode(), equalTo(201));
    }
}
