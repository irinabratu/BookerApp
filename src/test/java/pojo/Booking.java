package pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {

    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("totalprice")
    private Integer totalprice;
    @JsonProperty("depositpaid")
    private Boolean depositpaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingdates;
    @JsonProperty("additionalneeds")
    private String additionalneeds;

    public Booking(String firstname, String lastname, Integer totalprice, Boolean depositpaid, BookingDates bookingDates,String additionalneeds){
        this.firstname=firstname;
        this.lastname=lastname;
        this.totalprice=totalprice;
        this.depositpaid=depositpaid;
        this.bookingdates=bookingDates;
        this.additionalneeds=additionalneeds;
    }

    public Booking(){}

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }
    @JsonProperty("firstname")
    public Booking setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public Booking setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    @JsonProperty("totalprice")
    public Integer getTotalprice() {
        return totalprice;
    }

    @JsonProperty("totalprice")
    public Booking setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
        return this;
    }

    @JsonProperty("depositpaid")
    public Boolean getDepositpaid() {
        return depositpaid;
    }

    @JsonProperty("depositpaid")
    public Booking setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
        return this;
    }

    @JsonProperty("bookingdates")
    public BookingDates getBookingdates() {
        return bookingdates;
    }

    @JsonProperty("bookingdates")
    public Booking setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
        return this;
    }

    @JsonProperty("additionalneeds")
    public String getAdditionalneeds() {
        return additionalneeds;
    }

    @JsonProperty("additionalneeds")
    public Booking setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
        return this;
    }

}

