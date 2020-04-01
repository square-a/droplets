CREATE TABLE groups (
    id              INT UNSIGNED        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code            SMALLINT UNSIGNED   NOT NULL
);

CREATE TABLE users (
    id              INT UNSIGNED        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(50)         NOT NULL,
    group_id        INT UNSIGNED        NOT NULL,

    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
);

CREATE TABLE droplets (
    id              INT UNSIGNED    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    url             VARCHAR(2000)   NOT NULL,
    title           VARCHAR(50)     NOT NULL,
    description     VARCHAR(500),
    image_url       VARCHAR(2000),
    user_id         INT UNSIGNED,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);