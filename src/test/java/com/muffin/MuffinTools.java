package com.muffin;

import java.util.concurrent.Callable;

import com.muffin.shared.entity.JobPost;
import com.mvu.appengine.Bundle;
import com.mvu.appengine.RemoteAPI;
import com.mvu.appengine.Tools;
import com.mvu.core.shared.Type;
import com.mvu.core.shared.entity.Configuration;
import com.mvu.core.shared.entity.EventListener;
import com.mvu.core.shared.entity.Template;
import org.junit.Test;
import org.quartz.Job;

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
        createJobPosts();
        return createSuperUser("su@test.com");
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
