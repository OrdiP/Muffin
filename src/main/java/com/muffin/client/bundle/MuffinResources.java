package com.muffin.client.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Interface description...
 *
 * @author mvu
 */
public interface MuffinResources extends ClientBundle {
  MuffinResources RESOURCES = GWT.create(MuffinResources.class);

  @Source("muffin.css")
  MuffinCss css();
}
