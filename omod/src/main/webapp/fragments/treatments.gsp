${ ui.includeFragment("patientportaltoolkit", "genHistoryModal") }
<div>
    <div class="clearfix">
        <h4>General History</h4>


        <div>
            <% if (treatmentsummary) { %>
            <% treatmentsummary.each { genhistory -> %>
            <div class="pull-right">
                <a id="${(genhistory.encounterUuid)}"
                   class="glyphicon glyphicon-pencil editGenHistButton" data-toggle="modal" data-target="#edit-genHistory-modal"></a>
            </div>

            <div>
                <label>
                    <span class="reformatText">${(genhistory.cancerType)}</span>
                &ensp;
                    <span class="reformatText">${(genhistory.cancerStage)}</span>
                </label>
                <span>&emsp;<small class="dateFormatter">${(genhistory.diagnosisDate)}</small></span>
            </div>
            <% if (genhistory.hasGeneticOrPredisposingAbnormality) { %>
            <div>
                <label>Genetic or Predisposing Abnormality&emsp;</label>
                <span class="reformatText">${(genhistory.geneticOrPredisposingAbnormality)}</span>
            </div>
            <% } %>
            <div>
                <label>Primary Care Provider&emsp;</label>
                <span>${(genhistory.pcpName)}
                    <small>&emsp;${(genhistory.pcpPhone)}</small>
                    <small>&emsp;${(genhistory.pcpEmail)}</small>
                </span>
            </div>
            <% } %>
            <% } %>
        </div>
    </div>

    <hr/>
    ${ ui.includeFragment("patientportaltoolkit", "surgeriesModal") }
    <div class="clearfix">
        <h4>Surgeries&emsp;
            <a class="btn btn-primary btn-sm"   data-toggle="modal" data-target="#edit-surgeries-modal">Add</a>
        </h4>
    </div>

    <div>
        <div>
            <% if (surgeryencounters) { %>
            <% surgeryencounters.each { surgery -> %>
            <div class="pull-right">
                <a id="${(surgery.encounterUuid)}" class="glyphicon glyphicon-pencil editSurgeryButton"  data-toggle="modal" data-target="#edit-surgeries-modal"></a>
            </div>
            <div class="clearfix">
                <div class="pull-left">
                    <h5><% surgery.surgeryTypes.each { surgeryType -> %> <span class="${(surgery.encounterUuid)}surgeryType reformatText" id="${(surgery.encounterUuid)}surgeryType${(surgeryType)}">${(surgeryType)}</span> <% } %> &emsp;<small id="${(surgery.encounterUuid)}surgeryDate"  class="dateFormatter">${(surgery.surgeryDate)}</small></h5>
                    <% if (surgery.hasMajorComplications) { %>
                    <div>
                        <label>Major Complications&emsp;</label>
                        <span id="${(surgery.encounterUuid)}surgeryComplications">${(surgery.majorComplications)}</span>
                    </div>
                    <% } %>
                    <div>
                    <label>Surgery Location&emsp;</label>
                        <span id="${(surgery.encounterUuid)}surgeryinstituteName">${(surgery.institutionName)}</span>
                    &emsp;
                        <span id="${(surgery.encounterUuid)}surgeryCity">${(surgery.institutionCity)}</span>
                    &emsp;
                        <span id="${(surgery.encounterUuid)}surgeryState">${(surgery.institutionState)}</span>
                    </div>
                    <div>
                        <label>Surgeon&emsp;</label>
                        <span><em id="${(surgery.encounterUuid)}surgeryPCPName">${(surgery.pcpName)}</em>
                        &emsp;<small id="${(surgery.encounterUuid)}surgeryPCPPhone">${(surgery.pcpPhone)}</small>
                        &emsp;<small id="${(surgery.encounterUuid)}surgeryPCPEmail">${(surgery.pcpEmail)}</small>
                        </span>
                    </div>
                </div>
            </div>
            <% } %>
            <% } %>
        </div>
    </div>

    <hr/>

    <div>
        <div class="clearfix">
            <h4>Chemotherapies&emsp;
                <a href="../NotFoundPage.js"
                   class="btn btn-primary btn-sm">Add</a>
            </h4>
        </div>

        <div>
            <div class="clearfix">
                <div class="pull-right">
                    <a href="/treatments/all-treatments/surgeries/procedure-form"
                       class="glyphicon glyphicon-pencil"></a>
                </div>
                <% if (chemotherapyencounters) { %>
                <% chemotherapyencounters.each { chemotherapy -> %>
                <div class="clearfix">
                    <div class="pull-left">
                        <h5> <% chemotherapy.chemoMedications.each { chemotherapymed -> %> <span class="${(chemotherapy.encounterUuid)}chemotherapymed reformatText" id="${(chemotherapy.encounterUuid)}chemotherapymed${(chemotherapymed)}">${(chemotherapymed)}</span> <% } %>
                            <small> <% if (chemotherapy.chemoStartDate) { %>&emsp;<span class="dateFormatter">${(chemotherapy.chemoStartDate)}</span><% } %>  <% if (chemotherapy.chemoEndDate) { %>&ndash;<span class="dateFormatter"> ${(chemotherapy.chemoEndDate)}</span><% } %></small>
                        </h5>
                        <div class="">
                            Central Line &ndash; <span><small> <% if (chemotherapy.centralLine) { %>Yes<% } else{ %> No <% } %></small></span>
                        </div>
                        <div class="">
                            <label>Chemotherapy Location&emsp;</label>
                            <% if (chemotherapy.institutionName) { %> <span> ${(chemotherapy.institutionName)}</span><% } %>
                        <% if (chemotherapy.institutionCity) { %>   &emsp;
                            <span>${(chemotherapy.institutionCity)}</span><% } %>
                        <% if (chemotherapy.institutionState) { %> &emsp;
                            <span>${(chemotherapy.institutionState)}</span><% } %>
                        </div>
                        <div>
                            <label>Chemotherapist&emsp;</label>
                            <span>  <% if (chemotherapy.pcpName) { %> ${(chemotherapy.pcpName)}<% } %>
                            <% if (chemotherapy.pcpPhone) { %>  <small>&emsp;${(chemotherapy.pcpPhone)}</small><% } %>
                            <% if (chemotherapy.pcpEmail) { %> <small>&emsp;${(chemotherapy.pcpEmail)}</small><% } %>
                            </span>
                        </div>
                    </div>
                </div>
                <% } %>
                <% } %>
            </div>

        </div>
    </div>

    <div>
        <div class="clearfix">
            <h4>Radiation Surgery&emsp;
                <a href="../NotFoundPage.js"
                   class="btn btn-primary btn-sm">Add</a>
            </h4>
        </div>

        <div>
            <div class="clearfix">
                <div class="pull-right">
                    <a href="/treatments/all-treatments/surgeries/procedure-form"
                       class="glyphicon glyphicon-pencil"></a>
                </div>
                <% if (radiationencounters) { %>
                <% radiationencounters.each { radiation -> %>
                <div class="clearfix">
                    <div class="pull-left">
                        <h5> <% radiation.radiationTypes.each { radiationType -> %> <span class="${(radiation.encounterUuid)}radiationType reformatText" id="${(radiation.encounterUuid)}radiationType${(radiationType)}">${(radiationType)}</span> <% } %>
                            <small> <% if (radiation.startDate) { %>&emsp;<span class="dateFormatter">${(radiation.startDate)}</span><% } %>  <% if (radiation.endDate) { %>&ndash; <span class="dateFormatter">${(radiation.endDate)}</span><% } %></small>
                        </h5>
                        <div class="">
                            <label>Radation Location&emsp;</label>
                            <% if (radiation.institutionName) { %> <span> ${(radiation.institutionName)}</span><% } %>
                        <% if (radiation.institutionCity) { %>   &emsp;
                            <span>${(radiation.institutionCity)}</span><% } %>
                        <% if (radiation.institutionState) { %> &emsp;
                            <span>${(radiation.institutionState)}</span><% } %>
                        </div>
                        <div>
                            <label>Radation Specialist&emsp;</label>
                            <span>  <% if (radiation.pcpName) { %> ${(radiation.pcpName)}<% } %>
                            <% if (radiation.pcpPhone) { %>  <small>&emsp;${(radiation.pcpPhone)}</small><% } %>
                            <% if (radiation.pcpEmail) { %> <small>&emsp;${(radiation.pcpEmail)}</small><% } %>
                            </span>
                        </div>
                    </div>
                </div>
                <% } %>
                <% } %>
            </div>

        </div>
    </div>
</div>