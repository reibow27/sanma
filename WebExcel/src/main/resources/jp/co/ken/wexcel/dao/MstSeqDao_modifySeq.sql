UPDATE
 MST_SEQ_TBL
SET
 MST_SEQ = LAST_INSERT_ID(MST_SEQ + 1)
WHERE
 ID = /*id*/'aaa'


