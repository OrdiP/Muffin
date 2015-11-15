package com.muffin.client.view.activities;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.muffin.client.bundle.MuffinResources;
import com.muffin.client.view.job.AdminPostJobForm;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.appearance.BackgroundAP;
import com.mvu.core.client.form.Form;
import com.mvu.core.client.view.BackgroundView;
import com.mvu.core.shared.Place;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class PostJobActivity implements BaseActivity {
  @Override
  public void start(AcceptsOneWidget panel, Place place) {
    Form jobForm = new AdminPostJobForm();
    BackgroundAP background = createBackground();
    final Style style = jobForm.asWidget().getElement().getStyle();
    style.setMarginTop(100, Style.Unit.PX);
    style.setMarginBottom(100, Style.Unit.PX);
    background.setTop(jobForm);
    panel.setWidget(background);
  }

  private BackgroundAP createBackground() {
    return new BackgroundView(MuffinResources.RESOURCES.postJob());
  }
}