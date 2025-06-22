CREATE TABLE participante (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100),
       email VARCHAR(100),
       cpf VARCHAR(11)
   );

   CREATE TABLE palestrante (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100),
       curriculo TEXT,
       area_atuacao VARCHAR(100)
   );

   CREATE TABLE evento (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100),
       descricao TEXT,
       data DATE,
       local VARCHAR(100),
       capacidade INT
   );

   CREATE TABLE inscricao (
       id SERIAL PRIMARY KEY,
       evento_id INT REFERENCES evento(id),
       participante_id INT REFERENCES participante(id),
       UNIQUE (evento_id, participante_id)
   );

   CREATE TABLE evento_palestrante (
       evento_id INT REFERENCES evento(id),
       palestrante_id INT REFERENCES palestrante(id),
       PRIMARY KEY (evento_id, palestrante_id)
   );