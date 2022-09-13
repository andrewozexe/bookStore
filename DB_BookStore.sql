create database loja;
use loja;
create table usuarios(
    cpf varchar(14),
    senha varchar(20),
    nome varchar(100),
    constraint pk_cpf primary key (cpf));
create table livros(
    cod int auto_increment primary key,
    preco double, estoque int,
    nome varchar(100),
    autor varchar(100));

INSERT INTO usuarios(CPF, SENHA, NOME) VALUES ("107.008.099-37", "1234", "André");
INSERT INTO usuarios(cpf, senha, nome) VALUES ("admin", "admin", "admin");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (52.40, 20,"Homem de giz", "C. J. Tudor");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (141.90, 5, "Linguagem C - Completa e Descomplicada", "André Backes");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (22.90, 55, "1984", "George Orwell");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (39.90, 2, "Sapiens", "Yuval Noah Harari");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (34.90 , 10, "O poder do Hábito", "Charles Duhigg");

INSERT INTO livros(preco, estoque, nome, autor) VALUES (14.80 , 16, "Livro do desassossego", "Fernando Pessoa");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (58.41 , 33, "Sherlock Holmes", "Arthur Conan Doyle");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (28.30 , 8, "O Hobbit", "J.R.R. Tolkien");
INSERT INTO livros(preco, estoque, nome, autor) VALUES (13.50 , 10, "O diário de Anne Frank", "Anne Frank");


create table carrinho(
    cpf varchar(14),
    qnt_livros int,
    cod_livro int,
    valor double,
    constraint fk_USerCar foreign key (cpf) references usuarios(cpf),
    constraint foreign key (cod_livro) references livros(cod),
    constraint pk_cpfCod primary key (cpf, cod_livro)
);

create table relatorio(
    cpf varchar(14),
    valor double,
    constraint foreign key (cpf) references usuarios(cpf)
);
