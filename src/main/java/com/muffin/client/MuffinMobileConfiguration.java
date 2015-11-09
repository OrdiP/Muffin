package com.muffin.client;

import com.muffin.client.MuffinActivityMapper;
import com.muffin.client.MuffinNavagation;
import com.mvu.core.client.CoreActivityMapper;
import com.mvu.core.client.CoreConfiguration;
import com.mvu.core.client.Navigation;

/**
 * Created by Van on 11/8/15.
 */
public class MuffinMobileConfiguration extends CoreConfiguration {
  @Override
  public CoreActivityMapper createActivityMapper() {
    return new MuffinActivityMapper();
  }

  @Override
  public Navigation initNavigation() {
    return new MuffinNavagation();
  }
}
