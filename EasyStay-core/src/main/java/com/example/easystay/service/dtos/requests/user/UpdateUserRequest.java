package com.example.easystay.service.dtos.requests.user;

import com.example.easystay.core.exceptionhandling.exception.problemdetails.ErrorMessages;
import com.example.easystay.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private long id;

    @Size(min=0,max = 50,message = ErrorMessages.FIRST_NAME_SIZE_3_50)
    private String firstName;

    @Size(min=0,max = 50,message = ErrorMessages.LAST_NAME_SIZE_3_50)
    private String lastName;

    @Email(message = ErrorMessages.INVALID_EMAIL)
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$"
            ,message = ErrorMessages.REGEXP_FOR_PASS)
    private String password;

    @Pattern(regexp= "\\d+" , message = ErrorMessages.JUST_NUMERIC_CHAR)
    @Size(min = 10,max = 10, message = ErrorMessages.NUMBER_SIZE_10)
    private String phoneNumber;

    private Role role;
}
