SELECT
 a.*, b.width as itemWidth, b.name as itemName, c.name as contSelValue, c.color as rowColor
FROM
 CONTENT_TBL a
INNER JOIN
 ITEM_TBL b
ON
 a.item_id = b.id
LEFT JOIN
 ITEM_MST c
ON
 a.master_big_id = c.big_id
AND
 a.master_small_id = c.small_id
WHERE
 a.WTABLE_ID = /*wtableId*/'aaa'
ORDER BY
 a.ID, b.ITEM_ORDER



