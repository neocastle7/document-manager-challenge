package br.com.resendemh.document_manager.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String nomeArquivo;
    private LocalDateTime dataUpload;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();
}