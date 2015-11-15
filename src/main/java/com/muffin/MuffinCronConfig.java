package com.muffin;

import java.io.IOException;

import com.muffin.server.op.cron.ValidateJobPostOp;
import com.mvu.appengine.tools.CronConfig;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class MuffinCronConfig extends CronConfig {

  public static void main(String[] args) throws IOException {
    new MuffinCronConfig().write();
  }

  @Override
  protected void initOps() {
    addCronJob(ValidateJobPostOp.class, "Active new job", "every day 01:00");
  }
}
