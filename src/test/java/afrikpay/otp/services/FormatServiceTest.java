package afrikpay.otp.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatServiceTest {

    private final FormatService formatService = new FormatService();

    @Test
    void testIsFormatValid() {
        // Numéro de téléphone au format valide
        assertTrue(formatService.isFormat("237612345678"));

        // Numéro de téléphone avec une longueur incorrecte
        assertFalse(formatService.isFormat("23761234567"));

        // Numéro de téléphone avec un code incorrect
        assertFalse(formatService.isFormat("123612345678"));
    }
}