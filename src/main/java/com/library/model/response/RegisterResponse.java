package com.library.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private String mobileNumber;
}
