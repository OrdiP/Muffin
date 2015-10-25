package com.muffin.client;

import com.mvu.core.client.BaseNavigation;
import com.mvu.core.shared.typekey.CoreSection;
import com.muffin.shared.MuffinSection;

/**
 * Date: 5/28/14 9:41 AM
 */
public class MuffinNavigation extends BaseNavigation {
  public MuffinNavigation() {
    super();
    appearance.fixedTop();
    appearance.addItem(MuffinSection.targets);
    appearance.addItem(CoreSection.core.demoForm());
    appearance.addItem(CoreSection.core.demoTable());
    appearance.addItem(CoreSection.core.settings());
    appearance.addItem(CoreSection.core.templates());
  }

  @Override
  public void onUserChanged() {

  }
}
