package br.com.resendemh.document_manager.service;

import br.com.resendemh.document_manager.model.Documento;
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

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
                System.out.println("Pasta uploads criada com sucesso!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar a pasta de uploads: " + e.getMessage());
        }
    }

    public Documento salvar(MultipartFile arquivo, String titulo, String descricao) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String originalFilename = arquivo.getOriginalFilename();
            if (originalFilename == null || !originalFilename.contains(".")) {
                throw new RuntimeException("Arquivo inválido ou sem extensão.");
            }

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
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public List<Documento> listarTodos() {
        return repository.findAll();
    }
}