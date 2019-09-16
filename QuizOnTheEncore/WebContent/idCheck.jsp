<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheck.jsp</title>
</head>
<body>
	${requestScope.quizerId}
	${requestScope.message}
	
	<form action="quiz?command=quizerIdCheck" method="post">
	<input type="text" name="id" value="${requestScope.quizerId}"><input type="submit" value="중복확인">
	</form>
	
	<script type="text/javascript">
                        function moveClose(){
                           opener.location.href="quizerInsert.jsp";
                           self.close();
                        }
                     </script>
    <input type="button" value="사용하기" onclick="moveClose();">
	
	
	
	
	
	<script type="text/javascript">
                        function moveClose2(){
                           opener.location.href="index.html";
                           self.close();
                        }
                     </script>
	<input type="button" value="처음 페이지로" onclick="moveClose2()">
	
</body>
</html>