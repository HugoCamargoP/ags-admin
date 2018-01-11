<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Global Variables --%>
<c:set var="footerAppName" scope="application">YETIBERA</c:set>
<c:set var="appname" scope="application">yeti</c:set>
<c:set var="version" scope="application">1.0.0</c:set>
<c:set var="DeployContext" scope="application">/dev</c:set>
<c:set var="ContextPath" scope="application">${pageContext.request.contextPath}</c:set>

<%-- Global Links --%>
<c:url var="linkCopyright" value="http://www.arrow-gs.com/" scope="application"/>
<c:url var="linkAdminHome" value="/home" scope="application"/>
<c:url var="linkAdminUser" value="/users" scope="application"/>
<c:url var="linkAdminLogin" value="/login" scope="application"/>
<c:url var="linkLogout" value="/logout" scope="application"/>

<%-- Admin --%>
<c:url var="linkAdminCreateU" value="/createU" scope="application"/>
<c:url var="linkAdminModifyU" value="/modifyU" scope="application"/>
<c:url var="linkAdminReports" value="/reports" scope="application"/>
<c:url var="linkAdminOrders" value="/orders" scope="application"/>
<c:url var="linkAdminOrder" value="/order" scope="application"/>
<c:url var="linkAdminItems" value="/items" scope="application"/>
<c:url var="linkSales" value="/sales" scope="application"/>
<c:url var="linksalespdf" value="/rest/sales" scope="application"/>
<c:url var="linkFive" value="sales/top-five" scope="application"/>
<c:url var="linkFivePDF" value="/rest/top-five" scope="application"/>


<c:url var="linkAdmin" value="/" scope="application"/>
<c:url var="linkAdminEmpty" value="#" scope="application"/>
