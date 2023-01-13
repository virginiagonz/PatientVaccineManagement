drop table if exists users;
drop table if exists contacts;

create table users (
    id              integer auto_increment primary key,
    first_name           varchar(255),
    last_name            varchar(255),
	phone		          varchar(255)
);

insert into users values (1, 'Jane', 'Doe', '123-456-7890');
insert into users values (2, 'John', 'Smith', '098-765-4321');

create table contacts (
    id           integer auto_increment primary key,
    name_key             varchar(255),
    name_value                varchar(255),
    user_id              integer
);

insert into contacts values (1, 'email', 'jdoe@gmail.com', 1);
insert into contacts values (2, 'phone', '323-123-4567', 2);