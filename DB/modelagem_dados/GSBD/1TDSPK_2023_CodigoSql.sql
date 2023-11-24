-- Integrantes
--Breno Lemes Santiago - RM: 552270 - TDSPK
--Daniel dos Santos Araujo Faria - RM: 99067 - TDSPK
--Francineldo Luan Martins Alvelino - RM: 99558 - TDSPI
--Mariana Trentino Albano - RM: 551154 - TDSPM
--Vitória Maria de Camargo - RM: 552344 - TDSPK

-- Utilizando DROP TABLE para apagar as possíveis tabelas já existentes no ambiente.
DROP TABLE gs_endereco;
DROP TABLE gs_pessoa;
DROP TABLE gs_conteudo;
DROP TABLE gs_medico;
DROP TABLE gs_ticket;
DROP TABLE gs_usuario;

-- Realizando a criação de todas as tabelas necessárias para o projeto
/*
A tabela "gs_conteudo" armazena informações relacionadas ao conteúdo, 
identificado por um número único chamado "conteudo_id". 
Ela captura detalhes como o CRM do médico associado, o caminho da imagem e o texto associado ao conteúdo. 
A chave primária é baseada no "conteudo_id" para garantir a unicidade das entradas.*/
CREATE TABLE gs_conteudo (
    conteudo_id NUMBER(5) NOT NULL,
    medico_crm  VARCHAR2(20),
    image       VARCHAR2(80) NOT NULL,
    text        CLOB NOT NULL
);
 
ALTER TABLE gs_conteudo ADD CONSTRAINT conteudo_pk PRIMARY KEY ( conteudo_id );
 
/*A tabela "gs_endereco" armazena informações de endereços, identificados por um número único chamado "endereco_id". 
Cada endereço está associado a uma pessoa, identificada por "PESSOA_ID". Os detalhes incluem CEP, rua, número, bairro, cidade e UF. 
A chave primária é baseada no "endereco_id" para garantir a unicidade das entradas, 
e há uma restrição de chave estrangeira que conecta o endereço a uma pessoa.*/
CREATE TABLE gs_endereco (
    endereco_id      NUMBER(5) NOT NULL,
    PESSOA_ID NUMBER(5) NOT NULL,
    cep              VARCHAR2(10) NOT NULL,
    rua              VARCHAR2(80) NOT NULL,
    numero           VARCHAR2(10) NOT NULL,
    bairro           VARCHAR2(80) NOT NULL,
    cidade           VARCHAR2(80) NOT NULL,
    uf               VARCHAR2(2) NOT NULL
);
 
CREATE UNIQUE INDEX endereco__idx ON
    gs_endereco (
        PESSOA_ID
    ASC );
 
ALTER TABLE gs_endereco ADD CONSTRAINT endereco_pk PRIMARY KEY ( endereco_id );

/*A tabela "gs_medico" registra informações sobre médicos, como nome, CRM, especialidade, formação e autenticação. 
O "CRM" é a chave primária para garantir a unicidade dos registros, 
e há uma restrição de chave estrangeira que conecta o médico a um usuário.*/
CREATE TABLE gs_medico (
    nome               VARCHAR2(50) NOT NULL,
    crm                VARCHAR2(20) NOT NULL,
    USUARIO_ID NUMBER(5) NOT NULL,
    especialidade      VARCHAR2(50) NOT NULL,
    formacao           VARCHAR2(50) NOT NULL,
    autenticado        NUMBER(10) NOT NULL
);
 
CREATE UNIQUE INDEX medico__idx ON
    gs_medico (
        USUARIO_ID
    ASC );
 
ALTER TABLE gs_medico ADD CONSTRAINT medico_pk PRIMARY KEY ( crm );
 
/*A tabela "gs_pessoa" armazena informações pessoais, identificadas por "pessoa_id", incluindo nome, CPF, email, telefone, idade e gênero. 
A chave primária é baseada no "pessoa_id" para garantir a unicidade das entradas, 
e há uma restrição de chave estrangeira que conecta a pessoa a um usuário.*/ 
CREATE TABLE gs_pessoa (
    pessoa_id          NUMBER(5) NOT NULL,
    USUARIO_ID NUMBER(5) NOT NULL,
    nome               VARCHAR2(80) NOT NULL,
    cpf                VARCHAR2(14) NOT NULL,
    email              VARCHAR2(90) NOT NULL,
    telefone           VARCHAR2(20) NOT NULL,
    idade              NUMBER(3) NOT NULL,
    genero             VARCHAR2(18) NOT NULL
);
 
