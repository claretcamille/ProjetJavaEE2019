<%-- 
    Document   : affichageProduits
    Created on : 20 nov. 2019, 14:41:35
    Author     : Loic Leu et Aymeric Prévost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produits in ${CatSelect}</title>
    </head>
    ${CatSelect}
    <body>
        <form>
            <select name='state' onchange='this.form.submit()'>
                <c:forEach var="state" items="${Categories}">
                    <option value=${state.getCode()}
                            <c:if test="${state.getLibelle() eq CatSelect}">
                            selected
                        </c:if>
                    >${state.getLibelle()}</option>
                </c:forEach>
            </select>
        <input type='submit'>
    </form>
    <table border=2>
        <tr> <th>Références</th> <th>Nom</th> <th>Fournisseur</th> <th>Quantité</th> <th>Prix</th> </tr>
	<tbody>

	<c:forEach items="${Produits}" var="u">
		<tr>
			<td>${u.getRef()}</td>
			<td>${u.getNom()}</td>
			<td>${u.getFourni()}</td>
                        <td>${u.getQt()}</td>
                        <td>${u.getPrix()}</td>
		</tr>
            </c:forEach>
    </table>
    <hr>
</html>
