package br.com.resendemh.document_manager.repository;

import br.com.resendemh.document_manager.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
