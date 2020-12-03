
    CREATE TABLE dislikes (
        DISLIKE_ID   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        COMMENT_ID   BIGINT,
        USER_ID      BIGINT
    );

    ALTER TABLE dislikes
        ADD CONSTRAINT comment_id_fk FOREIGN KEY (COMMENT_ID)
            REFERENCES comment;

    ALTER TABLE dislikes
        ADD CONSTRAINT user_id_fk FOREIGN KEY (USER_ID)
            REFERENCES user_entity;