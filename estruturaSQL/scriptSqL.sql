-- drop table usuario,bibliotecario,livro,emprestimo

create table bibliotecario(
	id int not null primary key,
	nome varchar(100),
	email varchar(100) not null
);

create table usuario(
	cpf varchar(11) not null primary key,
	nome varchar(100) ,
	email varchar(100) ,
	celular varchar(11),
	suspensao boolean,
	numero_livro int 
);

create table livro(
	nome varchar(100) not null primary key,
	autor varchar(100),
	ano_publicacao int,
	editora varchar(100),
	quantidade int
);

create table emprestimo(
	id int not null generated by default as identity primary key,
	
	id_livro varchar(100) not null, 
	constraint id_livro foreign key (id_livro) references livro(nome),
	
	cpf_usuario varchar(11) not null,
	constraint cpf_usuario foreign key (cpf_usuario) references usuario(cpf),
	
	id_bibliotecario int not null ,
	constraint id_bibliotecario foreign key (id_bibliotecario) references bibliotecario(id),
	
	data_retirada date not null,
	data_devolucao date,
	data_limite date not null
);

insert into bibliotecario values(1,'Jesse', 'jessehaniel@letscode.com');
insert into livro values('thewitcher','andre', 2021, 'teste', 5);
insert into usuario values('1234','Rhuan', 'rhuan@gmail.com', '123', false, 0);
insert into emprestimo values(1, 'thewitcher', '1234', 1, '2022-04-10',null, '2022-04-17' );