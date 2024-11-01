<%-- 
    Document   : home
    Created on : 31 Oct 2024, 00:46:36
    Author     : A A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
        <title>Home</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            header {
                background-color: green;
                padding: 10px 20px;
                color: white;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .header-left {
                display: flex;
                gap: 10px;
            }
            .header-center {
                flex-grow: 1;
                text-align: center;
                font-size: 1.2em;
                font-weight: bold;
            }
            .header-right {
                display: flex;
                align-items: center;
            }
            .header-right span {
                margin-right: 10px;
            }
            .header-right button {
                padding: 8px 16px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .header-right button:hover {
                background-color: #0056b3;
            }

            /* Container styling */
            .container {
                display: flex;
                margin: 20px;
            }
            .container-left, .container-right {
                flex: 1;
                padding: 20px;
                border: 1px solid #ddd;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .container-left {
                background-color: #f9f9f9;
            }
            .container-right {
                background-color: #e9e9e9;
                margin-left: 20px;
            }

            /* Button styling */
            .container button {
                padding: 12px 24px;
                font-size: 16px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: background-color 0.3s ease, transform 0.2s ease;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .container button:hover {
                background-color: #45a049;
                transform: scale(1.05);
            }
            .container button:active {
                background-color: #3e8e41;
                transform: scale(1);
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            }
        </style>
    </head>
    <body>
        <header>
            <div class="header"> 
                <div class="header-left">
                    <i class="fa-solid fa-house"></i>
                    Manage Employee
                </div>
                <div class="header-center">
                    Welcome to Home Page
                </div>
                <div class="header-right">
                    <span>${sessionScope.account.username}</span>
                    <button onclick="logout()">Logout</button>
                </div>
            </div>
        </header>

        <!-- Container bên ngoài header -->
        <div class="container">
            <div class="container-left">
                <button onclick="btnViewSchedule()">View Schedule</button>
            </div>
            <div class="container-right">
                <button onclick="btnCreateSchedule()">Create Schedule</button>
            </div>
        </div>
        <script>
            function btnViewSchedule() {
                window.location.href = "${pageContext.request.contextPath}/productionplan/list";
            }
            function btnCreateSchedule() {
                window.location.href = "${pageContext.request.contextPath}/productionplan/create"; 
            }
             function logout() {
                window.location.href = "${pageContext.request.contextPath}/logout";
            }
        </script>
    </body>
</html>
