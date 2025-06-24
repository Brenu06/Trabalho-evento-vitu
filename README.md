📘Manual de Instruções – Sistema de Gerenciamento de Eventos
Este sistema permite o gerenciamento de eventos, palestrantes e participantes, com suporte a persistência de dados via PostgreSQL.

🧰 Requisitos
Java JDK 8 ou superior instalado

PostgreSQL instalado e configurado

IDE Java (IntelliJ, Eclipse ou similar) ou terminal

Git instalado

📥 1. Clonar o Projeto
Abra o terminal e execute:
git clone https://github.com/Brenu06/Trabalho-evento-vitu

📂 2. Abrir o Projeto
Abra a pasta events-java no seu IDE de preferência (como IntelliJ ou Eclipse), ou navegue pelo terminal:
cd events-java

🗄️ 3. Configurar o Banco de Dados (PostgreSQL)
Acesse o PostgreSQL (via pgAdmin ou terminal).
Crie um banco chamado, por exemplo:
CREATE DATABASE events_tech;
No código, edite as configurações de conexão em DatabaseConnection.java:
private static final String URL = "jdbc:postgresql://localhost:5432/events_tech";
private static final String USUARIO = "seu_usuario";
private static final String SENHA = "sua_senha";
Execute os scripts de criação de tabelas (se fornecidos) ou deixe o programa criá-las automaticamente (dependendo da implementação).

▶️ 4. Executar o Programa
Compile e execute o arquivo Main.java. Ele fica geralmente em:
src/main/java/com/br/breno/Main.java

No terminal:
javac -d bin src/main/java/com/br/breno/**/*.java
java -cp bin com.br.breno.Main

Ou clique em Run na sua IDE.

📝 Funcionalidades Disponíveis
Após executar o programa, o menu exibido (normalmente) deve conter as opções:
Cadastrar Evento

Informar nome, data, local, etc.

Cadastrar Palestrante

Nome, área de atuação, etc.

Cadastrar Participante

Nome, CPF, e-mail, etc.

Listar todos

Exibe todos os eventos/palestrantes/participantes cadastrados.

Buscar ou editar

💾 Observações
Os dados são salvos no banco PostgreSQL, portanto persistem mesmo após o programa ser encerrado.
Verifique se o banco está ativo e aceitando conexões antes de iniciar o sistema.
Caso apareça erro de conexão, revise usuário, senha ou porta no código.
