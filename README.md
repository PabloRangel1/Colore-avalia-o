#  Avaliação de Atendimento de TI

Sistema web simples para registrar avaliações rápidas sobre o atendimento da equipe de TI, usando botões de emojis com notas de **0 a 10**.

---

##  Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **Thymeleaf**
- **Lombok**
- **MapStruct**
- **MySQL**
- **Springdoc OpenAPI (Swagger)**

---

##  Funcionalidades

- Registrar avaliações de 0 a 10 com botão de emoji
- Impede múltiplos votos via `localStorage`
- Listar todas as avaliações via API
- Calcular média por período
- Classificar a média em: Excelente, Bom, Regular, Ruim
- Interface responsiva com design limpo

---
##  Endpoints REST

**Base URL:** `/avaliacao`

| Método | Rota              | Descrição                           |
|--------|-------------------|-------------------------------------|
| POST   | `/criar`          | Salva uma nova avaliação (0 a 10)  |
| GET    | `/media`          | Lista todas as avaliações          |
| GET    | `/periodo`        | Calcula a média entre datas        |

Exemplo: GET /avaliacao/periodo?inicio=2025-06-01&fim=2025-06-24


Classificação por média:

| Média | Classificação |
|-------|----------------|
| 2.8 + | Excelente     |
| 2.3 + | Bom           |
| 1.8+  | Regular       |
| - 1.8 | Ruim         |

---

##  Interface Web (Thymeleaf)

### `/ui/avaliar`

Página inicial com os botões:

- 😊 Bom (nota 3)
- 😐 Regular (nota 2)
- 😞 Ruim (nota 1)

> *As notas podem ser ajustadas conforme desejado no JS ou backend.*

---

### `/ui/media-form`

Formulário com campos de data para filtrar período da média.

---

### `/ui/media`

Exibe:

- Média das notas no período
- Classificação textual e com emoji

---

##  Exemplo de Avaliação Salva

```json
{
  "id": 1,
  "nota": 3,
  "dataAvaliacao": "2025-06-24"
}

```

## Executando Localmente

### Compile e execute
mvn clean install,
mvn spring-boot:run

Acesse:

Interface Web: http://localhost:8080/ui/avaliar

Swagger UI: http://localhost:8080/swagger-ui.html