CREATE UNIQUE INDEX pessoa__idx ON
    gs_pessoa (
        USUARIO_ID
    ASC );
 
ALTER TABLE gs_pessoa ADD CONSTRAINT pessoa_pk PRIMARY KEY ( pessoa_id );
 
/*A tabela "gs_ticket" registra informações sobre tickets de suporte, com um identificador único chamado "ticket_id", 
além de um assunto e um comentário associado. A chave primária é baseada no "ticket_id" para garantir a unicidade das entradas.*/ 
CREATE TABLE gs_ticket (
    ticket_id  NUMBER(5) NOT NULL,
    assunto    VARCHAR2(40) NOT NULL,
    comentario CLOB NOT NULL
);
 
ALTER TABLE gs_ticket ADD CONSTRAINT ticket_pk PRIMARY KEY ( ticket_id );
 
/*A tabela "gs_usuario" armazena informações de usuários, identificados por "usuario_id", incluindo email, login, senha e autenticação. 
A chave primária é baseada no "usuario_id" para garantir a unicidade das entradas.*/ 
CREATE TABLE gs_usuario (
    usuario_id  NUMBER(5) NOT NULL,
    email       VARCHAR2(90) NOT NULL,
    login       VARCHAR2(40) NOT NULL,
    senha       VARCHAR2(30) NOT NULL,
    autenticado NUMBER(10) NOT NULL
);
 
ALTER TABLE gs_usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( usuario_id );

/*Há várias restrições de chave estrangeira para estabelecer relações entre as tabelas, 
como a relação entre "gs_conteudo" e "gs_medico", "gs_endereco" e "gs_pessoa", "gs_medico" 
e "gs_usuario", e "gs_pessoa" e "gs_usuario". Essas relações são essenciais para manter a integridade referencial no banco de dados.*/
ALTER TABLE gs_conteudo
    ADD CONSTRAINT conteudo_medico_fk FOREIGN KEY ( medico_crm )
        REFERENCES gs_medico ( crm );
 
ALTER TABLE gs_endereco
    ADD CONSTRAINT endereco_pessoa_fk FOREIGN KEY ( PESSOA_ID )
        REFERENCES gs_pessoa ( pessoa_id );
 
ALTER TABLE gs_medico
    ADD CONSTRAINT medico_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES gs_usuario ( usuario_id );
 
ALTER TABLE gs_pessoa
    ADD CONSTRAINT pessoa_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES gs_usuario ( usuario_id );

-- CÓDIGOS DML 
-- INSERTS
-- Inserção de Dados na tabela Usuario
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (1, 'ibonin0@sun.com', 'Ibonin', 'lU6,2bM,O5SU', 1);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (2, 'enicholls1@last.fm', 'Hinicholls', 'zB7%a@@zA}~ad=U''', 0);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (3, 'rmuckle2@newsvine.com', 'Reggiee', 'dY2!''{g`J=Vo8d', 1);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (4, 'dhardin3@hexun.com', 'DrucyH', 'xX9?ev0{h6(tD', 1);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (5, 'adorsay4@amazon.co.jp', 'aalaric', 'yX7~3"N4>N', 0);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (6, 'barrighetti5@cnbc.com', 'arrighetti', 'nG5%.cLr7<2!''5N', 0);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (7, 'scundey6@gmpg.org', 'cundeyshepard', 'bJ3%t\%&|ft', 1);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (8, 'ecortez@cnn.com', 'cortezinha', 'R#9~p@6^sY', 1);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (9, 'lucasf@domaindriven.com', 'lucaslipe', 'F$2z&*aDq', 0);
INSERT INTO gs_usuario (usuario_id, email, login, senha, autenticado) VALUES (10, 'sophierodriguez@gmail.com', 'rodrisophi', 'W@7x!1vH3%', 1);

