package afrikpay.otp.services;

import afrikpay.otp.entity.OtpEntity;
import afrikpay.otp.repository.OtpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OtpServiceTest {

    @Autowired
    private OtpService otpService;
    @Autowired
    private OtpRepository otpRepository;

    private final Long expiryInterval = 5L * 60 * 1000;

    @Test
    public void createOtp() {
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtpId("5894362105876431");
        otpEntity.setCode(98192286);
        otpEntity.setReference(98192286);
        otpEntity.setCreationDate(new Date());
        otpEntity.setExpirationDate((new Date(System.currentTimeMillis() + expiryInterval)));

//        OtpEntity saveOtpEntity = otpRepository.save(otpEntity);

        OtpEntity otpGenerate = otpService.createOtp("237698192286");

        assertNotNull(otpGenerate);
        assertNotNull(otpGenerate.getId());
        assertNotEquals(otpEntity.getOtpId(), otpGenerate.getOtpId());
        assertEquals(otpEntity.getCode(), otpGenerate.getCode());
        assertEquals(otpEntity.getExpirationDate(), otpGenerate.getExpirationDate());
        assertEquals(otpEntity.getCreationDate(), otpGenerate.getCreationDate());
        assertEquals(otpEntity.getReference(), otpGenerate.getReference());
    }

    @Test
    public void isValide() {

        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtpId("5894362105876430");
        otpEntity.setCode(98192286);
        otpEntity.setReference(98192286);
        otpEntity.setCreationDate(new Date());
        otpEntity.setExpirationDate((new Date(System.currentTimeMillis() + expiryInterval)));

        otpRepository.save(otpEntity);
        assertEquals(otpService.isValide(98192286, "5894362105876430"), true);
    }
}