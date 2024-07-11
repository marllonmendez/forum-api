package com.marllonmendez.forum.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroTopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
