package com.muffin.server;

import javax.servlet.ServletException;

import com.muffin.server.op.MuffinUserOps;
import com.muffin.shared.entity.User;
import com.mvu.appengine.AppEngine;
import com.mvu.appengine.server.op.user.UserOps;
import com.mvu.core.server.ServerTypeSystem;

/**
 * Created by Van on 11/15/15.
 */
public class MuffinServer extends AppEngine {
  @Override
  public void init() throws ServletException {
    super.init();
    User.TYPE.entityName();
    ServerTypeSystem.replace(UserOps.class, MuffinUserOps.class);
  }

}
