package com.it.ebanking.security.services.interfaces;

import com.it.ebanking.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.ebanking.security.entities.AppUser;

public interface IAppUserService {
    public ResponseAppUserDTO create(CreateAppUserDTO user);

}
