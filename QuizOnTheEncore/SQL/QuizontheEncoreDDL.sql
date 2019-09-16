--drop Quiz
DROP TABLE Quiz cascade constraint;

--drop Quizer
DROP TABLE Quizer cascade constraint;

--drop backup_quizer
DROP TABLE backup_Quizer cascade constraint;

--drop Solved
DROP TABLE Solve cascade constraint;

--create Quiz
CREATE TABLE Quiz (
       quiz_number          	number,
       question               	VARCHAR2(50 char) NOT NULL,
       answer         			number NOT NULL,
       content              	VARCHAR2(20) NOT NULL,
       PRIMARY KEY(quiz_number)
);

--create Quizer
CREATE TABLE Quizer (
       quizer_id     		VARCHAR2(20),
       password             VARCHAR2(20) NOT NULL,
       nickName  			VARCHAR2(50) NOT NULL,
       score                VARCHAR2(20) NOT NULL,
       solvingTime          VARCHAR2(20) NOT NULL,
       PRIMARY KEY(quizer_id)
);

--create backup_Quizer
CREATE TABLE backup_Quizer (
       quizer_id     		VARCHAR2(20),
       password             VARCHAR2(20) NOT NULL,
       nickName  			VARCHAR2(50) NOT NULL,
       score                VARCHAR2(20) NOT NULL,
       solvingTime          VARCHAR2(20) NOT NULL,
       time                 timestamp,            
       PRIMARY KEY(quizer_id)
);

--create solve
CREATE TABLE Solve (
       quizer_id        VARCHAR2(20) NOT NULL,
       solved_quiz_number      number NOT NULL,
       CONSTRAINT PK_Solve PRIMARY KEY
    (
        quizer_id,
        solved_quiz_number
    ),
    FOREIGN KEY (quizer_id) REFERENCES Quizer (quizer_id),
    FOREIGN KEY (quiz_number) REFERENCES Quiz (quiz_number)
);


--backup_Quizer trigger - insert/update/delete
CREATE OR REPLACE TRIGGER backup_Quizer
AFTER INSERT OR UPDATE OR DELETE
ON Quizer
FOR EACH ROW
BEGIN
   IF INSERTING THEN
      INSERT INTO backup_Quizer VALUES (:NEW.quizer_id, :NEW.password, :NEW.nickName, :NEW.score, :NEW.solvingTime, sysdate);
   ELSIF UPDATING THEN
      UPDATE backup_Quizer SET password=:NEW.password, nickName=:NEW.nickName, score=:NEW.score, solvingTime=:NEW.solvingTime, time=sysdate
      WHERE quizer_id=:OLD.quizer_id;
   ELSIF DELETING THEN
      UPDATE backup_Quizer SET password=:OLD.password, nickName=:OLD.nickName, score=:OLD.score, solvingTime=:OLD.solvingTime, time=sysdate
      WHERE quizer_id=:OLD.quizer_id;
   END IF;
END;
/

