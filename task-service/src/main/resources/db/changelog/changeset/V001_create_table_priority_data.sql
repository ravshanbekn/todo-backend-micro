CREATE TABLE priority_data
(
    id      BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title   VARCHAR(255) NOT NULL,
    color   VARCHAR(255) NOT NULL,
    user_id BIGINT       NOT NULL
);

COMMENT ON TABLE priority_data IS 'This table stores priority-related data';
COMMENT ON COLUMN priority_data.id IS 'Primary key of the priority';
COMMENT ON COLUMN priority_data.title IS 'Title of the priority';
COMMENT ON COLUMN priority_data.color IS 'Color of the priority';
COMMENT ON COLUMN priority_data.user_id IS 'Foreign key, referenced to user_data';
