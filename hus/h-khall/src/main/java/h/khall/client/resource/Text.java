package h.khall.client.resource;

public interface Text
{
  String monogram();

  String domain();

  String rptS140(int inCongId, int inYear, int inMonth);

  String rptS89(int inCongId, int inYear, int inMonth);

  String rptOclmWork(int inCongId, int inYear, int inMonth);
}