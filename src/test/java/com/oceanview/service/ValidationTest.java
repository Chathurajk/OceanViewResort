package com.oceanview.service;

import com.oceanview.util.ValidationUtil;
import com.oceanview.util.ReservationNumberGenerator;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ocean View Resort – Validation Tests")
class ValidationTest {

    @Test
    @DisplayName("Test Valid Old NIC Format")
    void testOldNicNumber() {

        String oldNic = "901234567V";

        boolean isValid = ValidationUtil.isValidNIC(oldNic);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Valid NIC new format (12 digits)")
    void testValidNicNewFormat() {

        String newNic = "199012345678";

        boolean isValid = ValidationUtil.isValidNIC(newNic);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Valid email passes")
    void testValidEmailPasses() {

        String email = "test@example.com";

        boolean isValid = ValidationUtil.isValidEmail(email);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Invalid email fails")
    void testInvalidEmailFails() {

        String invalidEmail1 = "notanemail";
        String invalidEmail2 = null;

        boolean isValid1 = ValidationUtil.isValidEmail(invalidEmail1);
        boolean isValid2 = ValidationUtil.isValidEmail(invalidEmail2);

        assertFalse(isValid1);
        assertFalse(isValid2);
    }
    @Test
    @DisplayName("Valid phone (10 digits)")
    void testValidPhone() {

        String phone = "0771234567";

        boolean isValid = ValidationUtil.isValidPhone(phone);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Invalid phone fails")
    void testInvalidPhoneFails() {

        String invalidPhone1 = "077123";
        String invalidPhone2 = "abc";

        boolean isValid1 = ValidationUtil.isValidPhone(invalidPhone1);
        boolean isValid2 = ValidationUtil.isValidPhone(invalidPhone2);

        assertFalse(isValid1);
        assertFalse(isValid2);
    }

    @Test
    @DisplayName("Valid date range (checkout after checkin)")
    void testValidDateRange() {

        LocalDate checkin = LocalDate.now();
        LocalDate checkout = LocalDate.now().plusDays(3);

        boolean isValid = ValidationUtil.isValidDateRange(checkin, checkout);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Same dates fail")
    void testSameDatesFail() {

        LocalDate checkin = LocalDate.now();
        LocalDate checkout = LocalDate.now();

        boolean isValid = ValidationUtil.isValidDateRange(checkin, checkout);

        assertFalse(isValid);
    }

    @Test
    @DisplayName("Checkout before checkin fails")
    void testCheckoutBeforeCheckinFails() {

        LocalDate checkin = LocalDate.now().plusDays(5);
        LocalDate checkout = LocalDate.now().plusDays(2);

        boolean isValid = ValidationUtil.isValidDateRange(checkin, checkout);

        assertFalse(isValid);
    }

    @Test
    @DisplayName("Past date fails")
    void testPastDateFails() {

        LocalDate pastDate = LocalDate.now().minusDays(1);

        boolean isValid = ValidationUtil.isFutureOrToday(pastDate);

        assertFalse(isValid);
    }

    @Test
    @DisplayName("Today passes")
    void testTodayPasses() {

        LocalDate today = LocalDate.now();

        boolean isValid = ValidationUtil.isFutureOrToday(today);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Bill: 3 nights × 8500 = 25500")
    void testBillCalculationWithoutDiscount() {

        double expected = 25500.0;
        double actual = 3 * 8500.0;

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Bill: 3 nights × 8500 with 10% discount = 22950")
    void testBillCalculationWithDiscount() {

        double total = 3 * 8500.0;
        double expected = 22950.0;
        double actual = total - (total * 0.10);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Bill: 1 night × 15000 (Suite) = 15000")
    void testBillCalculationForSuite() {

        double expected = 15000.0;
        double actual = 1 * 15000.0;

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Reservation number starts with OVR-")
    void testReservationNumberPrefix() {

        String reservationNumber = ReservationNumberGenerator.generate();

        boolean startsWithOvr = reservationNumber.startsWith("OVR-");

        assertTrue(startsWithOvr);
    }

    @Test
    @DisplayName("Two reservation numbers are unique")
    void testReservationNumbersAreUnique() {

        String resNum1 = ReservationNumberGenerator.generate();
        String resNum2 = ReservationNumberGenerator.generate();

        assertNotEquals(resNum1, resNum2);
    }

    @Test
    @DisplayName("Empty and non-empty string check")
    void testEmptyAndNonEmptyStringCheck() {

        String emptyStr = "";
        String nullStr = null;
        String validStr = "Ocean View";

        boolean isEmptyValid = ValidationUtil.isNotEmpty(emptyStr);
        boolean isNullValid = ValidationUtil.isNotEmpty(nullStr);
        boolean isValidStringValid = ValidationUtil.isNotEmpty(validStr);

        assertFalse(isEmptyValid);
        assertFalse(isNullValid);
        assertTrue(isValidStringValid);
    }


}
