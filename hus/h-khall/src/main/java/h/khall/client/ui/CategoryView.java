package h.khall.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import h.khall.client.model.CategoryPresenter;
import h.khall.client.model.HasGLabelPresenter;
import h.model.shared.khall.Categories.Category;

public class CategoryView extends HasGLabelView<HasGLabelPresenter<Category>>
  implements CategoryPresenter.Display
{
  private static final Binder BINDER = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, CategoryView>
  {
  }

  public CategoryView()
  {
    initWidget(BINDER.createAndBindUi(this));
    mPresenter = new CategoryPresenter(this).handlers();
  }
}