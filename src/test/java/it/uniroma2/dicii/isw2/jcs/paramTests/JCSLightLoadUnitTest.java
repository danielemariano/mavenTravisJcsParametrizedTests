package it.uniroma2.dicii.isw2.jcs.paramTests;

import org.apache.jcs.JCS;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Description of the Class
 *
 */
public class JCSLightLoadUnitTest
    extends TestCase
{
	

    private static int items = 999;

    /**
     * Test setup
     */
    public void setUp()
        throws Exception
    {
        JCS.setConfigFilename( "/TestSimpleLoad.ccf" );
        JCS.getInstance( "testCache1" );
    }

    /**
     * Constructor for the TestSimpleLoad object
     *
     * @param testName
     *            Description of the Parameter
     */
    public JCSLightLoadUnitTest( String testName )
    {
        super( testName );
    }

    /**
     * Description of the Method
     *
     * @param args
     *            Description of the Parameter
     */
    public static void main( String args[] )
    {
        String[] testCaseName = { JCSLightLoadUnitTest.class.getName() };
        junit.textui.TestRunner.main( testCaseName );
    }

    /**
     * A unit test suite for JUnit
     *
     * @return The test suite
     */
    public static Test suite()
    {
        return new TestSuite( JCSLightLoadUnitTest.class );
    }

    /**
     * A unit test for JUnit
     *
     * @exception Exception
     *                Description of the Exception
     */
    public void testSimpleLoad()
        throws Exception
    {
        JCS jcs = JCS.getInstance( "testCache1" );
            //ICompositeCacheAttributes cattr = jcs.getCacheAttributes();
            //cattr.setMaxObjects( 20002 );
            //jcs.setCacheAttributes( cattr );

        for ( int i = 1; i <= items; i++ )
        {
            jcs.put( i + ":key", "data" + i );
        }

        for ( int i = items; i > 0; i-- )
        {
            String res = (String) jcs.get( i + ":key" );
            if ( res == null )
            {
                assertNotNull( "[" + i + ":key] should not be null", res );
            }
        }

        // test removal
        jcs.remove( "300:key" );
        assertNull( jcs.get( "300:key" ) );

    }

}
