package com.it.ebanking.security.dtos.AppUserDTO;

import com.it.ebanking.security.dtos.AppRoleDTO.EmbeddableAppRoleDTO;
import com.it.ebanking.security.entities.AppRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAppUserDTO {

    private Long id;

    private String username;

    private String password;

    private EmbeddableAppRoleDTO role;
}
