package com.it.ebanking.security.services.implementations;

import com.it.ebanking.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.UpdateAppUserDTO;
import com.it.ebanking.security.entities.AppRole;
import com.it.ebanking.security.entities.AppUser;
import com.it.ebanking.security.mappers.AppUserMapper;
import com.it.ebanking.security.repositories.AppRoleRepository;
import com.it.ebanking.security.repositories.AppUserRepository;
import com.it.ebanking.security.services.interfaces.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService implements IAppUserService {

    public final AppUserRepository appUserRepository;
    public final AppRoleRepository appRoleRepository;
    public final AppUserMapper appUserMapper;
    public final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseAppUserDTO create(CreateAppUserDTO createAppUserDTO) {
        if (appUserRepository.findByUsername(createAppUserDTO.getUsername()).isPresent()) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }
        AppUser user = appUserMapper.toEntity(createAppUserDTO);
        user.setPassword(passwordEncoder.encode(createAppUserDTO.getPassword()));
        return appUserMapper.toDTO(appUserRepository.save(user)) ;
    }

    @Override
    public List<ResponseAppUserDTO> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return users.stream().map(appUserMapper::toDTO).toList();
    }

    @Override
    public void deleteUser(String username){
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        if(userAuth.equals(username)) {
            throw new IllegalArgumentException("Vous ne pouvez pas supprimer votre propre compte.");
        }
        appUserRepository.delete(user);

    }

    @Override
    public ResponseAppUserDTO updateUser(String username ,UpdateAppUserDTO updateAppUserDTO) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));
        AppRole role = appRoleRepository.findById(updateAppUserDTO.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Ce rôle n'existe pas."));
        AppUser updatedAppUser = appUserMapper.updateEntityFromDTO(updateAppUserDTO, user);
        updatedAppUser = appUserRepository.save(updatedAppUser);
        return appUserMapper.toDTO(updatedAppUser);
    }


    @Override
    public ResponseAppUserDTO getByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));

        return appUserMapper.toDTO(user);
    }
}
