CREATE TABLE endereco (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  logradouro VARCHAR(255),
  cidade VARCHAR(100),
  estado VARCHAR(50)
);
CREATE TABLE cliente (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  cpf VARCHAR(20),
  email VARCHAR(255),
  endereco_id BIGINT,
  CONSTRAINT fk_cliente_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);
CREATE TABLE colaborador (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  cpf VARCHAR(20),
  email VARCHAR(255),
  cargo VARCHAR(100),
  endereco_id BIGINT,
  CONSTRAINT fk_colaborador_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);
CREATE TABLE chamado (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  cliente_id BIGINT,
  colaborador_id BIGINT,
  descritivo VARCHAR(2000),
  situacao VARCHAR(50),
  data_abertura TIMESTAMP,
  data_encerramento TIMESTAMP,
  CONSTRAINT fk_chamado_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  CONSTRAINT fk_chamado_colaborador FOREIGN KEY (colaborador_id) REFERENCES colaborador(id)
);
