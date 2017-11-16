package h.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class ReverseLineInputStream extends InputStream
{
  private final RandomAccessFile mIn;
  private final long mStartFrom;
  private final long mFileLength;

  private long mCurrentPos = -1;
  private long mCurrentLineStart = -1;
  private long mCurrentLineEnd = -1;
  private long mLastPosInFile = -1;

  public ReverseLineInputStream(File inFile, long inStartFrom) throws FileNotFoundException
  {
    mIn = new RandomAccessFile(inFile, "r");
    mStartFrom = inStartFrom;
    mCurrentLineStart = inFile.length();
    mCurrentLineEnd = inFile.length();
    mLastPosInFile = inFile.length() - 1;
    mCurrentPos = mCurrentLineEnd;
    mFileLength = inFile.length();
    System.out.println("file length " + mFileLength);
  }

  public long getNextStartFrom()
  {
    return mFileLength;
  }

  @Override
  public int read() throws IOException
  {
    if (mCurrentPos < mCurrentLineEnd)
    {
      mIn.seek(mCurrentPos++);
      int readByte = mIn.readByte();
      System.out.print(String.format("%c", readByte));
      return readByte;
    }
    else if (mCurrentPos < mStartFrom)
    {
      return -1;
    }
    else
    {
      findPrevLine();
      return read();
    }
  }

  private void findPrevLine() throws IOException
  {
    mCurrentLineEnd = mCurrentLineStart;

    // There are no more lines, since we are at the beginning of the file and no
    // lines.
    if (mCurrentLineEnd == 0)
    {
      mCurrentLineEnd = -1;
      mCurrentLineStart = -1;
      mCurrentPos = -1;
      return;
    }

    long filePointer = mCurrentLineStart - 1;

    while (true)
    {
      filePointer--;

      // we are at start of file so this is the first line in the file.
      if (filePointer < 0)
      {
        break;
      }

      mIn.seek(filePointer);
      int readByte = mIn.readByte();
      // String s = String.format("%c", readByte);

      // We ignore last LF in file. search back to find the previous LF.
      if (readByte == 0xA && filePointer != mLastPosInFile)
      {
        break;
      }
    }

    // we want to start at pointer +1 so we are after the LF we found or at 0
    // the start of the file.
    mCurrentLineStart = filePointer + 1;
    mCurrentPos = mCurrentLineStart;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("ReverseLineInputStream [mCurrentPos=");
    builder.append(mCurrentPos);
    builder.append(", mCurrentLineStart=");
    builder.append(mCurrentLineStart);
    builder.append(", mCurrentLineEnd=");
    builder.append(mCurrentLineEnd);
    builder.append(", mLastPosInFile=");
    builder.append(mLastPosInFile);
    builder.append("]");
    return builder.toString();
  }
}