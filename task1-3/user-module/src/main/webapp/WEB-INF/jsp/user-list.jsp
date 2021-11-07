<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>Users</H1>
<table border="1">
<caption>Users</caption>
<thead>
<tr>
<th>Id</th>
<th>First name</th>
<th>Last name</th>
<th>Email</th>
<th>Phone</th>
<th>Address</th>
<th>Password</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${users}" var="user">
<tr>
<td>${user.id}</td>
<td>${user.firstName}</td>
<td>${user.lastName}</td>
<td>${user.email}</td>
<td>${user.phone}</td>
<td>${user.address}</td>
<td>${user.password}</td>
<td><a type="button" href="user/update?id=${user.id}">UPDATE</a></td>
<td><a type="button" href="user/delete?id=${user.id}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>
<div>
<a href="user/new">Add user</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>