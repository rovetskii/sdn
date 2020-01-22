CREATE TABLE teachers(
  id_teacher INT PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(255),
  password VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  surname VARCHAR(255),
  academic_title VARCHAR(255),
  degree VARCHAR(255),
  organization VARCHAR(255),
  post VARCHAR(255),
  photo VARCHAR(255),
   user_role INT

);

CREATE TABLE courses(
 id_course INT PRIMARY KEY AUTO_INCREMENT,
  id_teacher INT NOT NULL ,
  course_name VARCHAR(255),
FOREIGN KEY (id_teacher) REFERENCES teachers(id_teacher)
);

CREATE TABLE materials(
  id_material INT PRIMARY KEY AUTO_INCREMENT,
  id_course INT NOT NULL ,
  material_type VARCHAR(255),
  material_name VARCHAR(255),
  FOREIGN KEY (id_course) REFERENCES courses(id_course)
);

INSERT INTO teacherdb.teachers (login, password, first_name, last_name, surname, academic_title, degree, organization, post, photo, user_role) 
	VALUES (NULL, 'password', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
