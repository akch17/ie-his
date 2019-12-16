package learnbpm.example.nc;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StarteProzessImmatrikulationNcDelegate implements JavaDelegate {

	public StarteProzessImmatrikulationNcDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String id = "4";
		RuntimeService rtm = execution.getProcessEngineServices().getRuntimeService();
		rtm.startProcessInstanceByMessage("ImmatrikulationNcStatus",id);
	}

}
