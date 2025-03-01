CREATE TABLE role_data
(
    id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL UNIQUE
);

COMMENT ON TABLE role_data IS 'This table stores role-related data';
COMMENT ON COLUMN role_data.id IS 'Primary key of the role';
COMMENT ON COLUMN role_data.name IS 'Name of the role';

INSERT INTO role_data (name)
VALUES ('USER'),
       ('ADMIN');