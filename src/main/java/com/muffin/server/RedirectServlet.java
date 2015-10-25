package com.muffin.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.mvu.appengine.Bundle;
import com.mvu.core.server.Server;
import com.muffin.shared.entity.Redirection;
import org.apache.commons.io.IOUtils;

/**
 * Created by m.vu on 9/2/15.
 */
public class RedirectServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Map<String, String> headers = new HashMap<>();
    Enumeration headerNames = request.getHeaderNames();
    while(headerNames.hasMoreElements()){
      String name = (String) headerNames.nextElement();
      headers.put(name, request.getHeader(name));
    }
    Server.httpService().send(getUrl(request), "POST", IOUtils.toString(request.getInputStream()), headers);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String url = getUrl(request);
    response.sendRedirect(url);
  }

  private String getUrl(HttpServletRequest request) {
    String name = request.getPathInfo().substring(1);
    return new Bundle().get(Redirection.TYPE, name).get(Redirection.target);
  }
}
