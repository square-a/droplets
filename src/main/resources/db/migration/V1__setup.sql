CREATE TABLE droplets (
    id              INT UNSIGNED    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    url             VARCHAR(255)    NOT NULL,
    title           VARCHAR(255)    NOT NULL,
    description     VARCHAR(255),
    image_url       VARCHAR(255)
);