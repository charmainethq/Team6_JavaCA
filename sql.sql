-- table User
INSERT INTO User (user_id, password)
VALUES ('stu_1_John', 'password123');
INSERT INTO User (user_id, password)
VALUES ('stu_2_Jane', 'securepassword');
INSERT INTO User (user_id, password)
VALUES ('stu_3_Michael', '123456');
INSERT INTO User (user_id, password)
VALUES ('stu_4_Sarah', 'pass123');
INSERT INTO User (user_id, password)
VALUES ('stu_5_David', 'qwerty');
INSERT INTO User (user_id, password)
VALUES ('lec_1_John', 'password123');
INSERT INTO User (user_id, password)
VALUES ('lec_2_Jane', 'securepassword');
INSERT INTO User (user_id, password)
VALUES ('lec_3_Michael', '123456');
INSERT INTO User (user_id, password)
VALUES ('lec_4_Sarah', 'pass123');
INSERT INTO User (user_id, password)
VALUES ('lec_5_David', 'qwerty');
INSERT INTO User (user_id, password)
VALUES ('adm_1_John', 'password123');
INSERT INTO User (user_id, password)
VALUES ('adm_2_Jane', 'securepassword');
INSERT INTO User (user_id, password)
VALUES ('adm_3_Michael', '123456');
INSERT INTO User (user_id, password)
VALUES ('adm_4_Sarah', 'pass123');
INSERT INTO User (user_id, password)
VALUES ('adm_5_David', 'qwerty');

-- table_Admin
INSERT INTO Admin (admin_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (1, 'adm_1_John', 'John', 'Doe', 'john@example.com', 123456789, '123 Main St');
INSERT INTO Admin (admin_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (2, 'adm_2_Jane', 'Jane', 'Smith', 'jane@example.com', 9876543210, '456 Elm St');
INSERT INTO Admin (admin_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (3, 'adm_3_Michael', 'Michael', 'Johnson', 'michael@example.com', 5555555555, '789 Oak Ave');
INSERT INTO Admin (admin_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (4, 'adm_4_Sarah', 'Sarah', 'Williams', 'sarah@example.com', 1111111111, '321 Pine Rd');
INSERT INTO Admin (admin_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (5, 'adm_5_David', 'David', 'Brown', 'david@example.com', 9999999999, '654 Cedar Ln');


-- table_Course
INSERT INTO Course (course_Num, name, description, credits, duration) 
VALUES (1, 'Mathematics', 'Introduction to Calculus', 3, 12);
INSERT INTO Course (course_Num, name, description, credits, duration) 
VALUES (2, 'Physics', 'Mechanics and Motion', 4, 16);
INSERT INTO Course (course_Num, name, description, credits, duration) 
VALUES (3, 'Computer Science', 'Introduction to Programming', 4, 14);
INSERT INTO Course (course_Num, name, description, credits, duration) 
VALUES (4, 'English Literature', 'Introduction to Shakespeare', 3, 10);
INSERT INTO Course (course_Num, name, description, credits, duration) 
VALUES (5, 'History', 'World History: Ancient Civilizations', 3, 12);

-- table_Student
INSERT INTO Student (student_id, user_id, first_Name, last_Name, email, contact_No, address, gpa)
VALUES (1, 'stu_1_John', 'John', 'Doe', 'john@example.com', 1234567890, '123 Main St', 3.8);
INSERT INTO Student (student_id, user_id, first_Name, last_Name, email, contact_No, address, gpa)
VALUES (2, 'stu_2_Jane', 'Jane', 'Smith', 'jane@example.com', 9876543210, '456 Elm St', 3.5);
INSERT INTO Student (student_id, user_id, first_Name, last_Name, email, contact_No, address, gpa)
VALUES (3, 'stu_3_Michael', 'Michael', 'Johnson', 'michael@example.com', 5555555555, '789 Oak Ave', 3.2);
INSERT INTO Student (student_id, user_id, first_Name, last_Name, email, contact_No, address, gpa)
VALUES (4, 'stu_4_Sarah', 'Sarah', 'Williams', 'sarah@example.com', 1111111111, '321 Pine Rd', 3.9);
INSERT INTO Student (student_id, user_id, first_Name, last_Name, email, contact_No, address, gpa)
VALUES (5, 'stu_5_David', 'David', 'Brown', 'david@example.com', 9999999999, '654 Cedar Ln', 3.7);

-- table_Lecturer
INSERT INTO Lecturer (lecturer_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (1, 'lec_1_John', 'John', 'Doe', 'john@example.com', 1234567890, '123 Main St');
INSERT INTO Lecturer (lecturer_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (2, 'lec_2_Jane', 'Jane', 'Smith', 'jane@example.com', 9876543210, '456 Elm St');
INSERT INTO Lecturer (lecturer_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (3, 'lec_3_Michael', 'Michael', 'Johnson', 'michael@example.com', 5555555555, '789 Oak Ave');
INSERT INTO Lecturer (lecturer_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (4, 'lec_4_Sarah', 'Sarah', 'Williams', 'sarah@example.com', 1111111111, '321 Pine Rd');
INSERT INTO Lecturer (lecturer_id, user_id, first_Name, last_Name, email, contact_No, address)
VALUES (5, 'lec_5_David', 'David', 'Brown', 'david@example.com', 9999999999, '654 Cedar Ln');

-- table Enrollment
INSERT INTO Enrollment (enrollment_id, student_id, enrollment_Status, score)
VALUES (1, 1, 'CONFIRMED', 85);
INSERT INTO Enrollment (enrollment_id, student_id, enrollment_Status, score)
VALUES (2, 2, 'CONFIRMED', 92);
INSERT INTO Enrollment (enrollment_id, student_id, enrollment_Status, score)
VALUES (3, 3, 'SUBMITTED', 0);
INSERT INTO Enrollment (enrollment_id, student_id, enrollment_Status, score)
VALUES (4, 4, 'COMPLETED', 78);
INSERT INTO Enrollment (enrollment_id, student_id, enrollment_Status, score)
VALUES (5, 5, 'WITHDRAWN', 0);


-- table_Course_Class
INSERT INTO Course_Class (course_id, start_Date, size, confirmed, room_Num, lecturer_id,enrollment_id)
VALUES (1, '2023-06-15', 30, 1, 'A101', 1, 1);
INSERT INTO Course_Class (course_id, start_Date, size, confirmed, room_Num, lecturer_id,enrollment_id)
VALUES (2, '2023-06-18', 25, 1, 'B202', 2, 2);
INSERT INTO Course_Class (course_id, start_Date, size, confirmed, room_Num, lecturer_id,enrollment_id)
VALUES (3, '2023-06-20', 40, 1, 'C303', 3, 3);
INSERT INTO Course_Class (course_id, start_Date, size, confirmed, room_Num, lecturer_id,enrollment_id)
VALUES (4, '2023-06-22', 20, 1, 'D404', 4, 4);
INSERT INTO Course_Class (course_id, start_Date, size, confirmed, room_Num, lecturer_id,enrollment_id)
VALUES (5, '2023-06-25', 35, 1, 'E505', 5, 5);
