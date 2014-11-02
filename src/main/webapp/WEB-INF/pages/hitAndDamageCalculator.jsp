<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	pageContext.setAttribute("VARIABLE_IMPUT", br.com.wrigg.dnd.hitAndDamage.feat.Feat.Type.VARIABLE_IMPUT.name());
%>

<html>
<body>
	<h2>Damage and Hit Calculator</h2>
	<h4>Damage Roll:</h4>
	<h4 id="damageRoll">${damageRoll}</h4>
	<form:form modelAttribute="character">
		<br />
		<label for="weaponSelect">Weapon:</label>
		<form:select path="weapon.name" id="weaponSelect">
			<c:forEach items="${weapons}" var="weapon">
				<form:option value="${weapon.name}">${weapon.name}</form:option>
			</c:forEach>
		</form:select>
		<label for="magicalWeaponBonus">Magical:</label>
		<form:input path="weapon.bonus.bonus.bonus" id="magicalWeaponBonus"/>
		<br />
		<h4>Feats</h4>
		<br />
		<ul>
			<c:forEach items="${feats}" var="feat" varStatus="featRow">
				<li>
					<form:checkbox path="feats" id="${feat.id}" value="${feat.id}" />
					<label for="${feat.id}">${feat.name}</label>
					<c:if test="${feat.type == VARIABLE_IMPUT}">
						<form:input path="feats[${featRow.index-1}].damageBonus.bonus.bonus" id="${feat.id}_value" />
					</c:if>
				</li>
			</c:forEach>
		</ul>
		<br />
		<h4>Atributes</h4>
		<br />
		<label for="str"><b>Str:</b></label>
		<form:input path="strength.value" type="text" id="str" />
		<br />
		<label for="cha"><b>Cha:</b></label>
		<form:input path="charisma.value" type="text" id="cha" />
		<br />
		<input type="submit" value="Calculate Damage Roll"
			id="calculateButton" />
	</form:form>
</body>
</html>