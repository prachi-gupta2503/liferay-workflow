<%@include file="/init.jsp" %>
<style>
	

    ul.the_dropdown, .the_dropdown li ul {
         list-style-type: none; 
    }
     /* .the_dropdown li ul {
        margin-top: 5px;   
    } */

    .the_dropdown li a {
        color:#000; 
        text-decoration:none;
        /* background-color: yellow; 
        padding:1px; 
        width:100px;*/
        display:block;

    }

    .the_dropdown li ul {
        display: none;

    }

    .the_dropdown li:hover ul{ /* Display the dropdown on hover */
        display:block;
    }
</style>
  <div class="container mt-4">
  <portlet:renderURL var="applicationForm">
<portlet:param name="mvcPath" value="/application-form.jsp" /></portlet:renderURL>
  
  <a href="${applicationForm}" >Application Form</a>
 <h1>Applicant List</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th> 
                    <th>Email</th> 
                    <th>Education</th>
                     <th>Level</th>
                     <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${candidateDtoList}" var="candidateDto">
            
                <tr>
                    <td>${candidateDto.firstName}</td>
                    <td>${candidateDto.lastName}</td>
                     <td>${candidateDto.phoneNo}</td>
                     <td>${candidateDto.email}</td>
                     <td>${candidateDto.education}</td>
                     <td>${candidateDto.level}</td>
                     <td>${candidateDto.status}</td>
                  
                    
                    <portlet:renderURL var="viewProfile">
						<portlet:param name="mvcRenderCommandName" value="/view/candidate" />
						<portlet:param name="workflowTaskId" value="${candidateDto.workflowTaskId}" />
						<portlet:param name="candidateId" value="${candidateDto.candidateId}" />
						<portlet:param name="candidateId" value="${candidateDto.candidateId}" />
						<portlet:param name="transitionNames" value="${candidateDto.transitionNames}" />
													
					</portlet:renderURL>
                   <td> <a href="${viewProfile}" class="dropdown-item">view candidate Profile</a>
                   </td>
               </tr>
             </c:forEach> 
                <!-- Add more applicants as needed -->
            </tbody>
        </table>
    </div>
    
    