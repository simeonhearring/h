package h.style.g.client.model;

import org.gwtbootstrap3.client.ui.base.HasId;
import org.gwtbootstrap3.client.ui.base.HasInlineStyle;
import org.gwtbootstrap3.client.ui.base.HasPull;
import org.gwtbootstrap3.client.ui.base.HasResponsiveness;
import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface ListGroupItemDisplay extends IsWidget, HasText, HasType<ListGroupItemType>, HasId,
    HasResponsiveness, HasInlineStyle, HasPull, IndexedPanel.ForIsWidget
{
}