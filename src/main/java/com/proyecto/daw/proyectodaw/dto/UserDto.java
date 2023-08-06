package com.proyecto.daw.proyectodaw.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Validated
public class UserDto {
    
    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres")
    private String userName;

    @NotEmpty(message = "La contraseña no puede estar vacía")    
    @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 y 20 caracteres")
    private String password;

    @NotEmpty(message = "El rol no puede estar vacío")
    @Size(min = 4, max = 20, message = "El rol debe tener entre 4 y 20 caracteres")
    private String role;

}
