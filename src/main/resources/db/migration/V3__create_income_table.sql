create table
    income (
        id varchar(255) not null,
        created_at timestamp(6),
        updated_at timestamp(6),
        amount bigint not null,
        description varchar(255),
        user_id varchar(255) not null,
        source smallint check (source between 0 and 4),
        primary key (id)
    );

alter table if exists income
drop constraint if exists UKpw0rpfltx9r2hkyicpwovd6qf;

alter table if exists income add constraint UKpw0rpfltx9r2hkyicpwovd6qf unique (user_id);

alter table if exists income add constraint FKnuw53hk0hha02go6e3itfne7t foreign key (user_id) references users;