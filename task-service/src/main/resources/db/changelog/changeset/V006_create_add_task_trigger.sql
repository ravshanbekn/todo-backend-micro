CREATE FUNCTION add_task_trigger_function()
    RETURNS TRIGGER AS
$$
BEGIN

    INSERT INTO stat_data (user_id, completed_total, uncompleted_total)
    VALUES (NEW.user_id, 0, 0)
    ON CONFLICT (user_id) DO NOTHING;

    IF (COALESCE(NEW.category_id, 0) > 0 AND
        NEW.completed = true)
    THEN
        UPDATE category_data
        SET completed_count = completed_count + 1
        WHERE id = NEW.category_id
          AND user_id = NEW.user_id;
    END IF;

    IF (COALESCE(NEW.category_id, 0) > 0 AND
        NEW.completed = false)
    THEN
        UPDATE category_data
        SET uncompleted_count = uncompleted_count + 1
        WHERE id = NEW.category_id
          AND user_id = NEW.user_id;
    END IF;

    IF NEW.completed = true
    THEN
        UPDATE stat_data
        SET completed_total = completed_total + 1
        WHERE user_id = NEW.user_id;
    ELSE
        UPDATE stat_data
        SET uncompleted_total = uncompleted_total + 1
        WHERE user_id = NEW.user_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER add_task_trigger
    AFTER INSERT
    ON task_data
    FOR EACH ROW
EXECUTE FUNCTION add_task_trigger_function();