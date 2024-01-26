package afrikpay.otp.controller;

import afrikpay.otp.dto.OtpDto;
import afrikpay.otp.entity.OtpEntity;
import afrikpay.otp.services.OtpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/otp", produces = APPLICATION_JSON_VALUE)
public class OtpController {

    private OtpService otpService;
    private ModelMapper modelMapper;

    @Autowired
    public OtpController(OtpService otpService, ModelMapper modelMapper){
        this.otpService = otpService;
        this.modelMapper = modelMapper;
    }

//    Generer un otpId
    @GetMapping("/generateOtpAndSendMessageTo")
    private ResponseEntity<OtpDto> generateOtpAndSendMessage(@RequestParam String telephone){

        OtpEntity otpEntity = otpService.createOtp(telephone);
        if (otpEntity == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(otpEntity, OtpDto.class), HttpStatus.OK);
    }

//    Verifier la validite d'un otp
    @GetMapping("/isValideOtp/code/{code}/otpId")
    public ResponseEntity<String> isValideOtp(@PathVariable int code, @RequestParam("valeur") String otpId){

        boolean isValide = otpService.isValide(code, otpId);
        if (isValide == true){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }
}
