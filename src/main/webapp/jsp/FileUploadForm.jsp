<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/FileUploadTest_20220922/fileupload" method="post" enctype="multipart/form-data">
			<input type="text" name="txt1" /><br />
			<input type="file" name="file1" /><br />
			<input type="file" name="file2" /><br />
			<input type="file" name="file3" /><br />
			<input type="button" value="전송" onclick="this.form.submit();" />
		</form>
	</div>
</body>
</html>