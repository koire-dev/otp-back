package afrikpay.otp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer reference;

    @Column(nullable = false, unique = true)
    private String otpId;

    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    private Date creationDate = new Date();

    @Column(nullable = false)
    private Date expirationDate;
}
