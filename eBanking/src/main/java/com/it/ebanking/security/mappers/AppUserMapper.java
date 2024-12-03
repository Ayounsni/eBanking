package com.it.ebanking.security.mappers;


import com.it.ebanking.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.UpdateAppUserDTO;
import com.it.ebanking.security.entities.AppUser;
import com.it.ebanking.security.mappers.helpers.AppRoleMapperHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {AppRoleMapperHelper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppUserMapper {

    @Mapping(target = "role", source = "roleId")
    AppUser toEntity(CreateAppUserDTO createDTO);

    @Mapping(target = "role", source = "roleId")
    AppUser updateEntityFromDTO(UpdateAppUserDTO updateAppUserDTO, @MappingTarget AppUser entity);

    ResponseAppUserDTO toDTO(AppUser entity);
}
