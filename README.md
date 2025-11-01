# 🍽️ Sistema de Acompanhamento e Controle de Consumo Diário de Calorias
## 📋 Descrição do Projeto

O Sistema de Acompanhamento e Controle de Consumo Diário de Calorias é uma aplicação Back-end desenvolvida com Spring Boot, projetada para auxiliar usuários no controle nutricional diário.
O sistema é multiusuário e permite o registro de alimentos consumidos, o acompanhamento de macronutrientes e o histórico de pesagens ao longo do tempo.

## 🎯 Funcionalidades
### 🥗 Lançamento de Alimentos

O usuário pode adicionar alimentos consumidos durante o dia, informando a refeição correspondente:

Café da manhã ☕

Almoço 🍛

Lanche da tarde 🍎

Jantar 🍲

Caso o alimento não exista no sistema, o usuário pode cadastrá-lo informando:

Nome do alimento

Quantidade de carboidratos, gorduras totais e proteínas (por 100g)

### ⚖️ Histórico de Pesagem

Registro de peso, data da pesagem e cálculo automático do IMC (Índice de Massa Corporal).

O usuário pode consultar seu histórico de pesagens para acompanhar sua evolução ao longo do tempo.

### 📊 Relatórios e Histórico

Recuperação de dados históricos de consumo e pesagem por meio de endpoints REST.

Possibilidade de visualizar registros anteriores e acompanhar o progresso nutricional.

## 🧰 Tecnologias Utilizadas
Tecnologia	Descrição
- Java 17+	Linguagem principal do projeto
- Spring Boot 3+	Framework para criação da API REST
- Spring Data JPA	Persistência de dados e integração com banco
- Oracle Database	Banco de dados relacional utilizado
- Maven ou Gradle	Gerenciamento de dependências e build
- Jakarta Validation	Validação de dados nas requisições
- Lombok	Redução de código boilerplate
- Swagger / Springdoc OpenAPI	Documentação interativa dos endpoints (opcional)

---

## ⚙️ Estrutura da Aplicação
```
src/
 ├── main/
 │   ├── java/
 │   │   └── com.fiap.calorias/
 │   │       ├── controller/      # Endpoints REST
 │   │       ├── service/         # Regras de negócio
 │   │       ├── repository/      # Integração com o banco
 │   │       ├── model/           # Entidades (Alimento, Usuario, Pesagem, etc.)
 │   │       └── dto/             # Objetos de transferência de dados
 │   └── resources/
 │       ├── application.properties  # Configurações do projeto
 │       └── data.sql / schema.sql   # Scripts de inicialização (opcional)
 └── test/
     └── ...                        # Testes automatizados
```
## 🔌 Endpoints Principais
Método	Endpoint	Descrição
- POST	/api/alimentos	Cadastra um novo alimento
- GET	/api/alimentos	Lista todos os alimentos cadastrados
- POST	/api/consumo	Registra o consumo diário de alimentos
- GET	/api/consumo/{data}	Lista os alimentos consumidos em uma data específica
- POST	/api/pesagens	Registra uma nova pesagem (com cálculo automático do IMC)
- GET	/api/pesagens/historico	Retorna o histórico completo de pesagens do usuário

---

##🧪 Como Executar o Projeto
✅ Pré-requisitos

- Java 17 ou superior

- Maven ou Gradle

- Oracle Database configurado e em execução

## ▶️ Passos

Clonar o repositório:

```
git clone https://github.com/seu-usuario/calorias-api.git
cd calorias-api
```

Configurar o banco de dados Oracle
No arquivo application.properties:
```
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Executar o projeto:

```
./mvnw spring-boot:run
```

ou

```
./gradlew bootRun
```

Acessar a API:

Base URL: http://localhost:8080

(Opcional) Swagger UI: http://localhost:8080/swagger-ui.html

## 📈 Próximas Melhorias

1. Autenticação JWT para controle de usuários

2. Dashboard de estatísticas (macro nutrientes e IMC)

3. Integração com frontend web ou mobile

4. Exportação de relatórios em PDF

## 👨‍💻 Autor

Desenvolvido por: Vinicius Soares Oliveira
🎓 Projeto acadêmico FIAP — Back-end com Spring Boot e Oracle Database
📧 Contato: vhisoares39@gmail.com
