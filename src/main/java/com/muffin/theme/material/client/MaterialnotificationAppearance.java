package com.muffin.theme.material.client;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.mvu.core.client.Core;
import com.mvu.core.client.JsUtil;
import com.mvu.core.client.NotificationAppearance;
import com.mvu.core.shared.Action;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialToast;

/**
 * Created by Van on 11/15/15.
 */
public class MaterialnotificationAppearance implements NotificationAppearance {
  private static DivElement container;
  private static String messageStyle;
  static SpanElement message;
  private static Timer timer;

  public MaterialnotificationAppearance() {
    container = Document.get().createDivElement();
    container.appendChild(createDismissButton());
    message = Document.get().createSpanElement();
    container.appendChild(message);
    messageStyle = Core.coreCss.infoMessage() + " col-xs-10 col-md-8 col-md-offset-2 alert alert-dismissible ";
    container.getStyle().setBottom(0, Style.Unit.PX);
    container.getStyle().setBackgroundColor("black");
    container.getStyle().setFontSize(15, Style.Unit.PX);
    RootPanel.get().getElement().appendChild(container);
  }

  public void hide() {
    MaterialLoader.showLoading(false);
    container.getStyle().setDisplay(Style.Display.NONE);
  }

  @Override
  public void alert(String message) {
    MaterialToast.alert(message);
  }

  private Timer ensureTimer() {
    if (timer == null) {
      timer = new Timer() {
        @Override
        public void run() {
          hide();
        }
      };
    }
    return timer;
  }

  public Node createDismissButton() {
    ButtonElement button = Document.get().createPushButtonElement();
    button.addClassName("close");
    button.setInnerHTML("&times;");
    button.getStyle().setMarginRight(15, Style.Unit.PX);
    JsUtil.addClickHandler(button, new Action<String>() {
      @Override
      public void execute(String type) {
        hide();
      }
    });
    return button;
  }

  @Override
  public NotificationAppearance type(Enum style) {
    container.setClassName(messageStyle + "alert-" + style.toString());
    return this;
  }

  @Override
  public NotificationAppearance text(String text) {
    message.setInnerHTML(text);
    return this;
  }

  @Override
  public NotificationAppearance autoDismiss(boolean autoDismiss) {
    ensureTimer().schedule(3000);
    return this;
  }

  @Override
  public void show() {
    if (message.getInnerText().equals("Processing request...") || message.getInnerText().equals("Loading...")) {
      MaterialLoader.showLoading(true);
    } else {
      MaterialLoader.showLoading(false);
      MaterialToast.alert(message.getInnerText());
    }
  }
}
