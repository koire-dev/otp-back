package afrikpay.otp.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateOtpServiceTest {

    private final GenerateOtpService generateOtpService = new GenerateOtpService();

    @Test
    void testGenerateOtp() {
        // Exécution du service pour générer un OTP
        String otp = generateOtpService.generateOtp();

        // Vérifications
        assertEquals(16, otp.length()); // Vérifie la longueur de l'OTP
        assertTrue(otp.matches("\\d{16}"), "L'OTP doit contenir uniquement des chiffres"); // Vérifie que l'OTP ne contient que des chiffres
    }
}