package br.com.resendemh.document_manager.service;

import br.com.resendemh.document_manager.model.Comentario;
import br.com.resendemh.document_manager.model.Documento;
import br.com.resendemh.document_manager.repository.ComentarioRepository;
import br.com.resendemh.document_manager.repository.DocumentoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentoService {

    private final Path root = Paths.get("uploads");

    @Autowired
    private DocumentoRepository repository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar a pasta de uploads.");
        }
    }

    public Documento salvar(MultipartFile arquivo, String titulo, String descricao) {
        try {
            String originalFilename = arquivo.getOriginalFilename();
            String extensao = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            String nomeArquivoFisico = System.currentTimeMillis() + extensao;

            Files.copy(arquivo.getInputStream(), this.root.resolve(nomeArquivoFisico));

            Documento doc = new Documento();
            doc.setTitulo(titulo);
            doc.setDescricao(descricao);
            doc.setNomeArquivo(nomeArquivoFisico);
            doc.setDataUpload(LocalDateTime.now());

            return repository.save(doc);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public Documento adicionarComentario(Long id, String texto) {
        Documento doc = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        Comentario novoComentario = new Comentario();
        novoComentario.setTexto(texto);
        novoComentario.setDataHora(LocalDateTime.now());
        novoComentario.setDocumento(doc);

        doc.getComentarios().add(novoComentario);
        return repository.save(doc);
    }

    public List<Documento> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.findById(id).ifPresent(doc -> {
            try {
                Files.deleteIfExists(this.root.resolve(doc.getNomeArquivo()));
            } catch (IOException e) {
                System.err.println("Erro ao apagar arquivo físico.");
            }
            repository.deleteById(id);
        });
    }

    public void deletarComentario(Long comentarioId) {
        comentarioRepository.deleteById(comentarioId);
    }
}