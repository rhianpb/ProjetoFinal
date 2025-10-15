package com.example.nassau.service;

import com.example.nassau.model.Plataforma;
import com.example.nassau.model.Usuario;
import com.example.nassau.model.Postagem;
import com.example.nassau.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService {

    @Autowired
    private PlataformaRepository plataformaRepository;

    // Retorna a plataforma principal (ou cria se não existir)
    public Plataforma getPlataforma() {
        return plataformaRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    Plataforma plataforma = new Plataforma();
                    plataforma.setNome("Plataforma Principal");
                    return plataformaRepository.save(plataforma);
                });
    }

    // Listar usuários da plataforma
    public List<Usuario> listarUsuarios() {
        return getPlataforma().getUsuarios();
    }

    // Adicionar usuário à plataforma
    public Usuario adicionarUsuario(Usuario usuario) {
        Plataforma plataforma = getPlataforma();
        usuario.setPlataforma(plataforma);
        plataforma.getUsuarios().add(usuario);
        plataformaRepository.save(plataforma);
        return usuario;
    }

    // Listar postagens da plataforma
    public List<Postagem> listarPostagens() {
        return getPlataforma().getPostagens();
    }
}
