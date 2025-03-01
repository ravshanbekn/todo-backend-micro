CREATE TABLE activity_data
(
    id        BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    uuid      VARCHAR(256) NOT NULL,
    activated BOOLEAN      NOT NULL,
    user_id   BIGINT UNIQUE
);

CREATE INDEX activity_user_activated ON activity_data(activated);
CREATE INDEX activity_user_id ON activity_data(uuid);
CREATE INDEX activity_user_uuid ON activity_data(uuid);
