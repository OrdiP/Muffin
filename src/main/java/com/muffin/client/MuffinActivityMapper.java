package com.muffin.client;

import com.google.gwt.core.client.GWT;
import com.muffin.client.view.SearchJobs;
import com.muffin.client.view.Signup;
import com.muffin.client.view.login.Login;
import com.mvu.core.client.BaseAsyncCallback;
import com.mvu.core.client.CoreActivityMapper;
import com.mvu.core.client.JsBean;
import com.mvu.core.client.NeedPermissionAction;
import com.mvu.core.shared.Action;
import com.mvu.core.shared.Place;
import com.mvu.core.shared.typekey.CoreSection;
import com.muffin.client.view.RedirectsView;
import com.muffin.shared.MuffinSection;

import static com.mvu.core.client.Permission.SYSTEM;
import static com.mvu.core.client.PlaceController.placeController;

/**
 * Date: 5/28/14 9:48 AM
 */
public class MuffinActivityMapper extends CoreActivityMapper {
  @Override
  protected void init() {
    super.init();
    add(MuffinSection.targets, new NeedPermissionAction(SYSTEM) {
      @Override
      public void execute(final Place place) {
        GWT.runAsync(new BaseAsyncCallback() {
          public void onCodeDownloaded() {
            placeController().reallyGoTo(place, new RedirectsView());
          }
        });
      }
    });
    add(MuffinSection.login, new Action<Place>() {
      @Override
      public void execute(final Place place) {
        GWT.runAsync(new BaseAsyncCallback() {
          public void onCodeDownloaded() {
            placeController().reallyGoTo(place, new Login());
          }
        });
      }
    });
    add(MuffinSection.search, new Action<Place>() {
      @Override
      public void execute(final Place place) {
        GWT.runAsync(new BaseAsyncCallback() {
          public void onCodeDownloaded() {
            placeController().reallyGoTo(place, new SearchJobs());
          }
        });
      }
    });
    add(MuffinSection.sign_up, new Action<Place>() {
      @Override
      public void execute(final Place place) {
        GWT.runAsync(new BaseAsyncCallback() {
          public void onCodeDownloaded() {
            placeController().reallyGoTo(place, new Signup());
          }
        });
      }
    });


  }

/*  @Override
  public Place getDefaultPlace(JsBean user) {
    return new Place(CoreSection.core.demoForm());
  }*/
}
