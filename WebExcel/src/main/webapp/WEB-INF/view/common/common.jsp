<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>  
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>  
<%@taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib prefix="s" uri="http://sastruts.seasar.org" %>
<%@taglib prefix="f" uri="http://sastruts.seasar.org/functions" %>
<%
    response.setHeader("Cache-Control","no-store");         //HTTP 1.1
    response.setHeader("Pragma","no-cache");                //HTTP 1.0
    response.setDateHeader ("Expires", 0);                  //prevents caching at the proxy server
%>