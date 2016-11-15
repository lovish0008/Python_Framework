package execution;

public class ExecuteSuite {

	public static void main(String[] args) {

		Execution execution = new Execution();
		
		execution.init();
		execution.execute();
		execution.generateReport();

	}

}
