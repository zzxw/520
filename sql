test
use drawing;
drop table if exists sequesce;
create table sequence (
	name varchar(50) not null,
    current_value int not null,
    increment int not null default 1,
    primary key(name)
)engine=InnoDB;

drop function if exists currval;
delimiter $
create table projects(
	pid int not null primary key,
	uid int ,
	checkId int,
	authorizeId int ,
    majorType varchar(20) not null,
    pName varchar(50) not null,
    pType varchar(20) not null,
    unitName varchar(50) not null,
    contacts varchar(20) not null,
	cPhone varchar(20) not null,
    status int not null,
    blueprint varchar(100)
)
create table worker(
	uid int not null primary key,
    uName varchar(20) not null,
    pwd varchar(20) not null,
    phone varchar(20) not null,
    mail varchar(20) not null
)
create table users(
	uid int not null primary key,
    uName varchar(20) not null,
    pwd varchar(20) not null,
    phone varchar(20) not null,
	mail varchar(20) not null,
    address varchar(50)not null,
    major varchar(20) not null,
	userType int 
)

create table engineer (
	pid int not null,
    eId int not null primary key,
    uid int not null,
	checkId1 int,
	checkId2 int,
    status int not null,
    comment varchar(50) not null,
    info varchar(100) not null
)
