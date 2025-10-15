package com.example.nassau.controller;

import com.example.nassau.model.Plataforma;
import com.example.nassau.model.Usuario;
import com.example.nassau.model.Postagem;
import com.example.nassau.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plataforma")
public class PlataformaController {

    @Autowired
    private PlataformaService plataformaService;

    //Com certeza vai ter mais coisa na plataforma
// Retorna a plataforma principal
    @GetMapping
    public ResponseEntity<Plataforma> getPlataforma() {
        Plataforma plataforma = plataformaService.getPlataforma();
        return ResponseEntity.ok(plataforma);
    }

    // Lista todos os usuários da plataforma
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = plataformaService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Adiciona um usuário à plataforma
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = plataformaService.adicionarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    // Lista todas as postagens da plataforma
    @GetMapping("/postagens")
    public ResponseEntity<List<Postagem>> listarPostagens() {
        List<Postagem> postagens = plataformaService.listarPostagens();
        return ResponseEntity.ok(postagens);
    }
}
