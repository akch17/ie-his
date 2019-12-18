package testdiagramme;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class setzeVariableDelegate implements JavaDelegate {

	public setzeVariableDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("BewerberID", "7"); //bestanden
		execution.setVariable("BewerberID2", "8");
		execution.setVariable("BewerberEmail", "windrich@gmx.de");
	}

}
