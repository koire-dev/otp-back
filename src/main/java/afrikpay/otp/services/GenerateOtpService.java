package afrikpay.otp.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateOtpService {

    private final static Integer LENGTH = 16;

//    Cette methode permet de generer un otp de taille 16 de maniere aleatoire
    public String generateOtp(){

        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int randomNumber = random.nextInt(10);
            otp.append(randomNumber);
        }
        return otp.toString().trim();
    }
}
