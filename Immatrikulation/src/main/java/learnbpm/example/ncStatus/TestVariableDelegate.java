package learnbpm.example.ncStatus;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.BooleanValue;
import org.camunda.bpm.engine.variable.value.IntegerValue;

public class TestVariableDelegate implements JavaDelegate {

	public TestVariableDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		    IntegerValue test = Variables.integerValue(4);
			execution.setVariable("testBewerberId", test);

	}

}
