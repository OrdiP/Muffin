package com.muffin.client;

import com.mvu.core.client.CoreActivityMapper;
import com.mvu.core.client.CoreConfiguration;
import com.mvu.core.client.Navigation;

/**
 * Date: 6/10/14 1:14 PM
 */
public class MuffinConfiguration extends CoreConfiguration {
  @Override
  public CoreActivityMapper createActivityMapper() {
    return new MuffinActivityMapper();
  }

  @Override
  public Navigation initNavigation() {
    return new MuffinNavigation();
  }
}
