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
                     
                 <!-- popup start -->  
                <portlet:actionURL name="/recruitment/flow" var="recruitmentFlow" />
  <div class="modal fade" id="applicant-info" tabindex="-1" role="dialog"
        aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">
                       Applicant Info
                    </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button> 
                </div>



              
                <div class="modal-body">
 					<form action="${recruitmentFlow}" id="recruitmentFlow" method="post"> 
					<input type="hidden" name="<portlet:namespace/>candidateId" value="${candidateDto.candidateId}" />
			        <input type="hidden" name="<portlet:namespace/>workflowTaskId" value="${candidateDto.workflowTaskId}" >
			        <input type="hidden" id="transitionName" name="<portlet:namespace/>transitionName" >

                    <div class="card">
                        <div class="card-title">
                            <liferay-ui:message key="personal-details" />
                        </div>
                        <div class="row">
                            <div class="col-lg-4 col-md-4">
                                <div class="form-group">
                                <div class="label-name">
                                    <liferay-ui:message key="name" />
                                </div>
                                <div class="label-content" id="userName">${candidateDto.firstName}</div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="form-group">
                                <div class="label-name">
                                    <liferay-ui:message key="lastName" />
                                </div>
                                <div class="label-content" id=lastName>${candidateDto.lastName}</div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="form-group">
                                    <div class="label-name">
                                        <liferay-ui:message key="phone Number" />
                                    </div>
                                    <div class="label-content" id="useremail">${candidateDto.phoneNo}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                       <div class="row">
                            <div class="col-lg-4 col-md-4">
                                <div class="form-group-">
                                <div class="label-name">
                                    <liferay-ui:message key="email" />
                                </div>
                                <div class="label-content" id="email">${candidateDto.email}</div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="form-group">
                                <div class="label-name">
                                    <liferay-ui:message key="education" />
                                </div>
                                <div class="label-content" id="userExamTitle">${candidateDto.education}</div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="form-group">
                                <div class="label-name">
                                    <liferay-ui:message key="skills" />
                                </div>
                                <div class="label-content" id="location">${candidateDto.skills}</div>
                                </div>
                            </div>
                        </div>
                    

    			</form>  
                </div>
                
                <div class="modal-footer">
               
                 <c:if test="${not empty candidateDto.transitionNames and !isAdmin}">
		            <c:forEach items="${candidateDto.transitionNames}" var="transitionName">
					<button class="btn-primary" type="button" onclick="submitForm('${transitionName}')">${transitionName}</button>
				</c:forEach>
				</c:if>
                    
                    <button type="button" data-dismiss="modal">
                        <liferay-ui:message key="cancel" />
                    </button>
                </div>
              
            </div>
        </div>
    </div>
 
 
 

                   
                   
                   
                   	  <c:forEach items="${recruitmentStep}" var="recStep">
                     
                     	  <td>
                     	    <c:set var="step" value="${recStep.getName()}" />
                     	    
                     	    <c:choose>
                     	    <c:when test="${candidateDto.levelStatus[step]}">
                     	    	 <c:choose>
                     	    	
						            <c:when test="${candidateDto.status eq 'denied' and step eq candidateDto.level}">
						               
						                 <a data-target="#applicant-info"  data-toggle="modal"> <img src="<%=request.getContextPath()%>/img/red-cross-sign.jpg" style="width:20px; height:20px;">
						            </c:when>
						            <c:otherwise>
						                 <a data-target="#applicant-info"  data-toggle="modal"><img src="<%=request.getContextPath()%>/img/check-mark.jpg" style="width:20px; height:20px;"></a>
						            </c:otherwise>
						        </c:choose>
                     	    </c:when>
                     	     
                     	     <c:when test="${candidateDto.levelStatus[step]==false}">
                     	      <a data-target="#applicant-info"  data-toggle="modal"><img src="<%=request.getContextPath()%>/img/pending-mark.png" style="width:20px; height:20px;"></a>
                     	     </c:when>
                     	    
                     	    </c:choose>
                     	 	
                     	  </td>
                    </c:forEach>
                   	 
                    
                    
                  
                </tr>
             </c:forEach> 
                <!-- Add more applicants as needed -->
            </tbody>
        </table>
    </div>
    
 <script>
 function submitForm(transitionName){

	 $("#transitionName").val(transitionName);
	 var form=document.getElementById('recruitmentFlow');
	 console.log(form)
	 form.submit();
	}
 
 </script>   
     