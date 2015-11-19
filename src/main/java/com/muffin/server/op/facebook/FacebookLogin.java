package com.muffin.server.op.facebook;

import java.util.Map;

import com.mvu.appengine.Bean;
import com.mvu.appengine.Bundle;
import com.mvu.appengine.server.op.AppEngineOp;
import com.mvu.appengine.server.op.user.UserOps;
import com.mvu.core.server.JSON;
import com.mvu.core.shared.entity.Credential;
import com.mvu.core.shared.entity.User;

/**
 * Created by Van on 11/19/15.
 */
public class FacebookLogin extends AppEngineOp {
  @Override
  public Object execute(JSON input) throws Exception {
    String code = input.getString("code");
    if (code == null || code.equals("")) {
      throw new RuntimeException(
              "ERROR: Didn't get code parameter in callback.");
    }
    FBConnection fbConnection = new FBConnection();
    String accessToken = fbConnection.getAccessToken(code);

    FBGraph fbGraph = new FBGraph(accessToken);
    String graph = fbGraph.getFBGraph();
    Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
    final Bean user = ensureUser(fbProfileData);
    getOp(UserOps.class).startSession(user);
    sendRedirect();
    return "";
  }

  private Bean ensureUser(Map<String, String> user) {
    final Bundle bundle = new Bundle();
    String firstName = user.get("first_name");
    String lastName = user.get("last_name");
    String email = user.get("email");
    Bean bean = bundle.ensure(User.TYPE, email);
    bean.set(User.FirstName, firstName);
    bean.set(User.LastName, lastName);
    bean.set(User.Email, email);
    if (!bean.hasValue(User.CredentialKey)) {
      Bean credential = bundle.create(Credential.TYPE, email)
              .set(Credential.Password, "pppppp")
              .set(Credential.UserKey, bean.getKeyCode());
      bean.set(User.CredentialKey, credential.getKeyCode());
    }

    bundle.commit();
    return bean;
  }

}
