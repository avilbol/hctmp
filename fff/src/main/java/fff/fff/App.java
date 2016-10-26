package fff.fff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final Logger LOG = LogManager.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	LOG.info("Hello World!");
    	LOG.debug("Hello World! - Debug");
        System.out.println( "Hello World!" );
    }
}
