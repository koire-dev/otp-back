package afrikpay.otp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormatServiceTest {

    @Autowired
    private FormatService formatService;

    @Test
    public void isValideWithSucces(){
        assertEquals(formatService.isFormat("237698192286"), true);
    }
}