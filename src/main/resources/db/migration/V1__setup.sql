CREATE TABLE droplets (
    id              INT UNSIGNED    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    url             VARCHAR(2000)   NOT NULL,
    title           VARCHAR(50)     NOT NULL,
    description     VARCHAR(500),
    image_url       VARCHAR(2000)
);