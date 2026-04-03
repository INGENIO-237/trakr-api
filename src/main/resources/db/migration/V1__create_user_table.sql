create table
   users (
      id varchar(255) not null,
      created_at timestamp(6),
      updated_at timestamp(6),
      email varchar(255) not null,
      full_name varchar(255) not null,
      password varchar(255),
      primary key (id)
   );

alter table if exists users
drop constraint if exists UK6dotkott2kjsp8vw4d0m25fb7;

alter table if exists users add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);