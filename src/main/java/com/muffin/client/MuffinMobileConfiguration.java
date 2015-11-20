package com.muffin.client;

import com.mvu.core.client.BaseNavigation;
import com.mvu.core.client.CoreActivityMapper;
import com.mvu.core.client.CoreConfiguration;
import com.mvu.core.client.ModulesManager;
import com.mvu.gapi.client.Drive;

/**
 * Created by Van on 11/8/15.
 */
public class MuffinMobileConfiguration extends CoreConfiguration {
  @Override
  public CoreActivityMapper createActivityMapper() {
    return new MuffinActivityMapper();
  }

  @Override
  public void initComponents(ModulesManager modulesManager) {
    super.initComponents(modulesManager);
    Drive.init();
  }

  @Override
  public BaseNavigation initNavigation() {
    return new MuffinNavagation();
  }
}
