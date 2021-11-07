<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>New user:</p>
<form:form method="post" modelAttribute="user">
	
	<form:input path="id" type="hidden" required="required" />
	<form:errors path="id" />

    <form:label path="firstName">First name:</form:label>
    <form:input path="firstName" type="text" required="required" />
    <form:errors path="firstName" />

    <form:label path="lastName">Last name:</form:label>
    <form:input path="lastName" type="text" required="required" />
    <form:errors path="lastName" />

	<form:label path="email">Email:</form:label>
	<form:input path="email" type="email" required="required" />
	<form:errors path="email"/>

	<form:label path="phone">Phone:</form:label>
	<form:input path="phone" type="text" required="required" />
	<form:errors path="phone" />

    <form:label path="address">Address:</form:label>
    <form:input path="address" type="text" required="required" />
    <form:errors path="address" />

    <form:label path="password">Password:</form:label>
    <form:input path="password" type="password" required="required" />
    <form:errors path="password" />

	<button type="submit">Save</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>