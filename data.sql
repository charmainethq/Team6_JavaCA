SET time_zone = '+08:00';

INSERT INTO team6.admin (address,contact_no,email,first_name,last_name,username) VALUES
                                                                                     ('123 Admin Street','12345678','john.doe@example.com','John','Doe','adm_1_John'),
                                                                                     ('456 Admin Avenue','98765432','jane.smith@example.com','Jane','Smith','adm_2_Jane');
INSERT INTO team6.student (address,contact_no,email,first_name,gpa,last_name,username) VALUES
                                                                                           ('321 Student Street','44444444','jennifer.lim@example.com','Jennifer',3.8,'Lim','stu_1_Jennifer'),
                                                                                           ('654 Student Road','55555555','thomas.ng@example.com','Thomas',3.2,'Ng','stu_2_Thomas'),
                                                                                           ('987 Student Lane','66666666','jessica.wong@example.com','Jessica',3.5,'Wong','stu_3_Jessica'),
                                                                                           ('234 Student Avenue','77777777','william.koh@example.com','William',3.9,'Koh','stu_4_William'),
                                                                                           ('567 Student Street','88888888','melissa.tan@example.com','Melissa',3.6,'Tan','stu_5_Melissa'),
                                                                                           ('890 Student Road','99999999','benjamin.lau@example.com','Benjamin',3.3,'Lau','stu_6_Benjamin'),
                                                                                           ('111 Student Lane','11112222','sophia.goh@example.com','Sophia',3.7,'Goh','stu_7_Sophia'),
                                                                                           ('222 Student Avenue','33334444','daniel.chin@example.com','Daniel',3.1,'Chin','stu_8_Daniel'),
                                                                                           ('333 Student Street','55556666','emily.foo@example.com','Emily',3.4,'Foo','stu_9_Emily'),
                                                                                           ('444 Student Road','77778888','ryan.tay@example.com','Ryan',3.0,'Tay','stu_10_Ryan');
INSERT INTO team6.lecturer (address,contact_no,email,first_name,last_name,username) VALUES
                                                                                        ('789 Lecturer Lane','11111111','david.lee@example.com','David','Lee','lec_1_David'),
                                                                                        ('101 Lecturer Road','22222222','sarah.tan@example.com','Sarah','Tan','lec_2_Sarah'),
                                                                                        ('222 Lecturer Avenue','33333333','michael.chen@example.com','Michael','Chen','lec_3_Michael');
INSERT INTO team6.course (course_num,credits,description,duration,name) VALUES
                                                                            (101,4,'Introduction to programming concepts and problem-solving techniques using a high-level programming language.',12,'Introduction to Programming'),
                                                                            (201,3,'Fundamentals of database systems, including data modeling, SQL, and database design.',10,'Database Management Systems'),
                                                                            (301,3,'Building dynamic websites using HTML, CSS, and JavaScript.',10,'Web Development'),
                                                                            (401,4,'Principles of object-oriented programming and design patterns.',12,'Object-Oriented Programming'),
                                                                            (501,4,'Study of data structures and algorithms for efficient problem-solving.',12,'Data Structures and Algorithms'),
                                                                            (601,3,'Software development methodologies, requirements engineering, and software testing.',10,'Software Engineering'),
                                                                            (701,3,'Introduction to AI concepts, including machine learning and natural language processing.',10,'Artificial Intelligence'),
                                                                            (801,3,'Fundamentals of computer networks, network protocols, and network security.',10,'Computer Networks'),
                                                                            (901,4,'Building mobile applications for iOS and Android platforms.',12,'Mobile App Development'),
                                                                            (1001,3,'Introduction to cloud computing and cloud-based application development.',10,'Cloud Computing');
