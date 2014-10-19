<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
	<h2>Damage and Hit Calculator</h2>
	<h4 id="damageRoll">Damage Roll: ${damageRoll}</h4>
	<form:form modelAttribute="character">
		<br />
		<label for="weaponSelect">Weapon:</label>
		<form:select path="weapon.name" id="weaponSelect">			
			<c:forEach items="${weapons}" var="weapon">
				<form:option value="${weapon.name}">${weapon.name}</form:option>
			</c:forEach>
		</form:select>
		<br />
		<h4>Atributes</h4>
		<br />
		<label for="str"><b>Str:</b></label>
		<form:input path="strength.value" type="text" id="str" />
		<br />
		<input type="submit" value="Calculate Damage Roll" id="calculateButton" />
	</form:form>
</body>
</html>