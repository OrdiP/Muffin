package com.muffin.client;

import com.mvu.core.client.CoreActivityMapper;
import com.mvu.core.client.CoreConfiguration;
import com.mvu.core.client.ModulesManager;
import com.mvu.core.client.Navigation;
import com.mvu.core.client.feedback.FeedbackMod;
import com.mvu.core.client.help.HelpMod;
import com.mvu.core.shared.Function;
import com.mvu.core.shared.HasFields;
import com.mvu.gapi.client.Drive;

/**
 * Date: 6/10/14 1:14 PM
 */
public class MuffinConfiguration extends CoreConfiguration {
  @Override
  public CoreActivityMapper createActivityMapper() {
    return new MuffinActivityMapper();
  }

  @Override
  public void initComponents(ModulesManager modulesManager) {
    super.initComponents(modulesManager);
    HelpMod.init(new Function<HasFields, Boolean>() {
      @Override
      public Boolean eval(HasFields input) {
        return true;
      }
    });
    FeedbackMod.init(new Function<HasFields, Boolean>() {
      @Override
      public Boolean eval(HasFields input) {
        return true;
      }
    });
    Drive.init();
  }

  @Override
  public Navigation initNavigation() {
    return new MuffinNavagation();
  }
}
