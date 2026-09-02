

DROP DATABASE IF EXISTS mydb0826_practice;
CREATE DATABASE mydb0826_practice;
USE mydb0826_practice;
CREATE TABLE waitList(
                      num int AUTO_INCREMENT ,
                      telNum VARCHAR(255) ,
                      people int ,
                      constraint PRIMARY KEY( num )
);
insert into waitList( telNum, people )values( "010-1111-1111", 6 ),( "010-2222-2222", 5),("010-3333-3333",6); -- 샘플 데이터 3개
