INSERT INTO user (email, password, enabled) VALUES
  ('ash@gmail.com', 'test', true),
  ('test@gmail.com', 'test', true);

  INSERT INTO role (userRole) VALUES
    ('ADMIN');

INSERT INTO foodtruck (name, type, distance) VALUES
  ('Paratha Junction', 'Indian', '0.6 miles'),
  ('Antojitos Chapines', 'Mexican', '1.2 miles'),
  ('Eat on Monday', 'American', '2.1 miles');