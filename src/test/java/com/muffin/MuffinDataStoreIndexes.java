package com.muffin;

import com.mvu.appengine.tools.DataStoreIndexes;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class MuffinDataStoreIndexes extends DataStoreIndexes {
  public static void main(String[] args) throws Exception {
    new MuffinDataStoreIndexes().write();
  }

  @Override
  protected void initIndexes() throws Exception {
    super.initIndexes();
  }
}
