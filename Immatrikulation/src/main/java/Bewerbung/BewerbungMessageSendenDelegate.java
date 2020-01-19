package Bewerbung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BewerbungMessageSendenDelegate implements JavaDelegate {

	public BewerbungMessageSendenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Integer id = (Integer) execution.getVariable("BewerberId");
		String bewerberId = id.toString();
		
		execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("Bewerbung")
		.setVariable("BewerberId", bewerberId)
		.correlate();
	}

}
