CREATE TABLE user_role_data
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL REFERENCES user_data (id) ON DELETE CASCADE,
    role_id VARCHAR(64) NOT NULL,
    UNIQUE (user_id, role_id)
);