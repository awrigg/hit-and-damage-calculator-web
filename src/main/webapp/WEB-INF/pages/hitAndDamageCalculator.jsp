<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	pageContext.setAttribute("VARIABLE_IMPUT", br.com.wrigg.dnd.hitAndDamage.Type.VARIABLE_IMPUT.name());
%>

<html>
<body>
	<h2>Damage and Hit Calculator</h2>
	<label for="damageRoll">Damage Roll:</label>
	<h4 id="damageRoll">${damageRoll}</h4>
	<label for="criticalDamageRoll">Critical Damage Roll:</label>
	<h4 id="criticalDamageRoll">${criticalDamageRoll}</h4>
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
		<h4>Spells</h4>
		<br />
		<ul>
			<c:forEach items="${spells}" var="spell" varStatus="spellRow">
				<li>
					<form:checkbox path="spells" id="${spell.id}" value="${spell.id}" />
					<label for="${spell.id}">${spell.name}</label>
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
		<h4>Class Features</h4>
		<br />
		<label for="casterLevel"><b>Caster Level:</b></label>
		<form:input path="casterLevel.level" type="text" id="casterLevel" />
		<br />
		<label for="turnLevel"><b>Turn Level:</b></label>
		<form:input path="turnLevel.level" type="text" id="turnLevel" />
		<br />
		<ul>
			<c:forEach items="${classFeatures}" var="feature">
				<li>
					<form:checkbox path="classFeatures" id="${feature.id}" value="${feature.id}" />
					<label for="${feature.id}">${feature.name}</label>
				</li>
			</c:forEach>
		</ul>
		
		
		<input type="submit" value="Calculate Damage Roll" id="calculateButton" />
	</form:form>
</body>
</html>