package Buchhaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BuchhaltungMessageSendenDelegate implements JavaDelegate {

	public BuchhaltungMessageSendenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("ImmatrikulationBuchhaltungStatus")
		.correlateAll();
		
	}

}
