<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Medimenu</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"/>
</head>

<body>
<section>
  <h1>Au menu pour les ${nombreDeJour} prochains jours</h1>
    ${result}

    <h2>Sauvegarder</h2>
    <form action="sauvegarde" method="get"> <!-- A MODIFIER -->
        <div><label for="regime">le régime</label>
        <input type="checkbox" id="regime" name="regime" checked>
        <input type="text" id="nomRegime" name="nomRegime"></div>

        <div><label for="menuUtilisateur">le menu(version utilisateur)</label>
            <input type="checkbox" id="menuUtilisateur" name="menuUtilisateur" checked>
            <input type="text" id="nomMenuUtilisateur" name="nomMenuUtilisateur"></div>

        <div><label for="menuNutritionniste">le menu (version nutritionniste)</label>
            <input type="checkbox" id="menuNutritionniste" name="menuNutritionniste" checked>
            <input type="text" id="nomMenuNutritionniste" name="nomMenuNutritionniste"></div>


        <button type="submit" value="Submit">Suivant</button>
    </form>

</section>


</body>
</html>