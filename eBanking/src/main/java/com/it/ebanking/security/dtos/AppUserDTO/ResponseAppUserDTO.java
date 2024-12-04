package com.it.ebanking.security.dtos.AppUserDTO;

import com.it.ebanking.security.dtos.AppRoleDTO.EmbeddableAppRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAppUserDTO {

    private Long id;

    private String username;

    private EmbeddableAppRoleDTO role;
}
