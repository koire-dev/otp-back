package afrikpay.otp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenerateOTPRequest {
    private String telephone;
}
