package com.muffin.client;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Class description...
 *
 *  @author mvu
 */
public class SanityTestGwt extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "com.muffin.Muffin";
  }

  public void testStartUp(){
    System.out.println("Yes we can start.");
  }
}
