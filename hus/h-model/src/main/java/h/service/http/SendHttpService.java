/*
 * Copyright (c) 2000, 2014, HU SOFTWARE. All rights reserved.
 * HU SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package h.service.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class SendHttpService implements HttpService
{
  private static final Logger LOGGER = Logger.getLogger(SendHttpService.class);

  @Override
  public String get(String inUrl, Map<String, String> inHeader)
  {
    String ret = null;

    LOGGER.info("get service url [" + inUrl + "] header [" + inHeader + "]");

    StringBuilder getmsg = new StringBuilder();
    try
    {
      HttpClient client = new HttpClient();
      GetMethod getmethod = new GetMethod(inUrl);
      for (Entry<String, String> value : inHeader.entrySet())
      {
        getmethod.addRequestHeader(value.getKey(), value.getValue());
      }
      client.executeMethod(getmethod);
      ret = getmethod.getResponseBodyAsString();

      if (LOGGER.isDebugEnabled())
      {
        getmsg.append(" got [").append(ret).append("]");
        LOGGER.debug(getmsg.toString());
      }
    }
    catch (Exception e)
    {
      LOGGER.error(getmsg.toString(), e);
    }
    return ret;
  }

  @Override
  public String[] post(String inUrl, Map<String, String> inHeader, String inXml)
  {
    String[] ret = null;

    LOGGER.info("post url [" + inUrl + "] header [" + inHeader + "] xml [" + inXml + "]");

    StringBuilder postmsg = new StringBuilder();
    try
    {
      StringEntity input = new StringEntity(inXml);
      input.setContentType("application/xml");

      HttpPost postRequest = new HttpPost(inUrl);
      for (Entry<String, String> value : inHeader.entrySet())
      {
        postRequest.addHeader(value.getKey(), value.getValue());
      }
      postRequest.setEntity(input);

      ret = execute(postRequest);

      if (LOGGER.isDebugEnabled())
      {
        postmsg.append(" got [").append(convert(ret)).append("]");
        LOGGER.debug(postmsg.toString());
      }
    }
    catch (Exception e)
    {
      LOGGER.error(postmsg.toString(), e);
    }
    return ret;
  }

  private String[] execute(HttpPost inPost) throws IOException, ClientProtocolException
  {
    String[] ret;

    DefaultHttpClient httpClient = new DefaultHttpClient();

    HttpResponse response = httpClient.execute(inPost);

    ret = buildServiceResponse(response);

    httpClient.getConnectionManager().shutdown();

    return ret;
  }

  private String[] buildServiceResponse(HttpResponse inResponse) throws IOException
  {
    String statusCode = String.valueOf(inResponse.getStatusLine().getStatusCode());

    String responseContent = getContentAsString(inResponse);

    String headerContent = getHeadersAsString(inResponse.getAllHeaders());

    return new String[]
    {
        statusCode, responseContent, headerContent
    };
  }

  private static String getContentAsString(HttpResponse inResponse) throws IOException
  {
    ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
    String output = null;

    BufferedReader br =
        new BufferedReader(new InputStreamReader((inResponse.getEntity().getContent())));
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputBuffer));

    while ((output = br.readLine()) != null)
    {
      writer.println(output);
    }

    writer.close();

    return outputBuffer.toString();
  }

  private static String convert(String... inStrings)
  {
    StringBuilder sb = new StringBuilder();
    for (String value : inStrings)
    {
      sb.append(value).append("\n");
    }
    return sb.toString();
  }

  private static String getHeadersAsString(Header[] inHeaders)
  {
    ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputBuffer));

    for (Header header : inHeaders)
    {
      writer.println("Header: " + header.getName() + "; " + header.getValue());
    }

    writer.close();

    return outputBuffer.toString();
  }

  @Override
  public String get(String inUrl)
  {
    StringBuilder sb = new StringBuilder();

    // inUrl = URLEncoder.encode(inUrl, "ISO-8859-1");

    BufferedReader reader = null;
    try
    {
      URL u = new URL(inUrl);
      reader = new BufferedReader(new InputStreamReader(u.openStream()));

      String line;

      while ((line = reader.readLine()) != null)
      {
        sb.append(line);
      }
    }
    catch (Exception e)
    {
      // do nothing
    }
    finally
    {
      if (reader != null)
      {
        try
        {
          reader.close();
        }
        catch (IOException e)
        {
        }
      }
    }

    return sb.toString();
  }
}
