package com.example.nassau.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String titulo;

    private String conteudo;

    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "plataforma_id")
    private Plataforma plataforma;
}