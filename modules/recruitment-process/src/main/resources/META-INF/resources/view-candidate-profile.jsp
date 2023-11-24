 <%@include file="/init.jsp" %>
 
 
 <portlet:actionURL name="/recruitment/flow" var="recruitmentFlow" />
 <div class="container">
        <h1>Candidate Info</h1>
       
        <div class="card">
      <form action="${recruitmentFlow}" id="recruitmentFlow" method="post"> 
        <input type="hidden" name="<portlet:namespace/>candidateId" value="${candidate.candidateId}" />
        <input type="hidden" name="<portlet:namespace/>workflowTaskId" value="${candidate.workflowTaskId}" >
        <input type="hidden" id="transitionName" name="<portlet:namespace/>transitionName" >
       
          <div class="card-body">
                <h5 class="card-title">Form Details</h5>
                <div class="form-group">
                    <label for="name"></label>
                    <p id="name">${candidate.firstName}</p>
                </div>
                <div class="form-group">
                    <label for="email"></label>
                    <p id="email">${candidate.lastName}</p>
                </div>
                <div class="form-group">
                    <label for="email"></label>
                    <p id="email">${candidate.email}</p>
                </div>
                <div class="form-group">
                    <label for="email"></label>
                    <p id="email">${candidate.phoneNo}</p>
                </div>
                <div class="form-group">
                    <label for="email"></label>
                    <p id="email">${candidate.education}</p>
                </div>
                <div class="form-group">
                    <label for="email"></label>
                    <p id="email">${candidate.skills}</p>
                </div>
               <c:if test="${not empty candidate.transitionNames and !isAdmin}">
		            <c:forEach items="${candidate.transitionNames}" var="transitionName">
				<button class="btn-primary" type="button" onclick="submitForm('${transitionName}')">${transitionName}</button>
			</c:forEach>
				</c:if>
               
                <!-- Add more form fields as needed -->
            </div>
           </form>
        </div>
        
</div>

<script>

function submitForm(transitionName){

 $("#transitionName").val(transitionName);
 var form=document.getElementById('recruitmentFlow');
 console.log(form)
 form.submit();
}
</script>