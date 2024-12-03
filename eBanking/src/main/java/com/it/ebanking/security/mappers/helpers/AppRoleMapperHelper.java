package com.it.ebanking.security.mappers.helpers;

import com.it.ebanking.security.entities.AppRole;
import com.it.ebanking.security.repositories.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppRoleMapperHelper {

    @Autowired
    private AppRoleRepository appRoleRepository;

    public AppRole mapAppRoleIdToAppRole(Long appRoleId) {
        return appRoleRepository.findById(appRoleId).orElse(null);
    }
}

