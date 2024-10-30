<%-- 
    Document   : login
    Created on : 31 Oct 2024, 00:36:46
    Author     : A A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(to right, #4facfe, #00f2fe);
            }

            form {
                background-color: #ffffff;
                border-radius: 10px;
                padding: 40px 30px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
                width: 350px;
                text-align: center;
                transition: transform 0.3s;
            }

            form:hover {
                transform: scale(1.02);
            }

            h1 {
                margin-bottom: 20px;
                color: #333;
                font-size: 24px;
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
            }

            label {
                font-weight: bold;
                color: #555;
                display: block;
                margin-bottom: 5px;
                text-align: left;
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 12px;
                margin: 8px 0;
                border: 2px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                transition: border-color 0.3s;
            }

            input[type="text"]:focus,
            input[type="password"]:focus {
                border-color: #4CAF50;
                outline: none;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                margin-top: 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
                font-size: 18px;
                transition: background-color 0.3s;
            }

            button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <form action="login" method="post">
            <h1>Manage Employee</h1>
            <label>Account</label>
            <input type="text" name="user" placeholder="Enter account" required/>
            <label>Password</label>
            <input type="password" name="pass" placeholder="Enter password" required/>
            <button type="submit">Login</button>
        </form> 
    </body>
</html>
