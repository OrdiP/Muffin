package com.muffin.shared;

/**
 * Date: 5/28/14 9:49 AM
 */
public enum MuffinSection {
  targets, login, search {
    @Override
    public String toString() {
      return "Find Your Job";
    }
  }, post_job, review_companies
}
