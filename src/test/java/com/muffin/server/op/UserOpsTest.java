package com.muffin.server.op;

import com.muffin.server.op.register.RegisterUserOp;
import com.muffin.shared.entity.User;
import com.mvu.appengine.AppEngineTest;
import com.mvu.core.server.JSON;
import com.mvu.core.shared.entity.Credential;
import org.junit.Test;

/**
 * Created by Van on 11/17/15.
 */
public class UserOpsTest extends MuffinTestBase {

  public static JSON sampleUser = JSON.empty()
          .set(User.FirstName, "Jack")
          .set(User.LastName, "Sparrow")
          .set(User.Email, "jack@sparrow.com")
          .set(Credential.Password, "pppppp");

  @Test
  public void testRegisterUser() throws Exception {
    getOp(RegisterUserOp.class).execute(sampleUser);
    assertEmailSent("activate_account");

  }
}
