# Api-todolist

API de gerenciamento de tarefas (To-Do List) desenvolvida em Java com Spring Boot.
Integra-se ao Firebase Firestore para persistência dos dados e é consumida por um aplicativo mobile.

> ⚠️ **Importante:** Este projeto depende da variável de ambiente `FIREBASE_KEY` para inicializar o Firebase Firestore.  
> Sem essa chave (que está privada), **o projeto não poderá ser executado localmente**.  
> Quem quiser rodar o projeto precisa criar suas próprias credenciais do Firebase e configurá-las como variável de ambiente.

---

## 🧾 Visão Geral

Esta API fornece operações CRUD para gerenciar tarefas, permitindo criar, consultar, atualizar e remover itens de uma lista de tarefas.
Projetada para ser consumida por aplicativos mobile, mantendo o backend leve e funcional.

---

## 📁 Estrutura do Projeto

```text
.
├── src
│   ├── main
│   │   ├── java/…
│   │   └── resources
│   └── test
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

```
* src/main/java: pacote principal com controllers, models, serviços e repositórios.
* src/main/resources: arquivos de configuração (application.properties / yaml, etc).
* pom.xml: dependências e configuração do Maven.

## ⚙️ Dependências / Tecnologias usadas

* Java 17
* Spring Boot
* Firestore (Firebase) → Banco de dados não relacional
* Maven → Gerenciador de dependências
* JUnit → Testes

## 📚 Endpoints

| Método | Rota          | Descrição              |
| ------ | ------------- | ---------------------- |
| GET    | `/tasks`      | Lista todas as tarefas |
| GET    | `/tasks/{id}` | Busca tarefa por ID    |
| POST   | `/tasks`      | Cria uma nova tarefa   |
| PUT    | `/tasks/{id}` | Atualiza uma tarefa    |
| DELETE | `/tasks/{id}` | Remove uma tarefa      |

> ⚠️ Autenticação: gerenciada no frontend (Firebase Auth). A API assume que o usuário já está autenticado pelo app mobile.

## 🔧 Melhorias futuras

* Documentação da API com Swagger/OpenAPI
* Paginação e filtros nos endpoints
* Validação de dados mais robusta
