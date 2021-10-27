package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.AllTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature de Exclusão de Reserva")
public class DeleteBookingTest extends BaseTest {
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Excluir uma Reserva utilizando um token válido")
    public void testeDeleteBooking() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class})
    @DisplayName("Excluir uma Reserva não existente")
    public void testDeleteANonExistentBooking() {
        int primeiroId = 9999;
        deleteBookingRequest.deleteBooking(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Excluir uma Reserva sem autorização")
    public void testDeleteAnUnauthorizedBooking() {

        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(primeiroId,"")
                .then()
                .statusCode(403);
    }
}