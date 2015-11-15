package com.muffin.client;

import com.google.gwt.core.client.GWT;
import com.muffin.client.view.RedirectsView;
import com.muffin.client.view.SearchJobs;
import com.muffin.client.view.Signup;
import com.muffin.client.view.activities.PostJobActivity;
import com.muffin.client.view.admin.CompaniesView;
import com.muffin.client.view.admin.JobsView;
import com.muffin.client.view.login.Login;
import com.muffin.shared.MuffinSection;
import com.mvu.core.client.BaseAsyncCallback;
import com.mvu.core.client.CoreActivityMapper;
import com.mvu.core.client.NeedPermissionAction;
import com.mvu.core.shared.Action;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Place;

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
    add(MuffinSection.jobs, new Action<Place>() {
      @Override
      public void execute(final Place place) {
        GWT.runAsync(new BaseAsyncCallback() {
          public void onCodeDownloaded() {
            placeController().reallyGoTo(place, new JobsView());
          }
        });
      }
    });
    add(MuffinSection.companies, new Action<Place>() {
      @Override
      public void execute(final Place place) {
        GWT.runAsync(new BaseAsyncCallback() {
          public void onCodeDownloaded() {
            placeController().reallyGoTo(place, new CompaniesView());
          }
        });
      }
    });
    add(MuffinSection.post_job, new Action<Place>() {
      @Override
      public void execute(final Place item) {
        GWT.runAsync(new BaseAsyncCallback() {
          @Override
          protected void onCodeDownloaded() {
            placeController().reallyGoTo(item, new PostJobActivity());
          }
        });
      }
    });
  }

  @Override
  public Place getDefaultPlace(HasFields user) {
    return new Place(MuffinSection.search);
  }
}
