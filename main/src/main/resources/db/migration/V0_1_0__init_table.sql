CREATE TABLE IF NOT EXISTS timeline (
    id bigint PRIMARY KEY,
    name varchar(100),
    tweet varchar(250),
    created_date timestamp without time zone NOT NULL
);