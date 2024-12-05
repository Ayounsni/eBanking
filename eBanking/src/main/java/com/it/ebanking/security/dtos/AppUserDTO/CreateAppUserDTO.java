package com.it.ebanking.security.dtos.AppUserDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Min(8)
    private String password;

    private Long roleId =1L;
}
