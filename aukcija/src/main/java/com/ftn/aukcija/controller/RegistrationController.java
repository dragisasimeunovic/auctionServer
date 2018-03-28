package com.ftn.aukcija.controller;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
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

import com.ftn.aukcija.App;
import com.ftn.aukcija.model.Firma;
import com.ftn.aukcija.model.Korisnik;
import com.ftn.aukcija.services.FirmaService;
import com.ftn.aukcija.services.KorisnikService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping(value = "/registration")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private FirmaService firmaService;
	
	public static final String FIRMA = "firma";
	public static boolean groupsSetted = false;
	
	@GetMapping("/processActivation")
	public ResponseEntity<Map<String,Object>> getTask() {
		System.out.println("Hellooo");
		runtimeService.startProcessInstanceByKey("registracija");
		Task task = taskService.createTaskQuery().active().list()
								.get(taskService.createTaskQuery()
								.active().list().size()-1);
		Map<String, Object> taskMap = new HashMap<>();
		taskMap.put("ime", task.getName());
		taskMap.put("taskID", task.getId());
		System.out.println(taskMap.get("ime")+ "i id" + taskMap.get("taskID"));
		return new ResponseEntity<Map<String,Object>>(taskMap, HttpStatus.OK);
	}
	
	@PostMapping("/{taskId}")
	public ResponseEntity<Map<String,Object>> registration(@PathVariable String taskId, @RequestBody Korisnik korisnik){
	
		//TODO: nesto izbacuje za JPA null
		System.out.println("Hellooo");
		
		korisnik.setPotvrdjenMail(false);
		korisnikService.save(korisnik);
		Task task = taskService.createTaskQuery().active().taskId(taskId).singleResult();
		System.out.println("aaaaa " + task.getProcessInstanceId());
		
		
		
		org.activiti.engine.identity.User newUser;
		
		newUser = identityService.newUser(korisnik.getKorisnickoIme());
		newUser.setFirstName(korisnik.getIme());
		newUser.setLastName(korisnik.getPrezime());
		newUser.setEmail(korisnik.getEmail());
		newUser.setPassword(korisnik.getSifra());
		identityService.saveUser(newUser);
	
		identityService.createMembership(newUser.getId(), korisnik.getTip());
		
		HashMap<String, Object> variables = (HashMap<String, Object>) runtimeService
				.getVariables(task.getProcessInstanceId());
		variables.put("korisnik", korisnik);
		System.out.println(variables + "   variables");
		
		taskService.complete(taskId, variables);
		
		if(FIRMA.equals(korisnik.getTip())){
			Task taskFirma = taskService.createTaskQuery().active().list()
					.get(taskService.createTaskQuery().active().list().size() - 1);
			
			Map<String, Object> taskMap = new HashMap<String, Object>();
			taskMap.put("taskId", taskFirma.getId());
			taskMap.put("name", taskFirma.getName());
			taskMap.put("userID", korisnik.getId());
			taskMap.put("korisnik", korisnik);
			System.out.println("firma task id " + taskFirma.getId() + " task name " + taskFirma.getName());
			return new ResponseEntity<Map<String,Object>>(taskMap, HttpStatus.OK);
		}
		
		return new ResponseEntity<Map<String,Object>>(variables, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/firm/{taskId}/{userId}")
	public ResponseEntity<Map<String, Object>> registerFirm(@PathVariable String taskId, @PathVariable Integer userId, @RequestBody Firma firma) {
		
		
		Korisnik korisnik = korisnikService.findById(Integer.toUnsignedLong(userId));
		firma.setAgent(korisnik);
		Firma agentFirma = firmaService.save(firma);
		//korisnik.setFirma(agentFirma);
		korisnikService.save(korisnik);
		
		Task task = taskService.createTaskQuery().active().taskId(taskId).singleResult();
		
		System.out.println("aaaaa " + task.getProcessInstanceId());
		
		HashMap<String, Object> variables = (HashMap<String, Object>) runtimeService
				.getVariables(task.getProcessInstanceId());
		variables.put("korisnik", korisnik);
		
		System.out.println(variables);

		taskService.complete(taskId, variables);

		return new ResponseEntity<>(variables, HttpStatus.OK);
	}
	
	
	@GetMapping("/confirmRegistration")
	public RedirectView confirmRegistration(@RequestParam("username") String username, @RequestParam("task") String task) {
		Execution execution = runtimeService.createExecutionQuery().processInstanceId(task).signalEventSubscriptionName("Aktiviraj korisnika").singleResult();
		runtimeService.signalEventReceived("Aktiviraj korisnika", execution.getId());
		return new RedirectView("http://localhost:4200/");
		
	}

}
