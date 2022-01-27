package it.uniroma2.dicii.isw2.jcs.paramTests;

import java.util.Arrays;
import java.util.Collection;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestCase;

// Description of the Class
@RunWith(Parameterized.class)
public class ParametricJCSLightLoadUnitTest extends TestCase {

	private int items;
	private JCS jcs;
    
    // Test setup class
    @Before
    public void setUp() throws Exception {
    	configure();
    }

    // Configuration test parameters
    private void configure() throws CacheException {
        JCS.setConfigFilename( "/TestSimpleLoad.ccf" );
        jcs = JCS.getInstance( "testCache1" );
        data();
    }
    
    // Constructor for the TestSimpleLoad object
    public ParametricJCSLightLoadUnitTest(int items, String expected) {
    	this.items = 999;
    }
    
    // Init test items array
    @Parameters
    public static Collection<Object[]> data() {
    	Object[][] data = new Object[][]{ {999, null}, }; 
        return Arrays.asList(data);
    }

    // A unit test for JUnit
    @Test
    public void testSimpleLoad() throws Exception {  
    	// Test put
        for ( int i = 2; i <= items; i++ ) {
            jcs.put( i + ":key", "data" + i );
        }
        jcs.put( null + ":key", "data" + 1 );

        // Test null
        for ( int i = items; i > 0; i-- ) {
            String res = (String) jcs.get( i + ":key" );
            if ( res == null ) {
            	System.out.println("[ " + i + ":key ] should not be null." + " res: " + res);
            }
        }

        // Test removal
        jcs.remove( "300:key" );
        assertNull( jcs.get( "300:key" ) );
    }

}
