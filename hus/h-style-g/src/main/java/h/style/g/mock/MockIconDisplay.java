package h.style.g.mock;

import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.Event;

import h.style.g.client.model.IconDisplay;

public class MockIconDisplay implements IconDisplay
{
  private final StringBuilder mStyle = new StringBuilder();
  private boolean mEnabled;
  private ClickHandler mHandler;
  private DoubleClickHandler mDblHandler;
  private boolean mSource;
  private boolean mVisible;
  private String mPosition;
  private String mText;
  private IconType mIconType;
  private String mTip;
  private boolean mSpin;
  private boolean mAnimate;
  private Animation mAnimation;
  private Integer mAnimationCount;
  private Integer mAnimationDuration;
  private Integer mAnimationDelay;

  public void setSource(boolean inSource)
  {
    mSource = inSource;
  }

  public IconType getIconType()
  {
    return mIconType;
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler inHandler)
  {
    mHandler = inHandler;
    return new HandlerRegistration()
    {
      @Override
      public void removeHandler()
      {
        mHandler = null;
      }
    };
  }

  @Override
  public HandlerRegistration addDoubleClickHandler(DoubleClickHandler inHandler)
  {
    mDblHandler = inHandler;
    return new HandlerRegistration()
    {
      @Override
      public void removeHandler()
      {
        mDblHandler = null;
      }
    };
  }

  @Override
  public HandlerRegistration setDoubleClickHandler(DoubleClickHandler inHandler)
  {
    return addDoubleClickHandler(inHandler);
  }

  @Override
  public Widget asWidget()
  {
    return null;
  }

  @Override
  public void setEnabled(boolean inEnable)
  {
    mEnabled = inEnable;
  }

  @Override
  public boolean isEnabled()
  {
    return mEnabled;
  }

  @Override
  public boolean isSource(Event<?> inEvent)
  {
    boolean ret = mSource;
    mSource = false;
    return ret;
  }

  public void click()
  {
    mSource = true;
    mHandler.onClick(null);
    mSource = false;
  }

  public void dblClick()
  {
    mSource = true;
    mDblHandler.onDoubleClick(null);
    mSource = false;
  }

  @Override
  public HandlerRegistration setClickHandler(ClickHandler inHandler)
  {
    return addClickHandler(inHandler);
  }

  @Override
  public void setShow(boolean inShow)
  {
    mVisible = inShow;
  }

  @Override
  public boolean isShowing()
  {
    return mVisible;
  }

  @Override
  public void setVisible(boolean inVisible)
  {
    mVisible = inVisible;
  }

  public boolean isVisible()
  {
    return mVisible;
  }

  @Override
  public void setText(String inText)
  {
    mText = inText;
  }

  @Override
  public String getText()
  {
    return mText;
  }

  @Override
  public void move(String inPosition)
  {
    mPosition = inPosition;
  }

  public String getPosition()
  {
    return mPosition;
  }

  @Override
  public void setIcon(IconType inType)
  {
    mIconType = inType;
  }

  @Override
  public void clearHandlers()
  {
  }

  public void clearStyle()
  {
    mStyle.delete(0, mStyle.length());
  }

  @Override
  public void addStyleName(String inStyleName)
  {
    mStyle.append(inStyleName).append(",");
  }

  @Override
  public void setTip(String inTip)
  {
    mTip = inTip;
  }

  public String getTip()
  {
    return mTip;
  }

  @Override
  public void setIconTip(IconType inType, String inTip)
  {
    setIcon(inType);
    setTip(inTip);
  }

  @Override
  public void animate(Animation inAnimation, int inCount, int inDuration, int inDelay)
  {
    mAnimate = true;
    mAnimation = inAnimation;
    mAnimationCount = inCount;
    mAnimationDuration = inDuration;
    mAnimationDelay = inDelay;
  }

  @Override
  public void stopAnimate()
  {
    mAnimate = false;
    mAnimationCount = null;
    mAnimationDuration = null;
    mAnimationDelay = null;
  }

  public boolean isAnimate()
  {
    return mAnimate;
  }

  public Animation getAnimation()
  {
    return mAnimation;
  }

  public Integer getAnimationCount()
  {
    return mAnimationCount;
  }

  public Integer getAnimationDelay()
  {
    return mAnimationDelay;
  }

  public Integer getAnimationDuration()
  {
    return mAnimationDuration;
  }

  @Override
  public void setIconSpin(boolean inSpin)
  {
    mSpin = inSpin;
  }

  public boolean isSpin()
  {
    return mSpin;
  }

  @Override
  public IconType getIcon()
  {
    return mIconType;
  }

  @Override
  public void setVisibility(boolean inShow)
  {
  }
}