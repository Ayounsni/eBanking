package com.it.ebanking.security.dtos.AppUserDTO;

import com.it.ebanking.security.entities.AppRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAppUserDTO {

    private Long roleId;

}
