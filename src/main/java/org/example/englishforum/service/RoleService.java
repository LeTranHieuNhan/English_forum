package org.example.englishforum.service;

import org.example.englishforum.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAllRoles();
    RoleDto findRoleById(Long id);
    RoleDto createRole(String name);
    void deleteRole(Long id);
    RoleDto updateRole(Long id, String name);
}
