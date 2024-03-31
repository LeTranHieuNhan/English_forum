package org.example.englishforum.controller;

import org.example.englishforum.dto.RoleDto;
import org.example.englishforum.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoleControllerTest {

    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController roleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get all roles and return ok status")
    void getAllRoles() {
        List<RoleDto> roleDtoList = Collections.singletonList(new RoleDto());
        when(roleService.findAllRoles()).thenReturn(roleDtoList);

        ResponseEntity<List<RoleDto>> response = roleController.getAllRoles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roleDtoList, response.getBody());
    }

    @Test
    @DisplayName("Should get role by id and return ok status")
    void getRoleById() {
        RoleDto roleDto = new RoleDto();
        when(roleService.findRoleById(1L)).thenReturn(roleDto);

        ResponseEntity<RoleDto> response = roleController.getRoleById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roleDto, response.getBody());
    }

    @Test
    @DisplayName("Should create role and return created status")
    void createRole() {
        RoleDto roleDto = new RoleDto();
        when(roleService.createRole("Admin")).thenReturn(roleDto);

        ResponseEntity<RoleDto> response = roleController.createRole("Admin");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(roleDto, response.getBody());
    }

    @Test
    @DisplayName("Should delete role and return no content status")
    void deleteRole() {
        doNothing().when(roleService).deleteRole(1L);

        ResponseEntity<Void> response = roleController.deleteRole(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Should update role and return ok status")
    void updateRole() {
        RoleDto roleDto = new RoleDto();
        when(roleService.updateRole(1L, "Admin")).thenReturn(roleDto);

        ResponseEntity<RoleDto> response = roleController.updateRole(1L, "Admin");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roleDto, response.getBody());
    }
}
