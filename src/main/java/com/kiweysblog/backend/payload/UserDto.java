package com.kiweysblog.backend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}