package com.marllonmendez.forum.controller;

import com.marllonmendez.forum.domain.topico.dto.AtualizacaoTopicoDTO;
import com.marllonmendez.forum.domain.topico.dto.CadastroTopicoDTO;
import com.marllonmendez.forum.domain.topico.dto.ListaTopicoDTO;
import com.marllonmendez.forum.domain.topico.entity.Topico;
import com.marllonmendez.forum.domain.topico.repository.ITopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópicos")
public class ForumController {

    @Autowired
    private ITopicoRepository iTopicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid CadastroTopicoDTO dto) {
        // Verifica se já existe um tópico com o mesmo autor
        long autorCount = iTopicoRepository.countByAutor(dto.autor());
        if (autorCount > 0) {
            throw new RuntimeException("Já existe um tópico cadastrado com o mesmo autor");
        }

        // Verifica se já existe um tópico com o mesmo título
        long tituloCount = iTopicoRepository.countByTitulo(dto.titulo());
        if (tituloCount > 0) {
            throw new RuntimeException("Já existe um tópico cadastrado com o mesmo título");
        }

        // Se não existir, pode cadastrar o novo tópico
        return ResponseEntity.ok(iTopicoRepository.save(new Topico(dto)));
    }

    @GetMapping
    public ResponseEntity<Page<ListaTopicoDTO>> ListarTopicos(Pageable pageable){
        var lista = iTopicoRepository.findAll(pageable).map(ListaTopicoDTO::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ListaTopicoDTO listarTopicoId(@PathVariable Long id) {
        Topico topico = iTopicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        return new ListaTopicoDTO(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@RequestBody @Valid AtualizacaoTopicoDTO dto){
        var topico = iTopicoRepository.getReferenceById(dto.id());
        topico.atualizarTopico(dto);
        return ResponseEntity.ok(new ListaTopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id) {
        var topico = iTopicoRepository.getReferenceById(id);
        topico.removerTopico();
        return ResponseEntity.noContent().build();
    }
}
