create table
    expense (
        id varchar(255) not null,
        created_at timestamp(6),
        updated_at timestamp(6),
        amount bigint not null,
        description varchar(255),
        user_id varchar(255) not null,
        reason smallint check (reason between 0 and 5),
        primary key (id)
    );

alter table if exists expense add constraint FKekyts7i8w5cam119wj1itdom2 foreign key (user_id) references users;