package org.openmrs.module.patientportaltoolkit.fragment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.context.Context;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Maurya on 10/08/2015.
 */
public class ChemotherapyModalFragmentController {
    protected final Log log = LogFactory.getLog(getClass());

    public void controller(FragmentModel model){}

    public void saveChemotherapyForm(FragmentModel model,  @RequestParam(value = "encounterId", required = false) String encounterId,
                                     @RequestParam(value = "chemotherapyMeds", required = false) String chemotherapyMeds,
                                     @RequestParam(value = "centralLine", required = false) String centralLine,
                                     @RequestParam(value = "chemoStartDate", required = false) String chemoStartDate,
                                     @RequestParam(value = "chemoEndDate", required = false) String chemoEndDate,
                                     @RequestParam(value = "chemotherapyPcpName", required = false) String chemotherapyPcpName,
                                     @RequestParam(value = "chemotherapyPcpEmail", required = false) String chemotherapyPcpEmail,
                                     @RequestParam(value = "chemotherapyPcpPhone", required = false) String chemotherapyPcpPhone,
                                     @RequestParam(value = "chemotherapyInstitutionName", required = false) String chemotherapyInstitutionName,
                                     @RequestParam(value = "chemotherapyInstitutionCity", required = false) String chemotherapyInstitutionCity,
                                     @RequestParam(value = "chemotherapyInstitutionState", required = false) String chemotherapyInstitutionState) throws ParseException {

        EncounterService encounterService= Context.getEncounterService();
        ConceptService conceptService=Context.getConceptService();
        String[] str_array = chemotherapyMeds.split("split");
        List<String> chemotherapyMedictionConcepts = new ArrayList<>();
        for(String s: str_array){
            chemotherapyMedictionConcepts.add(s);
        }
        List<String> existingChemotherapyMedictionConcepts = new ArrayList<>();
        Map<String,String> allTheEnteredValues = new HashMap<>();
        allTheEnteredValues.put("chemotherapyMeds",chemotherapyMeds);
        allTheEnteredValues.put("centralLine",centralLine);
        allTheEnteredValues.put("chemoStartDate",chemoStartDate);
        allTheEnteredValues.put("chemoEndDate",chemoEndDate);
        allTheEnteredValues.put("chemotherapyPcpName",chemotherapyPcpName);
        allTheEnteredValues.put("chemotherapyPcpEmail",chemotherapyPcpEmail);
        allTheEnteredValues.put("chemotherapyPcpPhone",chemotherapyPcpPhone);
        allTheEnteredValues.put("chemotherapyInstitutionName",chemotherapyInstitutionName);
        allTheEnteredValues.put("chemotherapyInstitutionCity",chemotherapyInstitutionCity);
        allTheEnteredValues.put("chemotherapyInstitutionState",chemotherapyInstitutionState);
        if(encounterId !=null) {
            Encounter chemotherapyEncounter = encounterService.getEncounterByUuid(encounterId);
            Map<String,List<Obs>> observationConceptUUIDToObsMap = new HashMap<>();
            for (Obs o:chemotherapyEncounter.getObs()){
                if(observationConceptUUIDToObsMap.get(o.getConcept().getUuid())== null) {
                    List<Obs> newObsList=new ArrayList<>();
                    newObsList.add(o);
                    observationConceptUUIDToObsMap.put(o.getConcept().getUuid(),newObsList);
                }
                else
                {
                    List<Obs> existingObsList= observationConceptUUIDToObsMap.get(o.getConcept().getUuid());
                    existingObsList.add(o);
                    observationConceptUUIDToObsMap.put(o.getConcept().getUuid(),existingObsList);
                }
            }
            for (Map.Entry<String, String> entry : allTheEnteredValues.entrySet())
            {
                if(entry.getValue() !=null) {
                    switch (entry.getKey()) {
                        case "chemotherapyMeds":
                            for (Obs o : observationConceptUUIDToObsMap.get("8481b9da-74e3-45a9-9124-d69ab572d636"))
                                existingChemotherapyMedictionConcepts.add(o.getValueCoded().getUuid());
                            break;
                        case "centralLine":
                            if (observationConceptUUIDToObsMap.get("361b7f9b-a985-4b18-9055-03af3b41b8b3") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("361b7f9b-a985-4b18-9055-03af3b41b8b3").get(0);
                                o.setValueCoded(conceptService.getConceptByUuid(centralLine));
                            } else {
                                Obs o = new Obs();
                                o.setConcept(conceptService.getConceptByUuid("361b7f9b-a985-4b18-9055-03af3b41b8b3"));
                                o.setValueCoded(conceptService.getConceptByUuid(centralLine));
                                chemotherapyEncounter.addObs(o);
                            }
                            break;
                        case "chemoStartDate":
                            if (observationConceptUUIDToObsMap.get("85c3a99e-0598-4c63-912b-796dee9c75b2") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("85c3a99e-0598-4c63-912b-796dee9c75b2").get(0);
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                    Date parsedDate = formatter.parse(chemoStartDate);
                                    if (o.getValueDate() != parsedDate)
                                        o.setValueDate(parsedDate);
                                }
                            } else {
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("85c3a99e-0598-4c63-912b-796dee9c75b2"));
                                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                    Date parsedDate = formatter.parse(chemoStartDate);
                                    o.setValueDate(parsedDate);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                        case "chemoEndDate":
                            if (observationConceptUUIDToObsMap.get("7dd8b8aa-b0f1-4eb1-862d-b6d737bdd315") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("7dd8b8aa-b0f1-4eb1-862d-b6d737bdd315").get(0);
                                if (chemoEndDate != null && chemoEndDate != "") {
                                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                    Date parsedDate = formatter.parse(chemoEndDate);
                                    if (o.getValueDate() != parsedDate)
                                        o.setValueDate(parsedDate);
                                }
                            } else {
                                if (chemoEndDate != null && chemoEndDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("7dd8b8aa-b0f1-4eb1-862d-b6d737bdd315"));
                                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                    Date parsedDate = formatter.parse(chemoEndDate);
                                    o.setValueDate(parsedDate);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                        case "chemotherapyPcpName":
                            if (observationConceptUUIDToObsMap.get("c2cb2220-c07d-47c6-a4df-e5918aac3fc2") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("c2cb2220-c07d-47c6-a4df-e5918aac3fc2").get(0);
                                if (o.getValueText() != chemotherapyPcpName)
                                    o.setValueText(chemotherapyPcpName);
                            } else {
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("c2cb2220-c07d-47c6-a4df-e5918aac3fc2"));
                                    o.setValueText(chemotherapyPcpName);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                        case "chemotherapyPcpEmail":
                            if (observationConceptUUIDToObsMap.get("898a0028-8c65-4db9-a802-1577fce59864") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("898a0028-8c65-4db9-a802-1577fce59864").get(0);
                                if (o.getValueText() != chemotherapyPcpEmail)
                                    o.setValueText(chemotherapyPcpEmail);
                            } else {
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("898a0028-8c65-4db9-a802-1577fce59864"));
                                    o.setValueText(chemotherapyPcpEmail);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                        case "chemotherapyPcpPhone":
                            if (observationConceptUUIDToObsMap.get("9285b227-4054-4830-ac32-5ea78462e8c4") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("9285b227-4054-4830-ac32-5ea78462e8c4").get(0);
                                if (o.getValueText() != chemotherapyPcpPhone)
                                    o.setValueText(chemotherapyPcpPhone);
                            } else {
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("9285b227-4054-4830-ac32-5ea78462e8c4"));
                                    o.setValueText(chemotherapyPcpPhone);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                        case "chemotherapyInstitutionName":
                            if (observationConceptUUIDToObsMap.get("47d58999-d3b5-4869-a52e-841e2e6bdbb3") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("47d58999-d3b5-4869-a52e-841e2e6bdbb3").get(0);
                                if (o.getValueText() != chemotherapyInstitutionName)
                                    o.setValueText(chemotherapyInstitutionName);
                            } else {
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("47d58999-d3b5-4869-a52e-841e2e6bdbb3"));
                                    o.setValueText(chemotherapyInstitutionName);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                        case "chemotherapyInstitutionCity":
                            if (observationConceptUUIDToObsMap.get("bfa752d6-2037-465e-b0a2-c4c2d485ec32") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("bfa752d6-2037-465e-b0a2-c4c2d485ec32").get(0);
                                if (o.getValueText() != chemotherapyInstitutionCity)
                                    o.setValueText(chemotherapyInstitutionCity);
                            } else {
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("bfa752d6-2037-465e-b0a2-c4c2d485ec32"));
                                    o.setValueText(chemotherapyInstitutionCity);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                        case "chemotherapyInstitutionState":
                            if (observationConceptUUIDToObsMap.get("34489100-487e-443a-bf27-1b6869fb9332") != null) {
                                Obs o = observationConceptUUIDToObsMap.get("34489100-487e-443a-bf27-1b6869fb9332").get(0);
                                if (o.getValueText() != chemotherapyInstitutionState)
                                    o.setValueText(chemotherapyInstitutionState);
                            } else {
                                if (chemoStartDate != null && chemoStartDate != "") {
                                    Obs o = new Obs();
                                    o.setConcept(conceptService.getConceptByUuid("34489100-487e-443a-bf27-1b6869fb9332"));
                                    o.setValueText(chemotherapyInstitutionState);
                                    chemotherapyEncounter.addObs(o);
                                }
                            }
                            break;
                    }
                }
            }
            for(String s: existingChemotherapyMedictionConcepts){
                if (chemotherapyMedictionConcepts.contains(s))
                    chemotherapyMedictionConcepts.remove(s);
                else {
                    for (Obs o : chemotherapyEncounter.getObs()) {
                        if (o.getConcept().getUuid().equals("8481b9da-74e3-45a9-9124-d69ab572d636")) {
                            if (o.getValueCoded().getUuid().equals(s))
                                o.setVoided(true);
                        }
                    }
                }

            }
            for (String s : chemotherapyMedictionConcepts){
                Obs o = new Obs();
                o.setConcept(conceptService.getConceptByUuid("8481b9da-74e3-45a9-9124-d69ab572d636"));
                o.setValueCoded(conceptService.getConceptByUuid(s));
                chemotherapyEncounter.addObs(o);
            }

            encounterService.saveEncounter(chemotherapyEncounter);
        }

    }
}