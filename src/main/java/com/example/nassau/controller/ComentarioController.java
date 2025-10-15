package com.example.nassau.controller;

import com.example.nassau.model.Comentario;
import com.example.nassau.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Listar todos os comentários
    @GetMapping
    public ResponseEntity<List<Comentario>> listarTodos() {
        List<Comentario> comentarios = comentarioService.listarTodos();
        return ResponseEntity.ok(comentarios);
    }

    // Buscar comentário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id) {
        return comentarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar novo comentário
    @PostMapping
    public ResponseEntity<Comentario> salvar(@RequestBody Comentario comentario) {
        Comentario salvo = comentarioService.salvar(comentario);
        return ResponseEntity.ok(salvo);
    }

    // Atualizar comentário existente
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @RequestBody Comentario comentarioAtualizado) {
        Comentario atualizado = comentarioService.atualizar(id, comentarioAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    // Excluir comentário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comentarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}