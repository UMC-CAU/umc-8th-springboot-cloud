package umc.spring.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    private Long id;
    private String email;
    private String nickname;
    private String name;
    private String gender;
    private String phoneNumber;
    private String status;
    private LocalDate inactiveDate;
    private String address;
    private LocalDateTime createdAt;
}