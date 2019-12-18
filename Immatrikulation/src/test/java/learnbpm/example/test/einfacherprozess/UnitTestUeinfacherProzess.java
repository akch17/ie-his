package learnbpm.example.test.einfacherprozess;

import org.apache.ibatis.logging.LogFactory;


import org.camunda.bpm.engine.runtime.ProcessInstance;

import org.camunda.bpm.engine.test.ProcessEngineRule;

import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;

import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;


/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class UnitTestUeinfacherProzess {

	// Variante 1 (Standard): Testen eines einzigen Prozessmodells

	/*
	 * @ClassRule
	 * 
	 * @Rule public static ProcessEngineRule rule =
	 * TestCoverageProcessEngineRuleBuilder.create().build();
	 * 
	 */

	// Variante 2 wenn man mehr als ein Prozessmodell in einer Testklasse testen
	// will

	// @Rule
	// public ProcessEngineRule rule =
	// TestCoverageProcessEngineRuleBuilder.create().build();

	// hier Variante 1
	@ClassRule

	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	static {
		LogFactory.useSlf4jLogging(); // MyBatis
	}

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	// JUnit-Test für den gesamten Prozess
	@Test
	@Deployment(resources = "testdiagramme.bpmn") // hier die Prozessmodelle aufführen die getest werden sollen
	public void testProzess() {

		// Prozessinstanz des Prozessmodells mit dem Key (=Id des Prozessmodells)
		// erzeugen und starten

		ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByKey("Testprozess");
		System.out.println("Prozessinstanz mit der Id "+ pi.getId()+ " gestartet");
	}

	// JUnit-Test für einen Prozessteil
	@Test
	@Deployment(resources = "testdiagramme.bpmn")
	public void testTeilprozess() {

		// Prozessinstanz starten vor der Aktivität mit dem Key (=Id des Elements im
		// Prozessmodell)
	//	ProcessInstance pi = processEngine().getRuntimeService().createProcessInstanceByKey("Testabwicklung")
	//			.startBeforeActivity("Nachrichtsenden").execute();
		
	//	System.out.println("Prozessinstanz mit der Id "+ pi.getId()+ " gestartet");

	}

}
