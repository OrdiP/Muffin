package com.muffin.server.op;

import com.muffin.server.MuffinServer;
import com.mvu.appengine.AbstractTest;
import com.mvu.appengine.Tools;
import com.mvu.core.shared.entity.Configuration;
import com.mvu.core.shared.entity.EventListener;
import com.mvu.core.shared.entity.ModulesConfiguration;
import com.mvu.core.shared.entity.Template;
import org.junit.Before;

/**
 * Created by Van on 11/17/15.
 */
public class MuffinTestBase extends AbstractTest {
  @Before
  public void setUp() throws Exception {
    super.setUp();
    Tools tools = new Tools();
    tools.loadTypes(true, EventListener.TYPE, Configuration.TYPE, Template.TYPE);
    new MuffinServer().init(false);
  }
}
