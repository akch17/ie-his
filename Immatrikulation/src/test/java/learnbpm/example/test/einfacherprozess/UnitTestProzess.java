package learnbpm.example.test.einfacherprozess;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.text.ParseException;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class UnitTestProzess {

	// Variante 1 (Standard): Testen eines einzigen Prozessmodells

	/*
	 * @ClassRule
	 * 
	 * @Rule public static ProcessEngineRule rule =
	 * TestCoverageProcessEngineRuleBuilder.create().build();
	 * 
	 */

	// Variante 2 wenn man mehr als ein Prozessmodell in einer Testklasse testen
	// will, muss man
	// @Class Rule auskommentieren
	// und ProcessEngineRule als nicht static erzeugen

	@Rule
	public ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	static {
		LogFactory.useSlf4jLogging(); // MyBatis
	}

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}
	
	
	// Methode zum Testen des gesamten Durchlauf des Prozesses vom Startereignis bis
	// zum Endereignis
	//@Test
	//@Deployment(resources = { "ImmatrikulationBewerber.bpmn" }) // Einbindung des Prozessmodells in den Test
	// public void testCompleteProcessDategenform() throws ParseException {	}	

	
		// Methode zum Testen eines Teils des Prozesses (hinter dem Startereignis)
		// Einstiegspunkt in den Prozess muss definiert werden
		@Test
		@Deployment(resources = { "ImmatrikulationBewerber.bpmn" })
		public void testProcessPartFromDategenform() throws ParseException {
		
			// Prozessinstanz von "ImmatrikulationBewerber" vor dem Start der Aktivität mit der Id "gateway_bezahlt"
			
			
			//nicht bezahlt false: statusID 10 
			
			// bezahlt true: statusID 9
			
			
			
			processEngine().getRuntimeService().createProcessInstanceByKey("ImmatrikulationBewerber")
			  .startBeforeActivity("gateway_bezahlt") 
			  .setVariable("BewerberId", 13)
			  .setVariable("BewerberEmail","camundaproject12341234@gmail.com" )
			  .setVariable("bezahlt",true )
			  .setVariable("statusID", 9)
			  .execute();
		
		}
		/*	
		
		// alternative Methode zum Testen des gesamten Prozesses bei der die ProcessInstanceQuery
		// zum Auffinden der richtigen Prozessinstanz genutzt wird (hier nicht unbedingt nötig da nur einn
		//Prozessmodell verwendet wird, bei mehr als einem Modell aber notwendig)
		@Test
		@Deployment(resources = { "u-date-genform-testing.bpmn" })
		public void testCompleteProcessDategenformAlternative() throws ParseException {

			// Prozess über dessen Key (=Id) starten ohne Prozessvariablen in de Prozess reinzugeben (hier nicht notwendig)
			ProcessInstance processInstance = processEngine().getRuntimeService()
					.startProcessInstanceByKey("u-date-genform-testing");

			// Id der gestarteten Prozessinstanz holen
			String processInstanceID = processInstance.getProcessInstanceId();

			// prüfen ob Prozessinstanz noch existiert oder abgebrochen wurde

			List<ProcessInstance> processinstancelist = processEngine().getRuntimeService().createProcessInstanceQuery().
					processInstanceId(processInstanceID).list();
			
				

			// optionale Zusatzinfo zum Debuggen
			System.out.println("Prozesslistenelemente: " + processinstancelist.size());

			// wenn die Lister der aktiven Prozessinstanzen nicht leer ist, dann folgendes
			// durchführen
			if (!processinstancelist.isEmpty()) {
				
				
				//---------------------------------------------------------------
				//User-Task "Tippfristende eingeben" simulieren
				//---------------------------------------------------------------
				
							

				// Nach dem User-Task mit dem Namen "Tippfristende eingeben" suchen

				// Task-Service holen
				TaskService taskService = processEngine().getTaskService();

				// über verketteten Methodenaufruf (FLUENT-API) eine Query mit dem Task-Service
				// starten
				// die in diesem Fall nach Tasks mit dem Namen "Tippfristende eingeben" sucht und
				// diese als typisierte Java-List zurückgibt
				List<Task> Taskliste = taskService.createTaskQuery().taskName("Tippfristende eingeben").list();

				// Erstes Element aus Taskliste holen
				Task meinTask = Taskliste.get(0);

				// optional zum Debuggen: Taskname auf der Konsole ausgeben
				System.out.println(meinTask.getName());

				// Datumsvariable "tippfristende" die über den User-Task erfasst werden sollen
				// erzeugen und mit Werten belegen

				// erzeugen der Datumsvariable mit Wert und Festlegung des Formats
				Date einTippfristende = new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-11");

				// Map erzeugen, in diese die Variable "tippfristende" mit Wert ablegen

				Map<String, Object> variables = new HashMap<String, Object>();
				
				variables.put("tippfristende", einTippfristende);

				// Task "completen" und Prozessvariablen aus Map übergeben
				taskService.complete(meinTask.getId(), variables);

				//---------------------------------------------------------------
				// User-Task "Tippdatum eingeben" simulieren
				//---------------------------------------------------------------
				
				// Nach dem Task durchgeführt und abgeschlossen ist, wird der User-Task
				// "Tippdatum eingeben" aktiviert
				// im folgenden auch nach diesem suchen und durchführen lassen

				List<Task> weitereTaskliste = taskService.createTaskQuery().taskName("Tippdatum eingeben").list();

				Task weitererTask = weitereTaskliste.get(0);

				// optional zum Debuggen: Taskname auf der Konsole ausgeben
				System.out.println(weitererTask.getName());

				// Datumsvariable "tippdatum" die über den User-Task erfasst werden sollen
				// erzeugen und mit Werten belegen

				// erzeugen der Datumsvariable mit Wert und Festlegung des Formats
				Date einTippdatum = new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-11");

				// Map erzeugen, in diese die Variable "tippdatum" mit Wert ablegen

				Map<String, Object> weitere_variables = new HashMap<String, Object>();
				
				weitere_variables.put("tippdatum", einTippdatum);

				// Task "completen" und Prozessvariablen aus Map übergeben
				taskService.complete(weitererTask.getId(), weitere_variables);

			}

		}
	
	
*/

}
