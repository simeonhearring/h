package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.CommentPresenter;

public class CommentView extends AbstractView<CommentPresenter> implements CommentPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, CommentView>
  {
  }

  public CommentView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new CommentPresenter(this);
  }
}