<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Global Variables --%>
<c:set var="footerAppName" scope="application">WARD</c:set>
<c:set var="appname" scope="application">ward</c:set>
<c:set var="version" scope="application">1.0.0</c:set>
<c:set var="DeployContext" scope="application">/dev</c:set>
<c:set var="ContextPath" scope="application">${pageContext.request.contextPath}</c:set>

<%-- Global Links --%>
<c:url var="linkHome" value="/home" scope="application"/>
<c:url var="linkCopyright" value="http://www.arrow-gs.com/" scope="application"/>
<c:url var="linkLogin" value="/login" scope="application"/>
<c:url var="linkLogout" value="/logout" scope="application"/>


<%-- Colonos --%>
<c:url var="linkCreateC" value="/createC" scope="application"/>
<c:url var="linkModifyC" value="/modifyC" scope="application"/>


<%-- Admin --%>
<c:url var="linkCreateU" value="/createU" scope="application"/>
<c:url var="linkModifyU" value="/modifyU" scope="application"/>


<c:url var="link" value="/" scope="application"/>
<c:url var="linkEmpty" value="#" scope="application"/>
