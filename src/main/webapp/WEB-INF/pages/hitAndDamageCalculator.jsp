<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
	<h2>Damage and Hit Calculator</h2>
	<h4>Damage Roll:</h4><h4 id="damageRoll">${damageRoll}</h4>
	<form:form modelAttribute="character">
		<br />
		<label for="weaponSelect">Weapon:</label>
		<form:select path="weapon.name" id="weaponSelect">			
			<c:forEach items="${weapons}" var="weapon">
				<form:option value="${weapon.name}">${weapon.name}</form:option>
			</c:forEach>
		</form:select>
		<br />
		<h4>Feats</h4>
		<br />
		<ul>
			<c:forEach items="${feats}" var="feat" varStatus="status">
				<li>
					<form:checkbox path="feats" id="${feat.id}" value="${feat.id}" /><label for="${feat.id}">${feat.name}</label>
					<c:if test="${feat.type == 'VariableInput'}">
						<form:input path="feats[${status.index-1}].damageBonus.bonus.bonus" id="${feat.id}_value" /> 
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
		<input type="submit" value="Calculate Damage Roll" id="calculateButton" />
	</form:form>
</body>
</html>