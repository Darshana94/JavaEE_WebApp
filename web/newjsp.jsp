<%-- 
    Document   : newjsp
    Created on : Jul 23, 2018, 11:22:30 PM
    Author     : Home
--%>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <%@include file="/assets/bootstrap.html" %><!--call in bootstrap imports html-->
        <%@include file="/assets/banner.html" %><!--call in banner html-->  
        <script>
            $(document).ready(function() {
                $('#paymenttable').DataTable();
                $('#claimtable').DataTable();
            } );
        </script>
    </head>
    <body>
        <div class="container">
            <% User user = (User)session.getAttribute("user");
                out.println(user.isUserValid());
            %>
            
            
        </div>
    </body>
</html>
