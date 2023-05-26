package com.library.model.request;

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
    private String email;
    private String password;
    private String mobileNumber;
}
