
    CREATE TABLE comment (
        COMMENT_ID       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        CONTENT          VARCHAR(255),
        CREATED_AT       TIMESTAMP,
        VOTES            INTEGER,
        PROBLEM_ID       BIGINT,
        USER_ID          BIGINT
    );
    CREATE TABLE district (
        DISTRICT_ID      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        DISTRICT_NAME    VARCHAR(50)
    );
    CREATE TABLE domain (
        DOMAIN_ID        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        DOMAIN_NAME      VARCHAR(50)
    );
    CREATE TABLE domain_problem (
        PROBLEM_ID       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        DOMAIN_ID        BIGINT
    );
    CREATE TABLE location (
        LOCATION_ID      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        LOCATION_NAME    VARCHAR(50),
        DISTRICT_ID      BIGINT
    );
    CREATE TABLE problem (
        PROBLEM_ID       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        CREATED_AT       TIMESTAMP,
        DESCRIPTION      VARCHAR(255),
        STATUS           VARCHAR(30),
        TITLE            VARCHAR(255),
        UPDATED_AT       TIMESTAMP,
        VOTES            INTEGER,
        DISTRICT_ID      BIGINT,
        LOCATION_ID      BIGINT,
        USER_ID          BIGINT
    );
    CREATE TABLE user_entity (
        USER_ID          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        EMAIL            VARCHAR(50) NOT NULL UNIQUE,
        FIRST_NAME       VARCHAR(50) NOT NULL,
        LAST_NAME        VARCHAR(50) NOT NULL,
        PASSWORD         VARCHAR(255) NOT NULL,
        ROLE             VARCHAR(50) NOT NULL,
        USER_STATUS      VARCHAR(50) NOT NULL
    );

    ALTER TABLE comment
        ADD CONSTRAINT comment_buss_fk FOREIGN KEY (PROBLEM_ID)
             REFERENCES problem;

    ALTER TABLE comment
        ADD CONSTRAINT comment_user_fk FOREIGN KEY (USER_ID)
            REFERENCES user_entity;

    ALTER TABLE domain_problem
        ADD CONSTRAINT domain_problem_fk FOREIGN KEY (DOMAIN_ID)
            REFERENCES DOMAIN;

    ALTER TABLE DOMAIN_PROBLEM
        ADD CONSTRAINT problem_domain FOREIGN KEY (PROBLEM_ID)
            REFERENCES problem;

    ALTER TABLE location
        ADD CONSTRAINT location_district_fk FOREIGN KEY (DISTRICT_ID)
            REFERENCES DISTRICT;

    ALTER TABLE problem
        ADD CONSTRAINT problem_district_fk FOREIGN KEY (DISTRICT_ID)
                REFERENCES district;

    ALTER TABLE problem
        ADD CONSTRAINT problem_location_fk FOREIGN KEY (LOCATION_ID)
            REFERENCES location;

    ALTER TABLE problem
        ADD CONSTRAINT problem_user_fk FOREIGN KEY (USER_ID)
                REFERENCES user_entity;