package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.CommentBPresenter;

public class CommentBView extends AbstractView<CommentBPresenter>
  implements CommentBPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, CommentBView>
  {
  }

  public CommentBView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new CommentBPresenter(this);
  }
}