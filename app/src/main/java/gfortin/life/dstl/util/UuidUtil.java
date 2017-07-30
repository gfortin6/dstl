package gfortin.life.dstl.util;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;


/**
 *
 */
public final class UuidUtil {

  /** DOCUMENT ME! */
  protected static final int ENCODING_BASE = 32;

  /** DOCUMENT ME! */
  protected static final int DIGIT_IN_JVMID = 5;

  /** DOCUMENT ME! */
  protected static final long JVMID_MAX_VALUE =
    BigInteger.valueOf(ENCODING_BASE).pow(DIGIT_IN_JVMID).longValue() - 1;

  /** DOCUMENT ME! */
  protected static final int DIGIT_IN_RANDOM_NUMBER = 5;
  
  /** DOCUMENT ME! */
  protected static final int RANDOM_NUMBER_MAX_VALUE =
    BigInteger.valueOf(ENCODING_BASE).pow(DIGIT_IN_RANDOM_NUMBER).intValue();

  /** DOCUMENT ME! */
  protected static final int DIGIT_IN_TIMESTAMP = 13;

  /** DOCUMENT ME! */
  protected static final int DIGIT_IN_INCREMENTED_NUMBER = 3;

  /** DOCUMENT ME! */
  protected static final long INCREMENTED_NUMBER_MAX_VALUE =
    BigInteger.valueOf(ENCODING_BASE).pow(DIGIT_IN_INCREMENTED_NUMBER)
              .longValue();

  /** DOCUMENT ME! */
  protected static final int TOTAL_NUMBER_OF_DIGITS =
    DIGIT_IN_JVMID + DIGIT_IN_RANDOM_NUMBER + DIGIT_IN_TIMESTAMP
    + DIGIT_IN_INCREMENTED_NUMBER;

  /** DOCUMENT ME! */
  protected static final int NB_OF_RANDOM_IN_USE = 8;

  /** DOCUMENT ME! */
  protected static String jvmId = null;

  /** DOCUMENT ME! */
  protected static final long[] nextIncrementedNumber = { 0L };

  /** DOCUMENT ME! */
  protected static final Random[] randoms = new Random[NB_OF_RANDOM_IN_USE];

  /** DOCUMENT ME! */
  protected static int nextRandomIndex = 0;

  /** DOCUMENT ME! */
  protected static boolean validState = false;

  /** DOCUMENT ME! */
  protected static final int STEP = 3;

  /** DOCUMENT ME! */
  protected static final long START_LONG = 0L;

  /** DOCUMENT ME! */
  protected static final int START = 0;

  /** DOCUMENT ME! */
  protected static final char PADDING_CHAR = '0';

  static {
    for (int i = 0; i < NB_OF_RANDOM_IN_USE; i++) {
      // have a little more sophisticated seed than plain getTime() ....
      randoms[i] =
        new Random(
          (new Date().getTime() / (i + 2)) + new Object().hashCode() + i
        );
    }

    String jvmId = String.valueOf (
    		    ( (new Date().getTime()) / 1000 ) % JVMID_MAX_VALUE
    		);
    
    UuidUtil.jvmId = format(convertJvmId(jvmId), DIGIT_IN_JVMID);
    UuidUtil.validState = true;
  }

  /**
   * Prevents instantiation
   */
  private UuidUtil() {
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public static String nextUuid() {
    if (!UuidUtil.validState) {
      throw new IllegalStateException("Jvm id was not set successfully");
    }

    long uniqueIdToUse;
    Random randomToUse;

    synchronized (UuidUtil.nextIncrementedNumber) {
      if (UuidUtil.nextIncrementedNumber[0] == INCREMENTED_NUMBER_MAX_VALUE) {
        UuidUtil.nextIncrementedNumber[0] = START_LONG;
      }

      uniqueIdToUse = UuidUtil.nextIncrementedNumber[0]++;

      if (UuidUtil.nextRandomIndex == UuidUtil.NB_OF_RANDOM_IN_USE) {
        UuidUtil.nextRandomIndex = START;
      }

      randomToUse = UuidUtil.randoms[nextRandomIndex++];
    }

    StringBuffer input =
      new StringBuffer(
        format(
          randomToUse.nextInt(RANDOM_NUMBER_MAX_VALUE),
          DIGIT_IN_RANDOM_NUMBER
        ) + UuidUtil.jvmId
        + format(uniqueIdToUse, DIGIT_IN_INCREMENTED_NUMBER)
        + format(System.currentTimeMillis(), DIGIT_IN_TIMESTAMP)
      );
    
    // return input.toString().toUpperCase();
    
    int length = input.length();
    StringBuffer output = new StringBuffer(length);
    /*
    for (int j = 0; j < STEP; j++) {
      for (int i = j; i < length; i = i + STEP) {
    	char ch = input.charAt(i);
    	if ( Character.isLetter(ch)) {
    		ch = (char)((int)ch + 11) ;
    	}
        output.append(ch);
      }
    }  
    */
    for(int j=0; j<STEP; j++) {
      for(int i=j; i<length; i=i+STEP) {
          output.append(input.charAt(i));
      }
    }
    
    
    ///return output.toString().toUpperCase();
    
    return maskId(output.toString().toUpperCase());
  }

  /**
   * DOCUMENT ME!
   *
   * @param jvmIdStr DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  protected static long convertJvmId(String jvmIdStr) {
    long jvmId = Long.parseLong(jvmIdStr);

    if ((jvmId < 0) || (jvmId > JVMID_MAX_VALUE)) {
      throw new IllegalArgumentException();
    }

    return jvmId;
  }

  /**
   * DOCUMENT ME!
   *
   * @param number DOCUMENT ME!
   * @param digits DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  protected static String format(long number, int digits) {
    String value = BigInteger.valueOf(number).toString(ENCODING_BASE);
    int diff = digits - value.length();

    if (diff != 0) {
      if (diff > 0) {
    	  StringBuffer formatedResult = new StringBuffer(value);
        char[] padding = new char[diff];

        for (int i = 0; i < diff; i++) {
          padding[i] = PADDING_CHAR;
        }

        formatedResult.insert(0, padding);

        return formatedResult.toString();
      } else {
        UuidUtil.validState = false;
        throw new IllegalStateException();
      }
    } else {
      return value;
    }
  }
  
  /**
   * DOCUMENT ME!
   *
   * @param zxfghiurytereifr DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public static String unMaskId(String zxfghiurytereifr) {
	  StringBuffer zxfghiuryterefr = new StringBuffer("");
    StringTokenizer zxfghiuryiterefr =
      new StringTokenizer(zxfghiurytereifr, "abcdefghij!#$+", false);

    while (zxfghiuryiterefr.hasMoreTokens())
      zxfghiuryterefr.append(zxfghiuryiterefr.nextToken());

    return (zxfghiuryterefr.toString());
  }
  
  public static String maskId(String id) {
	  StringBuffer stBuf = new StringBuffer(id);
	    String maskChars =  "WXYZ";
	                                 

	    while (stBuf.length() < 32) {
	      stBuf.insert(
	        (int) (Math.random() * stBuf.length()),
	        maskChars.charAt((int) (Math.random() * maskChars.length()))
	      );
	    }

	    return stBuf.toString();
	  }
  
}
