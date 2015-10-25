package com.muffin;

import java.util.concurrent.Callable;

import com.mvu.appengine.RemoteAPI;
import com.mvu.appengine.Tools;
import com.mvu.core.shared.Type;
import com.mvu.core.shared.entity.Configuration;
import com.mvu.core.shared.entity.EventListener;
import com.mvu.core.shared.entity.Template;
import org.junit.Test;

/**
 * Class description...
 *
 * @author mvu
 */
public class MuffinTools extends Tools {
  private static final Type[] TYPES = {Configuration.TYPE, Template.TYPE, EventListener.TYPE};

  @Test
  public void reset() throws Exception {
    RemoteAPI.getInstance().runOnDev(new Callable() {
      @Override
      public Object call() throws Exception {
        loadTypes(true, TYPES);
        return createSuperUser("su@test.com");
      }
    });
  }
}
