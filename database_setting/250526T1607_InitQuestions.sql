PRAGMA foreign_keys = ON;

-- Question 테이블 데이터 삽입
INSERT INTO Question (number, title) VALUES (1, '하루중 제일 기분이 좋을때는?');
INSERT INTO Question (number, title) VALUES (2, '나는 걸을때, 보통');
INSERT INTO Question (number, title) VALUES (3, '사람들과 얘기할때 나는');
INSERT INTO Question (number, title) VALUES (4, '편안히 쉴때 나는');
INSERT INTO Question (number, title) VALUES (5, '뭔가 아주 재미있는 일이 생겼을때 나는');
INSERT INTO Question (number, title) VALUES (6, '파티나 사람들이 많이 모이는 장소에 나는');
INSERT INTO Question (number, title) VALUES (7, '완전히 일에 몰두한 채로 열심히 일하다가 방해 받았을때 나는');
INSERT INTO Question (number, title) VALUES (8, '다음중 제일 좋아하는 색은?');
INSERT INTO Question (number, title) VALUES (9, '잠자리에 들어서 잠들기 바로 직전에 나는');
INSERT INTO Question (number, title) VALUES (10, '나는 이런 꿈을 자주 꾼다');

-- QuestionContent 테이블 데이터 삽입

-- 질문 1번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (1, 1, '아침', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (1, 2, '오후나 이른 저녁', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (1, 3, '늦은밤', 6);

-- 질문 2번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (2, 1, '보폭을 넓게, 빨리 걷는다.', 6);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (2, 2, '보폭을 좁게, 빨리 걷는다.', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (2, 3, '머리를 들고, 세상을 정면으로 바라보며 덜 빠르게 걷는다.', 7);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (2, 4, '바닥을 보며 덜 빠르게 걷는다.', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (2, 5, '아주 느리게 걷는다.', 1);

-- 질문 3번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (3, 1, '내 팔짱을 끼고 서서', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (3, 2, '두손을 마주잡고', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (3, 3, '한손이나 양손을 힙에 얹고', 5);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (3, 4, '얘기 나누는 상대방을 건드리거나 살짝 밀면서', 7);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (3, 5, '내 귀나 턱을 만지작거리거나 손가락으로 머리를 빗으면서', 6);

-- 질문 4번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (4, 1, '다리를 굽힌 채로 나란히 두고 앉는다.', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (4, 2, '다리를 꼬고 앉는다', 6);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (4, 3, '다리를 쭉 펴고 앉는다', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (4, 4, '한 쪽 다리를 접어 깔고 앉는다', 1);

-- 질문 5번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (5, 1, '아주 큰 소리로 즐거움을 숨기지 않고 웃는다', 6);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (5, 2, '웃지만 그다지 크지 않은 소리로 웃는다', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (5, 3, '조용히 소리를 별로 내지 않으며 웃는다', 3);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (5, 4, '오히려 쑥쓰러운 듯한 미소', 5);

-- 질문 6번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (6, 1, '사람들이 내 존재를 의식하도록 화려한 등장을 한다', 6);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (6, 2, '아는 사람들을 찾을 수 있을까해서 둘러보며 차분히 들어선다', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (6, 3, '시선을 끌지 않기 위해 할 수있는한 최대로 조용히 입장한다', 2);

-- 질문 7번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (7, 1, '휴식의 기회를 반갑게 맞이한다', 6);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (7, 2, '열라 짜증이 훨훨난다', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (7, 3, '그 중간 어딘가쯤', 4);

-- 질문 8번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (8, 1, '빨강이나 오렌지', 6);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (8, 2, '까망', 7);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (8, 3, '노랑이나 연한 파랑', 5);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (8, 4, '녹색', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (8, 5, '짙은 파랑이나 보라', 3);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (8, 6, '하양', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (8, 7, '갈색이나 회색', 1);

-- 질문 9번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (9, 1, '몸을 똑바로 펴고 누운 포즈이다', 7);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (9, 2, '엎드린 채로 몸을 죽 편 포즈이다', 6);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (9, 3, '약간 몸을 둥그린 채로 옆으로 누운포즈이다', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (9, 4, '한팔을 베고 있다', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (9, 5, '머리를 이불 밑에 넣고있다', 1);

-- 질문 10번 문항
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (10, 1, '싸우거나 애쓰는꿈', 4);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (10, 2, '무엇이나 누군가를 찾는꿈', 2);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (10, 3, '날아오르거나 떠오르는 꿈', 3);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (10, 4, '꿈은 잘 꾸지 않는다', 5);
INSERT INTO QuestionContent (question_number, item_number, content, value) VALUES (10, 5, '항상 좋은 느낌의 꿈이다', 1);