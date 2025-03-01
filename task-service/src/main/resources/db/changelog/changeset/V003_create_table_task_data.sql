CREATE TABLE task_data
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title       VARCHAR(255) NOT NULL,
    completed   BOOLEAN      NOT NULL,
    date        TIMESTAMP,
    category_id BIGINT       REFERENCES category_data (id) ON DELETE SET NULL,
    priority_id BIGINT       REFERENCES priority_data (id) ON DELETE SET NULL,
    user_id     BIGINT       NOT NULL
);

COMMENT ON TABLE task_data IS 'This table stores task-related data';
COMMENT ON COLUMN task_data.id IS 'Primary key of the task';
COMMENT ON COLUMN task_data.title IS 'Title of the task';
COMMENT ON COLUMN task_data.completed IS 'Is the task completed';
COMMENT ON COLUMN task_data.date IS 'Date for the task';
COMMENT ON COLUMN task_data.category_id IS 'Foreign key, referenced to category_data';
COMMENT ON COLUMN task_data.priority_id IS 'Foreign key, referenced to priority_data';
COMMENT ON COLUMN task_data.user_id IS 'Foreign key, referenced to user_data';