INSERT INTO team6.course_class (room_num, `size`, start_date, course_id, lecturer_id, confirmed_number) VALUES
                                                                                                            ('Room 1', 10, '2023-06-24', 1, 1, 4),
                                                                                                            ('Room 2', 20, '2023-06-24', 1, 2, 0),
                                                                                                            ('Room 3', 30, '2023-06-24', 1, 3, 0),
                                                                                                            ('Room 4', 40, '2023-06-24', 1, 1, 1),
                                                                                                            ('Room 5', 50, '2023-06-24', 1, 2, 2),
                                                                                                            ('Room 6', 50, '2023-06-24', 1, 3, 2),
                                                                                                            ('Room 1', 10, '2023-06-24', 2, 1, 8),
                                                                                                            ('Room 2', 20, '2023-06-24', 2, 2, 1),
                                                                                                            ('Room 3', 30, '2023-06-24', 2, 3, 1),
                                                                                                            ('Room 4', 40, '2023-06-24', 2, 1, 1);

INSERT INTO team6.course_class (room_num, `size`, start_date, course_id, lecturer_id, confirmed_number) VALUES
                                                                                                            ('Room 5', 50, '2023-06-24', 2, 2, 1),
                                                                                                            ('Room 6', 50, '2023-06-24', 2, 3, 2),
                                                                                                            ('Room 1', 10, '2023-06-24', 3, 1, 1),
                                                                                                            ('Room 2', 20, '2023-06-24', 3, 2, 2),
                                                                                                            ('Room 3', 30, '2023-06-24', 3, 3, 1),
                                                                                                            ('Room 4', 40, '2023-06-24', 3, 1, 2),
                                                                                                            ('Room 5', 50, '2023-06-24', 3, 2, 1),
                                                                                                            ('Room 6', 50, '2023-06-24', 3, 3, 1),
                                                                                                            ('Room 1', 10, '2023-06-24', 4, 1, 2),
                                                                                                            ('Room 2', 20, '2023-06-24', 4, 2, 1);

INSERT INTO team6.course_class (room_num, `size`, start_date, course_id, lecturer_id, confirmed_number) VALUES
                                                                                                            ('Room 3', 30, '2023-06-24', 4, 3, 1),
                                                                                                            ('Room 4', 40, '2023-06-24', 4, 1, 2),
                                                                                                            ('Room 5', 50, '2023-06-24', 4, 2, 2),
                                                                                                            ('Room 6', 50, '2023-06-24', 4, 3, 1),
                                                                                                            ('Room 1', 10, '2023-06-24', 5, 1, 1),
                                                                                                            ('Room 2', 20, '2023-06-24', 5, 2, 1),
                                                                                                            ('Room 3', 30, '2023-06-24', 5, 3, 2),
                                                                                                            ('Room 4', 40, '2023-06-24', 5, 1, 1),
                                                                                                            ('Room 5', 50, '2023-06-24', 5, 2, 1),
                                                                                                            ('Room 6', 50, '2023-06-24', 5, 3, 2);

INSERT INTO team6.course_class (room_num, `size`, start_date, course_id, lecturer_id, confirmed_number) VALUES
                                                                                                            ('Room 1', 10, '2023-06-24', 6, 1, 1),
                                                                                                            ('Room 2', 20, '2023-06-24', 6, 2, 1),
                                                                                                            ('Room 1', 10, '2023-06-24', 7, 1, 1),
                                                                                                            ('Room 2', 20, '2023-06-24', 7, 2, 0),
                                                                                                            ('Room 1', 10, '2023-06-24', 8, 1, 0),
                                                                                                            ('Room 2', 20, '2023-06-24', 8, 2, 0),
                                                                                                            ('Room 1', 10, '2023-06-24', 9, 1, 0),
                                                                                                            ('Room 2', 20, '2023-06-24', 9, 2, 0),
                                                                                                            ('Room 1', 10, '2023-06-24', 10, 1, 0),
                                                                                                            ('Room 2', 20, '2023-06-24', 10, 2, 0);

