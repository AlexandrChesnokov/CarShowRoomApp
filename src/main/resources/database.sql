drop table if exists makers cascade ;
create table makers
(
        id serial not null primary key ,
        name varchar(16) not null
);

insert into makers (name) values ('Mercedes');
insert into makers (name) values ('BMW');
insert into makers (name) values ('Audi');
insert into makers (name) values ('Tesla');
insert into makers (name) values ('Ferrari');

drop table if exists models cascade;
create table models
(
        id serial not null primary key ,
        name varchar(16) not null ,
        maker_id int not null ,
        price double precision not null ,
        color varchar(16) not null ,
        yearissue date not null ,
        hp int not null ,

        foreign key (maker_id) references makers(id)
);

insert into models (name, maker_id, price, color, yearissue, hp)
values ('S-class', 1, 15000, 'black', '2015-01-01', 420);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('E-class', 1, 12000, 'black', '2015-01-01', 380);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('X5M', 2, 18000, 'white', '2013-01-01', 510);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('M4', 2, 11000, 'red', '2011-01-01', 210);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('R8', 3, 42000, 'red', '2017-01-01', 820);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('A8', 3, 22000, 'black', '2013-01-01', 310);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('Cybertruck', 4, 70000, 'silver', '2019-01-01', 700);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('MODEL X', 4, 35000, 'white', '2013-01-01', 510);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('LaFerrari', 5, 100000, 'red', '2015-01-01', 750);
insert into models (name, maker_id, price, color, yearissue, hp)
values ('Superfast', 5, 120000, 'red', '2013-01-01', 840);

drop table if exists users cascade;
create table users
(
    id serial not null primary key ,
    firstname varchar(16) not null ,
    lastname varchar(16) not null ,
    phone_number varchar(16) not null ,
    email varchar(16) not null ,
    manager_id int  ,
    password varchar not null
);

insert into users (firstname, lastname, phone_number, email, manager_id, password)
values ('Alexandr', 'Chesnokov', '89261234567', 'admin@a.ua', null, '$2a$10$CIy6hcBPxgflOyj8IRVDj.hegrWet/3UhtKghgLo.Pbku3xOnENee');
insert into users (firstname, lastname, phone_number, email, manager_id, password)
values ('Ada', 'Lovelace', '89261234567', 'manager@a.ua', null, '$2a$10$CIy6hcBPxgflOyj8IRVDj.hegrWet/3UhtKghgLo.Pbku3xOnENee');
insert into users (firstname, lastname, phone_number, email, manager_id, password)
values ('Alan', 'Turing', '89261234567', 'user@a.ua', null, '$2a$10$CIy6hcBPxgflOyj8IRVDj.hegrWet/3UhtKghgLo.Pbku3xOnENee');

drop table if exists roles cascade;
create table roles
(
    id serial not null primary key ,
    name varchar(16) not null
    );

insert into roles (name) values ('ADMIN');
insert into roles (name) values ('MANAGER');
insert into roles (name) values ('USER');

drop table if exists user_roles cascade;
create table user_roles
(
    user_id int not null ,
    role_id int not null ,

    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (role_id) references roles(id)
    );

insert into user_roles (user_id, role_id) values (1,1);
insert into user_roles (user_id, role_id) values (2,2);
insert into user_roles (user_id, role_id) values (3,3);

drop table if exists enhance cascade;
create table enhance
(
    id serial not null primary key ,
    name varchar(16) not null ,
    price double precision not null ,
    increase int not null
);

insert into enhance (name, price, increase) VALUES ('STAGE 1', 2000, 200);
insert into enhance (name, price, increase) VALUES ('STAGE 2', 10000, 450);
insert into enhance (name, price, increase) VALUES ('STAGE 3', 25000, 1000);
insert into enhance (id, name, price, increase) VALUES (0, 'DEFAULT', 0, 0);


drop table if exists orders cascade;
create table orders
(
    id serial not null primary key ,
    user_id int not null ,
    model_id int not null ,
    enhance_id int not null ,
    sum_price double precision not null ,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (model_id) REFERENCES models(id),
    FOREIGN KEY (enhance_id) REFERENCES enhance(id)
);
