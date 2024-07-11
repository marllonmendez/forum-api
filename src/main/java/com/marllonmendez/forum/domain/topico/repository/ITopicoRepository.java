package com.marllonmendez.forum.domain.topico.repository;

import com.marllonmendez.forum.domain.topico.entity.Topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT COUNT(t) FROM Topicos t WHERE t.autor = :autor")
    long countByAutor(String autor);

    @Query("SELECT COUNT(t) FROM Topicos t WHERE t.titulo = :titulo")
    long countByTitulo(String titulo);
}
