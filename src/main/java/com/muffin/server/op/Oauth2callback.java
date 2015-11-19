package com.muffin.server.op;

import java.util.Date;

import com.mvu.appengine.Bean;
import com.mvu.appengine.Bundle;
import com.mvu.appengine.server.op.AppEngineOp;
import com.mvu.appengine.server.op.user.UserOps;
import com.mvu.core.server.JSON;
import com.mvu.core.server.Server;
import com.mvu.core.server.ThreadContext;
import com.mvu.core.server.op.bitbucket.Authorize;
import com.mvu.core.shared.entity.BitBucketConfiguration;
import com.mvu.core.shared.entity.Credential;
import com.mvu.core.shared.entity.User;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import static com.mvu.core.shared.entity.OAuthConfig.refresh_token;

/**
 * Created by Van on 11/18/15.
 */
public class Oauth2callback extends AppEngineOp {
  @Override
  public Object execute(JSON input) throws Exception {
    String code = input.getString("code");
    String urlParameters = "code="
            + code
            + "&client_id=570682536819-gvfntc6o18jujnggup1tclmtrn0hq6i3.apps.googleusercontent.com"
            + "&client_secret=8iy-js1ndGOnr-MB3sIa6TcB"
            + "&redirect_uri=https://muffin-master.appspot.com/execute/Oauth2callback"
            + "&grant_type=authorization_code";
    final String response = Server.httpService().send("https://accounts.google.com/o/oauth2/token",
            "POST", urlParameters);
    final JSONObject authorizationJson = (JSONObject) JSONValue.parseWithException(response);
    final String access_token = (String) authorizationJson.get("access_token");
    final String userResponse = Server.httpService().get("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token);
    logger().info(userResponse);
    final JSONObject userJson = (JSONObject) JSONValue.parse(userResponse);
    final Bean user = ensureUser(userJson);
    if(!user.hasValue(refresh_token)) {
      user.set(refresh_token, (String) authorizationJson.get("refresh_token"));
      user.set(BitBucketConfiguration.access_token, access_token);
      user.set(BitBucketConfiguration.expires_in, new Date(System.currentTimeMillis() + (int) authorizationJson.get("expires_in")));
      user.save();
    }
    getOp(UserOps.class).startSession(user);
    sendRedirect();
    return "";
  }

  private Bean ensureUser(JSONObject user) {
    final Bundle bundle = new Bundle();
    String givenName = (String) user.get("given_name");
    String email = (String) user.get("email");
    String lastName = (String) user.get("family_name");
    Bean bean = bundle.ensure(User.TYPE, email);
    bean.set(User.FirstName, givenName);
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
