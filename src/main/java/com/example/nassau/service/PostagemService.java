package com.example.nassau.service;

import com.example.nassau.model.Plataforma;
import com.example.nassau.model.Postagem;
import com.example.nassau.model.Usuario;
import com.example.nassau.repository.PostagemRepository;
import com.example.nassau.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    // Salvar uma nova postagem
    public Postagem salvarPostagem(Postagem postagem, Usuario autor) {
        postagem.setAutor(autor);
        postagem.setDataCriacao(LocalDateTime.now());

        // Vincula à plataforma principal
        Plataforma plataforma = plataformaRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    Plataforma p = new Plataforma();
                    p.setNome("Plataforma Principal");
                    return plataformaRepository.save(p);
                });
        postagem.setPlataforma(plataforma);
        plataforma.getPostagens().add(postagem);
        plataformaRepository.save(plataforma);

        return postagemRepository.save(postagem);
    }

    // Atualizar uma postagem existente
    public Postagem atualizarPostagem(Long id, String novoConteudo) {
        Optional<Postagem> optionalPostagem = postagemRepository.findById(id);
        if (optionalPostagem.isPresent()) {
            Postagem postagem = optionalPostagem.get();
            postagem.setConteudo(novoConteudo);
            return postagemRepository.save(postagem);
        }
        return null;
    }

    // Listar todas as postagens da plataforma
    public List<Postagem> listarPostagens() {
        return plataformaRepository.findAll().stream()
                .flatMap(p -> p.getPostagens().stream())
                .toList();
    }

    // Buscar postagem por ID
    public Postagem buscarPorId(Long id) {
        return postagemRepository.findById(id).orElse(null);
    }

    public Postagem buscarPorNome(String titulo){
        return (Postagem) postagemRepository.findByTitulo(titulo).orElse(null);
    }

    // Deletar postagem
    public void deletarPostagem(Long id) {
        postagemRepository.deleteById(id);
    }
}
