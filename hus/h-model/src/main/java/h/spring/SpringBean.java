package h.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBean implements ApplicationContextAware
{
  private ApplicationContext mApplicationContext;

  @Override
  public final void setApplicationContext(ApplicationContext inApplicationContext)
      throws RuntimeException
  {
    mApplicationContext = inApplicationContext;
  }

  public ApplicationContext getApplicationContext()
  {
    return mApplicationContext;
  }

  public <T> T getBean(String inName, Class<T> inType)
  {
    return mApplicationContext.getBean(inName, inType);
  }

  public Object getBean(String inName)
  {
    return mApplicationContext.getBean(inName);
  }
}