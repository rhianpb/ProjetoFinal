package com.example.nassau.controller;

import com.example.nassau.model.Postagem;
import com.example.nassau.model.Usuario;
import com.example.nassau.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    // Salvar nova postagem
    @PostMapping
    public ResponseEntity<Postagem> criarPostagem(@RequestBody Postagem postagem, @RequestParam Long autorId) {
        // Aqui você precisaria buscar o Usuario pelo ID usando UsuarioService
        Usuario autor = new Usuario();
        autor.setId(autorId);
        Postagem novaPostagem = postagemService.salvarPostagem(postagem, autor);
        return ResponseEntity.ok(novaPostagem);
    }

    // Atualizar postagem existente
    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizarPostagem(@PathVariable Long id, @RequestBody String novoConteudo) {
        Postagem postagemAtualizada = postagemService.atualizarPostagem(id, novoConteudo);
        if (postagemAtualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagemAtualizada);
    }

    // Listar todas as postagens da plataforma
    @GetMapping
    public ResponseEntity<List<Postagem>> listarPostagens() {
        List<Postagem> postagens = postagemService.listarPostagens();
        return ResponseEntity.ok(postagens);
    }

    // Buscar postagem por ID
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPorId(@PathVariable Long id) {
        Postagem postagem = postagemService.buscarPorId(id);
        if (postagem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagem);
    }

    // Buscar postagem por título
    @GetMapping("/titulo")
    public ResponseEntity<Postagem> buscarPorTitulo(@RequestParam String titulo) {
        Postagem postagem = postagemService.buscarPorNome(titulo);
        if (postagem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagem);
    }

    // Deletar postagem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(@PathVariable Long id) {
        postagemService.deletarPostagem(id);
        return ResponseEntity.noContent().build();
    }
}
