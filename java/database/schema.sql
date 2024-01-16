
DROP TABLE IF EXISTS users_itinerary, itinerary_landmarks;
DROP TABLE IF EXISTS users, itinerary, landmarks;
DROP SEQUENCE IF EXISTS seq_users_id, seq_itinerary_id;


BEGIN TRANSACTION;


CREATE SEQUENCE seq_users_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_users_id'),
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	emergency_contact int,
	phone_number int,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE SEQUENCE seq_itinerary_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;



CREATE TABLE itinerary(
	itinerary_id int NOT NULL DEFAULT nextval('seq_itinerary_id'),
	event_date DATE NOT NULL,
	event_start_time TIME,
	event_end_time TIME,
	CONSTRAINT PK_itinerary PRIMARY KEY (itinerary_id)
);

CREATE TABLE landmarks(
	place_id varchar(250) NOT NULL,
	landmark_name varchar(250) NOT NULL,
	opening_time TIME,
	closing_time TIME,
	landmark_description TEXT,
	tour_price int,
	landmark_category varchar(50),
	star_rating int,
	location_address varchar,
	CONSTRAINT PK_landmarks PRIMARY KEY (place_id)
);

CREATE TABLE users_itinerary(
	itinerary_id int,	
	user_id int,
	CONSTRAINT pk_users_itinerary primary key (itinerary_id, user_id),
	foreign key (itinerary_id) references itinerary(itinerary_id),
	foreign key (user_id) references users(user_id)
);

CREATE TABLE itinerary_landmarks(
	itinerary_id int,
	place_id varchar,
	CONSTRAINT pk_itinerary_landmarks primary key (itinerary_id, place_id),
	foreign key (itinerary_id) references itinerary(itinerary_id),
	foreign key (place_id) references landmarks(place_id)
);

COMMIT TRANSACTION;
