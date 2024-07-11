package com.marllonmendez.forum.domain.topico.dto;

import com.marllonmendez.forum.domain.topico.entity.Topico;

import java.time.LocalDateTime;

public record ListaTopicoDTO(String titulo, String mensagem, String autor, String curso, LocalDateTime data_criacao, boolean ativo) {

    public ListaTopicoDTO(Topico topico){
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso(), topico.getData_criacao(), topico.isAtivo());
    }

}
