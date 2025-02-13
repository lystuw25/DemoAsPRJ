<%-- 
    Document   : home
    Created on : 30 Oct 2024, 23:52:45
    Author     : A A
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Plan</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
        <style>
            body{
                font-family: Arial, sans-serif;
                background-color: #FBF3EA;
                margin: 0;
                padding: 0;
                display: flex;
                align-items: center; /* Đã thay đổi để căn giữa theo chiều dọc */
                justify-content: center; /* Căn giữa theo chiều ngang */
                min-height: 100vh;
            }
            
            h1{
                color: #333;
                margin-top: 20px;
                text-align: center; 
                font-family: "Decorative", sans-serifserif; 
                align-self: center;
            }
            
            .back-link {
                margin: 25px 0;
                text-decoration: none;
                color: #07ad90;
                font-weight: 500;
                font-size: 20px;
                display: flex;
                align-items: center;
                justify-content: flex-start; /* Giữ căn trái */
            }

            .back-link i {
                margin-right: 8px;
                color: #07ad90;
            }

            .container {
                display: flex; /* Sử dụng flexbox cho layout */
                flex-direction: column; /* Đổi hướng từ hàng sang cột để căn giữa */
                gap: 20px; /* Khoảng cách giữa các phần tử */
                width: 100%; /* Chiếm toàn bộ chiều rộng */
                max-width: 800px; /* Giới hạn chiều rộng tối đa cho container */
                margin: 0 20px; /* Để có một khoảng cách từ lề */
            }

            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                display: flex;
                flex-direction: column;
                align-items: flex-start; /* Giữ căn trái */
                gap: 15px;
            }

            .form-group {
                display: flex;
                align-items: center;
                gap: 10px;
                width: 100%;
            }

            .form-group label {
                width: 100px;
                font-weight: bold;
                color: #333;
                text-align: left;
            }

            form input[type="text"], form input[type="date"], form select {
                width: calc(100% - 120px);
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
                box-sizing: border-box;
                text-align: left;
            }

            form input[type="submit"] {
                padding: 12px;
                background-color: #FBDEC8;
                color: #333;
                border: none;
                border-radius: 5px;
                font-size: 18px;
                cursor: pointer;
                transition: background-color 0.3s;
                width: 100%;
                font-family: "Decorative", serif; 
            }

            form input[type="submit"]:hover {
                background-color: #678;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            }

            table th, table td {
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
                background-color: #f9f9f9;
                font-family: "Decorative", serif; 
            }

            table th {
                background-color: #FBDEC8;
                color: #333;
                font-size: 16px;
                text-align: center;
                font-family: "Decorative", monospace; 
            }

            table td input[type="text"] {
                text-align: left;
                width: 100%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            /* Định dạng cho nội dung bên phải */
            .sidebar {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 300px; /* Chiều rộng cố định cho sidebar */
                max-height: 500px; /* Chiều cao tối đa cho sidebar */
                overflow-y: auto; /* Cho phép cuộn khi nội dung vượt quá chiều cao */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="create" method="POST">
                <div style="margin: 25px 10px 0 10px">
                    <a href="${pageContext.request.contextPath}/home" style="text-decoration: none">
                        <i class="fa-solid fa-arrow-right-to-bracket fa-rotate-180 fa-xl" style="color: #07ad90"></i>
                        &nbsp;<span style="color: #07ad90; font-weight: 500; font-size: 20px; margin-top: 10px">Back to Home</span>
                    </a>
                </div>
                <h1>New Plan</h1>
                <div class="form-group">
                    <label>Title:</label>
                    <input type="text" name="name"/>
                </div>

                <div class="form-group">
                    <label>From:</label>
                    <input type="date" name="from"/>
                </div>

                <div class="form-group">
                    <label>To:</label>
                    <input type="date" name="to"/>
                </div>

                <div class="form-group">
                    <label>Workshop:</label>
                    <select name="did">
                        <c:forEach items="${requestScope.depts}" var="d">
                            <option value="${d.id}">${d.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <table border="1px">
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Cost</th>
                    </tr>
                    <c:forEach items="${requestScope.products}" var="p">
                        <tr>
                            <td>${p.name}<input type="hidden" value="${p.id}" name="pid"/></td>
                            <td><input type="text" name="quantity${p.id}"/></td>
                            <td><input type="text" name="cost${p.id}"/></td>
                        </tr>   
                    </c:forEach>
                </table>

                <input type="submit" name="Save"/>
            </form>
        </div>
    </body>
</html>
