
    CREATE TABLE votes (
        VOTE_ID      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        PROBLEM_ID   BIGINT,
        USER_ID      BIGINT
    );

    ALTER TABLE votes
        ADD CONSTRAINT problem_id_fk FOREIGN KEY (PROBLEM_ID)
            REFERENCES problem;

    ALTER TABLE votes
        ADD CONSTRAINT user_id_fk FOREIGN KEY (USER_ID)
            REFERENCES user_entity;