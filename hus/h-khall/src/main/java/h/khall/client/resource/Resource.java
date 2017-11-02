package h.khall.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resource extends ClientBundle
{
  @CssResource.NotStrict
  @Source("Dcss.css")
  Dcss css();
}