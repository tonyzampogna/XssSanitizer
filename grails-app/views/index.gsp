<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>

<a href="${createLink(controller: "xssSanitizer", action: "test1")}">test1</a>
<a href="${createLink(controller: "xssSanitizer", action: "test2")}">test2</a>
<a href="${createLink(controller: "xssSanitizer", action: "test3")}">test3</a>
<a href="${createLink(controller: "xssSanitizer", action: "test4")}">test4</a>

<p>Grails Value: ${params.value}</p>
<p>HttpRequest Value: ${request.getParameter("value")}</p>

</body>
</html>