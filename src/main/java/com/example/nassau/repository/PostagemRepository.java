package com.example.nassau.repository;

import com.example.nassau.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    Optional<Object> findByTitulo(String titulo);
}
