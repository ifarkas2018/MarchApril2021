<%-- 
    Document   : update_prev
    Created on : 14-Mar-2019, 04:56:04
    Author     : Ingrid Farkas
    Project    : Lesen
--%>

<!-- update_prev.jsp - when the user clicks on the Buch Ändern (the navigation bar) this web page is shown -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lesen - Buch Ändern</title>
        <!-- link to the external style sheet -->
        <link href="css/templatecss.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <% 
            HttpSession hSession = LesenMethoden.returnSession(request);
            hSession.setAttribute("source_name", "Buch Ändern"); // on which page I am now
        %>
        
        <!-- header.jsp contains - company logo, company name and the navigation bar -->
        <%@ include file="header.jsp" %>
        <%@ include file="upd_del_title.jsp" %> 
        <!-- footer.jsp contains the footer of the web page --> 
        <%@ include file="footer.jsp" %> 
    </body>
</html>

