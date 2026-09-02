drop database if exists mydb0902;
create database mydb0902;
use mydb0902;

create table exam(
    no INT auto_increment primary key,
    writer VARCHAR(255),
    content varchar(255)
);

insert into exam(writer, content) values ('유재석','aaa');
insert into exam(writer, content) values ('강호동','bbb');
insert into exam(writer, content) values ('신동엽','ccc');
