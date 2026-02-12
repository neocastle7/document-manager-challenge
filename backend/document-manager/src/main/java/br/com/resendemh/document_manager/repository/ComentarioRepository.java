package br.com.resendemh.document_manager.repository;

import br.com.resendemh.document_manager.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
