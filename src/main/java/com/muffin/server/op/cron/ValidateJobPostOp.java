package com.muffin.server.op.cron;

import java.util.Date;

import com.muffin.shared.entity.JobPost;
import com.mvu.appengine.Bean;
import com.mvu.appengine.server.op.CronOp;
import com.mvu.core.server.JSON;
import com.mvu.core.shared.typekey.CompareType;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class ValidateJobPostOp extends CronOp {
  @Override
  protected String getMessage() {
    return null;
  }

  @Override
  protected void processItem(Bean bean, Date now) {
    if (bean.get(JobPost.fromDate).before(now)) {
      bean.set(JobPost.active, true);
    }
  }

  @Override
  public Iterable<Bean> findItems(JSON input, Date lastRun, Date now) {
    return bundle.find(JobPost.TYPE).where(JobPost.toDate, CompareType.LESS_THAN_OR_EQUAL, now);
  }
}
