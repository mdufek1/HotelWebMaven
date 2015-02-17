<%-- 
    Document   : index
    Created on : Feb 10, 2015, 8:07:27 PM
    Author     : Mike
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object obj = request.getAttribute("hotels");
    if(obj == null){
        response.sendRedirect("hdc");
    }
%>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
            <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<meta name="viewport" content="width=device-width">
		<link href="css/bootstrap.min.css" rel="stylesheet" />
                <link href="css/style.css" rel="stylesheet" type="text/css"/>
		<title>Third Bootstrap</title>
	</head>
	<body>

    <nav class="navbar	navbar-default	navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">Hotel Manager</a>

                <button type="button" class="navbar-toggle" data-toggle="collapse" datatarget="#collapse-menu">
                    <span class="sr-only">Toggle	navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse	navbar-collapse pull-left" id="collapse-menu">
                <ul class="nav	navbar-nav navbar-right">
                    <li><a href="<%= response.encodeURL("home.jsp")%>">Home</a></li>
                    <!--<li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Links<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="http://www.asp.net">ASP.NET</a></li>
                            <li><a href="http://www.w3schools.com">W3Schools</a></li>
                            <li><a href="http://stackoverflow.com">Stack	Overflow</a></li>
                        </ul>-->
                </ul>

            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div id="box">
            <div id="hotelSelectLeft">
                        <ul>
        <c:forEach var="hotel" items="${hotels}">
            <li class="hotel" id="${hotel.hotelID}">${hotel}</li>
        </c:forEach>
                        </ul>
                
            </div>
            <div id="box2">
                <form id="hotelForm" name="hotelForm" method="POST" action="<%response.encodeURL("${contextPath}/hdc");%>">
                    <table>
                        <tr>    <td>ID:</td>    <td><input type="text" id="id" readonly name="id" value="${selectedHotel.hotelID}"/></td>     <td>Name:</td>      <td><input type="text" id="name" name="name" value="${selectedHotel.hotelName}"/></td>   <td><button type="button" id="clear" class="btn btn-default">Clear Table</button></td><td><button type="button" id="update" class="btn btn-primary">Update Changes</button></td></tr>
                        <tr>    <td>Street Address:</td>    <td><input type="text" id="street" name="street" value="${selectedHotel.streetAddress}"/></td>     <td>City:</td>      <td><input type="text" id="city" name="city" value="${selectedHotel.city}"/></td> <td><button type="button" id="create" class="btn btn-info">New Record</button></td><td><button type="button" id="delete" class="btn btn-danger">!!Delete Record!!</button></td>  </tr>
                        <tr>    <td>State:</td>    <td><input type="text" id="state" name="state" value="${selectedHotel.state}"/></td>     <td>Zip(Postal) Code:</td>      <td><input type="text" id="zip" name="zip" value="${selectedHotel.postalCode}"/></td>   </tr>
                        <tr>    <td>Notes:</td>    <td><TextArea name="notes" id="notes" rows="5">${selectedHotel.note}</textarea></td>    </tr>
                        
                    </table>
                    
                </form>
        </div>


    </div>
		<script src="js/jquery-1.10.2.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
                <script>
                    $( document ).ready(function(){
                        var id = $('#id').val();
                        $('#'+id).addClass('selected');
                    })
                    
                    $('body').on('click', 'li.hotel', function() {
                        $('.hotel').removeClass('selected');
                        $(this).addClass('selected');
                        var ID = $(this).attr("id");
                       $('#hotelForm').attr("action", "?id="+ID+"&op=retrieve");
                       $('#hotelForm').submit();
                       // $.post("hdc", { id: ID, op: "retrieve" },  function( data ) {
                        //       $('body').html(data);
                        //})

                            
                        
                        
                    });
                    
                    $("#clear").click(function(){
                        $('#hotelForm').find("input, textarea").val("");
                        $('.selected').removeClass('selected');
                    })
                    
                    $("#update").click(function(){
                        var id = $('#id').val();
                        var name = $('#name').val();
                        var address = $('#street').val();
                        var city = $('#city').val();
                        var state = $('#state').val();
                        var zip = $('#zip').val();
                        var note = $('#notes').val();
                        $('#hotelForm').attr("action", "?id="+id+"&name="+name+"&address="+address+"&city="+city+"&state="+state+"&zip="+zip+"&note="+note+"&op=update");
                        $('#hotelForm').submit();
                    });
                    $('#create').click(function(){
                        var id = $('#id').val();
                        var name = $('#name').val();
                        var address = $('#street').val();
                        var city = $('#city').val();
                        var state = $('#state').val();
                        var zip = $('#zip').val();
                        var note = $('#notes').val();
                        
                        if(name.length < 2 || address.lenght < 2 || city.length < 1 || address < 2 || zip < 1 || note < 1){
                            
                        } else{
                        $('#hotelForm').attr("action", "?id="+id+"&name="+name+"&address="+address+"&city="+city+"&state="+state+"&zip="+zip+"&note="+note+"&op=create");
                        $('#hotelForm').submit();
                    }})
                    $('#delete').click(function(){
                        var id = $('#id').val();
                        var r = confirm("This will delete this record forever! Is this okay? If not, press cancel.");
                        if(r){
                        $('#hotelForm').attr("action", "?id="+id+"&op=delete");
                        $('#hotelForm').submit();
                        }else{
                            
                        }
                    })
                    
                </script>
	</body>
</html>
