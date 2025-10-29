DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS streams CASCADE;
DROP TABLE IF EXISTS users CASCADE;


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE streams (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    content VARCHAR(140) NOT NULL CHECK (char_length(content) <= 140),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    stream_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (stream_id) REFERENCES streams (id) ON DELETE CASCADE
);


CREATE INDEX idx_posts_user ON posts(user_id);
CREATE INDEX idx_posts_stream ON posts(stream_id);


INSERT INTO streams (name, description)
VALUES ('Global Stream', 'Stream global para todos los posts');


INSERT INTO users (username, email, password)
VALUES ('admin', 'admin@microtweet.local', 'admin123');


INSERT INTO posts (content, user_id, stream_id)
VALUES ('¡Bienvenido a Microtweet! ', 1, 1);
