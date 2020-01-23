package junit.test;

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


	// Methode zum Testen eines Teils des Prozesses (hinter dem Startereignis)
	// Einstiegspunkt in den Prozess muss definiert werden
	@Test
	@Deployment(resources = { "ImmatrikulationBewerber.bpmn" })
	public void testProcessPartFromBezahltGate() throws ParseException {

		// Prozessinstanz von "ImmatrikulationBewerber" vor dem Start der
		// Aktivität mit der Id "gateway_bezahlt"

		// nicht bezahlt false: statusID 10

		// bezahlt true: statusID 9

		processEngine().getRuntimeService().createProcessInstanceByKey("ImmatrikulationBewerber")
				.startBeforeActivity("gateway_bezahlt")
				.setVariable("BewerberId", 13)
				.setVariable("BewerberEmail", "camundaproject12341234@gmail.com")
				.setVariable("bezahlt", false)
				.setVariable("statusID", 10).execute();

	}

}
