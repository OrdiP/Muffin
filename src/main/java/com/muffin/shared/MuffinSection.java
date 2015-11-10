package com.muffin.shared;

/**
 * Date: 5/28/14 9:49 AM
 */
public enum MuffinSection {
  targets, login, search {
    @Override
    public String toString() {
      return "Find Job";
    }
  }, post_job, sign_up, companies_review, jobs, companies
}
