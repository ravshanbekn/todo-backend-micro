CREATE TABLE user_data
(
    id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    role     VARCHAR(64)  NOT NULL
);

COMMENT ON TABLE user_data IS 'This table stores user data';
COMMENT ON COLUMN user_data.id IS 'User''s primary key';
COMMENT ON COLUMN user_data.email IS 'User''s email address';
COMMENT ON COLUMN user_data.password IS 'User''s password';
COMMENT ON COLUMN user_data.name IS 'User''s name';

INSERT INTO user_data (email, password, name, role)
VALUES ('admin@email.com', 'adminPassword', 'admin', 'ADMIN');
