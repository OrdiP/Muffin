package com.muffin;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.muffin.client.SanityTestGwt;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Class description...
 *
 *  @author mvu
 */
public class GwtTestSuite extends TestCase {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("UI Suite");
    suite.addTestSuite(SanityTestGwt.class);
    return suite;
  }
}
