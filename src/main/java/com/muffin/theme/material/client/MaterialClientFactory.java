package com.muffin.theme.material.client;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.muffin.theme.material.client.datetimepicker.DateTimePicker;
import com.muffin.theme.material.client.datetimepicker.DateTimePickerClientBundle;
import com.mvu.core.client.BaseRadioInputAppearance;
import com.mvu.core.client.BaseSelectInputAppearance;
import com.mvu.core.client.Core;
import com.mvu.core.client.CoreClientFactory;
import com.mvu.core.client.InputGroupAppearance;
import com.mvu.core.client.JsUtil;
import com.mvu.core.client.NavBarAppearance;
import com.mvu.core.client.RadioInputAppearance;
import com.mvu.core.client.SelectInputAppearance;
import com.mvu.core.client.SuggestInputAppearance;
import com.mvu.core.client.appearance.PanelAP;
import com.mvu.core.client.appearance.TableAP;
import com.mvu.core.client.builder.ButtonBuilder;
import com.mvu.core.client.form.ButtonAP;
import com.mvu.core.client.form.FormAppearance;
import com.mvu.core.client.form.FormType;
import com.mvu.core.client.input.CheckBox;
import com.mvu.core.client.resource.CoreJs;
import com.mvu.core.client.style.Position;
import com.mvu.core.client.style.Size;
import com.mvu.core.shared.SharedConstants;
import com.mvu.core.shared.datatype.DateType;
import com.mvu.core.shared.datatype.DecimalType;
import com.mvu.core.shared.input.InputAppearance;
import com.mvu.core.shared.input.InputConfig;
import com.mvu.core.shared.input.TextInputAppearance;

/**
 * Date: 2/5/13 3:57 PM
 */
public class MaterialClientFactory extends CoreClientFactory {

  @UiTemplate("CheckBox.ui.xml")
  interface CheckBoxUiBinder extends UiBinder<Element, CheckBox> {
  }

  @UiTemplate("Radio.ui.xml")
  interface RadioUiBinder extends UiBinder<Element, CheckBox> {
  }

  public void init() {
    bindCheckBoxAndRadio();

    prefixes = new HashMap<>();
    prefixes.put(DecimalType.money(), icon("usd"));
    prefixes.put(DecimalType.wholeDollar(), icon("usd"));
    prefixes.put(DateType.datetime(), icon("calendar"));

    postfixes = new HashMap<>();
    postfixes.put(DecimalType.percentage(), "%");
    postfixes.put(DateType.midnight(), icon("calendar"));

    builders.put(ButtonAP.class, new MButtonAPBuilder());
  }

  protected void bindCheckBoxAndRadio() {
    checkBoxUiBinder = GWT.create(CheckBoxUiBinder.class);
    radioBinder = GWT.create(RadioUiBinder.class);
  }

  @Override
  public NavBarAppearance createNavBar() {
    final RootPanel panel = Core.CF.getRootPanel(SharedConstants.UI_NAVIGATION);
    return panel == null ? null : new MNavBarAP(panel);
  }

  @Override
  public TextBox createTextBox() {
    TextBox textBox = super.createTextBox();
    textBox.setStyleName("form-control");
    return textBox;
  }

  @Override
  public TextBoxBase createDateBox(boolean forceNative, boolean datetime) {
    if (forceNative) {
      return super.createDateBox(forceNative, datetime);
    } else {
      JsUtil.inject(CoreJs.INSTANCE.modernizr());
      if (supportDatePicker()) {
        return super.createDateBox(forceNative, datetime);
      }
      JsUtil.inject(DateTimePickerClientBundle.INSTANCE.dateTimePicker());
      DateTimePickerClientBundle.INSTANCE.css().ensureInjected();
      DateTimePicker dateBox = new DateTimePicker(datetime);
      dateBox.addStyleName("form-control");
      dateBox.setAutoClose(true);
      return dateBox;
    }
  }

  private native boolean supportDatePicker() /*-{
    return $wnd.Modernizr.inputtypes.date && $wnd.Modernizr.touch;
  }-*/;

  @Override
  public FormAppearance formAppearance(FormType type, Size size, Position position) {
    switch (type) {
      case DEFAULT:
        return new StandardFormAppearance();
      case INLINE:
        return new InlineFormAppearance();
      case MODAL:
        return new ModalFormAppearance(size);
      case HORIZONTAL:
        return new HorizontalFormAppearance();
      case FULLPAGE:
        return new PageFormAppearance();
      default:
        throw new IllegalArgumentException("Unknown form type: " + type);
    }
  }

  @Override
  public TextInputAppearance textInputAppearance(String type) {
    return new MTextInputAppearance(type);
  }

  @Override
  public InputGroupAppearance inputGroupAppearance(String type, String pre, String post) {
    return new MInputGroupAppearance(type, pre, post);
  }

  @Override
  public RadioInputAppearance radioInputAppearance(String name) {
    return new BaseRadioInputAppearance(name, new MRadioInputAppearance(name));
  }

  @Override
  public SuggestInputAppearance suggestInputAppearance(SuggestOracle oracle) {
    return new MSuggestInputAppearance(oracle);
  }

  @UiTemplate("MTextAreaAppearance.ui.xml")
  interface MTextAreaAppearanceUiBinder extends UiBinder<HTMLPanel, MTextInputAppearance> {
  }

  private static MTextAreaAppearanceUiBinder textAreaBinder = GWT.create(MTextAreaAppearanceUiBinder.class);

  @Override
  public TextInputAppearance textAreaAppearance() {
    return new MTextInputAppearance("text", textAreaBinder);
  }

  public <V> InputAppearance createInputAppearance(InputConfig<V> config) {
    return new MaterialInputAppearance(config);
  }

  @Override
  public SelectInputAppearance selectInputAppearance() {
    return new BaseSelectInputAppearance(new MSelectInputAppearance());
  }

  @Override
  public String size(int value) {
    return "col-md-" + value;
  }

  @Override
  public ButtonBuilder buttonBuilder() {
    return new MaterialButtonBuilder();
  }

  @Override
  public void makeTooltip(Element label, String tooltip) {
    label.appendChild(Document.get().createTextNode(" "));
    final SpanElement spanElement = Document.get().createSpanElement();
    label.appendChild(spanElement);
    spanElement.setInnerHTML(icon("question-circle"));
    label.setAttribute("rel", "has-tooltip");
    label.setAttribute("data-title", tooltip);
    label.setAttribute("data-placement", "right");
    label.setAttribute("data-container", "body");
    activateTooltip();
  }

  @Override
  public native void activateTooltip()/*-{
    $wnd.$('body').tooltip({
      selector: '[rel="has-tooltip"]',
      html: true
    });
  }-*/;
}