-- Inserção de Dados na tabela Pessoa
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (1, 1, 'Indira Bonin', '8995820780', 'ibonin0@sun.com', '11985004559', '19', 'Feminino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (2, 2, 'Emmet Nicholls', '663.784.245-3', 'enicholls1@last.fm', '2182921694', '23', 'Masculino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (3, 3, 'Reggie Muckle', '7289789203', 'rmuckle2@newsvine.com', '4344770144', '38', 'Masculino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (4, 4, 'Drucy Hardin', '152.102.300-0', 'dhardin3@hexun.com', '11975411991', '27', 'Feminino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (5, 5, 'Alaric D''Orsay', '7509392903', 'adorsay4@amazon.co.jp', '962064432', '52', 'Masculino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (6, 6, 'Barri Arrighetti', '916.259.083-8', 'barrighetti5@cnbc.com', '2157048615', '21', 'Masculino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (7, 7, 'Shepard Cundey', '4359992916', 'scundey6@gmpg.org', '962619617', '45', 'Masculino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (8, 8, 'Elena Cortez', '12345678900', 'ecortez@cnn.com', '9988776655', '30', 'Feminino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (9, 9, 'Lucas Ferreira', '98765432101', 'lucasf@domaindriven.com', '1122334455', '25', 'Masculino');
INSERT INTO gs_pessoa (pessoa_id, USUARIO_ID, nome, cpf, email, telefone, idade, genero) VALUES (10, 10, 'Sophie Rodriguez', '55544433322', 'sophierodriguez@gmail.com', '777888999', '42', 'Feminino');

-- Inserção de Dados na tabela Endereco
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (1, 1, '01311-000', 'Avenida Paulista', '1106', 'Bela Vista', 'São Paulo', 'sp');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (2, 2, '01538-001', 'Avenida Lins de Vasconcelos', '1222', 'Aclimação', 'São Paulo', 'SP');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (3, 3, '30140-061', 'Rua dos Timbiras', '1660', 'Lourdes', 'Belo Horizonte', 'MG');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (4, 4, '20031-202', 'Rua Senador Dantas', '25', ' Centro', 'Rio de Janeiro', 'rj');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (5, 5, '61619-012', 'Avenida dos Coqueiros', 'S/N', 'Cumbuco', 'Caucaia', 'ce');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (6, 6, '69301-380', 'Praça do Centro Cívico', '974', 'Centro', 'Boa Vista', 'RR');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (7, 7, '78005-290', 'Rua Joaquim Murtinho', '170', 'Centro-Norte', 'Cuiabá', 'mt');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (8, 8, '90250590', 'Avenida Padre Leopoldo Brentano', '110', 'Farrapos', 'Porto Alegre', 'rs');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (9, 9, '08295-005', 'Avenida Miguel Ignácio Curi', '111', 'Vila Carmosina', 'São Paulo', 'SP');
INSERT INTO gs_endereco (endereco_id, PESSOA_ID, cep, rua, numero, bairro, cidade, uf) VALUES (10, 10, '60861211', 'Avenida Alberto Craveiro', '2901', 'Boa Vista-Castelão', 'Fortaleza', 'CE');

-- Inserção de Dados na tabela Medico
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Joao', '123456/SP', 1, 'cardiologia', 'cardiologia', 1);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Maria', '654321/RJ', 2, 'dermatologia', 'dermatologia estética', 0);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Pedro', '987654/MG', 3, 'ortopedia', 'ortopedia geral', 1);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Ana', '456789/SP', 4, 'pediatria', 'pediatria neonatal', 0);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Fernanda', '321654/RS', 5, 'neurologia', 'neurologia clínica', 1);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Rafael', '789123/BA', 6, 'ginecologia', 'ginecologia obstétrica', 0);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Luciana', '456123/PR', 7, 'cardiologia', 'cardiologia', 1);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Gabriel', '987321/SC', 8, 'pediatria', 'pediatria neonatal', 0);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Julia', '159753/DF', 9, 'endocrinologia', 'endocrinologia pediátrica', 1);
INSERT INTO gs_medico(nome, crm, USUARIO_ID, especialidade, formacao, autenticado) VALUES ('Mateus', '852963/GO', 10, 'pediatria', 'pediatria neonatal', 0);

