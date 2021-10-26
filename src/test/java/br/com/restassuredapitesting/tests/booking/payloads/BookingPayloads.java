package br.com.restassuredapitesting.tests.booking.payloads;

import org.json.JSONObject;

public class BookingPayloads {

    public static JSONObject payloadValidBooking(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", "Júlia");
        payload.put("lastname", "Moraes");
        payload.put("totalprice", "210");
        payload.put("depositpaid", "true");
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "breakfast");

        return payload;

    }
}
