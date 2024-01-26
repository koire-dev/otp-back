package afrikpay.otp.services;

import org.springframework.stereotype.Service;

@Service
public class FormatService {

    public boolean isFormat(String telephone){

        int code = Integer.parseInt(telephone.substring(0, telephone.length() - 8));;
        int taille = telephone.length();

        if ((taille == 12) && (code == 2376)){
            return true;
        }
        return false;
    }
}
