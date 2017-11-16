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

import java.util.Map;

public interface HttpService
{
  String get(String inUrl);

  String get(String inUrl, Map<String, String> inHeader);

  String[] post(String inUrl, Map<String, String> inHeader, String inXml);
}