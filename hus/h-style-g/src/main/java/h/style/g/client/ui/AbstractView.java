package h.style.g.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.Tooltip;
import org.gwtbootstrap3.client.ui.constants.IconSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.animate.client.ui.Animate;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;
import org.gwtbootstrap3.extras.bootbox.client.callback.PromptCallback;
import org.gwtbootstrap3.extras.bootbox.client.callback.SimpleCallback;
import org.gwtbootstrap3.extras.bootbox.client.options.AlertOptions;
import org.gwtbootstrap3.extras.bootbox.client.options.BootboxSize;
import org.gwtbootstrap3.extras.bootbox.client.options.ConfirmOptions;
import org.gwtbootstrap3.extras.bootbox.client.options.PromptOptions;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.HumanInputEvent;
import com.google.gwt.event.dom.client.KeyCodeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.UIObject;

import h.style.g.client.model.CallBack;
import h.style.g.client.model.ModalDisplay;
import h.style.g.client.ui.common.Global;

public abstract class AbstractView extends Composite
{
  public enum Effect
  {
    blind,
    bounce,
    clip,
    drop,
    explode,
    fade,
    fold,
    highlight,
    puff,
    pulsate,
    scale,
    shake,
    size,
    slide,
    transfer
  }

  protected String mElementId;

  protected PopupPanel mPop;

  protected List<HandlerRegistration> mRegistrations = new ArrayList<>();

  protected Tooltip mTooltip;

  protected UIObject mUiObject;

  protected String mAnimateKey;

  public void attach(HasWidgets inPanel)
  {
    inPanel.clear();
    inPanel.add(this);
  }

  public void setAnimateObject(UIObject inUiObject)
  {
    mUiObject = inUiObject;
  }

  public void animate(UIObject inUiObject, Animation inAnimation, int inCount, int inDuration,
      int inDelay)
  {
    if (inAnimation != null)
    {
      mAnimateKey = Animate.animate(inUiObject, inAnimation, inCount, inDuration, inDelay);
    }
    else
    {
      stopAnimate(inUiObject);
    }
  }

  public void animate(Animation inAnimation, int inCount, int inDuration, int inDelay)
  {
    animate(mUiObject, inAnimation, inCount, inDuration, inDelay);
  }

  public void stopAnimate(UIObject inUiObject)
  {
    Animate.stopAnimation(inUiObject, mAnimateKey);
  }

  public void stopAnimate()
  {
    stopAnimate(mUiObject);
  }

  @Override
  protected void onUnload()
  {
    super.onUnload();
    clearHandlers();
  }

  public IsWidget getTooltip()
  {
    return mTooltip;
  }

  public void setTip(String inTip)
  {
    boolean isnew = mTooltip == null;
    if (isnew)
    {
      mTooltip = new Tooltip(this);
    }

    mTooltip.setTitle(inTip);

    if (!isnew)
    {
      // mTooltip.reconfigure();
    }
  }

  public boolean isDev()
  {
    return !GWT.isScript();
  }

  public String environment()
  {
    return Global.getEnvironment();
  }

  public ModalDisplay getModal()
  {
    return new ModalView();
  }

  public void showPop(IsWidget inWidget, boolean inShow)
  {
    if (inShow)
    {
      mPop = new PopupPanel(true);
      mPop.add(inWidget);
      mPop.setAnimationEnabled(true);
      mPop.center();
    }
    else if (mPop != null)
    {
      mPop.hide();
    }
  }

  public CallBack<Void> pleaseWait()
  {
    FlowPanel panel = new FlowPanel();
    panel.getElement().getStyle().setTextAlign(TextAlign.CENTER);

    Label wait = new Label("Please wait");
    wait.getElement().getStyle().setFontSize(70.0, Unit.PX);
    panel.add(wait);

    Icon spinner = new Icon(IconType.SPINNER);
    spinner.setSpin(true);
    spinner.setSize(IconSize.TIMES5);
    panel.add(spinner);

    final PopupPanel popup = new PopupPanel(false, true);
    popup.add(panel);
    popup.setGlassEnabled(true);
    popup.center();

    CallBack<Void> ret = new CallBack<Void>()
    {
      @Override
      public void onCallBack(Void inResult)
      {
        popup.hide();
      }
    };
    return ret;
  }

