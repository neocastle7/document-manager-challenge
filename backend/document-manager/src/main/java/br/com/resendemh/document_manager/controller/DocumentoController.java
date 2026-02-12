package br.com.resendemh.document_manager.controller;

import br.com.resendemh.document_manager.model.Documento;
import br.com.resendemh.document_manager.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentoController {

    @Autowired
    private DocumentoService service;

    @PostMapping("/upload")
    public ResponseEntity<Documento> upload(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao) {
        // Método para atender o requisito de upload de documentos (PDF, JPG, PNG)
        return ResponseEntity.ok(service.salvar(arquivo, titulo, descricao));
    }

    @GetMapping
    public List<Documento> listar() {
        // Método para atender o requisito de listagem de documentos
        return service.listarTodos();
    }
}