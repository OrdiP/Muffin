package com.muffin.client.view.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.muffin.shared.MuffinSection;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.Core;
import com.mvu.core.client.JsCallback;
import com.mvu.core.client.PlaceController;
import com.mvu.core.client.RemoteCall;
import com.mvu.core.client.ServerOps;
import com.mvu.core.client.action.LoginAction;
import com.mvu.core.client.form.Form;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Place;
import com.mvu.core.shared.datatype.BooleanType;
import com.mvu.core.shared.entity.Contact;
import com.mvu.core.shared.entity.Credential;
import com.mvu.core.shared.entity.User;
import com.mvu.core.shared.typekey.CoreSection;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTextBox;

import static com.mvu.core.client.PlaceController.placeController;

/**
 * Created by Van on 11/7/15.
 */
public class Login implements IsWidget, BaseActivity {
  private Panel rootElement;

  @UiField
  MaterialTextBox email;

  @UiField
  MaterialTextBox password;

  @UiField
  MaterialButton btnLogin;

  @UiField
  MaterialButton signupBtn;

  @Override
  public Widget asWidget() {
    return rootElement;
  }

  @Override
  public void start(AcceptsOneWidget panel, Place place) {
    panel.setWidget(rootElement);
  }

  interface LoginUiBinder extends UiBinder<Panel, Login> {
  }

  private static LoginUiBinder ourUiBinder = GWT.create(LoginUiBinder.class);

  public Login() {
    rootElement = ourUiBinder.createAndBindUi(this);
    btnLogin.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        login();
      }
    });
    signupBtn.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        signup();
      }
    });
  }

  private void login() {
    String emailAddress = email.getValue();
    Core.CF.setCookie(User.Email.name(), emailAddress);
    new RemoteCall(ServerOps.LoginOp).input(getValues()).execute(new JsCallback() {
      @Override
      public void processBean(HasFields results) {
        Core.changeUser(results);
        placeController().goTo(CoreSection.core.myAccount());
      }
    });
  }

  private void signup() {
    PlaceController.placeController().goTo(MuffinSection.sign_up);
  }

  public HasFields getValues() {
    HasFields params = Core.CF.createBean();
    params.set(Contact.Email, email.getValue());
    params.set(Credential.Password, password.getValue());
    return params;
  }
}