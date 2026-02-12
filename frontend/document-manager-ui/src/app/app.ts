import { Component, OnInit, ChangeDetectorRef, ViewChild, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DocumentoService, Documento } from './services/documento';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  // Referência para o input de arquivo no HTML para resetá-lo fisicamente
  @ViewChild('fileInput') fileInput!: ElementRef;

  titulo: string = '';
  descricao: string = '';
  arquivoSelecionado: File | null = null;
  documentos: Documento[] = [];

  constructor(private service: DocumentoService, private cdr: ChangeDetectorRef) { }

  ngOnInit() {
    this.carregarDocumentos();
  }

  trackByFn(index: number, item: Documento) {
    return item.id;
  }

  carregarDocumentos() {
    this.service.listar().subscribe({
      next: (dados) => {
        this.documentos = dados;
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Erro ao buscar documentos', err)
    });
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.arquivoSelecionado = file;
    }
  }

  subir() {
    if (!this.arquivoSelecionado || !this.titulo) {
      alert('Por favor, preencha o título e selecione um arquivo.');
      return;
    }

    this.service.upload(this.arquivoSelecionado, this.titulo, this.descricao).subscribe({
      next: () => {
        alert('Documento enviado com sucesso!');
        this.limparFormulario();
        this.carregarDocumentos();
      },
      error: (err) => alert('Erro no upload. Verifique o tamanho do arquivo.')
    });
  }

  comentar(docId: number | undefined, texto: string) {
    if (!docId || !texto.trim()) return;

    this.service.adicionarComentario(docId, texto).subscribe({
      next: () => this.carregarDocumentos(),
      error: (err) => console.error('Erro ao comentar', err)
    });
  }

  deletarDoc(id: number | undefined) {
    if (!id) return;
    if (confirm('Tem certeza que deseja excluir este documento?')) {
      this.service.deletar(id).subscribe({
        next: () => this.carregarDocumentos(),
        error: (err) => alert('Erro ao deletar documento.')
      });
    }
  }

  deletarComentario(comentarioId: number | undefined) {
    if (!comentarioId) return;
    this.service.deletarComentario(comentarioId).subscribe({
      next: () => this.carregarDocumentos(),
      error: (err) => console.error('Erro ao deletar comentário', err)
    });
  }

  private limparFormulario() {
    this.titulo = '';
    this.descricao = '';
    this.arquivoSelecionado = null;
    
    // Limpa o valor físico do input de arquivo no DOM
    if (this.fileInput) {
      this.fileInput.nativeElement.value = '';
    }
  }
}