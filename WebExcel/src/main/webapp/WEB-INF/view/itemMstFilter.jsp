<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xhtml="true" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>WebExcel</title>
<meta name="keywords" content="" lang="ja" xml:lang="ja" />
<meta name="description" content="" lang="ja" xml:lang="ja" />
<meta http-equiv="content-style-type" content="text/css" />
<meta http-equiv="content-script-type" content="text/javascript" />
<meta http-equiv="content-language" content="ja" />
<meta name="author" content="author" />
<link rel="stylesheet" href="/WebExcel/style.css" type="text/css" />
<link rel="stylesheet" href="/WebExcel/hyou.css" type="text/css" />
<script type="text/javascript" src="/WebExcel/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<c:forEach var="record" items="${itemMstList}" varStatus="status">
<a href="/WebExcel/list?wtableId=${wtableId}&itemMstBigId=${itemMstBigId}&itemMstSmallId=${record.smallId}">${record.name}</a><br/>
</c:forEach>
</body>
</html>