  public void styleName(UIObject inUiObject, boolean inAdd, String... inStyle)
  {
    for (String style : inStyle)
    {
      if (inAdd)
      {
        inUiObject.addStyleName(style);
      }
      else
      {
        inUiObject.removeStyleName(style);
      }
    }
  }

  public void styleName(String inStyle, boolean inAdd, UIObject... inUiObject)
  {
    for (UIObject object : inUiObject)
    {
      if (inAdd)
      {
        object.addStyleName(inStyle);
      }
      else
      {
        object.removeStyleName(inStyle);
      }
    }
  }

  protected HandlerRegistration add(HandlerRegistration inRegistration)
  {
    mRegistrations.add(inRegistration);
    return inRegistration;
  }

  public void clearHandlers()
  {
    for (HandlerRegistration value : mRegistrations)
    {
      try
      {
        value.removeHandler();
      }
      catch (AssertionError e)
      {
        // do nothing
      }
    }
    mRegistrations.clear();
  }

  protected <T> HandlerRegistration addValueChangeHandler(HasValueChangeHandlers<T> inWidget,
      ValueChangeHandler<T> inHandler)
  {
    return add(inWidget.addValueChangeHandler(inHandler));
  }

  protected <T> HandlerRegistration setValueChangeHandler(HasValueChangeHandlers<T> inWidget,
      ValueChangeHandler<T> inHandler)
  {
    clearHandlers();
    return addValueChangeHandler(inWidget, inHandler);
  }

  protected HandlerRegistration addClickHandler(HasClickHandlers inWidget, ClickHandler inHandler)
  {
    return add(inWidget.addClickHandler(inHandler));
  }

  protected HandlerRegistration addDoubleClickHandler(HasDoubleClickHandlers inWidget,
      DoubleClickHandler inHandler)
  {
    return add(inWidget.addDoubleClickHandler(inHandler));
  }

  protected HandlerRegistration addBlurHandler(HasBlurHandlers inWidget, BlurHandler inHandler)
  {
    return add(inWidget.addBlurHandler(inHandler));
  }

  protected HandlerRegistration addFocusHandler(HasFocusHandlers inWidget, FocusHandler inHandler)
  {
    return add(inWidget.addFocusHandler(inHandler));
  }

  protected HandlerRegistration addMouseOutHandler(HasMouseOutHandlers inWidget,
      MouseOutHandler inHandler)
  {
    return add(inWidget.addMouseOutHandler(inHandler));
  }

  protected HandlerRegistration addMouseOverHandler(HasMouseOverHandlers inWidget,
      MouseOverHandler inHandler)
  {
    return add(inWidget.addMouseOverHandler(inHandler));
  }

  protected HandlerRegistration setClickHandler(HasClickHandlers inWidget, ClickHandler inHandler)
  {
    clearHandlers();
    return addClickHandler(inWidget, inHandler);
  }

  protected HandlerRegistration setDoubleClickHandler(HasDoubleClickHandlers inWidget,
      DoubleClickHandler inHandler)
  {
    clearHandlers();
    return addDoubleClickHandler(inWidget, inHandler);
  }

  public void scrollTo(int inLeft, int inTop)
  {
    Window.scrollTo(inLeft, inTop);
  }

  public boolean isControlKeyDown(KeyCodeEvent<?> inEvent)
  {
    return inEvent.isControlKeyDown();
  }

  public int getNativeKeyCode(KeyCodeEvent<?> inEvent)
  {
    return inEvent.getNativeKeyCode();
  }

  public boolean isAltKeyDown(KeyCodeEvent<?> inEvent)
  {
    return inEvent.isAltKeyDown();
  }

  public boolean isShiftKeyDown(HumanInputEvent<?> inEvent)
  {
    return inEvent.isShiftKeyDown();
  }

  public boolean isKeyDelete(KeyCodeEvent<?> inEvent)
  {
    return inEvent.getNativeKeyCode() == KeyCodes.KEY_DELETE;
  }

  public boolean isAltKeyDown(HumanInputEvent<?> inEvent)
  {
    return inEvent.isAltKeyDown();
  }

  public boolean isMetaKeyDown(HumanInputEvent<?> inEvent)
  {
    return inEvent.isMetaKeyDown();
  }

