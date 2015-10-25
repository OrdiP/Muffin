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
  }

  @Override
  public void onUserChanged() {

  }
}
