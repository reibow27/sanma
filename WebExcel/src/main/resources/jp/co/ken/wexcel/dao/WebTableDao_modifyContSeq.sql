UPDATE
 WEB_TABLE_TBL
SET
 CONT_SEQ = LAST_INSERT_ID(CONT_SEQ + 1)
WHERE
 ID = /*id*/'aaa'


