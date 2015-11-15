package com.muffin.client.view.activities;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.muffin.client.view.job.AdminPostJobForm;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.form.Form;
import com.mvu.core.shared.Place;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class PostJobActivity implements BaseActivity {
  @Override
  public void start(AcceptsOneWidget panel, Place place) {
    Form jobForm = new AdminPostJobForm();
    panel.setWidget(jobForm);
  }
}
