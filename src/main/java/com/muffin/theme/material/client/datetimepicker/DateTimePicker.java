package com.muffin.theme.material.client.datetimepicker;

/*
 * #%L
 * Gwtmaterial3
 * %%
 * Copyright (C) 2013 Gwtmaterial3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.mvu.core.client.ClientFormat;
import com.mvu.core.client.event.HideEvent;
import com.mvu.core.client.event.HideHandler;
import com.mvu.core.client.event.ShowEvent;
import com.mvu.core.client.event.ShowHandler;
import com.muffin.theme.material.client.datetimepicker.constants.DateTimePickerDayOfWeek;
import com.muffin.theme.material.client.datetimepicker.constants.DateTimePickerLanguage;
import com.muffin.theme.material.client.datetimepicker.constants.DateTimePickerPosition;
import com.muffin.theme.material.client.datetimepicker.constants.DateTimePickerView;
import com.muffin.theme.material.client.datetimepicker.constants.HasAutoClose;
import com.muffin.theme.material.client.datetimepicker.constants.HasDateTimePickerHandlers;
import com.muffin.theme.material.client.datetimepicker.constants.HasDaysOfWeekDisabled;
import com.muffin.theme.material.client.datetimepicker.constants.HasEndDate;
import com.muffin.theme.material.client.datetimepicker.constants.HasForceParse;
import com.muffin.theme.material.client.datetimepicker.constants.HasFormat;
import com.muffin.theme.material.client.datetimepicker.constants.HasHighlightToday;
import com.muffin.theme.material.client.datetimepicker.constants.HasKeyboardNavigation;
import com.muffin.theme.material.client.datetimepicker.constants.HasLanguage;
import com.muffin.theme.material.client.datetimepicker.constants.HasMaxView;
import com.muffin.theme.material.client.datetimepicker.constants.HasMinView;
import com.muffin.theme.material.client.datetimepicker.constants.HasMinuteStep;
import com.muffin.theme.material.client.datetimepicker.constants.HasPosition;
import com.muffin.theme.material.client.datetimepicker.constants.HasShowMeridian;
import com.muffin.theme.material.client.datetimepicker.constants.HasShowTodayButton;
import com.muffin.theme.material.client.datetimepicker.constants.HasStartDate;
import com.muffin.theme.material.client.datetimepicker.constants.HasStartView;
import com.muffin.theme.material.client.datetimepicker.constants.HasViewSelect;
import com.muffin.theme.material.client.datetimepicker.constants.HasWeekStart;
import com.muffin.theme.material.client.datetimepicker.events.ChangeDateEvent;
import com.muffin.theme.material.client.datetimepicker.events.ChangeDateHandler;
import com.muffin.theme.material.client.datetimepicker.events.ChangeMonthEvent;
import com.muffin.theme.material.client.datetimepicker.events.ChangeMonthHandler;
import com.muffin.theme.material.client.datetimepicker.events.ChangeYearEvent;
import com.muffin.theme.material.client.datetimepicker.events.ChangeYearHandler;
import com.muffin.theme.material.client.datetimepicker.events.OutOfRangeEvent;
import com.muffin.theme.material.client.datetimepicker.events.OutOfRangeHandler;
import com.mvu.core.shared.Format;
import com.mvu.core.shared.util.StringUtils;

/**
 * @author Joshua Godi
 */