  public boolean isControlKeyDown(HumanInputEvent<?> inEvent)
  {
    return inEvent.isControlKeyDown();
  }

  public boolean isKeyEnter(KeyCodeEvent<?> inEvent)
  {
    return inEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER;
  }

  public boolean isKeyTab(KeyCodeEvent<?> inEvent)
  {
    return inEvent.getNativeKeyCode() == KeyCodes.KEY_TAB;
  }

  public boolean isShiftKeyDown(KeyCodeEvent<?> inEvent)
  {
    return inEvent.isShiftKeyDown();
  }

  public void notify(String inMessage)
  {
    Notify.notify(inMessage);
  }

  public boolean confirm(String inMessage)
  {
    return Window.confirm(inMessage);
  }

  public void alert(String inMessage)
  {
    AlertOptions options = AlertOptions.newOptions(inMessage);
    options.setTitle("Alert!");
    Bootbox.alert(options);
  }

  public void alert(String inMessage, SimpleCallback inCallback)
  {
    Bootbox.alert(inMessage, inCallback);
  }

  public void confirm(String inMessage, ConfirmCallback inCallback)
  {
    ConfirmOptions options = ConfirmOptions.newOptions(inMessage);
    options.setSize(BootboxSize.SMALL);
    options.setTitle("Confirm action!");
    options.setCloseButton(false);
    options.setCallback(inCallback);
    Bootbox.confirm(options);
  }

  public void prompt(String inMessage, PromptCallback inCallback)
  {
    PromptOptions options = PromptOptions.newOptions(inMessage);
    options.setSize(BootboxSize.SMALL);
    options.setTitle(inMessage);
    options.setCloseButton(false);
    options.setCallback(inCallback);
    Bootbox.prompt(options);
  }

  public String format(String inPattern, Date inDate)
  {
    return formatDate(inPattern, inDate);
  }

  public Date parse(String inPattern, String inDate)
  {
    return parseDate(inPattern, inDate);
  }

  public static String formatDate(String inPattern, Date inDate)
  {
    if (inDate == null)
    {
      return "";
    }
    return DateTimeFormat.getFormat(inPattern).format(inDate);
  }

  public static Date parseDate(String inPattern, String inDate)
  {
    if (inDate == null || "".equals(inDate))
    {
      return null;
    }
    return DateTimeFormat.getFormat(inPattern).parse(inDate);
  }

  public void open(String inUrl)
  {
    Window.open(inUrl, "_blank", null);
  }

  public String setElementId(Element inElement)
  {
    mElementId = generateId();
    inElement.setPropertyString("id", mElementId);
    return mElementId;
  }

  public String makeAnimate(UIObject inUiObject)
  {
    mElementId = generateId();
    inUiObject.getElement().setPropertyString("id", mElementId);
    setAnimateObject(inUiObject);
    return mElementId;
  }

  public static String getElementId(Element inElement)
  {
    String ret = generateId();
    inElement.setPropertyString("id", ret);
    return ret;
  }

  public static String generateId()
  {
    return String.valueOf(Random.nextInt(999999));
  }

  public void move(String inPosition)
  {
    move(mElementId, inPosition);
  }

  public void color(boolean inColor)
  {
    color(mElementId, inColor);
  }

  public void effect(Effect inEffect)
  {
    effect(mElementId, inEffect.name());
  }

  public static void showDeferred(final String inElementId, final boolean inShow)
  {
    Scheduler.get().scheduleDeferred(new ScheduledCommand()
    {
      @Override
      public void execute()
      {
        show(inElementId, inShow);
      }
    });
  }

  public static native void move(String inId, String inPosition)
  /*-{
  	$wnd.$("#" + inId).animate({
  		'margin-left' : inPosition
  	}, 1000);
  }-*/;

  public static native void show(String inId, boolean inShow)
  /*-{
  	if (inShow) {
  		$wnd.$("#" + inId).show("fold", 1000);
  	} else {
  		$wnd.$("#" + inId).hide("drop", {
  			direction : "down"
  		}, "slow");
  	}
  }-*/;

