package afrikpay.otp.services;

import afrikpay.otp.dto.GenerateOTPRequest;
import afrikpay.otp.dto.GenerateOTPResponse;
import afrikpay.otp.dto.ValidateOTPRequest;
import afrikpay.otp.dto.ValidateOTPResponse;
import afrikpay.otp.entity.OtpEntity;
import afrikpay.otp.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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

    public GenerateOTPResponse generateOTP(GenerateOTPRequest request) {

        String telephone = request.getTelephone();
        if (formatService.isFormat(telephone) == true){
            OtpEntity otpEntity = new OtpEntity();
//        code et reference vont garder les 8 derniers chiffres du numero de telephone, donc excepter 2376
            int code = Integer.parseInt(telephone.substring(telephone.length() - 8));

            String otpId = generateOtpService.generateOtp();

            otpEntity.setOtpid(otpId);
            otpEntity.setExpirationDate(new Date(System.currentTimeMillis() + expiryInterval));
            otpEntity.setCode(code);
            otpEntity.setReference(code);
            otpRepository.save(otpEntity);

            GenerateOTPResponse response = new GenerateOTPResponse();
            response.setResult(otpEntity.getOtpid());
            return response;
        }
        return null;
    }

    public ValidateOTPResponse validateOTP(ValidateOTPRequest request) {
        Optional<OtpEntity> otpOptional = otpRepository.findByCodeAndOtpid(request.getCode(), request.getOtpid());

        ValidateOTPResponse response = new ValidateOTPResponse();
        response.setResult(otpOptional.isPresent() ? "success" : "failed");
        return response;
    }

}
