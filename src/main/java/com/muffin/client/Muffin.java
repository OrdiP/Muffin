package com.muffin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.muffin.client.bundle.MuffinResources;
import com.muffin.client.view.MuffinNavBar;
import com.mvu.core.shared.SharedConstants;
/**
 * Date: 5/28/14 9:28 AM
 */
public class Muffin implements EntryPoint {

  public void onModuleLoad() {
    RootPanel.get(SharedConstants.UI_NAVIGATION).add(new MuffinNavBar());
    MuffinResources.RESOURCES.css().ensureInjected();
  }
}
