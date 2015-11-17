package com.muffin.server.op.register;

import com.mvu.appengine.Bean;
import com.mvu.appengine.server.op.user.SaveUserOp;
import com.mvu.core.server.JSON;

/**
 * Created by Van on 11/17/15.
 */
public class RegisterUserOp extends SaveUserOp {
  @Override
  public Bean execute(JSON input) throws Exception {
    Bean user = getOp(SaveUserOp.class).execute(input);
    user.addEvent("User Created").save();
    return user;
  }
}
