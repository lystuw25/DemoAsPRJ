<%-- 
    Document   : viewPlan
    Created on : 30 Oct 2024, 23:56:53
    Author     : A A
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Plan</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FBF3EA;
                margin: 0;
                padding: 0;
                align-items: center;
                min-height: 100vh;
            }

            h1 {
                text-align: left;
                margin: 20px 150px;
                color: #333;
                font-family: "Decorative", serif; 
                font-style: italic; /* In nghiêng */
            }

            table {
                width: 80%;
                border-collapse: collapse;
                margin: 20px auto;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background-color: #fff;
            }

            thead tr {  
                /*background-color: #4CAF50;*/
                background-color: #FBDEC8;
                color: #fff;
                text-align: left;
            }

            thead tr th {
                padding: 12px;
                font-size: 16px;
                color: #333;
                font-family: "Decorative", monospace; 
            }

            tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tbody tr td {
                padding: 12px;
                border-bottom: 1px solid #ddd;
                text-align: left;
                font-family: "Decorative", serif; 
            }

            tbody tr:hover {
                background-color: #f1f1f1;
            }

        </style>
    </head>
    <body>
        <div style="margin: 25px 10px  20px 150px ">
            <a href="${pageContext.request.contextPath}/home" style="text-decoration: none">
                <i class="fa-solid fa-arrow-right-to-bracket fa-rotate-180 fa-xl" style="color: #07ad90"></i>
                &nbsp;<span
                    style="color: #07ad90; font-weight: 500; font-size: 20px; margin-top: 10px">Back to Home</span>
            </a>
        </div>

        <h1>Production Plans</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Plan Name</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Quantity</th>
                    <th>Estimate</th>
                    <th>Department Name</th>
                    <th>Type</th>
                    <th>View</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${SchePlans}" var="sp">
                    <tr>
                        <td>${sp.id}</td>
                        <td>${sp.product.name}</td>
                        <td>${sp.plan.name}</td>
                        <td>${sp.plan.start}</td>
                        <td>${sp.plan.end}</td>
                        <td>${sp.quantity}</td>
                        <td>${sp.estimate}</td>
                        <td>${sp.plan.dept.name}</td>
                        <td>${sp.plan.dept.type}</td>

                        <td><a href="${pageContext.request.contextPath}/productionplan/list/product?id=${sp.id}"><i class="fa-solid fa-eye"></i></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
