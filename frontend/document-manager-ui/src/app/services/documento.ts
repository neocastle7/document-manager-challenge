import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Documento {
  id: number;
  titulo: string;
  descricao: string;
  nomeArquivo: string;
  dataUpload: string;
  comentarios: any[];
}

@Injectable({
  providedIn: 'root'
})
export class DocumentoService {
  private apiUrl = 'http://localhost:8080/api/documentos';

  constructor(private http: HttpClient) { }

  listar(): Observable<Documento[]> {
    return this.http.get<Documento[]>(this.apiUrl);
  }

  upload(arquivo: File, titulo: string, descricao: string): Observable<Documento> {
    const formData = new FormData();
    formData.append('arquivo', arquivo);
    formData.append('titulo', titulo);
    formData.append('descricao', descricao);
    return this.http.post<Documento>(`${this.apiUrl}/upload`, formData);
  }

  adicionarComentario(docId: number, texto: string): Observable<Documento> {
    return this.http.post<Documento>(`${this.apiUrl}/${docId}/comentarios`, texto);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  deletarComentario(comentarioId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-comentario/${comentarioId}`);
  }
}