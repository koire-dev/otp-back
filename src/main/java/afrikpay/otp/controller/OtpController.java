package afrikpay.otp.controller;

import afrikpay.otp.dto.*;
import afrikpay.otp.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/otp",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OtpController {

    private OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService){
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public ResponseEntity<GenerateOTPResponse> generateOTP(@RequestBody GenerateOTPRequest request) {
        GenerateOTPResponse response = otpService.generateOTP(request);
        if (response == null){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateOTPResponse> validateOTP(@RequestBody ValidateOTPRequest request) {
        ValidateOTPResponse response = otpService.validateOTP(request);
        if (response == null){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
