CREATE TABLE chat.role(
    id BIGINT PRIMARY KEY,
    name VARCHAR(25) UNIQUE NOT NULL

);
CREATE TABLE chat.user_roles (
                                 user_id BIGINT NOT NULL,
                                 role_id BIGINT NOT NULL,
                                 PRIMARY KEY (user_id, role_id),
                                 FOREIGN KEY (user_id) REFERENCES chat.users(id) ON DELETE CASCADE,
                                 FOREIGN KEY (role_id) REFERENCES chat.role(id) ON DELETE CASCADE
);