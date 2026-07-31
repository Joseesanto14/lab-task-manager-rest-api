# Task Manager API 📝

API REST em **Kotlin** + **Spring Boot** para gerenciamento de tarefas.

---

## 🚀 Como Executar

```bash
./gradlew bootRun
```
Servidor: `http://localhost:8080/tasks`

---

## 🔌 Endpoints & Payloads Mínimos

### 1. Criar Tarefa (`POST /tasks`) — `201 Created`
**Payload Mínimo:**
```json
{
  "title": "Estudar Kotlin"
}
```
**cURL:**
```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Estudar Kotlin"}'
```

---

### 2. Listar Tarefas (`GET /tasks`) — `200 OK`
**cURL:**
```bash
curl http://localhost:8080/tasks
```

---

### 3. Buscar por ID (`GET /tasks/{id}`) — `200 OK`
**cURL:**
```bash
curl http://localhost:8080/tasks/{id}
```

---

### 4. Atualizar Tarefa (`PATCH /tasks/{id}`) — `200 OK`
**Payload Mínimo:**
```json
{
  "title": "Estudar Kotlin Avançado",
  "status": "COMPLETED"
}
```
**cURL:**
```bash
curl -X PATCH http://localhost:8080/tasks/{id} \
  -H "Content-Type: application/json" \
  -d '{"title": "Estudar Kotlin Avançado", "status": "COMPLETED"}'
```

---

### 5. Deletar Tarefa (`DELETE /tasks/{id}`) — `204 No Content`
**cURL:**
```bash
curl -X DELETE http://localhost:8080/tasks/{id}
```

---

## 📑 Documentação Automatizada (REST Docs)

```bash
./gradlew test asciidoctor
```
Gera a documentação completa em HTML:  
`build/docs/asciidoc/index.html`
