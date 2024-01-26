package afrikpay.otp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateOtpServiceTest {

    @Autowired
    private GenerateOtpService generateOtpService;

    @Test
    public void generateOtp() {
        assertNotEquals(generateOtpService.generateOtp(), "4534982632015894");
    }
}