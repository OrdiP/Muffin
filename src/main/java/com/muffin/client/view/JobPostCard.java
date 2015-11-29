package com.muffin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.muffin.client.view.login.JobDetails;
import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.Core;
import com.mvu.core.client.JsBean;
import com.mvu.core.client.PlaceController;
import com.mvu.core.client.ViewStack;
import com.mvu.core.client.widget.BasePanel;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialToast;

public class JobPostCard extends Composite {

  private static MaterialCardUiBinder uiBinder = GWT
          .create(MaterialCardUiBinder.class);
  private JobDetails jobDetails;

  interface MaterialCardUiBinder extends UiBinder<Widget, JobPostCard> {
  }

  @UiField
  Image companyLogo;

  @UiField
  Label jobDescription, jobTitle, companyName;

  @UiField
  MaterialButton apply;

  private Long id;

  public JobPostCard() {
    initWidget(uiBinder.createAndBindUi(this));
    jobTitle.addStyleName(Core.coreCss.fontSize150Percent());
    jobTitle.addStyleName(Core.coreCss.clickable());
    jobTitle.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        ensureJobDetails().push(id);
      }
    });
    apply.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        MaterialToast.alert("Burn!");
      }
    });
  }

  private JobDetails ensureJobDetails() {
    if (jobDetails == null) {
      jobDetails = new JobDetails();
    }
    return jobDetails;
  }

  public JobPostCard companyLogoUrl(String url){
    companyLogo.setUrl(url);
    return this;
  };

  public JobPostCard jobTitle(String title){
    jobTitle.setText(title);
    return this;
  };


  public JobPostCard jobDescription(String des){
    jobDescription.setText(des);
    return this;
  };


  public JobPostCard companyName(String name){
    companyName.setText(name);
    return this;
  };

  public JobPostCard jobId(Long id) {
    this.id = id;
    return this;
  }
}
