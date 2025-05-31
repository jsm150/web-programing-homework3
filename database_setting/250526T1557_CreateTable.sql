PRAGMA foreign_keys = ON;

-- -----------------------------------------------------
-- Table User
-- "사용자"
-- -----------------------------------------------------
CREATE TABLE User (
  email TEXT PRIMARY KEY,              -- "이메일"
  name TEXT NOT NULL,                  -- "이름"
  password_hash TEXT NOT NULL          -- "비밀번호"
);

-- -----------------------------------------------------
-- Table Session
-- "세션 (로그인 정보)"
-- -----------------------------------------------------
CREATE TABLE Session (
  session_id TEXT PRIMARY KEY,
  email TEXT NOT NULL,                 -- "유저 이메일"
  FOREIGN KEY (email) REFERENCES User (email)
    ON DELETE CASCADE  -- 유저 삭제 시 관련 세션도 삭제
    ON UPDATE CASCADE  -- 유저 이메일 변경 시 세션의 이메일도 변경
);

-- Session.email 외래 키에 대한 인덱스
CREATE INDEX idx_session_email ON Session (email);

-- -----------------------------------------------------
-- Table Question
-- "질문"
-- -----------------------------------------------------
CREATE TABLE Question (
  number INTEGER PRIMARY KEY,          -- "문제번호"
  title TEXT NOT NULL                  -- "질문"
);

-- -----------------------------------------------------
-- Table QuestionContent
-- "질문 문항 (질문 목록)"
-- -----------------------------------------------------
CREATE TABLE QuestionContent (
  question_number INTEGER NOT NULL,    -- "문제번호"
  item_number INTEGER NOT NULL,        -- "문항번호"
  content TEXT NOT NULL,               -- "문항"
  value INTEGER NOT NULL,    		   -- "점수"
  PRIMARY KEY (question_number, item_number),
  FOREIGN KEY (question_number) REFERENCES Question (number)
    ON DELETE CASCADE  -- 질문 삭제 시 관련 문항도 삭제
    ON UPDATE CASCADE  -- 질문 번호 변경 시 문항의 질문 번호도 변경
);


-- -----------------------------------------------------
-- Table ResultContent
-- "결과 내용 텍스트"
-- -----------------------------------------------------
CREATE TABLE ResultContent (
  "index" INTEGER PRIMARY KEY,         -- PK (참고: "index"는 SQL 예약어이므로 따옴표로 묶음)
  content TEXT NOT NULL                -- "내용"
);

-- -----------------------------------------------------
-- Table Result
-- "검사 결과"
-- -----------------------------------------------------
CREATE TABLE Result (
  email TEXT PRIMARY KEY,              -- "유저 이메일" (User 테이블의 PK를 참조하는 FK이기도 함)
  value INTEGER NOT NULL,              -- "결과 점수"
  result_index INTEGER NOT NULL,       -- "결과 내용 인덱스" (ResultContent 테이블의 PK를 참조하는 FK)
  FOREIGN KEY (email) REFERENCES User (email)
    ON DELETE CASCADE  -- 유저 삭제 시 관련 결과도 삭제
    ON UPDATE CASCADE, -- 유저 이메일 변경 시 결과의 이메일도 변경
  FOREIGN KEY (result_index) REFERENCES ResultContent ("index")
    ON DELETE RESTRICT -- 결과 내용이 삭제되려고 할 때, 이를 참조하는 결과가 있으면 삭제 제한 (또는 CASCADE)
    ON UPDATE CASCADE  -- 결과 내용 인덱스 변경 시 결과의 인덱스도 변경
);

-- Result.result_index 외래 키에 대한 인덱스
CREATE INDEX idx_result_result_index ON Result (result_index);