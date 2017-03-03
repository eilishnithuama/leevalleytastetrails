CREATE TABLE TOUR (
  tour_id       INTEGER AUTO_INCREMENT,
  name     VARCHAR(50),
  date	Date,
  amount_booked INTEGER,
  completed INTEGER,

  PRIMARY KEY (tour_id)
);