  public static native void color(String inId, boolean inColor)
  /*-{
  	if (inColor) {
  		$wnd.$("#" + inId).animate({
  			backgroundColor : "#eae6d8"
  		}, 1000);
  	} else {
  		$wnd.$("#" + inId).animate({
  			backgroundColor : "#fff"
  		}, 1000);
  	}
  }-*/;

  // public static native void setAnimatedScrollTop(MyView instance, String
  // topElementId,
  // String scrollPanelId, int offsetPosition)
  // /*-{
  // $wnd.Velocity($wnd.document.getElementById(topElementId), 'scroll',
  // {
  // duration: 1000, offset: offsetPosition, container:
  // $wnd.document.getElementById(scrollPanelId),
  // complete:
  // function(){instance.@com.project.client.views.MyView::setScrollAnimationInProgress(Z)(false);}
  // });
  // }-*/;

  public static native void effect(String inId, String inEffect)
  /*-{
  	$wnd.$("#" + inId).effect(inEffect);
  }-*/;

  public static native String getUserAgent()
  /*-{
  	return navigator.userAgent.toLowerCase();
  }-*/;

  public static boolean isChromeBrowser()
  {
    return getUserAgent().toLowerCase().contains("chrome");
  }

  public static boolean isFirefoxBrowser()
  {
    return getUserAgent().toLowerCase().contains("firefox");
  }

  public static boolean isIEBrowser()
  {
    return getUserAgent().toLowerCase().contains("msie");
  }

  public static native void consoleNative(String inMessage)
  /*-{
  	console.log(inMessage);
  }-*/;

  public void fire(double inDelaySeconds, final CallBack<?> inCallBack)
  {
    Scheduler.get().scheduleFixedDelay(new RepeatingCommand()
    {
      @Override
      public boolean execute()
      {
        inCallBack.onCallBack(null);
        return false;
      }
    }, (int) (inDelaySeconds * 1000));
  }

  public void jumpToBottom()
  {
    Scheduler.get().scheduleDeferred(new ScheduledCommand()
    {
      @Override
      public void execute()
      {
        Window.scrollTo(0, Window.getScrollTop() + Window.getClientHeight());
      }
    });
  }

  public ScriptElement scriptText(String inText)
  {
    ScriptElement sce = Document.get().createScriptElement();
    sce.setInnerHTML(inText);
    return sce;
  }

  public ScriptElement scriptSrc(String inSrc)
  {
    ScriptElement sce = Document.get().createScriptElement();
    sce.setType("text/javascript");
    sce.setSrc(inSrc);
    return sce;
  }

  public static native void icheck()
  /*-{
        $wnd.jQuery('.i-checks').iCheck({
            checkboxClass : 'icheckbox_square-green',
            radioClass : 'iradio_square-green',
        });
  }-*/;

  public static native void steps()
  /*-{
         $wnd.jQuery('#wizard').steps();
  }-*/;

  public static native void slimScroll()
  /*-{
        $wnd.jQuery('.sidebar-collapse').slimScroll({
            height: '100%',
            railOpacity: 0.9
        });
        $wnd.jQuery('body.canvas-menu .sidebar-collapse').slimScroll({
            height: '100%',
            railOpacity: 0.9
        });
        $wnd.jQuery('.sidebar-container').slimScroll({
            height: '100%',
            railOpacity: 0.4,
            wheelStep: 10
        });
        $wnd.jQuery('.small-chat-box .content').slimScroll({
            height: '234px',
            railOpacity: 0.4
        });
        $wnd.jQuery('.full-height-scroll').slimscroll({
            height: '100%'
        })
  }-*/;

  public static native void datepicker(String inElementId)
  /*-{
        $wnd.jQuery(inElementId).datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
  }-*/;

  public static native void datepicker()
  /*-{
      $wnd.jQuery('#data_1 .input-group.date').datepicker({
          todayBtn: "linked",
          keyboardNavigation: false,
          forceParse: false,
          calendarWeeks: true,
          autoclose: true
      });
  }-*/;

  public static native void metis()
  /*-{
        $wnd.jQuery('#side-menu').metisMenu().show();
  }-*/;

  public static native void toggleNavBar()
  /*-{
        $wnd.jQuery('body').toggleClass('mini-navbar');
        $wnd.SmoothlyMenu();
  }-*/;
}