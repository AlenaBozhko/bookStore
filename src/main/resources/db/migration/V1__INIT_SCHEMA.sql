-- Таблица книг
create table if not exists books
(
    "id"		    bigserial       	    PRIMARY KEY,
    created 	    timestamp with time zone       not null    default now(),
    updated		    timestamp with time zone,
    status	        varchar(15)     not null    default 'ACTIVE',
    title	        varchar(255)    not null,
    author	        varchar(255)    not null,
    genre	        varchar(255)    not null,
    description	    varchar(255)   not null,
    price	        integer        not null,
    currency	    varchar(255)    not null
    );

comment on table  books is 'Книги';
comment on column books.id is 'ИД книги';
comment on column books.created is 'Дата поступления книги';
comment on column books.updated is 'Дата последнего обновления информации о книге';
comment on column books.status is 'Статус книги';
comment on column books.title is 'Название книги';
comment on column books.author is 'Автор книги';
comment on column books.genre is 'Жанр книги';
comment on column books.description is 'Краткое описание книги';
comment on column books.price is 'Стоимость книги';
comment on column books.currency is 'Валюта';
