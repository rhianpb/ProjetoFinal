package com.example.nassau.service;

import com.example.nassau.model.Comentario;
import com.example.nassau.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // Listar todos os comentários
    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    // Buscar comentário por ID
    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    // Salvar novo comentário
    public Comentario salvar(Comentario comentario) {
        // Evita sobrescrever algo existente
        if (comentario.getId() != null && comentarioRepository.existsById(comentario.getId())) {
            throw new RuntimeException("Comentário com este ID já existe. Use atualizar() para modificá-lo.");
        }
        return comentarioRepository.save(comentario);
    }

    // Atualizar comentário
    public Comentario atualizar(Long id, Comentario comentarioAtualizado) {
        Comentario comentarioExistente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado com ID: " + id));

        comentarioExistente.setConteudo(comentarioAtualizado.getConteudo());
        comentarioExistente.setAutor(comentarioAtualizado.getAutor());
        comentarioExistente.setDataCriacao(comentarioAtualizado.getDataCriacao());

        return comentarioRepository.save(comentarioExistente);
    }

    // Excluir comentário
    public void deletar(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentário não encontrado com ID: " + id);
        }
        comentarioRepository.deleteById(id);
    }
}
