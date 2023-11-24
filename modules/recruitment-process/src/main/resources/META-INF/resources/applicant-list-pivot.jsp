<%@include file="/init.jsp" %>
<style>
	/* img.dropdown_class {
         width: 16px;
         height: 16px;
         text-align: left;
    }

    .the_dropdown {
        width: 100px;

    } */

    /* .selectedoption {
        width: 100px;
        border: 1px solid #000;
        height: 14px;
        padding: 5px;
    } */

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
                    <c:forEach items="${recruitmentStep}" var="recStep">
                     	<th>${recStep.getName()}</th>
                    </c:forEach>
                   
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${candidateDtoList}" var="candidateDto">
             <portlet:renderURL var="viewProfile">
								<portlet:param name="mvcRenderCommandName" value="/view/candidate" />
								<portlet:param name="workflowTaskId" value="${candidateDto.workflowTaskId}" />
								<portlet:param name="candidateId" value="${candidateDto.candidateId}" />
								<portlet:param name="candidateId" value="${candidateDto.candidateId}" />
								<portlet:param name="transitionNames" value="${candidateDto.transitionNames}" />
															
							</portlet:renderURL>
                <tr>
                    <td>${candidateDto.firstName}</td>
                    <td>${candidateDto.lastName}</td>
                     <td>${candidateDto.phoneNo}</td>
                     <td>${candidateDto.email}</td>
                     <td>${candidateDto.education}</td>
                     
                   
                   	  <c:forEach items="${recruitmentStep}" var="recStep">
                     
                     	  <td>
                     	    <c:set var="step" value="${recStep.getName()}" />
                     	    
                     	    <c:choose>
                     	    <c:when test="${candidateDto.levelStatus[step]}">
                     	    	 <c:choose>
                     	    	
						            <c:when test="${candidateDto.status eq 'denied' and step eq candidateDto.level}">
						               
						                <a href="${viewProfile}" class="dropdown-item"> <img src="<%=request.getContextPath()%>/img/red-cross-sign.jpg" style="width:20px; height:20px;">
						            </c:when>
						            <c:otherwise>
						                 <a href="${viewProfile}" class="dropdown-item"><img src="<%=request.getContextPath()%>/img/check-mark.jpg" style="width:20px; height:20px;"></a>
						            </c:otherwise>
						        </c:choose>
                     	    </c:when>
                     	     
                   
                     	     <c:when test="${candidateDto.levelStatus[step]==false}">
                     	      <a href="${viewProfile}" class="dropdown-item"><img src="<%=request.getContextPath()%>/img/pending-mark.png" style="width:20px; height:20px;"></a>
                     	     </c:when>
                     	    
                     	    </c:choose>
                     	 	
                     	  </td>
                    </c:forEach>
                   	 
                    
                    
                    	<%-- <div>
		                    <div class="left" >
		                        <ul class="the_dropdown">
		                            <li><span class="selectedoption">selected option</span>
		                                <ul>
		                            	<c:if test="${not empty candidateDto.transitionNames and !isAdmin}">
		                                    <c:forEach items="${candidateDto.transitionNames}" var="transitionName">
												
												<portlet:renderURL var="nextStep">
													<portlet:param name="mvcRenderCommandName" value="/recruitment/flow" />
													<portlet:param name="workflowTaskId" value="${candidateDto.workflowTaskId}" />
													<portlet:param name="candidateId" value="${candidateDto.candidateId}" />
													<portlet:param name="candidateId" value="${candidateDto.candidateId}" />
													<portlet:param name="transitionName" value="${candidateDto.transitionNames}" />
													
												</portlet:renderURL>
											<li><a href="${nextStep}" class="dropdown-item">${transitionName}</a></li>
											</c:forEach>
											
										</c:if>
											<li><a  class="dropdown-item">view Profile</a></li>
		                                </ul>
		                            </li>
		                        </ul>
		                    </div>
		                </div> --%>
                    
                  
                </tr>
             </c:forEach> 
                <!-- Add more applicants as needed -->
            </tbody>
        </table>
    </div>
    
