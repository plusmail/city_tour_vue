-- Drop existing tables and sequences if they exist
DROP TABLE IF EXISTS users_itinerary, itinerary_landmarks;
DROP TABLE IF EXISTS users, itinerary, landmarks;
DROP SEQUENCE IF EXISTS seq_user_id, seq_itinerary_id;

BEGIN TRANSACTION;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE users (
  user_id int DEFAULT nextval('seq_user_id') primary key,
  username varchar(50) NOT NULL UNIQUE,
  password_hash varchar(200) NOT NULL,
  role varchar(50) NOT NULL
);

CREATE SEQUENCE seq_itinerary_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE itinerary(
  itinerary_id int DEFAULT nextval('seq_itinerary_id') primary key,
  name varchar(250) NOT NULL,
  event_date DATE NOT NULL,
  start_time TIME,
  end_time TIME,
  starting_point TEXT
);

CREATE TABLE landmarks(
  place_id varchar(50) primary key,
  name varchar(250),
  address TEXT,
  description TEXT,
  price_range int check(price_range >= 1 and price_range <= 3 ),
  star_rating int check(star_rating >= 1 and star_rating <= 5 ),
  category varchar(50),
  opening_time TIME,
  closing_time TIME
);

CREATE TABLE users_itinerary(
  user_id int,
  itinerary_id int,
  CONSTRAINT pk_users_itinerary primary key (user_id, itinerary_id),
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (itinerary_id) REFERENCES itinerary(itinerary_id)
);

CREATE TABLE itinerary_landmarks(
  itinerary_id int,
  place_id varchar(250),
  CONSTRAINT pk_itinerary_landmarks primary key (itinerary_id, place_id),
  FOREIGN KEY (itinerary_id) REFERENCES itinerary(itinerary_id),
  FOREIGN KEY (place_id) REFERENCES landmarks(place_id)
);

COMMIT;
