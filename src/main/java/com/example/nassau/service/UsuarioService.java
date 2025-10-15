package com.example.nassau.service;

import com.example.nassau.model.Plataforma;
import com.example.nassau.model.Usuario;
import com.example.nassau.repository.PlataformaRepository;
import com.example.nassau.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    // Salvar um novo usuário e vincular à plataforma
    public Usuario salvarUsuario(Usuario usuario) {
        // Busca a plataforma principal, ou cria se não existir
        Plataforma plataforma = plataformaRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    Plataforma p = new Plataforma();
                    p.setNome("Plataforma Principal");
                    return plataformaRepository.save(p);
                });

        usuario.setPlataforma(plataforma);
        plataforma.getUsuarios().add(usuario);
        plataformaRepository.save(plataforma);

        return usuarioRepository.save(usuario);
    }

    // Atualizar dados do usuário
    public Usuario atualizarUsuario(Long id, Usuario dadosAtualizados) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(dadosAtualizados.getNome());
            usuario.setEmail(dadosAtualizados.getEmail());
            usuario.setSenha(dadosAtualizados.getSenha());
            usuario.setAvatar(dadosAtualizados.getAvatar());
            return usuarioRepository.save(usuario);
        }
        return null; // ou lançar exceção
    }

    // Buscar usuário por ID
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Buscar usuário por email (para login)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    // Listar todos os usuários da plataforma
    public List<Usuario> listarUsuarios() {
        return plataformaRepository.findAll().stream()
                .flatMap(p -> p.getUsuarios().stream())
                .toList();
    }

    // Deletar usuário
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
