package com.library.model.request;

import com.library.validation.ValidEmail;
import com.library.validation.ValidMobileNumber;
import com.library.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterRequest {
    @NotEmpty(message = "first name cannot be empty!")
    private String firstName;
    @NotEmpty(message = "last name cannot be empty!")
    private String lastName;
    @NotEmpty(message = "nick name cannot be empty!")
    private String nickName;
    @ValidEmail
    private String email;
    @ValidPassword
    private String password;
    @ValidMobileNumber
    private String mobileNumber;
}
