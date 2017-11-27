package h.service.http;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface Service
{
  void execute(Map<String, String> inParams, HttpServletResponse inResponse);
}
