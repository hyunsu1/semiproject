<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/popup.js"></script>
<link rel="stylesheet" href="css/pay.css">
<style>
   input[type="number"]::-webkit-outer-spin-button,
   input[type="number"]::-webkit-inner-spin-button {
       -webkit-appearance: none;
       margin: 0;
   }
</style>
</head>
<body>
   <form action="PaymentC">
      <table id="payTbl">
         <tr id="payTr">
            <td id="payTd">카드 번호</td>
            <td id="payTd">
            <input style="width:30px" type="number" maxlength="4" oninput="numberMaxLength(this);"> - 
            <input style="width:30px" type="number" maxlength="4" oninput="numberMaxLength(this);"> - 
            <input style="width:30px" type="number" maxlength="4" oninput="numberMaxLength(this);"> - 
            <input style="width:30px" type="number" maxlength="4" oninput="numberMaxLength(this);">
            </td>
         </tr>
         
         <tr id="payTr">
            <td id="payTd">유효기간</td>
            <td id="payTd"><input style="width:15px" type="number" min="0" max="12" maxlength="2" oninput="numberMaxLength(this);"> /
            <input style="width:15px" type="number" min="0" max="31" maxlength="2" oninput="numberMaxLength(this);"></td>
         </tr>
         
         <tr id="payTr">
            <td id="payTd">비밀번호</td>
            <td id="payTd"><input style="width:20px" type="password" maxlength="4"></td>
         </tr>
         
         <tr id="payTr">
            <td id="payTd">결제금액</td>
            <td id="payTd">${moims.fee }</td>
         </tr>
         
         <tr>
            <td colspan="2" id="payTd2">
               <button id="payBtn">결제</button>
               <button id="payBtn2" type="button" onclick="popupClose();">취소</button>
            </td>
         </tr>
      </table>
      <input name="name" type="hidden" value="${moims.name }">
      <input name="introduce" type="hidden" value="${moims.introduce }">
   </form>
</body>
</html>