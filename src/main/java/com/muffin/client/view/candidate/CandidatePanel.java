package com.muffin.client.view.candidate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.shared.Place;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;

/**
 * Created by Van on 12/2/15.
 */
public class CandidatePanel implements BaseActivity, IsWidget {

  private final HTMLPanel mainPanel;

  @UiField
  MaterialContainer container;

  @UiField
  MaterialLink savedJobs, activities, profile;

  @Override
  public void start(AcceptsOneWidget panel, Place place) {
    panel.setWidget(mainPanel);
  }

  @Override
  public Widget asWidget() {
    return mainPanel;
  }

  interface CandidatePanelUiBinder extends UiBinder<HTMLPanel, CandidatePanel> {
  }

  private static CandidatePanelUiBinder ourUiBinder = GWT.create(CandidatePanelUiBinder.class);

  public CandidatePanel() {
    mainPanel = ourUiBinder.createAndBindUi(this);
    savedJobs.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        mainPanel.add(new ProfileView());
      }
    });

    profile.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        MaterialToast.alert("save Jobs");
      }
    });
    activities.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        MaterialToast.alert("save Jobs");
      }
    });

  }
}