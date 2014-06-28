/**
 * 
 */
package commutingChallenge.JUnit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 * @author Abhishek
 *
 */
public class TestRunner {
 public static void main(String[] args){
	 Result result = JUnitCore.runClasses(TestJUnit1.class);
	 for(Failure failure : result.getFailures()){
		 System.out.println(failure.toString());
	 }
	 System.out.println("Result of the test: "+result.wasSuccessful());
	 
 }
	
}
