CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active INT DEFAULT 1,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance INT,
    user_id BIGINT
);

ALTER TABLE card
    ADD CONSTRAINT fk_card_id
        FOREIGN KEY(user_id) REFERENCES user(id);