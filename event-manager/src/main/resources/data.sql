-- === EVENTS ===
INSERT INTO event (event_name, event_date, capacity, event_type, status)
VALUES
  ('Obra: Hamlet', '2025-08-10 20:00:00',10, 'PLAY', 'ACTIVE'),
  ('Recital: Rock & Roll Night', '2025-08-15 22:00:00', 10, 'RECITAL', 'ACTIVE'),
  ('Conferencia: Innovación y Futuro', '2025-08-20 18:00:00', 10, 'CONFERENCE', 'ACTIVE'),
  ('Obra: Sueño de una noche de verano', '2025-08-25 21:00:00', 10, 'PLAY', 'ACTIVE'),
  ('Recital: La Noche del Metal', '2025-08-30 23:00:00', 10, 'RECITAL', 'ACTIVE');

-- === ATTENDEES ===
INSERT INTO attendee (attendee_name, attendee_email, attendee_password, has_free_pass, token, status)
VALUES
  ('Lucía Fernández', 'lucia.fernandez@example.com', '12345', false, 'ADMIN', 'ACTIVE'),
  ('Martín Gómez', 'martin.gomez@example.com','67890', false, 'USER', 'ACTIVE'),
  ('Camila Ruiz', 'camila.ruiz@example.com', '98765', false, 'USER', 'ACTIVE');

-- === RESERVATIONS ===

INSERT INTO reservation (reservation_date, ticket_type, event_id, attendee_id, free_pass, status)
VALUES
  ('2025-07-01 10:30:00', 'GENERAL', 1, 1, false, 'ACTIVE'),
  ('2025-07-01 11:00:00', 'VIP', 1, 1, false, 'ACTIVE');


INSERT INTO reservation (reservation_date, ticket_type, event_id, attendee_id, free_pass, status)
VALUES
  ('2025-07-01 14:00:00', 'CAMPO', 2, 2, false, 'ACTIVE'),
  ('2025-07-01 15:30:00', 'PLATEA', 2, 2, false, 'ACTIVE');


INSERT INTO reservation (reservation_date, ticket_type, event_id, attendee_id, free_pass, status)
VALUES
  ('2025-07-04 09:00:00', 'MEET_AND_GREET', 3, 3, false, 'ACTIVE');


INSERT INTO reservation (reservation_date, ticket_type, event_id, attendee_id, free_pass, status)
VALUES
  ('2025-07-02 12:00:00', 'GENERAL', 4, 2, false, 'ACTIVE');


INSERT INTO reservation (reservation_date, ticket_type, event_id, attendee_id, free_pass, status)
VALUES
  ('2025-07-03 16:00:00', 'PALCO', 5, 3, false, 'ACTIVE');