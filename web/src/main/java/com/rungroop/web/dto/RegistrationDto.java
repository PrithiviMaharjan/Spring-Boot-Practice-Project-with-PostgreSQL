package com.rungroop.web.dto;

import jakarta.validation.constraints.NotEmpty;

public class RegistrationDto {
    private Long id;

    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
