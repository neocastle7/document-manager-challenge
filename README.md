# 📁 Gestão de Documentos - Prova Técnica

Este projeto é uma aplicação Full Stack desenvolvida para o processo seletivo de Estagiário Desenvolvedor. A solução permite o upload de arquivos, listagem reativa, visualização/download e um sistema de histórico de comentários, atendendo integralmente aos requisitos da prova técnica.

## 🚀 Tecnologias Utilizadas
- **Back-end:** Java 21, Spring Boot 3.x, Spring Data JPA.
- **Front-end:** Angular 17, Bootstrap 5 (UI/UX).
- **Banco de Dados:** H2 Database (Persistência em memória para facilitar a avaliação).
- **Gestão de Arquivos:** Armazenamento local (pasta /uploads).

## 📌 Requisitos Atendidos (Conforme Edital)
- **4.1 Upload:** Suporte a PDF, JPG e PNG com armazenamento local e persistência em banco de dados.
- **4.2 Listagem:** Exibição de título, data de upload e ações de visualização/download.
- **4.3 Comentários:** Histórico vinculado ao documento com registro de data e hora.

## ✨ Diferenciais Implementados (Proatividade)
- **CRUD Completo:** Adicionada funcionalidade de exclusão de documentos e comentários para gestão total dos dados.
- **Visualização Nativa:** Visualização de arquivos diretamente no navegador.
- **Download Forçado:** Rota específica no back-end para garantir o download do arquivo fisicamente.
- **UX Refinada:** Interface reativa com limpeza física automática de formulário (ViewChild) e informativo de formatos aceitos.

## 📦 Como Executar Localmente

### ### Pré-requisitos
- Java 21 ou superior instalado.
- Node.js e Angular CLI instalados.

### ### 1. Clonar o Repositório
```bash
git clone [https://github.com/neocastle7/document-manager-challenge.git](https://github.com/neocastle7/document-manager-challenge.git)
```

### ### 2. Rodar o Back-end (Spring Boot)
1. Importe o projeto em sua IDE (IntelliJ ou Eclipse).
2. Execute a classe principal `DocumentManagerApplication`.
3. O servidor estará ativo em `http://localhost:8080`.

### ### 3. Rodar o Front-end (Angular)
1. Abra um terminal na pasta do projeto.
2. Navegue até a pasta do frontend:
```bash
cd document-manager-ui
```
3. Instale as dependências:
```bash
npm install
```
4. Inicie o servidor de desenvolvimento:
```bash
ng serve
```
5. Acesse a aplicação em `http://localhost:4200`.

## ⚠️ Observações e Limitações Conhecidas
- **Persistência:** Por utilizar banco de dados H2 e armazenamento local, os dados e arquivos físicos são resetados ao reiniciar a aplicação em ambientes de deploy efêmero.

## 👨‍💻 Autor
**Lucas Vera** - Estudante de Análise e Desenvolvimento de Sistemas na UCB.