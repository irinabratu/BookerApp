package pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDates {

    @JsonProperty("checkin")
    private String checkin;
    @JsonProperty("checkout")
    private String checkout;

    public BookingDates(String checkin, String checkout){
        this.checkout=checkout;
        this.checkin=checkin;
    }
    public BookingDates(){}

    @JsonProperty("checkin")
    public String getCheckin() {
        return checkin;
    }

    @JsonProperty("checkin")
    public BookingDates setCheckin(String checkin) {
        this.checkin = checkin;
        return this;
    }

    @JsonProperty("checkout")
    public String getCheckout() {
        return checkout;
    }

    @JsonProperty("checkout")
    public BookingDates setCheckout(String checkout) {
        this.checkout = checkout;
        return this;
    }

}
