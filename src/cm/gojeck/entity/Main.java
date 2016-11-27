package cm.gojeck.entity;

import cm.gojeck.inputstrategy.*;

public class Main {

	public static void main(String[] args) throws Exception {
		if(args.length == 0 || args[0].equals("")){
			InputContext input = new InputContext(new UserInput());
			input.execute("");
		}else{
			InputContext input = new InputContext(new FileInput());
			input.execute(args[0].trim());
		}
		
	}

}
