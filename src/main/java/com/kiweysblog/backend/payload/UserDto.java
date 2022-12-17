package com.kiweysblog.backend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4, message = "First name must be greater than 3 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 4, message = "Last name must be greater than 3 characters")
    private String lastName;
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"+"[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid Email")
    private String email;
    @NotEmpty
    @Size(min = 4, message = "Username name must be greater than 3 characters")
    private String username;
    @NotEmpty
    @Size(min = 6, message = "Password must be greater than 6 characters")
    private String password;
}