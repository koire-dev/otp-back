package afrikpay.otp.services;
import afrikpay.otp.dto.GenerateOTPRequest;
import afrikpay.otp.dto.GenerateOTPResponse;
import afrikpay.otp.dto.ValidateOTPRequest;
import afrikpay.otp.dto.ValidateOTPResponse;
import afrikpay.otp.entity.OtpEntity;
import afrikpay.otp.repository.OtpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OtpServiceTest {

    @Mock
    private OtpRepository otpRepository;

    @Mock
    private FormatService formatService;

    @Mock
    private GenerateOtpService generateOtpService;

    @InjectMocks
    private OtpService otpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateOTP() {
        // Données de test
        GenerateOTPRequest generateOTPRequest = new GenerateOTPRequest();
        generateOTPRequest.setTelephone("237612345678");

        // Mock du service de format
        when(formatService.isFormat(any(String.class))).thenReturn(true);

        // Mock du service de génération d'OTP
        when(generateOtpService.generateOtp()).thenReturn("generated-otpid");

        // Mock du repository
        when(otpRepository.save(any(OtpEntity.class))).thenReturn(new OtpEntity());

        // Exécution du service
        GenerateOTPResponse response = otpService.generateOTP(generateOTPRequest);

        // Vérifications
        assertNotNull(response);
        assertEquals("generated-otpid", response.getResult());
    }

    @Test
    void testValidateOTP() {
        // Données de test
        ValidateOTPRequest validateOTPRequest = new ValidateOTPRequest();
        validateOTPRequest.setCode(12345678);
        validateOTPRequest.setOtpid("generated-otpid");

        // Mock du repository
        when(otpRepository.findByCodeAndOtpid(any(Integer.class), any(String.class)))
                .thenReturn(Optional.of(new OtpEntity()));

        // Exécution du service
        ValidateOTPResponse response = otpService.validateOTP(validateOTPRequest);

        // Vérifications
        assertNotNull(response);
        assertEquals("success", response.getResult());
    }
}
