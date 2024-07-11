package com.marllonmendez.forum.domain.usuario.repository;

import com.marllonmendez.forum.domain.usuario.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
