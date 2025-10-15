package com.example.nassau.repository;

import com.example.nassau.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository <Comentario, Long>{
}
