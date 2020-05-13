create table testtable
(
    id serial not null primary key ,
    name varchar(16) not null
);

insert into testtable (name) values ('Mercedes');
insert into testtable (name) values ('BMW');
insert into testtable (name) values ('Audi');
insert into testtable (name) values ('Tesla');
insert into testtable (name) values ('Ferrari');