package com.marllonmendez.forum.domain.topico.entity;

import com.marllonmendez.forum.domain.topico.dto.AtualizacaoTopicoDTO;
import com.marllonmendez.forum.domain.topico.dto.CadastroTopicoDTO;

import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topicos")
@Entity(name = "Topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    private String mensagem;

    private String autor;

    private String curso;

    private LocalDateTime data_criacao;

    private boolean ativo;

    public Topico(CadastroTopicoDTO dto) {
        this.titulo = dto.titulo();
        this.mensagem = dto.mensagem();
        this.autor = dto.autor();
        this.curso = dto.curso();
        this.data_criacao = LocalDateTime.now();
        this.ativo = true;
    }

    public void atualizarTopico(AtualizacaoTopicoDTO dto) {
        this.titulo = dto.titulo() != null ? dto.titulo() : this.titulo;
        this.mensagem = dto.mensagem() != null ? dto.mensagem() : this.mensagem;
        this.autor = dto.autor() != null ? dto.autor() : this.autor;
        this.curso = dto.curso() != null ? dto.curso() : this.curso;
    }

    public void removerTopico() {
        this.ativo = false;
    }

}