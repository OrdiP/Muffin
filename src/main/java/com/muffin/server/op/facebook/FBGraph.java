package com.muffin.server.op.facebook;

/**
 * Created by Van on 11/19/15.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.mvu.core.server.JSON;

public class FBGraph {
  private String accessToken;

  public FBGraph(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getFBGraph() {
    String graph = null;
    try {

      String g = "https://graph.facebook.com/me?" + accessToken;
      URL u = new URL(g);
      URLConnection c = u.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(
              c.getInputStream()));
      String inputLine;
      StringBuffer b = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        b.append(inputLine + "\n");
      }
      in.close();
      graph = b.toString();
      System.out.println(graph);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("ERROR in getting FB graph data. " + e);
    }
    return graph;
  }

  public Map getGraphData(String fbGraph) {
    Map fbProfile = new HashMap();
    JSON json = JSON.parse(fbGraph);
    fbProfile.put("id", json.getString("id"));
    fbProfile.put("first_name", json.getString("first_name"));
    if (json.getString("email") != null) {
      fbProfile.put("email", json.getString("email"));
    }
    if (json.getString("gender") != null) {
      fbProfile.put("gender", json.getString("gender"));
    }
    if (json.getString("last_name") != null) {
      fbProfile.put("last_name", json.getString("last_name"));
    }
    return fbProfile;
  }
}