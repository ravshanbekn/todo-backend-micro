CREATE TABLE category_data
(
    id                BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title             VARCHAR(255) NOT NULL,
    user_id           BIGINT       NOT NULL,
    completed_count   INTEGER      NOT NULL,
    uncompleted_count INTEGER      NOT NULL
);

COMMENT ON TABLE category_data IS 'This table stores category-related data';
COMMENT ON COLUMN category_data.id IS 'Primary key of the category';
COMMENT ON COLUMN category_data.title IS 'Title of the category';
COMMENT ON COLUMN category_data.user_id IS 'Foreign key, referenced to user_data';
COMMENT ON COLUMN category_data.completed_count IS 'Completed count of the tasks in the of the category';
COMMENT ON COLUMN category_data.uncompleted_count IS 'Uncompleted count of the tasks in the of the category';