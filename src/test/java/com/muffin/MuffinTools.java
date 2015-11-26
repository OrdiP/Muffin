package com.muffin;

import java.util.concurrent.Callable;

import com.muffin.shared.entity.JobPost;
import com.mvu.appengine.Bean;
import com.mvu.appengine.Bundle;
import com.mvu.appengine.RemoteAPI;
import com.mvu.appengine.Tools;
import com.mvu.appengine.server.op.user.SaveUserOp;
import com.mvu.core.client.Permission;
import com.mvu.core.server.JSON;
import com.mvu.core.shared.Type;
import com.mvu.core.shared.entity.Configuration;
import com.mvu.core.shared.entity.Credential;
import com.mvu.core.shared.entity.EventListener;
import com.mvu.core.shared.entity.Template;
import com.mvu.core.shared.entity.User;
import org.junit.Test;

/**
 * Class description...
 *
 * @author mvu
 */
public class MuffinTools extends Tools {
  private static final String MASTER = "muffin-master";
  private static final Type[] TYPES = {Configuration.TYPE, Template.TYPE, EventListener.TYPE};

  @Test
  public void reset() throws Exception {
    RemoteAPI.getInstance().runRemoteOn(null, new Callable() {
      @Override
      public Object call() throws Exception {
        loadTypes(true, Configuration.TYPE, Template.TYPE);
        createSuperUser("su@test.com");
        createJobPosts();
        return null;
      }
    });
  }

  @Test
  public void downloadTypes() {
    upload(MASTER, false, Template.TYPE, EventListener.TYPE);
  }

  public Bean createSuperUser(String email) throws Exception {
    //        Server.init();
    final Bundle bundle = new Bundle();
    if (bundle.exists(Credential.TYPE.entityName(), email)) {
      bundle.get(User.TYPE, email).delete();

      bundle.get(Credential.TYPE, email).delete();
    }
    final JSON params = JSON.make(User.FirstName, "System")
            .set(User.keyName, email)
            .set("role", "Admin")
            .set(User.approved, true)
            .set(User.LastName, "User")
            .set(User.Email, email)
            .set(Credential.Password, "pppppp");
    params.addToList(User.Permissions, false, Permission.ADMIN.name());
    params.addToList(User.Permissions, false, Permission.SYSTEM.name());
    return new SaveUserOp().execute(params);
//        return null;
  }

  @Test
  public void testName() throws Exception {
    RemoteAPI.getInstance().runOnDev(new Callable() {
      @Override
      public Object call() throws Exception {
        createJobPosts();
        return null;
      }
    });

  }

  private void createJobPosts() {
    Bundle bundle = new Bundle();
    for (int i = 0 ; i < 20 ; i++) {
      bundle.create(JobPost.TYPE).set(JobPost.description, "Yahoo is in need of a good developer")
      .set(JobPost.title, "Java Web Developers").save();
    }
    bundle.commit();
  }
}
