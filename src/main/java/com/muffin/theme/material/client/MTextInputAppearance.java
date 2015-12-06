package com.muffin.theme.material.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.mvu.core.client.style.Size;
import com.mvu.core.shared.DataType;
import com.mvu.core.shared.input.TextInputAppearance;

/**
 * Created by minh on 9/27/15.
 */
public class MTextInputAppearance extends MInputAppearance implements TextInputAppearance {
  @Override
  public String getText() {
    return textBox.getText();
  }

  @Override
  public void setText(String text) {
    textBox.setText(text);
  }

  @Override
  public void setFocus(boolean value) {
    textBox.setFocus(value);
  }

  @Override
  public void addKeyDownHandler(final Command command) {
    textBox.addKeyDownHandler(new KeyDownHandler() {
      public void onKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          command.execute();
        }
      }
    });
  }

  @Override
  public void setMaxLength(int max) {
    ((TextBox) textBox).setMaxLength(max);
  }

  @Override
  public void setAttribute(String name, String value) {
    textBox.getElement().setAttribute(name, value);
  }

  @Override
  public void addValueChangeHandler(ValueChangeHandler<String> changeHandler) {
    textBox.addValueChangeHandler(changeHandler);
  }

  @Override
  public void mask(String pattern) {
  }

  @Override
  public void mask(DataType dataType) {
  }

  @Override
  public void unMask() {
  }

  @Override
  public void placeHolder(String text) {
    textBox.getElement().setAttribute("placeholder", text);
  }

  @Override
  public void setName(String name) {
    textBox.setName(name);
  }

  @Override
  public String getName() {
    return textBox.getName();
  }

  @Override
  public boolean isEnabled() {
    return textBox.isEnabled();
  }

  @Override
  public void setEnabled(boolean enabled) {
    textBox.setEnabled(enabled);
  }

  @Override
  public void setSize(Size size) {

  }

  interface MTextInputAppearanceUiBinder extends UiBinder<HTMLPanel, MTextInputAppearance> {
  }

  private static MTextInputAppearanceUiBinder ourUiBinder = GWT.create(MTextInputAppearanceUiBinder.class);
  @UiField
  public ValueBoxBase textBox;

  protected MTextInputAppearance() {
  }

  public MTextInputAppearance(String type) {
    this(type, ourUiBinder);
  }

  public MTextInputAppearance(String type, UiBinder<HTMLPanel, MTextInputAppearance> binder) {
    root = binder.createAndBindUi(this);
    initWidget(root);
    textBox.getElement().setAttribute("type", type);
  }
}