create table episode
(
    episode_id  bigint auto_increment primary key,
    number      bigint unsigned not null,
    season      int unsigned not null,
    title       varchar(60) not null,
    released_at timestamp default CURRENT_TIMESTAMP,
    constraint unique_title
        unique (title),
    constraint unique_number
        unique (number)
);

create table character_table
(
    character_id   bigint auto_increment primary key,
    character_name varchar(55) not null,
    gender         enum('M', 'F', 'O') not null,
    origin         varchar(55),
    constraint unique_name
        unique (character_name)
);

create table character_to_episode
(
    character_id bigint not null,
    episode_id   bigint not null,
    constraint c_t_e_pk
        primary key (episode_id, character_id),
    constraint character_to
        foreign key (character_id) references character_table (character_id),
    constraint episode_to
        foreign key (episode_id) references episode (episode_id)
);
