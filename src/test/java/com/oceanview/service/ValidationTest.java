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


}
