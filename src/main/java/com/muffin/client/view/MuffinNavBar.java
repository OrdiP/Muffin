package com.muffin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.muffin.shared.MuffinSection;
import com.mvu.core.client.Navigation;
import com.mvu.core.client.PlaceController;
import com.mvu.core.shared.Place;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBar;

/**
 * Created by Van on 11/7/15.
 */
public class MuffinNavBar implements Navigation, IsWidget {

  @UiField
  MaterialLink postAJob;

  @UiField
  MaterialLink findYourJob;

  @UiField
  MaterialLink companyReview;

  @UiField
  MaterialLink login;

  @Override
  public void onUserChanged() {
  }

  @Override
  public Widget asWidget() {
    return navBar;
  }

  interface MuffinNavBarUiBinder extends UiBinder<MaterialNavBar, MuffinNavBar> {
  }

  private MaterialNavBar navBar;

  private static MuffinNavBarUiBinder ourUiBinder = GWT.create(MuffinNavBarUiBinder.class);

  public MuffinNavBar() {
    navBar = ourUiBinder.createAndBindUi(this);
    login.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        PlaceController.placeController().goTo(MuffinSection.login);
      }
    });
    findYourJob.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        PlaceController.placeController().goTo(MuffinSection.search);
      }
    });
    postAJob.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        PlaceController.placeController().goTo(MuffinSection.post_job);
      }
    });
    companyReview.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        PlaceController.placeController().goTo(MuffinSection.review_companies);
      }
    });
  }
}