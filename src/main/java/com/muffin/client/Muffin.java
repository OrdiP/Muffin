package com.muffin.client;

import com.google.gwt.core.client.EntryPoint;
import com.muffin.client.bundle.MuffinResources;

/**
 * Date: 5/28/14 9:28 AM
 */
public class Muffin implements EntryPoint {
  public void onModuleLoad() {
    MuffinResources.RESOURCES.css().ensureInjected();
  }
}
