/**
 * 
 */
package commutingChallenge.JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import commutingChallenge.driver.Driver;

/**
 * @author Abhishek
 *
 */
public class TestJUnit1 {

	@Test
	public void test(){
		
		Driver d = new Driver();
		Double dist = d.distance(37.7768016, -122.4169151, 37.7860105, -122.4025377);
		assertEquals(1.0105741006412017, dist, 0.0);
		
	}

	
}
