package afrikpay.otp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateOTPRequest {
    private Integer code;
    private String otpid;
}
