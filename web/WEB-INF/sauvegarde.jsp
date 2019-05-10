<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medimenu</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"/>
</head>

<body>
<section>
    ${sauvegarde}

    <h2>Détails nutritionnels du menu</h2>
    ${detailsNutriments}
</section>
</body>
</html>