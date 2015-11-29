package com.muffin.client.view.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.JsCallback;
import com.mvu.core.client.RemoteCall;
import com.mvu.core.client.ServerOps;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Place;
import com.mvu.core.shared.entity.Keyable;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

import static com.mvu.core.client.Core.VIEW_STACK;

/**
 * Created by Van on 11/29/15.
 */
public class JobDetails implements IsWidget, BaseActivity {

  private MaterialPanel panel;

  @UiField MaterialPanel locationLink;

  @UiField
  MaterialLink jobTitle, companyName;

  @UiField
  MaterialLabel companyInfo;

  @UiField
  HTML jobDescription, benefits;

  @Override
  public Widget asWidget() {
    return panel;
  }

  @Override
  public void start(AcceptsOneWidget panel, Place place) {
    panel.setWidget(this.panel);
  }

  interface JobDetailsUiBinder extends UiBinder<MaterialPanel, JobDetails> {
  }

  private static JobDetailsUiBinder ourUiBinder = GWT.create(JobDetailsUiBinder.class);

  public JobDetails() {
     panel = ourUiBinder.createAndBindUi(this);
    final IFrameElement locationFrame = Document.get().createIFrameElement();
    locationFrame.setSrc("https://maps.google.com/maps?hl=en&q=Pariser Platz, 10117 Berlin&ie=UTF8&t=roadmap&z=6&iwloc=B&output=embed");
    locationLink.getElement().appendChild(locationFrame);
  }

  public void push(Long id) {
    new RemoteCall(ServerOps.GetOp)
            .set(Keyable.kind, JobPost.TYPE.entityName())
            .set(Keyable.id, id).execute(new JsCallback() {
      @Override
      protected void processBean(HasFields result) {
        jobDescription.setHTML("• Gay <br>" +
                "• At least 3+ years of experience in Java J2EE; <br>" +
                "• Expert in Java J2EE, Spring, Hibernate; <br>" +
                "• Database: MySQL, MSSQL, Postgres, RabbitMQ, Spark; <br>" +
                "• Basic knowledge of CSS, JavaScript; <br>" +
                "• Frameworks: Spring MVC, AngularJS, jQuery, CAS (plus points); <br>" +
                "• Strong focus on TDD & BDD; <br>" +
                "• Business facing skills; <br>" +
                "• Pragmatic attitude; <br>" +
                "• Ability to learn and adapt new technology quickly; <br>" +
                "• Self-management and teamwork-minded.");
        benefits.setHTML("• Gay <br>" +
                "• At least 3+ years of experience in Java J2EE; <br>" +
                "• Expert in Java J2EE, Spring, Hibernate; <br>" +
                "• Database: MySQL, MSSQL, Postgres, RabbitMQ, Spark; <br>" +
                "• Basic knowledge of CSS, JavaScript; <br>" +
                "• Frameworks: Spring MVC, AngularJS, jQuery, CAS (plus points); <br>" +
                "• Strong focus on TDD & BDD; <br>" +
                "• Business facing skills; <br>" +
                "• Pragmatic attitude; <br>" +
                "• Ability to learn and adapt new technology quickly; <br>" +
                "• Self-management and teamwork-minded.");
        jobTitle.setText(result.get(JobPost.title));
        String name = "Job Details";
        VIEW_STACK.push(JobDetails.this, name);
      }
    });
  }

}