<%@include file="/libs/foundation/global.jsp"%>
<%@taglib prefix="tags" uri="http://www.myProject.com/taglibs/common/1.1" %>
<h1>
Test

<c:set var="practiceName" value="${properties['./practiceName']}" />
  <c:set var="practiceHeading" value="${properties['./practiceHeading']}" />
<c:set var="excludedPlanDetails" value="${properties['./excludedPlanDetails']}" />

    <div>
        <div> Plan name :${practiceName}</div>
        <div> plan heading :${practiceHeading} </div>
    </div>
</h1>

<c:if test="${fn:length(excludedPlanDetails) gt 0}">
    <c:forEach var="records" items="${excludedPlanDetails}">
    	 <c:set var="record" value="${tags:parseJson(records)}" />
        <div class="plan-details-cost-desc">
            <div class="plan-and-sub-plan-des">
                <span class="plan-desc">${record['planDescValue']}</span>
                    <span class="plan-sub-desc">${record['planSubDescValue']}</span>
            </div>
            <div class="cost-desc"><span>${record['costDescValue']}</span></div>
        </div>
    </c:forEach>
</c:if>
<tags:customTag/>
${test}
