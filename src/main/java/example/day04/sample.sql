drop database if exists mydb0902;
create database mydb0902;
use mydb0902;

create table exam(
    eno INT auto_increment primary key,
    ename VARCHAR(255)
);

insert into exam(ename) values ('유재석');
insert into exam(ename) values ('강호동');
insert into exam(ename) values ('신동엽');
