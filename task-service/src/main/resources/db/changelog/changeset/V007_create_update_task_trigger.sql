CREATE FUNCTION update_task_trigger_function()
    RETURNS TRIGGER AS
$$
BEGIN

    /* If the task status changed from false to true and the category remained the same */
    IF (COALESCE(OLD.category_id, 0) = COALESCE(NEW.category_id, 0) AND
        OLD.completed = false AND
        NEW.completed = true)
    THEN
        /* Decrease the uncompleted task count and increase the completed task count in the same category */
        UPDATE category_data
        SET uncompleted_count = uncompleted_count - 1,
            completed_count   = completed_count + 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;

        /* Total statistics */
        UPDATE stat_data
        SET uncompleted_total = uncompleted_total - 1,
            completed_total = completed_total + 1
        WHERE user_id = OLD.user_id;

    END IF;


    /* If the task status changed from true to false and the category remained the same */
    IF (COALESCE(OLD.category_id, 0) = COALESCE(NEW.category_id, 0) AND
        OLD.completed = true AND
        NEW.completed = false)
    THEN
        /* Decrease the completed task count and increase the uncompleted task count in the same category */
        UPDATE category_data
        SET completed_count   = completed_count - 1,
            uncompleted_count = uncompleted_count + 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;

        /* Total statistics */
        UPDATE stat_data
        SET completed_total = completed_total - 1,
            uncompleted_total = uncompleted_total + 1
        WHERE user_id = OLD.user_id;

    END IF;


    /* If the category was changed but the task remains completed (completed = true) */
    IF (COALESCE(OLD.category_id, 0) <> COALESCE(NEW.category_id, 0) AND
        OLD.completed = true AND
        NEW.completed = true)
    THEN
        /* Decrease the completed task count in the old category */
        UPDATE category_data
        SET completed_count = completed_count - 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;

        /* Increase the completed task count in the new category */
        UPDATE category_data
        SET completed_count = completed_count + 1
        WHERE id = NEW.category_id
          AND user_id = NEW.user_id;

        /* Total statistics do not change */
    END IF;


    /* If the category was changed but the task remains uncompleted (completed = false) */
    IF (COALESCE(OLD.category_id, 0) <> COALESCE(NEW.category_id, 0) AND
        OLD.completed = false AND
        NEW.completed = false)
    THEN
        /* Decrease the uncompleted task count in the old category */
        UPDATE category_data
        SET uncompleted_count = uncompleted_count - 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;

        /* Increase the uncompleted task count in the new category */
        UPDATE category_data
        SET uncompleted_count = uncompleted_count + 1
        WHERE id = NEW.category_id
          AND user_id = NEW.user_id;

        /* Total statistics do not change */
    END IF;


    /* If both the category and task status changed from completed (true) to uncompleted (false) */
    IF (COALESCE(OLD.category_id, 0) <> COALESCE(NEW.category_id, 0) AND
        OLD.completed = true AND
        NEW.completed = false)
    THEN
        /* Decrease the completed task count in the old category */
        UPDATE category_data
        SET completed_count = completed_count - 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;

        /* Increase the uncompleted task count in the new category */
        UPDATE category_data
        SET uncompleted_count = uncompleted_count + 1
        WHERE id = NEW.category_id
          AND user_id = NEW.user_id;

        /* Total statistics*/
        UPDATE stat_data
        SET uncompleted_total = uncompleted_total + 1,
            completed_total = completed_total - 1
        WHERE user_id = OLD.user_id;

    END IF;


    /* If both the category and task status changed from uncompleted (false) to completed (true) */
    IF (COALESCE(OLD.category_id, 0) <> COALESCE(NEW.category_id, 0) AND
        OLD.completed = false AND
        NEW.completed = true)
    THEN
        /* Decrease the uncompleted task count in the old category */
        UPDATE category_data
        SET uncompleted_count = uncompleted_count - 1
        WHERE id = OLD.category_id
          AND user_id = OLD.user_id;

        /* Increase the completed task count in the new category */
        UPDATE category_data
        SET completed_count = completed_count + 1
        WHERE id = NEW.category_id
          AND user_id = NEW.user_id;

        /* Total statistics */
        UPDATE stat_data
        SET uncompleted_total = uncompleted_total - 1,
            completed_total = completed_total + 1
        WHERE user_id = OLD.user_id;

    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_task_trigger
    AFTER UPDATE
    ON task_data
    FOR EACH ROW
EXECUTE FUNCTION update_task_trigger_function();