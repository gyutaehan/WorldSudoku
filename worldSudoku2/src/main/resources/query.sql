CREATE TABLE MEMBER
		(
			USERID varchar2(500) primary key,
			PASSWORD varchar2(500),
			POINT number
		);
		
	CREATE TABLE BOARD(
		BOARD_SEQ number primary key,
		title varchar2(1000),
		content varchar2(2000),
		userid varchar2(500),
		hitcount number,
		CONSTRAINT BOARD_FK_USERID foreign key(userid) references member(userid)
	);
	
	CREATE TABLE RECORD(
			RECORD_SEQ number primary key,
			USER_RECORD varchar2(500),
			DIFFICULTY varchar2(500),
			USERID varchar2(500),
			CONSTRAINT RECORD_FK_USERID foreign key(userid) references member(userid)
	);
	create SEQUENCE RECORD_SEQ;
	CREATE SEQUENCE BOARD_SEQ;
