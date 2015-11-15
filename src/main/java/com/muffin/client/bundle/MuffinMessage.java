package com.muffin.client.bundle;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public interface MuffinMessage extends Messages {
  MuffinMessage messages = GWT.create(MuffinMessage.class);

  String jobDescriptionHelpBlock();
}
