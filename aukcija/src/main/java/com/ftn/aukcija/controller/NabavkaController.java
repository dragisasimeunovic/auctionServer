package com.ftn.aukcija.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ftn.aukcija.constants.Constants;
import com.ftn.aukcija.model.Firma;
import com.ftn.aukcija.model.ZahtevZaNabavku;
import com.ftn.aukcija.services.NabavkaService;

@RestController
@RequestMapping(value = "/nabavka")
@CrossOrigin(origins = "http://localhost:4200")
public class NabavkaController {

	@Autowired
	private NabavkaService nabavkaService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping("/saveSupplyRequest/{korisnikID}")
	public ResponseEntity<Map<String,Object>> saveSupplyRequest(@RequestBody ZahtevZaNabavku zahtevZaNabavku, @PathVariable Long korisnikID) throws ParseException{
	
		
		runtimeService.startProcessInstanceByKey("aukcija");
		Task task = taskService.createTaskQuery().active().list()
								.get(taskService.createTaskQuery()
								.active().list().size()-1);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		Date date = format1.parse(zahtevZaNabavku.getRokZaPonude());
		System.out.println(format2.format(date));
		
		ArrayList<Firma> firme = (ArrayList<Firma>) nabavkaService.getFirmsForAuction(zahtevZaNabavku, korisnikID);
		
		Task task1 = taskService.createTaskQuery().active().taskId(task.getId()).singleResult();
		
		HashMap<String, Object> dataMap = (HashMap<String, Object>) runtimeService.getVariables(task1.getProcessInstanceId());
		dataMap.put("taskID", task1.getId());

		
		if (firme.isEmpty()) {
			dataMap.put("status", Constants.NEMA_FIRMI_IZ_IZABRANE_KATEGORIJE);
		}
		else if (firme.size() < zahtevZaNabavku.getMaxBrojPonuda()) {
			dataMap.put("status", Constants.MANJI_BROJ_FIRMI_OD_OCEKIVANOG);
		}
		else {
			dataMap.put("status", Constants.OK);
		}
		
		nabavkaService.save(zahtevZaNabavku);
		
		dataMap.put("zahtjev", zahtevZaNabavku);
		dataMap.put("korisnik", zahtevZaNabavku.getKorisnik());
		dataMap.put("firme", firme);
		
		
		taskService.complete(task1.getId(), dataMap);
		
		
		
		return new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
	}
	
	
	@GetMapping("/lackOfFirmsDecision")
	public RedirectView lackOfFirmsDecision(@RequestParam("decision") String decision, @RequestParam("task") String task) {
		
		if (decision.equals(Constants.YES)) {
			Execution execution = runtimeService.createExecutionQuery().processInstanceId(task).signalEventSubscriptionName("posaljiNaManjeFirmi").singleResult();
			runtimeService.signalEventReceived("posaljiNaManjeFirmi", execution.getId());
		}
		else {
			Execution execution = runtimeService.createExecutionQuery().processInstanceId(task).signalEventSubscriptionName("neSaljiNaManjeFirmi").singleResult();
			runtimeService.signalEventReceived("neSaljiNaManjeFirmi", execution.getId());
		}
		
		return new RedirectView("http://localhost:4200/");
	}
	
	
	
}
