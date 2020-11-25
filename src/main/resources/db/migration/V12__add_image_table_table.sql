
 CREATE TABLE image(
     ID      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     PROBLEM_ID  BIGINT,
     IMAGE_FILE BYTEA
 );

 ALTER TABLE image
    ADD CONSTRAINT problem_id_fk FOREIGN KEY (PROBLEM_ID)
        REFERENCES problem;


