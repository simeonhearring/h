package h.khall.server.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.CompareToBuilder;

import h.khall.server.dao.Dao;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Report;
import h.model.shared.khall.Reports;
import h.model.shared.util.NumberUtil;
import h.model.shared.util.TimeUtil;

public class S_21Report extends AbstractReportDefault<PublisherRecordCard>
{
  @Override
  public Map<String, Object> getParameters(Map<String, String> inParameters)
  {
    Map<String, Object> ret = new HashMap<>();

    String key0 = "pCongregation";
    String value0 = inParameters.get(key0);
    ret.put(key0, Integer.valueOf(value0));

    String key1 = "pYear";
    String value1 = inParameters.get(key1);
    ret.put(key1, Integer.valueOf(value1));

    String key2 = "pEncrypt";
    String value2 = inParameters.get(key2);
    ret.put(key2, value2);

    String key3 = "pServiceGroup";
    String value3 = inParameters.get(key3);
    ret.put(key3, Integer.valueOf(value3));

    String key4 = "pPublisherId";
    String value4 = inParameters.get(key4);
    ret.put(key4, NumberUtil.toLong(value4));

    return ret;
  }

  @Override
  public Collection<PublisherRecordCard> getCollection(Map<String, Object> inParameters)
  {
    List<PublisherRecordCard> ret = new ArrayList<>();

    String e = (String) inParameters.get("pEncrypt");

    Integer c = (Integer) inParameters.get("pCongregation");
    Integer s = (Integer) inParameters.get("pServiceGroup");
    Long p = (Long) inParameters.get("pPublisherId");
    int y = (Integer) inParameters.get("pYear");
    y = y - 1; // start from last service year

    Dao dao = getDao();

    FieldServiceGroup serviceGroup = getFsg(c, s);

    Date d = TimeUtil.getFirstOfMonth(y, 9);

    List<Person> publishers = getPublishers(p, serviceGroup, e, dao, c);

    Comparator<Report> reportComparator = reportComparator();

    PublisherRecordCard.Total totals = new PublisherRecordCard.Total();

    boolean moreThanOne = publishers.size() > 1;

    int cy = TimeUtil.currentYear();
    int cm = TimeUtil.currentServiceMonth();

    Reports reports = dao.selectReports(c, 12 * 4);

    for (Person value : publishers)
    {
      PublisherRecordCard card = new PublisherRecordCard();
//      card.setPublisher(value);
//      card.setReports(reports.);
//      ServiceAnalysis sa = new ServiceAnalysis(value, dao.select(e, value, d));
//
//      int sy = y;
//      boolean noreports = sa.isEmpty();
//
//      if (noreports)
//      {
//        Date last = dao.selectMaxReportDate(value.getId());
//        if (last != null)
//        {
//          Date lastSy = YearMonthRange.getFirstOfServiceYear(last, -1);
//          sa.setReports(dao.select(e, value, lastSy));
//          sy = getYear(lastSy);
//        }
//      }
//
//      card.setReports(sa.getCard(sy, 2)); // adds missing
//
//      card.setStatus(sa.getStatus(cy - 1, cm, cy, cm));
//
//      Collections.sort(card.getReports(), reportComparator);
//
//      ret.add(card);
//
//      if (moreThanOne && !noreports)
//      {
//        totals.add(card.getReports());
//      }
    }

    if (moreThanOne)
    {
      totals.addMissing(y, 2); // adds missing

      Collections.sort(totals.getCongregation().getReports(), reportComparator);
      Collections.sort(totals.getRegPioneers().getReports(), reportComparator);
      Collections.sort(totals.getAuxPioneers().getReports(), reportComparator);
      Collections.sort(totals.getPublishers().getReports(), reportComparator);

      ret.add(totals.getCongregation());
      ret.add(totals.getRegPioneers());
      ret.add(totals.getAuxPioneers());
      ret.add(totals.getPublishers());
    }

    Collections.sort(ret, cardComparator());

    return ret;
  }

  @SuppressWarnings("deprecation")
  private int getYear(Date inDate)
  {
    return inDate.getYear() + 1900;
  }

  private FieldServiceGroup getFsg(Integer inCongId, Integer inFsgId)
  {
    FieldServiceGroup serviceGroup = new FieldServiceGroup();
    serviceGroup.setId(inFsgId);
    return serviceGroup;
  }

  private List<Person> getPublishers(Long inPubId, FieldServiceGroup inFsg, String inEncrypt,
      Dao inDao, int inCongId)
  {
    List<Person> ret;

    if (inPubId != null)
    {
      ret = new ArrayList<>();
      ret.add(inDao.selectPerson(inEncrypt, inPubId));
    }
    else
    {
      Persons persons = inDao.selectPersons(inEncrypt, inCongId);

      if (inFsg.isElderOrServant())
      {
        ret = persons.getElders();
      }
      else if (inFsg.isPioneers())
      {
        ret = persons.getRegular();
      }
      else if (inFsg.getId() > 0)
      {
        ret = persons.getFsg(inFsg.getId());
      }
      else
      {
        ret = persons.getPersons();
      }
    }

    return ret;
  }

  public PublisherRecordCard getCard(long inPubId, int inYear, int inServiceYearsToAdd,
      Reports inReports)
  {
    PublisherRecordCard ret = new PublisherRecordCard();

    Map<String, Report> list = inReports.gReportByMonth(inPubId);

    for (String value : inReports.getRange(inYear, 9, inYear + inServiceYearsToAdd, 8))
    {
      ret.add(inReports.find(value, list));
    }
    return ret;
  }

  public static Comparator<PublisherRecordCard> cardComparator()
  {
    Comparator<PublisherRecordCard> ret = new Comparator<PublisherRecordCard>()
    {
      @Override
      public int compare(PublisherRecordCard inArg0, PublisherRecordCard inArg1)
      {
        return new CompareToBuilder().append(inArg0.getSortOrder(), inArg1.getSortOrder())
            .append(inArg0.getName(), inArg1.getName()).toComparison();
      }
    };
    return ret;
  }

  public static Comparator<Report> reportComparator()
  {
    Comparator<Report> ret = new Comparator<Report>()
    {
      @Override
      public int compare(Report inArg0, Report inArg1)
      {
        return new CompareToBuilder().append(inArg1.getServiceYear(), inArg0.getServiceYear())
            .append(inArg0.gDate(), inArg1.gDate()).toComparison();
      }
    };
    return ret;
  }
}