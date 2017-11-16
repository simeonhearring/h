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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public abstract class BasicAuthService
{
  protected String mAuthorization;

  public Map<String, String> header()
  {
    Map<String, String> ret = new HashMap<>();
    ret.put("Accept", "application/xml");
    ret.put("Authorization", mAuthorization);
    return ret;
  }

  public void setBasicAuthorization(String inAuthorization)
  {
    String authorization = new String(Base64.encodeBase64(inAuthorization.getBytes()));
    mAuthorization = "Basic " + authorization;
  }
}