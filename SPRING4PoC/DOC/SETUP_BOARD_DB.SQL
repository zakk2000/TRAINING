DELIMITER //

DROP PROCEDURE IF EXISTS SETUP_BOARD_DATA//

CREATE PROCEDURE SETUP_BOARD_DATA()
BEGIN

	DECLARE SEQNUM INT DEFAULT '0';
	
	DECLARE ERRCODE CHAR(5) DEFAULT '00000';
	DECLARE ERRMSG TEXT;
	DECLARE ROWS INT DEFAULT '0';
	DECLARE PROCRESULT TEXT;
	
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		
		BEGIN
			
			GET DIAGNOSTICS CONDITION 1
				
				ERRCODE = RETURNED_SQLSTATE, ERRMSG = MESSAGE_TEXT;
		
		END;
	
	WHILE SEQNUM < 1000 DO
		
		SET SEQNUM = SEQNUM + 1;
		
		INSERT INTO BOARD (
			TITLE
			, CONT
			, CREATE_DATE
			, CREATED_BY
		) VALUES (
			CONCAT('게시물 제목 ', SEQNUM)
			, CONCAT('내용 ', SEQNUM)
			, CURRENT_TIMESTAMP
			, 'appuser01'
		);
	
	END WHILE;
	
	IF ERRCODE = '00000' THEN
		
		SELECT	COUNT(SEQ) INTO ROWS FROM BOARD;
		SET PROCRESULT = CONCAT('INSERTED ROWS COUNT : ', ROWS);
		
	ELSE
		
		SET PROCRESULT = CONCAT('INSERT FAILED... [ERROR CODE] : ', ERRCODE, ' [ERROR MESSAGE] ; ', ERRMSG);
		
	END IF;
	
	SELECT PROCRESULT;


END//

DELIMITER ;