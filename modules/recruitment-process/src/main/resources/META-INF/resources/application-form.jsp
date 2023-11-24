<%@include file="/init.jsp" %>


<portlet:actionURL name="/application/form" var="saveApplicationForm" />
<portlet:renderURL var="viewCandidateList">
<portlet:param name="mvcRenderCommandName" value="/viewCandidateList" /></portlet:renderURL>
<!DOCTYPE html>
<html>
<head>
    <title>Application Form</title>
</head>
<body>
    <h1>Application Form</h1>
    <form action="${saveApplicationForm}" method="post">
        <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="<portlet:namespace/>firstName" required>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="<portlet:namespace/>lastName" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="<portlet:namespace/>email" required>
            </div>

            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Phone Number</label>
                <input type="tel" class="form-control" id="phoneNumber" name="<portlet:namespace/>phoneNo">
            </div>

            <div class="mb-3">
                <label for="education" class="form-label">Education</label>
                <input type="text" class="form-control" id="education" name="<portlet:namespace/>education">
            </div>

            <div class="mb-3">
                <label for="skills" class="form-label">Skills</label>
                <textarea  class="form-control" id="skills" name="<portlet:namespace/>skills"></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

    </form>
</body>
</html>