public class DateTimePicker extends TextBoxBase implements HasEnabled,
        HasVisibility, HasAutoClose, HasDaysOfWeekDisabled, HasEndDate, HasForceParse,
        HasFormat, HasHighlightToday, HasKeyboardNavigation, HasMaxView, HasMinuteStep, HasMinView,
        HasShowMeridian, HasShowTodayButton, HasStartDate, HasStartView, HasViewSelect, HasWeekStart,
        HasDateTimePickerHandlers, HasLanguage, HasName, HasPosition {

  /**
   * DEFAULT values
   */
  private String format;
  private DateTimeFormat dateTimeFormat;
  private DateTimeFormat isoFormat;
  private DateTimePickerDayOfWeek weekStart = DateTimePickerDayOfWeek.SUNDAY;
  private DateTimePickerDayOfWeek[] daysOfWeekDisabled = {};
  private boolean autoClose = false;
  private DateTimePickerView startView = DateTimePickerView.MONTH;
  private DateTimePickerView minView = DateTimePickerView.HOUR;
  private DateTimePickerView maxView = DateTimePickerView.DECADE;
  private boolean showTodayButton = false;
  private boolean highlightToday = false;
  private boolean keyboardNavigation = true;
  private boolean forceParse = true;
  private int minuteStep = 5;
  private DateTimePickerView viewSelect = DateTimePickerView.HOUR;
  private boolean showMeridian = false;
  private Widget container = null;
  private DateTimePickerLanguage language = DateTimePickerLanguage.EN;
  private DateTimePickerPosition position = DateTimePickerPosition.BOTTOM_RIGHT;

  public @UiConstructor DateTimePicker(boolean datetime) {
    super(Document.get().createTextInputElement());
    if (datetime) {
      setShowMeridian(true);
      setFormat(ClientFormat.dateTimeFormat.getPattern());
      isoFormat = DateTimeFormat.getFormat(Format.DATE_TIME_ISO);
    } else {
      setMinView(DateTimePickerView.MONTH);
      setFormat(ClientFormat.dateFormat.getPattern());
      isoFormat = DateTimeFormat.getFormat(Format.DATE_ISO);
    }
  }

  /**
   * Return iso date just like the native date input
   */

  @Override
    public String getText() {
    String text = super.getText();
    return text.isEmpty() ? "" : isoFormat.format(dateTimeFormat.parse(text));
  }

  @Override
  public void setText(String text) {
    if (StringUtils.isEmpty(text)) {
      super.setText("");
    } else {
      super.setText(dateTimeFormat.format(isoFormat.parse(text)));
    }
  }

  public void setContainer(final Widget container) {
    this.container = container;
  }

  public Widget getContainer() {
    return container;
  }

  @Override
  public void setLanguage(final DateTimePickerLanguage language) {
    this.language = language;

    // Inject the JS for the language
    if (language.getJs() != null) {
      ScriptInjector.fromString(language.getJs().getText())
              .setWindow(ScriptInjector.TOP_WINDOW).inject();
    }
  }

  @Override
  public DateTimePickerLanguage getLanguage() {
    return language;
  }

  @Override
  public void setPosition(final DateTimePickerPosition position) {
    this.position = position;
  }

  @Override
  public DateTimePickerPosition getPosition() {
    return position;
  }

  /**
   * Call this whenever changing any settings: minView, startView, format, etc. If you are changing
   * format and date value, the updates must take in such order:
   * <p/>
   * 1. DateTimePicker.reload()
   * 2. DateTimePicker.setValue(newDate); // Date newDate.
   * <p/>
   * Otherwise date value is not updated.
   */
  public void reload() {
    configure();
  }

  public void show() {
    show(getElement());
  }

  public void hide() {
    hide(getElement());
  }

  @Override
  public void setAutoClose(final boolean autoClose) {
    this.autoClose = autoClose;
  }

  @Override
  public void onShow(final Event e) {
    // On show we put focus on the textbox
    setFocus(true);

    fireEvent(new ShowEvent(e));
  }

  @Override
  public HandlerRegistration addShowHandler(final ShowHandler showHandler) {
    return addHandler(showHandler, ShowEvent.getType());
  }

  @Override
  public void onHide(final Event e) {
    // On hide we remove focus from the textbox
    setFocus(false);

    fireEvent(new HideEvent(e));
  }

  @Override
  public HandlerRegistration addHideHandler(final HideHandler hideHandler) {
    return addHandler(hideHandler, HideEvent.getType());
  }

  @Override
  public void onChangeDate(final Event e) {
    fireEvent(new ChangeDateEvent(e));
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> handler) {
    return addChangeDateHandler(new ChangeDateHandler() {
      @Override
      public void onChangeDate(ChangeDateEvent evt) {
        handler.onValueChange(null);
      }
    });
  }


  @Override
  public HandlerRegistration addChangeDateHandler(ChangeDateHandler changeDateHandler) {
    return addHandler(changeDateHandler, ChangeDateEvent.getType());
  }

  @Override
  public void onChangeYear(final Event e) {
    fireEvent(new ChangeYearEvent(e));
  }

  @Override
  public HandlerRegistration addChangeYearHandler(final ChangeYearHandler changeYearHandler) {
    return addHandler(changeYearHandler, ChangeYearEvent.getType());
  }

  @Override
  public void onChangeMonth(final Event e) {
    fireEvent(new ChangeMonthEvent(e));
  }

  @Override
  public HandlerRegistration addChangeMonthHandler(final ChangeMonthHandler changeMonthHandler) {
    return addHandler(changeMonthHandler, ChangeMonthEvent.getType());
  }

  @Override
  public void onOutOfRange(final Event e) {
    fireEvent(new OutOfRangeEvent(e));
  }

  @Override
  public HandlerRegistration addOutOfRangeHandler(final OutOfRangeHandler outOfRangeHandler) {
    return addHandler(outOfRangeHandler, OutOfRangeEvent.getType());
  }

  @Override
  public void setDaysOfWeekDisabled(final DateTimePickerDayOfWeek... daysOfWeekDisabled) {
    setDaysOfWeekDisabled(getElement(), toDaysOfWeekDisabledString(daysOfWeekDisabled));
  }

  @Override
  public void setEndDate(final Date endDate) {
    // Has to be in the format YYYY-MM-DD
    setEndDate(isoFormat.format(endDate));
  }

  @Override
  public void setEndDate(final String endDate) {
    // Has to be in the format YYYY-MM-DD
    getElement().setAttribute("data-date-enddate", endDate); // if this is called before configure()
    setEndDate(getElement(), endDate);
  }

  @Override
  public void clearEndDate() {
    setStartDate(getElement(), null);
  }

  @Override
  public void setForceParse(final boolean forceParse) {
    this.forceParse = forceParse;
  }

  @Override
  public void setHighlightToday(final boolean highlightToday) {
    this.highlightToday = highlightToday;
  }

  @Override
  public void setHasKeyboardNavigation(final boolean hasKeyboardNavigation) {
    this.keyboardNavigation = hasKeyboardNavigation;
  }

  @Override
  public void setMaxView(final DateTimePickerView dateTimePickerView) {
    this.maxView = dateTimePickerView;
  }

  @Override
  public void setMinView(final DateTimePickerView dateTimePickerView) {
    this.minView = dateTimePickerView;

    // We keep the view select the same as the min view
    if (viewSelect != minView) {
      setViewSelect(dateTimePickerView);
    }
  }

  @Override
  public void setMinuteStep(final int minuteStep) {
    this.minuteStep = minuteStep;
  }

  @Override
  public void setShowMeridian(final boolean showMeridian) {
    this.showMeridian = showMeridian;
  }

  @Override
  public void setShowTodayButton(final boolean showTodayButton) {
    this.showTodayButton = showTodayButton;
  }

  @Override
  public void setStartDate(final Date startDate) {
    // Has to be in the format YYYY-MM-DD
    setStartDate(isoFormat.format(startDate));
  }

  @Override
  public void setStartDate(final String startDate) {
    // Has to be in the format YYYY-MM-DD
    getElement().setAttribute("data-date-startdate", startDate); // if this is called before configure()
    setStartDate(getElement(), startDate);
  }

  @Override
  public void clearStartDate() {
    setStartDate(getElement(), null);
  }

  @Override
  public void setStartView(final DateTimePickerView dateTimePickerView) {
    this.startView = dateTimePickerView;
  }

  @Override
  public void setViewSelect(final DateTimePickerView dateTimePickerView) {
    this.viewSelect = dateTimePickerView;

    // We keep the min view the same as the view select
    if (viewSelect != minView) {
      setMinView(dateTimePickerView);
    }
  }

  @Override
  public void setWeekStart(final DateTimePickerDayOfWeek weekStart) {
    this.weekStart = weekStart;
  }

  @Override
  public void setFormat(final String format) {
    this.dateTimeFormat = DateTimeFormat.getFormat(format);
    this.format = convertToJsFormat(format);

    // Get the old value
    final String oldValue = getValue();

    if (oldValue != null) {
      setValue(oldValue);
    }
  }

  private String convertToJsFormat(final String format) {
    // Check http://www.gwtproject.org/javadoc/latest/com/google/gwt/i18n/client/DateTimeFormat.html
    // for more information on syntax
    final Map<Character, Character> map = new HashMap<Character, Character>() {{
      put('h', 'H'); // 12/24 hours
      put('H', 'h'); // 12/24 hours
      put('M', 'm'); // months
      put('m', 'i'); // minutes
      put('a', 'P'); // meridian
    }};

    final StringBuilder fb = new StringBuilder(format);
    for (int i = 0; i < fb.length(); i++) {
      if (map.containsKey(fb.charAt(i))) {
        fb.setCharAt(i, map.get(fb.charAt(i)));
      }
    }

    return fb.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onLoad() {
    super.onLoad();
    configure();
  }

  @Override
  protected void onUnload() {
    super.onUnload();
    remove(getElement());
  }

  protected void configure() {
    // If the user hasn't specified the container, default to the widget's parent
    // This makes sure the modal scroll with the content correctly
    if (container == null) {
      configure(this, this.getParent());
    } else {
      configure(this, container);
    }
  }

  protected void configure(final Widget w, final Widget container) {
    w.getElement().setAttribute("data-date-format", format);

    // If configuring not for the first time, datetimepicker must be removed first.
    this.remove(w.getElement());

    configure(w.getElement(), container.getElement(), format, weekStart.getValue(), toDaysOfWeekDisabledString(daysOfWeekDisabled), autoClose,
            startView.getValue(), minView.getValue(), maxView.getValue(), showTodayButton, highlightToday,
            keyboardNavigation, forceParse, minuteStep, viewSelect.getValue(), showMeridian, language.getCode(), position.getPosition());
  }

  protected void execute(final String cmd) {
    execute(getElement(), cmd);
  }

  private native void execute(Element e, String cmd) /*-{
    $wnd.jQuery(e).datetimepicker(cmd);
  }-*/;

  private native void remove(Element e) /*-{
    $wnd.jQuery(e).datetimepicker('remove');
    $wnd.jQuery(e).off('show');
    $wnd.jQuery(e).off('hide');
    $wnd.jQuery(e).off('changeDate');
    $wnd.jQuery(e).off('changeYear');
    $wnd.jQuery(e).off('changeMonth');
    $wnd.jQuery(e).off('outOfRange');
  }-*/;

  private native void show(Element e) /*-{
    $wnd.jQuery(e).datetimepicker('show');
  }-*/;

  private native void hide(Element e) /*-{
    $wnd.jQuery(e).datetimepicker('hide');
  }-*/;

  private native void update(Element e) /*-{
    $wnd.jQuery(e).datetimepicker('update');
  }-*/;

  private native void setStartDate(Element e, String startDate) /*-{
    $wnd.jQuery(e).datetimepicker('setStartDate', startDate);
  }-*/;

  private native void setEndDate(Element e, String endDate) /*-{
    $wnd.jQuery(e).datetimepicker('setEndDate', endDate);
  }-*/;

  private native void setDaysOfWeekDisabled(Element e, String daysOfWeekDisabled) /*-{
    $wnd.jQuery(e).datetimepicker('setDaysOfWeekDisabled', daysOfWeekDisabled);
  }-*/;

  protected native void configure(Element e, Element p, String format, int weekStart, String daysOfWeekDisabled,
                                  boolean autoClose, int startView, int minView,
                                  int maxView, boolean todayBtn, boolean highlightToday, boolean keyboardNavigation,
                                  boolean forceParse, int minuteStep, int viewSelect, boolean showMeridian, String language,
                                  String position) /*-{
    var that = this;
    $wnd.jQuery(e).datetimepicker({
      format: format,
      language: language,
      weekStart: weekStart,
      daysOfWeekDisabled: daysOfWeekDisabled,
      autoclose: autoClose,
      startView: startView,
      minView: minView,
      maxView: maxView,
      todayBtn: todayBtn,
      todayHighlight: highlightToday,
      keyboardNavigation: keyboardNavigation,
      forceParse: forceParse,
      minuteStep: minuteStep,
      showMeridian: showMeridian,
      pickerPosition: position,
      container: p
    })
            .on('show', function (e) {
              that.@com.muffin.theme.material.client.datetimepicker.DateTimePicker::onShow(Lcom/google/gwt/user/client/Event;)(e);
            })
            .on("hide", function (e) {
              that.@com.muffin.theme.material.client.datetimepicker.DateTimePicker::onHide(Lcom/google/gwt/user/client/Event;)(e);
            })
            .on("changeDate", function (e) {
              that.@com.muffin.theme.material.client.datetimepicker.DateTimePicker::onChangeDate(Lcom/google/gwt/user/client/Event;)(e);
            })
            .on("changeYear", function (e) {
              that.@com.muffin.theme.material.client.datetimepicker.DateTimePicker::onChangeYear(Lcom/google/gwt/user/client/Event;)(e);
            })
            .on("changeMonth", function (e) {
              that.@com.muffin.theme.material.client.datetimepicker.DateTimePicker::onChangeMonth(Lcom/google/gwt/user/client/Event;)(e);
            })
            .on("outOfRange", function (e) {
              that.@com.muffin.theme.material.client.datetimepicker.DateTimePicker::onOutOfRange(Lcom/google/gwt/user/client/Event;)(e);
            });
  }-*/;

  protected String toDaysOfWeekDisabledString(final DateTimePickerDayOfWeek... dateTimePickerDayOfWeeks) {
    this.daysOfWeekDisabled = dateTimePickerDayOfWeeks;

    final StringBuilder builder = new StringBuilder();

    if (dateTimePickerDayOfWeeks != null) {
      int i = 0;
      for (final DateTimePickerDayOfWeek dayOfWeek : dateTimePickerDayOfWeeks) {
        builder.append(dayOfWeek.getValue());

        i++;
        if (i < dateTimePickerDayOfWeeks.length) {
          builder.append(",");
        }
      }
    }
    return builder.toString();
  }
}
