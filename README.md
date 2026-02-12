📁 Gestão de Documentos - Prova Técnica
Este projeto é uma aplicação Full Stack desenvolvida para o processo seletivo de Estagiário Desenvolvedor na Resende Mori Hutchison. A solução permite o upload de arquivos, listagem reativa, visualização/download e um sistema de histórico de comentários.

🚀 Links de Acesso (Deploy)
Aplicação Online (Vercel): https://document-manager-challenge.vercel.app

Documentação da API (Swagger): https://document-manager-challenge.onrender.com/swagger-ui/index.html

🛠️ Tecnologias Utilizadas
Back-end: Java 21, Spring Boot 3.x, Spring Data JPA, Maven.

Front-end: Angular 17, Bootstrap 5, RxJS.

Infraestrutura: Docker, Nginx (Frontend), Render (API), Vercel (UI).

Banco de Dados: H2 Database (Persistência em memória).

📌 Requisitos Atendidos
4.1 Upload: Suporte a PDF, JPG e PNG com armazenamento local conteinerizado.

4.2 Listagem: Exibição de título, data de upload e ações de visualização/download.

4.3 Comentários: Histórico vinculado ao documento com registro automático de data e hora.

✨ Diferenciais Implementados
Dockerization: Projeto totalmente preparado para rodar em containers.

Swagger/OpenAPI: Documentação automática das rotas do back-end.

CORS Configurado: Comunicação segura entre diferentes domínios (Vercel -> Render).

UX/UI: Interface limpa com feedbacks de erro e sucesso.

📦 Como Executar Localmente
Via Docker (Recomendado)
Certifique-se de ter o Docker instalado e rode:


# Na raiz do projeto
cd backend/document-manager
docker build -t document-manager-api .
docker run -p 8080:8080 document-manager-api
Manualmente (Desenvolvimento)
Back-end:

Navegue até backend/document-manager.

Execute ./mvnw spring-boot:run ou via sua IDE.

Front-end:

Navegue até frontend/document-manager-ui.

Execute npm install e depois ng serve.

Acesse http://localhost:4200.

⚠️ Observações Importantes
Armazenamento Efêmero: Por utilizar o plano gratuito do Render, a pasta /uploads e o banco H2 são resetados após períodos de inatividade do servidor ou novos deploys. Em um ambiente real, utilizaria-se S3 (AWS) ou Azure Blob Storage.

👨‍💻 Autor
Lucas Vera - LinkedIn | GitHub
