package com.muffin.client.view.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.muffin.client.Muffin;
import com.muffin.shared.MuffinSection;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.Core;
import com.mvu.core.client.JsCallback;
import com.mvu.core.client.PlaceController;
import com.mvu.core.client.RemoteCall;
import com.mvu.core.client.ServerOps;
import com.mvu.core.shared.Format;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Place;
import com.mvu.core.shared.entity.Contact;
import com.mvu.core.shared.entity.Credential;
import com.mvu.core.shared.entity.User;
import com.mvu.core.shared.typekey.CoreSection;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialImage;
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

  @UiField
  MaterialImage loginWithGoogle;

  @UiField
  MaterialImage loginWithFacebook;

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
    loginWithGoogle.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        loginWithGoogle();
      }
    });
    loginWithFacebook.addStyleName(Core.coreCss.clickable());
    loginWithGoogle.addStyleName(Core.coreCss.clickable());
    loginWithFacebook.addStyleName(Core.coreCss.margin10px());
    loginWithFacebook.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        loginWithFacebook();
      }
    });
  }

  private void loginWithFacebook() {
    String url = "http://www.facebook.com/dialog/oauth?" + "client_id="
            + "426163860915578" + "&redirect_uri="
            + "https://muffin-master.appspot.com/execute/facebook.FacebookLogin"
            + "&scope=email";
    Window.Location.assign(URL.encode(url));
  }

  private void loginWithGoogle() {
    Window.Location.assign(URL.encode("https://accounts.google.com/o/oauth2/auth?scope=email" +
            "&redirect_uri=https://muffin-master.appspot.com/execute/Oauth2callback&response_type=code&client_id=570682536819-gvfntc6o18jujnggup1tclmtrn0hq6i3.apps.googleusercontent.com" +
            "&approval_prompt=force"));
  }

  private void login() {
    String emailAddress = email.getValue();
    Core.CF.setCookie(User.email.name(), emailAddress);
    new RemoteCall(ServerOps.LoginOp).input(getValues()).execute(new JsCallback() {
      @Override
      public void processBean(HasFields results) {
        Core.changeUser(results);
        placeController().goTo(MuffinSection.dash_board);
      }
    });
  }

  private void signup() {
    PlaceController.placeController().goTo(MuffinSection.dash_board);
  }

  public HasFields getValues() {
    HasFields params = Format.format.bean();
    params.set(Contact.email, email.getValue());
    params.set(Credential.password, password.getValue());
    return params;
  }
}