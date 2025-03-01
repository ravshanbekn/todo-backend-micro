CREATE FUNCTION delete_task_trigger_function()
    RETURNS TRIGGER AS
$$
BEGIN

    IF (COALESCE(OLD.category_id, 0) > 0 AND
        OLD.completed = true)
    THEN
        UPDATE category_data
        SET completed_count = completed_count - 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;
    END IF;

    IF (COALESCE(OLD.category_id, 0) > 0 AND
        OLD.completed = false)
    THEN
        UPDATE category_data
        SET uncompleted_count = uncompleted_count - 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;
    END IF;

    IF OLD.completed = true
    THEN
        UPDATE stat_data
        SET completed_total = completed_total - 1
        WHERE user_id = OLD.user_id;
    ELSE
        UPDATE stat_data
        SET uncompleted_total = uncompleted_total - 1
        WHERE user_id = OLD.user_id;
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_task_trigger
    AFTER DELETE
    ON task_data
    FOR EACH ROW
EXECUTE FUNCTION delete_task_trigger_function();