package com.fiap.calorias.repository;

import com.fiap.calorias.DTO.UsuarioExibicaoDTO;
import com.fiap.calorias.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Optional<Usuario> findByEmail(String email);

}
