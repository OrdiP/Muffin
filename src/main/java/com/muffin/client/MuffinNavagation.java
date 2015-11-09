package com.muffin.client;

import com.muffin.shared.MuffinSection;
import com.mvu.core.client.BaseNavigation;
import com.mvu.core.shared.Place;

/**
 * Created by Van on 11/8/15.
 */
public class MuffinNavagation extends BaseNavigation {

  private static final String logo = "iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAABp0lEQVRIS7VWwVHDMBDcrQCoAKgAqADoACogVABUAHRAKoAOCBWQVIDdQdIBVHDMek4eWVaQ7Qz38Xh8utXurU4m/jk4pL6ZHQE4BHAKYB/AEsCG5Lq0fiuAmanQDYB7AAJIY0XyYhKAmWnha6bwyhmcAFiTPB4NYGYzL661PwDeALwEOZyZpNkDsADw4CB3AGqSym+jI1FSXLud5XRO8lIStzFIC+CN/HIJivqa2ZWYefNjkG8AZ2FjDYAnP7pLNnqSVGIxXDIZQvmVA+r5THJBL/4eVbokKRuODjfHZ7TwWgBCkytqWXJq8VDUQSSdalYCMP84JynP7xxmJgC5CjEDvU+SJrOjcOJrAehQxbrtzKDTT3eREHXAGloA5u6KsWCybuinzlCVHrTQj0lOMjNJfO4WfWp6EG8xlzCGQmSYdoMpQOj+B0nRHRxmJpk1CRQH4aBum0WDJmXCPgxJ3RPteE8BsrsYQiPyfod978LJ6TgQoNfgXpPdsiFRM794JUbg4XbrODDHQPbSZJ0abYO3MdDolYNy93AJdJkOy0F/FaWqf33/BX9Pvjj42Ou9AAAAAElFTkSuQmCC";

  public MuffinNavagation() {
    super();
    appearance.staticTop();
    appearance.buildBrand().withLogoAndText(logo, "Muffin");
    appearance.addItem(MuffinSection.search);
    appearance.addItem(MuffinSection.post_job);
    appearance.addItem(MuffinSection.companies_review);
    appearance.addButton("Sign In", new Place(MuffinSection.login));
  }

  @Override
  public void onUserChanged() {
  }
}
