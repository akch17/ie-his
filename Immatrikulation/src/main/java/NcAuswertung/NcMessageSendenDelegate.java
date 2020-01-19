package NcAuswertung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NcMessageSendenDelegate implements JavaDelegate {

	public NcMessageSendenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("ImmatrikulationNcStatus")
				.correlateAll();
	}

}
