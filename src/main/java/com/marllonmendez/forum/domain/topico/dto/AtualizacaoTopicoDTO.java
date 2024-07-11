package com.marllonmendez.forum.domain.topico.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoTopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso) {
}