INSERT INTO team6.enrollment (enrollment_status,score,submitted_date,class_id,student_id) VALUES
                                                                                              ('CONFIRMED',79,'2023-06-02 10:45:00',1,3),
                                                                                              ('FAILED',45,'2023-06-02 11:45:00',1,2),
                                                                                              ('CONFIRMED',NULL,'2023-06-02 11:30:00',1,1),
                                                                                              ('CONFIRMED',NULL,'2023-06-02 8:30:00',1,4),
                                                                                              ('SUBMITTED',NULL,'2023-06-01 10:00:00',4,8),
                                                                                              ('COMPLETED',88,'2023-06-01 09:15:00',5,9),
                                                                                              ('COMPLETED',92,'2023-06-01 08:30:00',5,2),
                                                                                              ('REMOVED',NULL,'2023-06-01 10:30:00',6,4),
                                                                                              ('CONFIRMED',NULL,'2023-06-01 08:30:00',6,10),
                                                                                              ('COMPLETED',65,'2023-06-01 09:30:00',7,6);
INSERT INTO team6.enrollment (enrollment_status,score,submitted_date,class_id,student_id) VALUES
                                                                                              ('CONFIRMED',NULL,'2023-06-01 09:45:00',8,3),
                                                                                              ('COMPLETED',90,'2023-06-01 11:15:00',9,5),
                                                                                              ('COMPLETED',85,'2023-06-01 09:00:00',10,1),
                                                                                              ('CONFIRMED',NULL,'2023-06-01 08:45:00',11,7),
                                                                                              ('COMPLETED',63,'2023-06-02 12:15:00',12,8),
                                                                                              ('SUBMITTED',NULL,'2023-06-02 16:45:00',12,2),
                                                                                              ('COMPLETED',60,'2023-06-02 12:45:00',13,4),
                                                                                              ('COMPLETED',55,'2023-06-02 11:00:00',14,3),
                                                                                              ('SUBMITTED',NULL,'2023-06-02 11:30:00',14,9),
                                                                                              ('FAILED',45,'2023-06-02 14:30:00',15,1);
INSERT INTO team6.enrollment (enrollment_status,score,submitted_date,class_id,student_id) VALUES
                                                                                              ('SUBMITTED',NULL,'2023-06-02 11:45:00',16,6),
                                                                                              ('COMPLETED',70,'2023-06-02 10:45:00',16,10),
                                                                                              ('SUBMITTED',NULL,'2023-06-02 13:30:00',17,5),
                                                                                              ('COMPLETED',67,'2023-06-03 13:00:00',18,2),
                                                                                              ('CONFIRMED',NULL,'2023-06-03 14:30:00',19,8),
                                                                                              ('FAILED',35,'2023-06-03 16:00:00',19,4),
                                                                                              ('COMPLETED',78,'2023-06-03 11:45:00',20,1),
                                                                                              ('COMPLETED',73,'2023-06-03 16:00:00',21,6),
                                                                                              ('COMPLETED',76,'2023-06-03 13:45:00',22,9),
                                                                                              ('FAILED',24,'2023-06-03 15:15:00',22,3);
INSERT INTO team6.enrollment (enrollment_status,score,submitted_date,class_id,student_id) VALUES
                                                                                              ('COMPLETED',71,'2023-06-02 10:00:00',23,7),
                                                                                              ('COMPLETED',88,'2023-06-03 13:00:00',23,10),
                                                                                              ('COMPLETED',79,'2023-06-03 14:45:00',24,5),
                                                                                              ('WITHDRAWN',NULL,'2023-06-04 10:15:00',25,1),
                                                                                              ('COMPLETED',88,'2023-06-04 10:15:00',26,6),
                                                                                              ('COMPLETED',81,'2023-06-04 16:45:00',27,8),
                                                                                              ('COMPLETED',75,'2023-06-04 09:30:00',27,3),
                                                                                              ('COMPLETED',83,'2023-06-04 10:45:00',28,4),
                                                                                              ('SUBMITTED',NULL,'2023-06-03 14:15:00',29,7),
                                                                                              ('COMPLETED',90,'2023-06-04 16:00:00',30,9);
INSERT INTO team6.enrollment (enrollment_status,score,submitted_date,class_id,student_id) VALUES
                                                                                              ('COMPLETED',88,'2023-06-04 12:30:00',30,2),
                                                                                              ('CONFIRMED',NULL,'2023-06-04 11:00:00',31,5),
                                                                                              ('COMPLETED',87,'2023-06-04 15:15:00',32,10),
                                                                                              ('COMPLETED',94,'2023-06-04 08:30:00',33,7);
