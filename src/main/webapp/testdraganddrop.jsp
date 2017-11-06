<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="stylesheet" href="css/testdragndrop.css" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/testdragndrop.js"></script>
    </head>
    <body>
        <form class="box" method="post" action="testuploadfile" enctype="multipart/form-data">
			<div class="box__input">
				<input class="box__file" type="file" name="myFile" id="files"/>
				<label for="file"><strong>Choose a file</strong><span class="box__dragndrop"> by dragging it here</span>.</label>
				<button class="box__button" type="submit">Upload</button>
			</div>
			<div class="box__uploading">Uploading&hellip;</div>
			<div class="box__success">Done!</div>
			<div class="box__error">Error! <span></span>.</div>
		</form>
    </body>
</html>
