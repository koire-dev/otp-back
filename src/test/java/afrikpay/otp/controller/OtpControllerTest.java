package afrikpay.otp.controller;

import afrikpay.otp.dto.GenerateOTPRequest;
import afrikpay.otp.dto.GenerateOTPResponse;
import afrikpay.otp.dto.ValidateOTPRequest;
import afrikpay.otp.dto.ValidateOTPResponse;
import afrikpay.otp.services.OtpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OtpControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OtpService otpService;

    @InjectMocks
    private OtpController otpController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(otpController).build();
    }

    @Test
    public void testGenerateOTP() throws Exception {
        // Données de test
        GenerateOTPRequest generateOTPRequest = new GenerateOTPRequest();
        generateOTPRequest.setTelephone("2376XXXXXXXX");

        GenerateOTPResponse generateOTPResponse = new GenerateOTPResponse();
        generateOTPResponse.setResult("generated-otpid");

        // Mock du service
        when(otpService.generateOTP(any(GenerateOTPRequest.class))).thenReturn(generateOTPResponse);

        // Exécution de la requête HTTP
        mockMvc.perform(post("/otp/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(generateOTPRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("generated-otpid"));
    }

    @Test
    public void testValidateOTP() throws Exception {
        // Données de test
        ValidateOTPRequest validateOTPRequest = new ValidateOTPRequest();
        validateOTPRequest.setCode(12345678);
        validateOTPRequest.setOtpid("generated-otpid");

        ValidateOTPResponse validateOTPResponse = new ValidateOTPResponse();
        validateOTPResponse.setResult("success");

        // Mock du service
        when(otpService.validateOTP(any(ValidateOTPRequest.class))).thenReturn(validateOTPResponse);

        // Exécution de la requête HTTP
        mockMvc.perform(get("/otp/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(validateOTPRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    // Méthode utilitaire pour convertir un objet en JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
