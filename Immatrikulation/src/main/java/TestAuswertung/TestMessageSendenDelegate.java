package TestAuswertung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TestMessageSendenDelegate implements JavaDelegate {

	public TestMessageSendenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("ImmatrikulationTestStatus")
		.correlateAll();
	}

}
