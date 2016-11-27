package cm.gojeck.inputstrategy;

import java.io.IOException;

public class InputContext {
	private InputStrategy inputStrategy;

	public InputContext(InputStrategy inputStrategy) {
		super();
		this.inputStrategy = inputStrategy;
	}
	
	public void execute(String inputDetails) throws Exception{
		inputStrategy.readInput(inputDetails);
	}
}
