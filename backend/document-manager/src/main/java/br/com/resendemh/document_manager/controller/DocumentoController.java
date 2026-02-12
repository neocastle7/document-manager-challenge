package br.com.resendemh.document_manager.controller;

import br.com.resendemh.document_manager.model.Documento;
import br.com.resendemh.document_manager.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class DocumentoController {

    @Autowired
    private DocumentoService service;

    @PostMapping("/upload")
    public ResponseEntity<Documento> upload(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao) {
        return ResponseEntity.ok(service.salvar(arquivo, titulo, descricao));
    }

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<Documento> adicionarComentario(
            @PathVariable Long id,
            @RequestBody String texto) {
        String textoLimpo = texto.replace("\"", "");
        return ResponseEntity.ok(service.adicionarComentario(id, textoLimpo));
    }

    @GetMapping
    public List<Documento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // Isso aqui resolve o código estranho!
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/download-forced/{filename:.+}")
    public ResponseEntity<Resource> downloadForced(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDocumento(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-comentario/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable Long id) {
        service.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