-- Inserção de Dados na tabela Conteudo
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (1, '123456/SP', 'caminho/img1', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (2, '654321/RJ', 'caminho/img2', 'It has survived not only five centuries, but also the leap into electronic typesetting');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (3, '987654/MG', 'caminho/img3', 'remaining essentially unchanged. It was popularised in the 1960s with the release of');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (4, '456789/SP', 'caminho/img4', 'Letraset sheets containing Lorem Ipsum passages, and more recently');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (5, '321654/RS', 'caminho/img5', 'with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (6, '789123/BA', 'caminho/img6', 'Contrary to popular belief, Lorem Ipsum is not simply random text.');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (7, '456123/PR', 'caminho/img7', 'It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (8, '987321/SC', 'caminho/img8', 'Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (9, '159753/DF', 'caminho/img9', 'looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word');
INSERT INTO gs_conteudo(conteudo_id, medico_crm, image, text) VALUES (10, '852963/GO', 'caminho/img10', 'classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections');

-- Inserção de Dados na tabela Ticket
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (1, 'engasgo', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (2, 'febre', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (3, 'dores', 'Contrary to popular belief, Lorem Ipsum is not simply random text.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (4, 'tosse persistente', 'It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (5, 'tosse persistente', 'Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (6, 'dor de cabeça intensa', 'Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (7, 'dor de cabeça intensa', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (8, 'vômitos frequentes', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (9, 'tosse persistente', 'If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text.');
INSERT INTO gs_ticket(ticket_id, assunto, comentario) VALUES (10, 'sangramento nasal', 'All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.');

-- UPDATES
-- 3 linhas de Atualizacao de Dados na tabela Usuario
UPDATE gs_usuario SET login = 'vicky2121', senha = '@@zA}.cLr7ft' WHERE usuario_id = 1;
UPDATE gs_usuario SET login = 'danieldaniel', senha = '3"N4@zr!7<2' WHERE usuario_id = 2;
UPDATE gs_usuario SET login = 'santiagobreno', senha = 't\%9?ev0{=N4>' WHERE usuario_id = 3;

-- 3 linhas de Atualizacao de Dados na tabela Pessoa
UPDATE gs_pessoa SET nome = 'Vitoria', cpf = '1234567890', email = 'vitoria@gmail.com', telefone = '11912345678' WHERE pessoa_id = 1;
UPDATE gs_pessoa SET nome = 'Daniel', cpf = '9087654321', email = 'felipe@gmail.com', telefone = '4340028922', idade = '21' WHERE pessoa_id = 2;
UPDATE gs_pessoa SET  nome = 'Breno', cpf = '912.365.470-8', email = 'breno@gmail.com', telefone = '2189224002', idade = '20' WHERE pessoa_id = 3;

-- 3 linhas de Atualizacao de Dados na tabela Endereco
UPDATE gs_endereco SET cep = '01508-020', rua = 'Rua Pirapitingui', numero = '69', bairro = 'Liberdade' WHERE endereco_id = 1;
UPDATE gs_endereco SET cep = '41810700', rua = 'Rua Fernando Menezes de Góes', numero = '165', bairro = 'Pituba', cidade = 'Salvador', uf = 'BA' WHERE endereco_id = 2;
UPDATE gs_endereco SET cep = '66075150', rua = 'Avenida Bernardo Sayão', numero = 'S/N', bairro = 'Guamá', cidade = 'Belém', uf = 'PA' WHERE endereco_id = 3;

-- 3 linhas de Atualizacao de Dados na tabela Conteudo
UPDATE gs_conteudo SET image = 'caminho/img01' WHERE conteudo_id = 1;
UPDATE gs_conteudo SET image = 'caminho/img02' WHERE conteudo_id = 2;
UPDATE gs_conteudo SET image = 'caminho/img03' WHERE conteudo_id = 3;

-- 3 linhas de Atualizacao de Dados na tabela Ticket
UPDATE gs_ticket SET assunto = 'dor persistente' WHERE ticket_id = 1;
UPDATE gs_ticket SET assunto = 'engasgo frequente' WHERE ticket_id = 2;
UPDATE gs_ticket SET assunto = 'manchas no corpo' WHERE ticket_id = 3;

-- DELETES
-- 3 linhas de Exclusao de Dados na tabela Endereco
DELETE FROM gs_endereco WHERE endereco_id = 1; 
DELETE FROM gs_endereco WHERE endereco_id = 2;
DELETE FROM gs_endereco WHERE endereco_id = 3;

-- 3 linhas de Exclusao de Dados na tabela Conteúdo
DELETE FROM gs_conteudo WHERE conteudo_id = 1; 
DELETE FROM gs_conteudo WHERE conteudo_id = 2;
DELETE FROM gs_conteudo WHERE conteudo_id = 3; 

-- 3 linhas de Exclusao de Dados na tabela Ticket
DELETE FROM gs_ticket WHERE ticket_id = 1; 
DELETE FROM gs_ticket WHERE ticket_id = 2; 
DELETE FROM gs_ticket WHERE ticket_id = 3; 

-- 3 linhas de Exclusao de Dados na tabela Pessoa
DELETE FROM gs_pessoa WHERE pessoa_id = 1; 
DELETE FROM gs_pessoa WHERE pessoa_id = 2; 
DELETE FROM gs_pessoa WHERE pessoa_id = 3; 


-- CONSULTAS
-- Consulta simples envolvendo SELECT/FROM/WHERE/ORDER BY
-- Essa consulta está buscando as colunas nome, email, e idade da tabela gs_pessoa para todas as pessoas do gênero feminino. 
-- A cláusula ORDER BY idade DESC ordena os resultados em ordem decrescente de idade.
SELECT nome, email, idade FROM gs_pessoa WHERE genero = 'Feminino' ORDER BY idade DESC;

-- Consulta envolvendo uma ou mais junções de tabela, contendo: SELECT/FROM/WHERE/ORDER BY
-- Essa consulta está buscando o conteudo_id e o text da tabela gs_conteudo juntamente com o nome do médico da tabela gs_medico. 
-- A junção é realizada através da condição c.medico_crm = m.crm, e apenas os conteúdos relacionados à especialidade 'cardiologia' 
-- são incluídos. Os resultados são ordenados pelo conteudo_id.
SELECT c.conteudo_id, c.text, m.nome AS nome_medico FROM gs_conteudo c JOIN gs_medico m ON c.medico_crm = m.crm
WHERE m.especialidade = 'cardiologia'
ORDER BY c.conteudo_id;

-- Consulta envolvendo função de grupo e agrupamento
-- Essa consulta está contando o número de pessoas em cada gênero na tabela gs_pessoa. 
-- A função de grupo COUNT(*) é utilizada para contar o número de ocorrências para cada valor único na coluna genero. 
-- A cláusula GROUP BY genero agrupa os resultados pelo gênero.  
-- WHERE idade > 30 para incluir apenas pessoas com idade superior a 30 anos. 
-- A cláusula ORDER BY quantidade_pessoas DESC ordena os resultados em ordem descendente com base na contagem de pessoas.
SELECT genero, COUNT(*) AS quantidade_pessoas FROM gs_pessoa WHERE idade > 30 GROUP BY genero ORDER BY quantidade_pessoas DESC;


-- Consulta envolvendo função de grupo, agrupamento com filtro (HAVING) e junção de tabelas
-- Esta consulta está contando o número de tickets por assunto na tabela gs_ticket e filtrando 
-- apenas aqueles que têm mais de uma pessoa associada. 
-- A junção JOIN gs_pessoa p ON t.ticket_id = p.pessoa_id é realizada para relacionar os tickets com as pessoas. 
-- A função de grupo COUNT(*) é usada para contar o número de ocorrências para cada assunto, 
-- e a cláusula HAVING COUNT(*) = 1  filtra apenas os assuntos com uma ocorrência. 
-- WHERE p.idade > 22 para incluir apenas pessoas com idade superior a 22 anos. A consulta continua a contar os tickets por assunto, 
-- e em seguida, os resultados são ordenados alfabeticamente pelo assunto.
SELECT t.assunto, COUNT(*) AS quantidade_tickets FROM gs_ticket t JOIN gs_pessoa p ON t.ticket_id = p.pessoa_id
WHERE p.idade > 22 GROUP BY t.assunto HAVING COUNT(*) = 1 ORDER BY t.assunto;


