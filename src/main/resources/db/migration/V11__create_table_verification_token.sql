
          CREATE TABLE verification_token (
            TOKEN_ID     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
            TOKEN   VARCHAR (255) UNIQUE NOT NULL,
            USER_ID      BIGINT
          );

          ALTER TABLE verification_token
              ADD CONSTRAINT verification_token_user_fk FOREIGN KEY (USER_ID)
                  REFERENCES user_entity;

          ALTER TABLE user_entity
              ADD CONSTRAINT user_entity_verification_token_fk FOREIGN KEY (verification_token_token_id)
                   REFERENCES verification_token;