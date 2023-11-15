CREATE TABLE IF NOT EXISTS contact_message (
                                               id SERIAL PRIMARY KEY,
                                               first_name VARCHAR(255) NOT NULL,
                                               email VARCHAR(255),
                                               gender VARCHAR(10),
                                               message TEXT NOT NULL,
                                               recaptcha VARCHAR(255) NOT NULL,
                                               recaptcha_response VARCHAR(255)
);

INSERT INTO contact_message (first_name, email, gender, message, recaptcha)
VALUES
    ('John', 'john@example.com', 'Male', 'Hello, how are you?', '03AFcWeA5Sq9...'),
    ('Alice', 'alice@example.com', 'Female', 'This is another message', '03AFcWeA5Sq9...'),
    ('Bob', 'bob@example.com', 'Male', 'A third message here', '03AFcWeA5Sq9...');
