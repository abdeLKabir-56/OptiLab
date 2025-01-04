package com.optilab.utilisateur.mappers;

import com.optilab.utilisateur.dto.request.RoleRequest;
import com.optilab.utilisateur.dto.response.RoleResponse;
import com.optilab.utilisateur.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);
    Role toEntity (RoleRequest roleRequest);
    RoleResponse toResponse (Role role);

}
