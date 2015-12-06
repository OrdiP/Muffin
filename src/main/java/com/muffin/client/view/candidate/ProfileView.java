package com.muffin.client.view.candidate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.shared.Place;

/**
 * Created by Van on 12/5/15.
 */
public class ProfileView  implements IsWidget, BaseActivity{

  private HTMLPanel panel;

  @Override
  public void start(AcceptsOneWidget panel, Place place) {
    panel.setWidget(this.panel);
  }

  @Override
  public Widget asWidget() {
    return panel;
  }

  interface ProfileViewUiBinder extends UiBinder<HTMLPanel, ProfileView> {
  }

  private static ProfileViewUiBinder ourUiBinder = GWT.create(ProfileViewUiBinder.class);

  public ProfileView() {
    panel = ourUiBinder.createAndBindUi(this);
  }
}