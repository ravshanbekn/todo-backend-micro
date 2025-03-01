CREATE TABLE stat_data
(
    id                BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    completed_total   INTEGER   NOT NULL,
    uncompleted_total INTEGER   NOT NULL,
    user_id           BIGSERIAL NOT NULL UNIQUE
);