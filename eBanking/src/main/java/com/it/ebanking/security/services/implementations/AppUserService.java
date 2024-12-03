package com.it.ebanking.security.services.implementations;

import com.it.ebanking.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.ebanking.security.entities.AppUser;
import com.it.ebanking.security.mappers.AppUserMapper;
import com.it.ebanking.security.repositories.AppUserRepository;
import com.it.ebanking.security.services.interfaces.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements IAppUserService {

    public final AppUserRepository appUserRepository;
    public final AppUserMapper appUserMapper;
    public final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseAppUserDTO create(CreateAppUserDTO createAppUserDTO) {
        if (appUserRepository.findByUsername(createAppUserDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        AppUser user = appUserMapper.toEntity(createAppUserDTO);
        user.setPassword(passwordEncoder.encode(createAppUserDTO.getPassword()));
        return appUserMapper.toDTO(appUserRepository.save(user)) ;
    }
}
