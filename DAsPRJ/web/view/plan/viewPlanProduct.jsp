<%-- 
    Document   : viewPlanProduct
    Created on : 30 Oct 2024, 23:58:17
    Author     : A A
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View PP</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                align-items: center;
                min-height: 100vh;
            }

            h1,h2 {
                text-align: left;
                margin: 20px 150px;
                color: #333;
            }

            table {
                width: 80%;
                border-collapse: collapse;
                margin: 20px auto;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background-color: #fff;
            }

            thead tr {
                background-color: #4CAF50;
                color: #fff;
                text-align: left;
            }

            thead tr th {
                padding: 12px;
                font-size: 16px;
            }

            tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tbody tr td {
                padding: 12px;
                border-bottom: 1px solid #ddd;
                text-align: left;
            }

            tbody tr:hover {
                background-color: #f1f1f1;
            }
            .info {
                display: flex; /* Sử dụng Flexbox để sắp xếp nội dung nằm ngang */
                flex-wrap: wrap; /* Cho phép các phần tử bọc lại nếu không đủ không gian */
                background-color: #fff; /* Nền trắng để làm nổi bật */
                padding: 20px; /* Thêm khoảng đệm cho các mục */
                border-radius: 8px; /* Bo góc cho phần thông tin */
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ để nổi bật */
                margin: 20px 150px; /* Khoảng cách từ các cạnh */
            }

            .info-item {
                flex: 1 1 30%; /* Cho phép các mục chiếm khoảng 30% chiều rộng và co dãn */
                margin: 10px; /* Khoảng cách giữa các mục */
            }

            .info-item h2 {
                margin: 0; /* Bỏ margin cho tiêu đề */
                font-size: 18px; /* Kích thước chữ */
                color: #333; /* Màu chữ tối */
            }

            .info-item span {
                color: #4CAF50; /* Màu chữ cho giá trị */
                font-weight: bold; /* Làm cho giá trị đậm hơn */
            }
            .popup {
                display: none; /* Ẩn pop-up ban đầu */
                position: fixed;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.7);
                justify-content: center;
                align-items: center;
            }

            .popup-content {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
                text-align: center;
                width: 300px;
            }

            .close {
                cursor: pointer;
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
            .popup-inner {
                display: flex; /* Sử dụng Flexbox để sắp xếp hai cột */
                justify-content: space-between; /* Căn giữa và phân bố đều */
                margin-top: 20px; /* Khoảng cách giữa tiêu đề và nội dung */
            }

            .left-content, .right-content {
                flex: 1; /* Cả hai phần tử sẽ chiếm cùng một khoảng không gian */
                margin: 10px; /* Khoảng cách giữa hai phần */
                padding: 15px; /* Thêm khoảng đệm cho các phần */
                background-color: #f9f9f9; /* Màu nền xám nhạt */
                border-radius: 8px; /* Bo góc cho các phần */
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ cho các phần */
                text-align: left; /* Căn trái cho nội dung */
            }

            .left-content h3, .right-content h3 {
                margin-top: 0; /* Xóa khoảng cách trên cùng cho tiêu đề */
                color: #333; /* Màu chữ cho tiêu đề */
                text-align: left; /* Căn trái tiêu đề */
            }

            .popup-content {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
                text-align: center;
                width: 800px;
            }
            .save-button {
                background-color: #4CAF50; /* Màu nền xanh */
                color: white; /* Màu chữ trắng */
                border: none; /* Không có viền */
                padding: 10px 20px; /* Khoảng đệm cho nút */
                text-align: center; /* Căn giữa chữ */
                text-decoration: none; /* Không có gạch chân */
                display: inline-block; /* Hiển thị như một khối nội tuyến */
                font-size: 16px; /* Kích thước chữ */
                margin: 4px 20px 20px 180px; /* Khoảng cách xung quanh nút */
                cursor: pointer; /* Con trỏ khi hover */
                border-radius: 5px; /* Bo góc cho nút */
                transition: background-color 0.3s, transform 0.2s; /* Hiệu ứng chuyển động */
            }

            .save-button:hover {
                background-color: #45a049; /* Màu nền khi hover */
                transform: scale(1.05); /* Phóng to nhẹ khi hover */
            }

            .save-button:active {
                transform: scale(0.95); /* Thu nhỏ khi nhấn */
            }
        </style>
    </head>
    <body>
        <div style="margin: 25px 10px  20px 150px ">
            <a href="${pageContext.request.contextPath}/productionplan/list" style="text-decoration: none">
                <i class="fa-solid fa-arrow-right-to-bracket fa-rotate-180 fa-xl" style="color: #07ad90"></i>
                &nbsp;<span
                    style="color: #07ad90; font-weight: 500; font-size: 20px; margin-top: 10px">Back to Home </span>
            </a>
        </div>
        <h1>Plan Campaign</h1>
        <div class="info">
            <div class="info-item">
                <h2>ID: <span>${planTimelines.id}</span></h2>
            </div>
            <div class="info-item">
                <h2>Plan Name: <span>${planTimelines.plan.name}</span></h2>
            </div>
            <div class="info-item">
                <h2>Product Name: <span>${planTimelines.product.name}</span></h2>
            </div>
            <div class="info-item">
                <h2>Start: <span>${planTimelines.plan.start}</span></h2>
            </div>
            <div class="info-item">
                <h2>End: <span>${planTimelines.plan.end}</span></h2>
            </div>

            <div class="info-item">
                <h2>Estimate: <span>${planTimelines.estimate}</span></h2>
            </div>
            <div class="info-item">
                <h2>Quantity: <span>${planTimelines.quantity}</span></h2>
            </div>
        </div>
        <c:if test="${dates != null}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Shift 1</th>
                        <th>Shift 2</th>
                        <th>Shift 3</th>
                        <th>View</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dates}" var="d" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${d.key}</td>
                            <td>${d.value.split(",")[0].split(":")[1].trim()}</td> 
                            <td>${d.value.split(",")[1].split(":")[1].trim()}</td> 
                            <td>${d.value.split(",")[2].split(":")[1].trim()}</td> 
                            <td>
                                <a href="${pageContext.request.contextPath}/productionplan/list/product?id=${param.id}&date=${d.key}">
                                    <i class="fa-solid fa-eye"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${param.id != null}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Employee Name</th>
                            <th>Date</th>
                            <th>Shift</th>
                            <th>Quantity</th>
                            <th>Department Name</th>
                            <th>View</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ses}" var="se">
                            <tr>
                                <td>${se.employee.id}</td> 
                                <td>${se.employee.name}</td>
                                <td>${se.sc.date}</td>
                                <td>${se.sc.shift}</td>
                                <td>${se.quantity}</td>
                                <td>${se.employee.dept.name}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/productionplan/list/product?id=${param.id}&date=${se.sc.date}&ScheEm=${se.id}">
                                        <i class="fa-solid fa-eye"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
                <div class="popup" id="popup">
                    <div class="popup-content">
                        <span class="close" id="closePopup">&times;</span>
                        <h2>Information Employee</h2>
                        <div class="popup-inner">
                            <div class="left-content">                  
                                <h3>Employee Details</h3>
                                <p><strong>Name:</strong> ${eInfo.employee.name}</p>
                                <p><strong>Gender:</strong> <c:choose>
                                        <c:when test="${eInfo.employee.gender}">Male</c:when>
                                        <c:otherwise>Female</c:otherwise>
                                    </c:choose></p>
                                <p><strong>Date of Birth:</strong> ${eInfo.employee.dob}</p>
                                <p><strong>Address:</strong> ${eInfo.employee.address}</p>
                                <p><strong>Department:</strong> ${eInfo.employee.dept.name}</p>
                            </div>
                            <div class="right-content">
                                <h3>Attendance Information</h3>
                                <p><strong>Date:</strong> ${eInfo.sc.date}</p>
                                <p><strong>Quantity:</strong> ${eInfo.attendence.quantity}</p>
                                <p><strong>Alpha:</strong> ${eInfo.attendence.alpha}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${param.ScheEm != null}">
                    <script>
                        document.addEventListener("DOMContentLoaded", function () {
                            setupPopup();
                            openPopup();
                        });
                    </script>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${dates == null}">
            <h2>Set Shift for Plan Campaign</h2>
            <form action="${pageContext.request.contextPath}/productionplan/list/product" method="POST" onsubmit="return validateForm();">
                <input type="hidden" value="${planTimelines.id}" name="pcId"/>
                <input type="hidden" id="maxTotal" value="${planTimelines.quantity}" name="maxTotal"/>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Shift 1</th>
                            <th>Shift 2</th>
                            <th>Shift 3</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${dateTimes}" var="d" varStatus="status">
                            <tr> 
                                <td>${status.index + 1}<input type="hidden" value="${status.index}" name="pid"/></td>
                                <td>${d}<input type="hidden" value="${d}" name="dateChange${status.index}"/></td>
                                <td><input type="text" name="sone${status.index}"></td> 
                                <td><input type="text" name="stwo${status.index}"></td> 
                                <td><input type="text" name="sthree${status.index}"></td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="save-button">SAVE</button>
            </form>

        </c:if>       
        <script>
            function openPopup() {
                var popup = document.getElementById("popup");
                popup.style.display = "flex";
            }

            function setupPopup() {
                var closePopup = document.getElementById("closePopup");

                closePopup.onclick = function () {
                    var popup = document.getElementById("popup");
                    popup.style.display = "none";
                };

                window.onclick = function (event) {
                    var popup = document.getElementById("popup");
                    if (event.target === popup) {
                        popup.style.display = "none";
                    }
                };
            }
            function validateForm() {
                const maxTotal = parseInt(document.getElementById('maxTotal').value);
                const inputs = document.querySelectorAll('input[type="text"]');
                let total = 0;

                for (let input of inputs) {
                    const value = input.value.trim();
                    if (value === "") {
                        alert("Shift values cannot be empty. Please fill all shifts.");
                        return false;
                    }

                    const intValue = parseInt(value);
                    if (isNaN(intValue) || intValue < 0) {
                        alert("Please enter valid positive integers for shifts.");
                        return false;
                    }

                    total += intValue;
                }

                if (total >= maxTotal) {
                    alert("The total of shifts must be less than " + maxTotal + ".");
                    return false; 
                }

                return true; 
            }
        </script>
    </body>
</html>

