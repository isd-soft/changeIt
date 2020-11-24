
    CREATE TABLE comment_votes (
        COMMENT_VOTE_ID      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        COMMENT_ID   BIGINT,
        USER_ID      BIGINT
    );

    ALTER TABLE comment_votes
        ADD CONSTRAINT comment_id_fk FOREIGN KEY (COMMENT_ID)
            REFERENCES comment;

    ALTER TABLE comment_votes
        ADD CONSTRAINT user_id_fk FOREIGN KEY (USER_ID)
            REFERENCES user_entity;