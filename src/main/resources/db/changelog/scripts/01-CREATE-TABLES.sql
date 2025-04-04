CREATE TABLE chat.users (
                            id SERIAL PRIMARY KEY,
                            username VARCHAR(50) UNIQUE NOT NULL,
                            password TEXT NOT NULL,
                            email VARCHAR(100) UNIQUE NOT NULL,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE chat.chatrooms (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE chat.messages (
                               id SERIAL PRIMARY KEY,
                               chatroom_id INT REFERENCES chat.chatrooms(id) ON DELETE CASCADE,
                               sender_id INT REFERENCES chat.users(id) ON DELETE CASCADE,
                               content TEXT NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO chat.users (username, password, email) VALUES ('john_doe', 'hashed_password', 'john@example.com');
INSERT INTO chat.users (username, password, email) VALUES ('Joni balboa', 'hashed_password', 'joni@example.com');

INSERT INTO chat.chatrooms (name) VALUES ('General Chat');

INSERT INTO chat.messages (chatroom_id, sender_id, content) VALUES (1, 1, 'Hello, everyone!');
INSERT INTO chat.messages (chatroom_id, sender_id, content) VALUES (1, 2, 'Hi John!');
