package afrikpay.otp.services;

import afrikpay.otp.entity.OtpEntity;
import afrikpay.otp.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OtpService {

    private final Long expiryInterval = 5L * 60 * 1000;

    private OtpRepository otpRepository;
    private FormatService formatService;
    private GenerateOtpService generateOtpService;

    @Autowired
    public OtpService(OtpRepository otpRepository, FormatService formatService, GenerateOtpService generateOtpService) {
        this.otpRepository = otpRepository;
        this.formatService = formatService;
        this.generateOtpService = generateOtpService;
    }

    public OtpEntity createOtp(String telephone){

        if (formatService.isFormat(telephone) == true){
            OtpEntity otpEntity = new OtpEntity();
//        code et reference vont garder les 8 derniers chiffres du numero de telephone, donc excepter 2376
            int code = Integer.parseInt(telephone.substring(telephone.length() - 8));

            String otpId = generateOtpService.generateOtp();

            otpEntity.setOtpId(otpId);
            otpEntity.setExpirationDate(new Date(System.currentTimeMillis() + expiryInterval));
            otpEntity.setCode(code);
            otpEntity.setReference(code);

            return otpRepository.save(otpEntity);
        }
        return null;
    }

    public boolean isValide(int code, String otpId){

        boolean result = false;
        for (OtpEntity otpEntity : otpRepository.findAll()){
            if((otpEntity.getCode().equals(code)) && (otpEntity.getOtpId().equals(otpId))){
                result = true;
            }
        }
        return result;
    }
}
