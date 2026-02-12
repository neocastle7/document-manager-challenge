# 📁 Gestão de Documentos - Prova Técnica

Este projeto é uma aplicação Full Stack desenvolvida para o processo seletivo de Estagiário Desenvolvedor na **Resende Mori Hutchison**. A solução permite o upload de arquivos, listagem reativa, visualização/download e um sistema de histórico de comentários.

## 🚀 Links de Acesso (Deploy)
- **Aplicação Online (Vercel):** [https://document-manager-challenge.vercel.app](https://document-manager-challenge.vercel.app)
- **Documentação da API (Swagger):** [https://document-manager-challenge.onrender.com/swagger-ui/index.html](https://document-manager-challenge.onrender.com/swagger-ui/index.html)

## 🛠️ Tecnologias Utilizadas
- **Back-end:** Java 21, Spring Boot 3.x, Spring Data JPA, Maven.
- **Front-end:** Angular 17, Bootstrap 5, RxJS.
- **Infraestrutura:** Docker, Nginx (Frontend), Render (API), Vercel (UI).
- **Banco de Dados:** H2 Database (Persistência em memória).

## 📌 Requisitos Atendidos (Conforme Edital)
- **4.1 Upload:** Suporte a PDF, JPG e PNG com armazenamento local conteinerizado e persistência em banco de dados.
- **4.2 Listagem:** Exibição de título, data de upload e ações de visualização/download direto pelo navegador.
- **4.3 Comentários:** Histórico vinculado ao documento com registro automático de data e hora de cada inserção.

## ✨ Diferenciais Implementados
- **Dockerization:** Projeto totalmente preparado para rodar em containers, garantindo portabilidade.
- **Swagger/OpenAPI:** Documentação automática e interativa das rotas do back-end.
- **CORS Configurado:** Comunicação segura e funcional entre domínios distintos (Vercel -> Render).
- **UX/UI:** Interface limpa, responsiva (testada em dispositivos móveis) e com feedbacks de sucesso/erro.

## 📦 Como Executar Localmente

### Via Docker (Recomendado)
Certifique-se de ter o Docker instalado e rode os seguintes comandos no terminal:

```bash
# Navegue até a pasta do backend
cd backend/document-manager

# Construa a imagem
docker build -t document-manager-api .

# Rode o container
docker run -p 8080:8080 document-manager-api
