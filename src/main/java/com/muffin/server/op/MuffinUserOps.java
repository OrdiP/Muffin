package com.muffin.server.op;

import com.muffin.shared.entity.User;
import com.mvu.appengine.Bean;
import com.mvu.appengine.server.op.user.UserOps;
import com.mvu.core.server.JSON;
import com.mvu.core.server.SessionUser;
import com.mvu.core.shared.ErrorMessage;
import com.mvu.core.shared.entity.Credential;

import static com.mvu.core.shared.entity.Contact.Email;

/**
 * Created by Van on 11/15/15.
 */
public class MuffinUserOps extends UserOps{

  @Override
  public SessionUser createSessionUser(Bean user) {
    SessionUser sessionUser = user.toMap(new SessionUser(), User.TYPE.fields());
    return sessionUser;
  }

  public SessionUser login(JSON params) {
    params.require(Email, Credential.Password);
    Bean user = authenticate(params);
    if (user != null) {
      if (!user.is(com.mvu.core.shared.entity.User.approved)) {
        throw new ErrorMessage("Your account has not been confirmed yet.");
      }
      return startSession(user);
    } else {
      throw new ErrorMessage("Invalid credential");
    }
  }


}
