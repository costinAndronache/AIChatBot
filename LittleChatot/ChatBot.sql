DROP TABLE Persoane
/
CREATE TABLE Persoane 
    (PERSNO NUMBER(3) PRIMARY KEY, 
	PERSNAME CHAR(50) UNIQUE NOT NULL, 
	AGE NUMBER(2) NULL,
	JOB CHAR(50) NULL,
	INFO VARCHAR(1000) NULL
	) 
/

DROP TABLE Questions
/
CREATE TABLE Questions 
    (QUEID NUMBER(8) PRIMARY KEY, 
	QUESTION CHAR(255) UNIQUE NOT NULL, 
	TIPNAME CHAR(50) NULL,
	INFO VARCHAR(1000) NULL
	) 
/

DROP TABLE QuestionsAsked
/
CREATE TABLE QuestionsAsked 
    (PERSNAME CHAR(50) NOT NULL, 
	QUEID NUMBER(10) NOT NULL
	) 
/


DROP TABLE QuestionsVersion
/
CREATE TABLE QuestionsVersion 
    (QUEVERSID NUMBER(10) PRIMARY KEY, 
	QUEID NUMBER(10) NOT NULL, 
	QUESTION CHAR(255) UNIQUE NOT NULL
	) 
/

DROP TABLE Answers
/
CREATE TABLE Answers 
    (ANSID NUMBER(10) PRIMARY KEY, 
	PERSNO NUMBER(3) NULL,
	QUEID NUMBER(8) NOT NULL,
	QUEVERSID NUMBER(10) NOT NULL,
	ANSWER CHAR(255) NOT NULL, 
	INFO VARCHAR(1000) NULL
	) 
/

DROP TABLE Tip
/
CREATE TABLE Tip 
    (TIPID NUMBER(10) PRIMARY KEY, 
	TIPNAME CHAR(50) UNIQUE NOT NULL,
	INFO VARCHAR(1000) NULL
	) 
/

COMMIT;


--TRIGGERE

	--trigger 1
DROP SEQUENCE Persoane_seq
/
CREATE SEQUENCE Persoane_seq
	MAXVALUE 999
	START WITH 1
	INCREMENT BY 1;

DROP TRIGGER Persoane_id_trigger
/
CREATE OR REPLACE TRIGGER Persoane_id_trigger 
BEFORE INSERT ON Persoane 
FOR EACH ROW

BEGIN
  SELECT Persoane_seq.NEXTVAL
  INTO   :new.PERSNO
  FROM   dual;
END;
/ 

	--trigger 2
DROP SEQUENCE Questions_seq
/
CREATE SEQUENCE Questions_seq
	MAXVALUE 999
	START WITH 1
	INCREMENT BY 1;

DROP TRIGGER Questions_id_trigger
/
CREATE OR REPLACE TRIGGER Questions_id_trigger 
BEFORE INSERT ON Questions 
FOR EACH ROW

BEGIN
  SELECT Questions_seq.NEXTVAL
  INTO   :new.QUEID
  FROM   dual;
END;
/ 

	--trigger 3
DROP SEQUENCE QuestionsVersion_seq
/
CREATE SEQUENCE QuestionsVersion_seq
	MAXVALUE 999
	START WITH 1
	INCREMENT BY 1;

DROP TRIGGER QuestionsVersion_id_trigger
/
CREATE OR REPLACE TRIGGER QuestionsVersion_id_trigger 
BEFORE INSERT ON QuestionsVersion 
FOR EACH ROW

BEGIN
  SELECT QuestionsVersion_seq.NEXTVAL
  INTO   :new.QUEVERSID
  FROM   dual;
END;
/ 

	--trigger 4
DROP SEQUENCE Answers_seq
/
CREATE SEQUENCE Answers_seq
	MAXVALUE 999
	START WITH 1
	INCREMENT BY 1;

DROP TRIGGER Answers_id_trigger
/
CREATE OR REPLACE TRIGGER Answers_id_trigger 
BEFORE INSERT ON Answers 
FOR EACH ROW

BEGIN
  SELECT Answers_seq.NEXTVAL
  INTO   :new.ANSID
  FROM   dual;
END;
/ 


	--trigger 5
DROP SEQUENCE Tip_seq
/
CREATE SEQUENCE Tip_seq
	MAXVALUE 999
	START WITH 1
	INCREMENT BY 1;

DROP TRIGGER Tip_id_trigger
/
CREATE OR REPLACE TRIGGER Tip_id_trigger 
BEFORE INSERT ON Tip 
FOR EACH ROW

BEGIN
  SELECT Tip_seq.NEXTVAL
  INTO   :new.TIPID
  FROM   dual;
END;
/ 


-- Inserturi

INSERT INTO Persoane (PERSNAME, AGE, JOB) VALUES ('Alex',22,'Software Developer')
/


INSERT INTO Tip (TIPNAME, INFO) VALUES ('Basic','details about person')
/



INSERT INTO Questions (QUESTION, TIPNAME) VALUES ('your name','Basic')
/

INSERT INTO QuestionsVersion (QUEID, QUESTION) VALUES (1,'What is your name?')
/
INSERT INTO QuestionsVersion (QUEID, QUESTION) VALUES (1,'Could you tell me your name, please?')
/

INSERT INTO Questions (QUESTION, TIPNAME) VALUES ('your age how old','Basic')
/
INSERT INTO QuestionsVersion (QUEID, QUESTION) VALUES (2,'How old are you?')
/

INSERT INTO Questions (QUESTION, TIPNAME) VALUES ('you your job work working place','Basic')
/
INSERT INTO QuestionsVersion (QUEID, QUESTION) VALUES (3,'What do you do for a living?')
/
INSERT INTO QuestionsVersion (QUEID, QUESTION) VALUES (3,'What job do you have?')
/
INSERT INTO QuestionsVersion (QUEID, QUESTION) VALUES (3,'What kind of work do you do?')
/