INSERT INTO bdn_db.role
VALUES (1,'ADMIN'),
       (2,'USER');
SELECT setval('bdn_db.role_id_seq', (SELECT max(id) FROM bdn_db.role));


INSERT INTO bdn_db.hospital
VALUES (1,'mav123','Moscati Avellino'),
       (2,'can123','Cardarelli Napoli'),
       (3,'san123','Santobono Napoli');
SELECT setval('bdn_db.hospital_id_seq', (SELECT max(id) FROM bdn_db.hospital));


INSERT INTO bdn_db.who_2016
VALUES (1,'Polycythemia vera (PV)'),
       (2,'Essential thrombocythemia (ET)'),
       (3,'Primary myelofibrosis (PMF) prefibrotic/early stage'),
       (4,'Primary myelofibrosis (PMF) overt fibrotic stage'),
       (5,'Post-polycythemia vera myelofibrosis (PPV-MF)'),
       (6,'Post-essential thrombocythemia myelofibrosis (PET-MF)'),
       (7,'Chronic myelomonocytic leukemia (CMML)'),
       (8,'Chronic neutrophilic leukemia (CNL)'),
       (9,'MPN Unclassifiable');
SELECT setval('bdn_db.who_2016_id_seq', (SELECT max(id) FROM bdn_db.who_2016));


INSERT INTO bdn_db.medical_record_category
VALUES (1,'MDS (Myelodysplastic)'),
       (2,'Myeloma'),
       (3,'Lymphoma'),
       (4,'CLL (Chronic Lymphocytic Leukemia)'),
       (5,'Myeloproliferative');
SELECT setval('bdn_db.medical_record_category_id_seq', (SELECT max(id) FROM bdn_db.medical_record_category));


INSERT INTO bdn_db.bone_marrow_fibrosis
VALUES (1,'MF-0'),
       (2,'MF-1'),
       (3,'MF-2'),
       (4,'MF-3');
SELECT setval('bdn_db.bone_marrow_fibrosis_id_seq', (SELECT max(id) FROM bdn_db.bone_marrow_fibrosis));


INSERT INTO bdn_db.symptom
VALUES (1,'Tiredness'),
       (2,'Loss of appetite'),
       (3,'Focusing problem'),
       (4,'Insomnia'),
       (5,'Formication'),
       (6,'Weight loss'),
       (7,'Abdominal pain'),
       (8,'Joint pain'),
       (9,'Fever'),
       (10,'Stasis');
SELECT setval('bdn_db.symptom_id_seq', (SELECT max(id) FROM bdn_db.symptom));


INSERT INTO bdn_db.therapy_line
VALUES (1,'First Line Therapy'),
       (2,'Second Line and Subsequent Therapy');
SELECT setval('bdn_db.therapy_line_id_seq', (SELECT max(id) FROM bdn_db.therapy_line));


INSERT INTO bdn_db.treatment_response
VALUES (1,'CR'),
       (2,'PR'),
       (3,'CI'),
       (4,'SD'),
       (5,'PD'),
       (6,'RELAPSE');
SELECT setval('bdn_db.treatment_response_id_seq', (SELECT max(id) FROM bdn_db.treatment_response));


-- psw: ivan
INSERT INTO bdn_db.user (id, email, first_name, hospital_code, last_name, password, role, username)
VALUES (1, 'admin@admin.com', 'admin', 'mav123', 'admin', '$2a$10$ANn8FaVd4LMjO6OybxGHHuLYj7Xa9nVa..mqRUPkLk4/gLhj/HQNq', 'ADMIN', 'admin'),
       (2, 'user@user.com', 'user', 'mav123', 'user', '$2a$10$mHUQt0sUv10//5Aqk/zGT.I9lWKbQpPLUzBtLYZQ/I4QcL/TBr87i', 'USER', 'user');
SELECT setval('bdn_db.user_id_seq', (SELECT max(id) FROM bdn_db.user));