DROP TABLE IF EXISTS users_itinerary;
DROP TABLE IF EXISTS itinerary_landmarks;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS itinerary;
DROP TABLE IF EXISTS landmarks;

-- Create users table
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password_hash VARCHAR(200) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

-- Create itinerary table
CREATE TABLE itinerary (
                           itinerary_id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(250) NOT NULL,
                           event_date DATE NOT NULL,
                           start_time TIME,
                           end_time TIME,
                           starting_point TEXT
);

-- Create landmarks table
CREATE TABLE landmarks (
                           place_id VARCHAR(50) PRIMARY KEY
);

-- Create users_itinerary linking table
CREATE TABLE users_itinerary (
                                 user_id INT,
                                 itinerary_id INT,
                                 PRIMARY KEY (user_id, itinerary_id),
                                 FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                                 FOREIGN KEY (itinerary_id) REFERENCES itinerary(itinerary_id) ON DELETE CASCADE
);

-- Create itinerary_landmarks linking table
CREATE TABLE itinerary_landmarks (
                                     itinerary_id INT,
                                     place_id VARCHAR(50),
                                     PRIMARY KEY (itinerary_id, place_id),
                                     FOREIGN KEY (itinerary_id) REFERENCES itinerary(itinerary_id) ON DELETE CASCADE,
                                     FOREIGN KEY (place_id) REFERENCES landmarks(place_id) ON DELETE CASCADE
);
