create table
    token_holder (
        id varchar(255) not null,
        created_at timestamp(6),
        updated_at timestamp(6),
        expires_at timestamp(6) not null,
        token varchar(255) not null,
        user_id varchar(255) not null,
        primary key (id)
    );

alter table if exists token_holder
drop constraint if exists UK3q6vg8hjj3oyunqe5a67cy4vb;

alter table if exists token_holder add constraint UK3q6vg8hjj3oyunqe5a67cy4vb unique (user_id);

alter table if exists token_holder add constraint FKln1tydwtod529vel9p6h6oj23 foreign key (user_id) references users;