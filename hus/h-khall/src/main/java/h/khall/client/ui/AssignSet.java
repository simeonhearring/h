package h.khall.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.extras.typeahead.client.base.Dataset;
import org.gwtbootstrap3.extras.typeahead.client.base.Suggestion;
import org.gwtbootstrap3.extras.typeahead.client.base.SuggestionCallback;
import org.gwtbootstrap3.extras.typeahead.client.base.SuggestionTemplate;
import org.gwtbootstrap3.extras.typeahead.client.base.Template;

import h.khall.shared.command.AssignLookupCommand;
import h.khall.shared.model.Part;
import h.model.shared.Tag;
import h.style.g.client.ui.common.Global;
import h.style.g.client.ui.common.RpcCallback;

public class AssignSet extends Dataset<Tag>
{
  private Part mPart;

  public AssignSet()
  {
    setSuggestionTemplate(new SuggestionTemplate<Tag>()
    {
      @Override
      public String render(Suggestion<Tag> inSuggestion)
      {
        Tag tag = inSuggestion.getData();
        return "<strong>" + tag.getName() + "</strong>";
      }
    });

    setEmptyTemplate(new Template()
    {
      @Override
      public String render()
      {
        return "<div style=\"color:red;padding:6px 12px\"><strong>Empty, no matches!</strong></div>";
      }
    });
  }

  @Override
  public void findMatches(String inQuery, final SuggestionCallback<Tag> inCallback)
  {
    final String queryLower = inQuery.toLowerCase();
    Global.fireS(new AssignLookupCommand(queryLower, mPart), new RpcCallback<AssignLookupCommand>()
    {
      @Override
      public void onRpcSuccess(AssignLookupCommand inCommand)
      {
        List<Suggestion<Tag>> suggestions = new ArrayList<>();
        for (Tag value : inCommand.getData())
        {
          suggestions.add(Suggestion.create(value.getName(), value, AssignSet.this));
        }
        inCallback.execute(suggestions);
      }
    });
  }

  public void setPart(Part inPart)
  {
    mPart = inPart;
  }
}