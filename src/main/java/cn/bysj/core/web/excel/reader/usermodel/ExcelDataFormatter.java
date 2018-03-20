package cn.bysj.core.web.excel.reader.usermodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.FractionFormat;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LocaleUtil;
/**
 * DataFormatter contains methods for formatting the value stored in an
 * Cell. This can be useful for reports and GUI presentations when you
 * need to display data exactly as it appears in Excel. Supported formats
 * include currency, SSN, percentages, decimals, dates, phone numbers, zip
 * codes, etc.
 * <p>
 * Internally, formats will be implemented using subclasses of {@link Format}
 * such as {@link DecimalFormat} and {@link java.text.SimpleDateFormat}. Therefore the
 * formats used by this class must obey the same pattern rules as these Format
 * subclasses. This means that only legal number pattern characters ("0", "#",
 * ".", "," etc.) may appear in number formats. Other characters can be
 * inserted <em>before</em> or <em> after</em> the number pattern to form a
 * prefix or suffix.
 * </p>
 * <p>
 * For example the Excel pattern <code>"$#,##0.00 "USD"_);($#,##0.00 "USD")"
 * </code> will be correctly formatted as "$1,000.00 USD" or "($1,000.00 USD)".
 * However the pattern <code>"00-00-00"</code> is incorrectly formatted by
 * DecimalFormat as "000000--". For Excel formats that are not compatible with
 * DecimalFormat, you can provide your own custom {@link Format} implementation
 * via <code>DataFormatter.addFormat(String,Format)</code>. The following
 * custom formats are already provided by this class:
 * </p>
 * <pre>
 * <ul><li>SSN "000-00-0000"</li>
 *     <li>Phone Number "(###) ###-####"</li>
 *     <li>Zip plus 4 "00000-0000"</li>
 * </ul>
 * </pre>
 * <p>
 * If the Excel format pattern cannot be parsed successfully, then a default
 * format will be used. The default number format will mimic the Excel General
 * format: "#" for whole numbers and "#.##########" for decimal numbers. You
 * can override the default format pattern with <code>
 * DataFormatter.setDefaultNumberFormat(Format)</code>. <b>Note:</b> the
 * default format will only be used when a Format cannot be created from the
 * cell's data format string.
 *
 * <p>
 * Note that by default formatted numeric values are trimmed.
 * Excel formats can contain spacers and padding and the default behavior is to strip them off.
 * </p>
 * <p>Example:</p>
 * <p>
 * Consider a numeric cell with a value <code>12.343</code> and format <code>"##.##_ "</code>.
 *  The trailing underscore and space ("_ ") in the format adds a space to the end and Excel formats this cell as <code>"12.34 "</code>,
 *  but <code>DataFormatter</code> trims the formatted value and returns <code>"12.34"</code>.
 * </p>
 * You can enable spaces by passing the <code>emulateCsv=true</code> flag in the <code>DateFormatter</code> cosntructor.
 * If set to true, then the output tries to conform to what you get when you take an xls or xlsx in Excel and Save As CSV file:
 * <ul>
 *  <li>returned values are not trimmed</li>
 *  <li>Invalid dates are formatted as  255 pound signs ("#")</li>
 *  <li>simulate Excel's handling of a format string of all # when the value is 0.
 *   Excel will output "", <code>DataFormatter</code> will output "0".
 * </ul>
 * Modified 02/01/2016 by shizhongtao - * 
 */
public class ExcelDataFormatter implements Observer{

	private boolean ignoreNumFormat=false;
	
	private static final ThreadLocal<SimpleDateFormat> localFormat=new ThreadLocal<>();
	
    private static final String defaultFractionWholePartFormat = "#";
    private static final String defaultFractionFractionPartFormat = "#/##";
    /** Pattern to find a number format: "0" or  "#" */
    private static final Pattern numPattern = Pattern.compile("[0#]+");

    /** Pattern to find days of week as text "ddd...." */
    private static final Pattern daysAsText = Pattern.compile("([d]{3,})", Pattern.CASE_INSENSITIVE);

    /** Pattern to find "AM/PM" marker */
    private static final Pattern amPmPattern = Pattern.compile("((A|P)[M/P]*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern chineseConvertorPatternGroup = Pattern.compile("(\\[DBNum\\d\\])");

    /** 
     * A regex to find locale patterns like [$$-1009] and [$?-452].
     * Note that we don't currently process these into locales 
     */
    private static final Pattern localePatternGroup = Pattern.compile("(\\[\\$[^-\\]]*-[0-9A-Z]+\\])");

    /**
     * A regex to match the colour formattings rules.
     * Allowed colours are: Black, Blue, Cyan, Green,
     *  Magenta, Red, White, Yellow, "Color n" (1<=n<=56)
     */
    private static final Pattern colorPattern = 
       Pattern.compile("(\\[BLACK\\])|(\\[BLUE\\])|(\\[CYAN\\])|(\\[GREEN\\])|" +
       		"(\\[MAGENTA\\])|(\\[RED\\])|(\\[WHITE\\])|(\\[YELLOW\\])|" +
       		"(\\[COLOR\\s*\\d\\])|(\\[COLOR\\s*[0-5]\\d\\])", Pattern.CASE_INSENSITIVE);

    /**
     * A regex to identify a fraction pattern.
     * This requires that replaceAll("\\?", "#") has already been called 
     */
    private static final Pattern fractionPattern = Pattern.compile("(?:([#\\d]+)\\s+)?(#+)\\s*\\/\\s*([#\\d]+)");

    /**
     * A regex to strip junk out of fraction formats
     */
    private static final Pattern fractionStripper = Pattern.compile("(\"[^\"]*\")|([^ \\?#\\d\\/]+)");

    /**
      * Cells formatted with a date or time format and which contain invalid date or time values
     *  show 255 pound signs ("#").
      */
     private static final String invalidDateTimeString;
     static {
         StringBuilder buf = new StringBuilder();
         for(int i = 0; i < 255; i++) buf.append('#');
         invalidDateTimeString = buf.toString();
     }

    /**
     * The decimal symbols of the locale used for formatting values.
     */
    private DecimalFormatSymbols decimalSymbols;

    /**
     * The date symbols of the locale used for formatting values.
     */
    private DateFormatSymbols dateSymbols;

    /** <em>General</em> format for whole numbers. */
    private Format generalWholeNumFormat;

    /** <em>General</em> format for decimal numbers. */
    private Format generalDecimalNumFormat;

    /** A default format to use when a number pattern cannot be parsed. */
    private Format defaultNumFormat;

    /**
     * A map to cache formats.
     *  Map<String,Format> formats
     */
    private final Map<String,Format> formats = new HashMap<String,Format>();

    private boolean emulateCsv = false;

    /** stores the locale valid it the last formatting call */
    private Locale locale;
    
    /** stores if the locale should change according to {@link LocaleUtil#getUserLocale()} */
    private boolean localeIsAdapting = true;
    
    private class LocaleChangeObservable extends Observable {
        void checkForLocaleChange() {
            checkForLocaleChange(LocaleUtil.getUserLocale());
        }
        void checkForLocaleChange(Locale newLocale) {
            if (!localeIsAdapting) return;
            if (newLocale.equals(locale)) return;
            super.setChanged();
            notifyObservers(newLocale);
        }
    }
    
    /** the Observable to notify, when the locale has been changed */
    private final LocaleChangeObservable localeChangedObervable = new LocaleChangeObservable();
    
    /**
     * Creates a formatter using the {@link Locale#getDefault() default locale}.
     */
    public ExcelDataFormatter() {
        this(false);
        this.localeIsAdapting = true;
    }

    /**
     * Creates a formatter using the {@link Locale#getDefault() default locale}.
     *
     * @param  emulateCsv whether to emulate CSV output.
     */
    public ExcelDataFormatter(boolean emulateCsv) {
        this(LocaleUtil.getUserLocale(), emulateCsv);
        this.localeIsAdapting = true;
    }

    /**
     * Creates a formatter using the given locale.
     *
     * @param  emulateCsv whether to emulate CSV output.
     */
    public ExcelDataFormatter(Locale locale, boolean emulateCsv) {
        this(locale);
        this.emulateCsv = emulateCsv;
    }

    /**
     * Creates a formatter using the given locale.
     */
    public ExcelDataFormatter(Locale locale) {
        localeChangedObervable.addObserver(this);
        localeChangedObervable.checkForLocaleChange(locale);
        this.localeIsAdapting = false;
    }

    /**
     * Return a Format for the given cell if one exists, otherwise try to
     * create one. This method will return <code>null</code> if the any of the
     * following is true:
     * <ul>
     * <li>the cell's style is null</li>
     * <li>the style's data format string is null or empty</li>
     * <li>the format string cannot be recognized as either a number or date</li>
     * </ul>
     *
     * @param cell The cell to retrieve a Format for
     * @return A Format for the format String
     */
    private Format getFormat(Cell cell) {
        if ( cell.getCellStyle() == null) {
            return null;
        }

        int formatIndex = cell.getCellStyle().getDataFormat();
        String formatStr = cell.getCellStyle().getDataFormatString();
        if(formatStr == null || formatStr.trim().length() == 0) {
            return null;
        }
        return getFormat(cell.getNumericCellValue(), formatIndex, formatStr);
    }

    private Format getFormat(double cellValue, int formatIndex, String formatStrIn) {
        localeChangedObervable.checkForLocaleChange();
        
//      // Might be better to separate out the n p and z formats, falling back to p when n and z are not set.
//      // That however would require other code to be re factored.
//      String[] formatBits = formatStrIn.split(";");
//      int i = cellValue > 0.0 ? 0 : cellValue < 0.0 ? 1 : 2; 
//      String formatStr = (i < formatBits.length) ? formatBits[i] : formatBits[0];

        String formatStr = formatStrIn;
        // Excel supports positive/negative/zero, but java
        // doesn't, so we need to do it specially
        final int firstAt = formatStr.indexOf(';');
        final int lastAt = formatStr.lastIndexOf(';');
        // p and p;n are ok by default. p;n;z and p;n;z;s need to be fixed.
        if (firstAt != -1 && firstAt != lastAt) {
            final int secondAt = formatStr.indexOf(';', firstAt + 1);
            if (secondAt == lastAt) { // p;n;z
                if (cellValue == 0.0) {
                    formatStr = formatStr.substring(lastAt + 1);
                } else {
                    formatStr = formatStr.substring(0, lastAt);
                }
            } else {
                if (cellValue == 0.0) { // p;n;z;s
                    formatStr = formatStr.substring(secondAt + 1, lastAt);
                } else {
                    formatStr = formatStr.substring(0, secondAt);
                }
            }
        }

       // Excel's # with value 0 will output empty where Java will output 0. This hack removes the # from the format.
       if (emulateCsv && cellValue == 0.0 && formatStr.contains("#") && !formatStr.contains("0")) {
           formatStr = formatStr.replaceAll("#", "");
       }
       
        // See if we already have it cached
        Format format = formats.get(formatStr);
        if (format != null) {
            return format;
        }
        
        // Is it one of the special built in types, General or @?
        if ("General".equalsIgnoreCase(formatStr) || "@".equals(formatStr)) {
            if (isWholeNumber(cellValue)) {
                return generalWholeNumFormat;
            }
            return generalDecimalNumFormat;
        }
        
        // Build a formatter, and cache it
        format = createFormat(cellValue, formatIndex, formatStr);
        formats.put(formatStr, format);
        return format;
    }

    /**
     * Create and return a Format based on the format string from a  cell's
     * style. If the pattern cannot be parsed, return a default pattern.
     *
     * @param cell The Excel cell
     * @return A Format representing the excel format. May return null.
     */
    public Format createFormat(Cell cell) {

        int formatIndex = cell.getCellStyle().getDataFormat();
        String formatStr = cell.getCellStyle().getDataFormatString();
        return createFormat(cell.getNumericCellValue(), formatIndex, formatStr);
    }

    private Format createFormat(double cellValue, int formatIndex, String sFormat) {
        localeChangedObervable.checkForLocaleChange();
        
        String formatStr = sFormat;
        
        // Remove colour formatting if present
        Matcher colourM = colorPattern.matcher(formatStr);
        while(colourM.find()) {
           String colour = colourM.group();
           
           // Paranoid replacement...
           int at = formatStr.indexOf(colour);
           if(at == -1) break;
           String nFormatStr = formatStr.substring(0,at) +
              formatStr.substring(at+colour.length());
           if(nFormatStr.equals(formatStr)) break;

           // Try again in case there's multiple
           formatStr = nFormatStr;
           colourM = colorPattern.matcher(formatStr);
        }

        // Strip off the locale information, we use an instance-wide locale for everything
        Matcher m = localePatternGroup.matcher(formatStr);
        while(m.find()) {
            String match = m.group();
            String symbol = match.substring(match.indexOf('$') + 1, match.indexOf('-'));
            if (symbol.indexOf('$') > -1) {
                StringBuffer sb = new StringBuffer();
                sb.append(symbol.substring(0, symbol.indexOf('$')));
                sb.append('\\');
                sb.append(symbol.substring(symbol.indexOf('$'), symbol.length()));
                symbol = sb.toString();
            }
            formatStr = m.replaceAll(symbol);
            m = localePatternGroup.matcher(formatStr);
        }

        // Check for special cases
        if(formatStr == null || formatStr.trim().length() == 0) {
            return getDefaultFormat(cellValue);
        }
        
        if ("General".equalsIgnoreCase(formatStr) || "@".equals(formatStr)) {
           if (isWholeNumber(cellValue)) {
               return generalWholeNumFormat;
           }
           return generalDecimalNumFormat;
        }

        if(ExcelDateUtil.isADateFormat(formatIndex,formatStr) &&
                ExcelDateUtil.isValidExcelDate(cellValue)) {
            return createDateFormat(formatStr, cellValue);
        }
        // Excel supports fractions in format strings, which Java doesn't
        if (formatStr.indexOf("#/") >= 0 || formatStr.indexOf("?/") >= 0) {
            String[] chunks = formatStr.split(";");
            for (int i = 0; i < chunks.length; i++){
                String chunk = chunks[i].replaceAll("\\?", "#");
                Matcher matcher = fractionStripper.matcher(chunk);
                chunk = matcher.replaceAll(" ");
                chunk = chunk.replaceAll(" +", " ");
                Matcher fractionMatcher = fractionPattern.matcher(chunk);
                //take the first match
                if (fractionMatcher.find()){
                    String wholePart = (fractionMatcher.group(1) == null) ? "" : defaultFractionWholePartFormat;
                    return new FractionFormat(wholePart, fractionMatcher.group(3));
                }
            }
            
            // Strip custom text in quotes and escaped characters for now as it can cause performance problems in fractions.
            //String strippedFormatStr = formatStr.replaceAll("\\\\ ", " ").replaceAll("\\\\.", "").replaceAll("\"[^\"]*\"", " ").replaceAll("\\?", "#");
            //System.out.println("formatStr: "+strippedFormatStr);
            return new FractionFormat(defaultFractionWholePartFormat, defaultFractionFractionPartFormat);
        }
        
        if (numPattern.matcher(formatStr).find()) {
            return createNumberFormat(formatStr, cellValue);
        }

        if (emulateCsv) {
            return new ConstantStringFormat(cleanFormatForNumber(formatStr));
        }
        // TODO - when does this occur?
        return null;
    }
    
 

    private Format createDateFormat(String pFormatStr, double cellValue) {
        String formatStr = pFormatStr;
        formatStr = formatStr.replaceAll("\\\\-","-");
        formatStr = formatStr.replaceAll("\\\\,",",");
        formatStr = formatStr.replaceAll("\\\\\\.","."); // . is a special regexp char
        formatStr = formatStr.replaceAll("\\\\ "," ");
        formatStr = formatStr.replaceAll("\\\\/","/"); // weird: m\\/d\\/yyyy 
        formatStr = formatStr.replaceAll(";@", "");
        formatStr = formatStr.replaceAll("\"/\"", "/"); // "/" is escaped for no reason in: mm"/"dd"/"yyyy
        formatStr = formatStr.replace("\"\"", "'");	// replace Excel quoting with Java style quoting
        formatStr = formatStr.replaceAll("\\\\T","'T'"); // Quote the T is iso8601 style dates


        boolean hasAmPm = false;
        Matcher amPmMatcher = amPmPattern.matcher(formatStr);
        while (amPmMatcher.find()) {
            formatStr = amPmMatcher.replaceAll("@");
            hasAmPm = true;
            amPmMatcher = amPmPattern.matcher(formatStr);
        }
        formatStr = formatStr.replaceAll("@", "a");
        //TODO 如果实现中文方法表示数字的还，修改此处
        Matcher convertorMatcher = chineseConvertorPatternGroup.matcher(formatStr);
        while (convertorMatcher.find()) {
            formatStr = convertorMatcher.replaceAll("");
            convertorMatcher = chineseConvertorPatternGroup.matcher(formatStr);
        }
        
        
        
        Matcher dateMatcher = daysAsText.matcher(formatStr);
        if (dateMatcher.find()) {
            String match = dateMatcher.group(0).toUpperCase(Locale.ROOT).replaceAll("D", "E");
            formatStr = dateMatcher.replaceAll(match);
        }

        // Convert excel date format to SimpleDateFormat.
        // Excel uses lower and upper case 'm' for both minutes and months.
        // From Excel help:
        /*
            The "m" or "mm" code must appear immediately after the "h" or"hh"
            code or immediately before the "ss" code; otherwise, Microsoft
            Excel displays the month instead of minutes."
          */
        //start shizhongtao bing
       
        StringBuilder sb = new StringBuilder();
        char[] chars = formatStr.toCharArray();
        boolean mIsMonth = true;
        List<Integer> ms = new ArrayList<Integer>();
        boolean isElapsed = false;
        for(int j=0; j<chars.length; j++) {
        	
            char c = chars[j];
            if (c == '\'') {
                sb.append(c);
                j++;

                // skip until the next quote
                while(j<chars.length) {
                    c = chars[j];
                    sb.append(c);
                    if(c == '\'') {
                        break;
                    }
                    j++;
                }
            }
            else if (c == '[' && !isElapsed) {
                isElapsed = true;
                mIsMonth = false;
                sb.append(c);
            }
            else if (c == ']' && isElapsed) {
                isElapsed = false;
                sb.append(c);
            }
            else if (isElapsed) {
            if (c == 'h' || c == 'H') {
                    sb.append('H');
                }
                else if (c == 'm' || c == 'M') {
                    sb.append('m');
                }
                else if (c == 's' || c == 'S') {
                    sb.append('s');
                }
                else {
                    sb.append(c);
                }
            }
            else if (c == 'h' || c == 'H') {
                mIsMonth = false;
                if (hasAmPm) {
                    sb.append('h');
                } else {
                    sb.append('H');
                }
            }
            else if (c == 'm' || c == 'M') {
                if(mIsMonth) {
                    sb.append('M');
                    ms.add(
                            Integer.valueOf(sb.length() -1)
                    );
                } else {
                    sb.append('m');
                }
            }
            else if (c == 's' || c == 'S') {
                sb.append('s');
                // if 'M' precedes 's' it should be minutes ('m')
                for (int i = 0; i < ms.size(); i++) {
                    int index = ms.get(i).intValue();
                    if (sb.charAt(index) == 'M') {
                        sb.replace(index, index+1, "m");
                    }
                }
                mIsMonth = true;
                ms.clear();
            }
            else if (Character.isLetter(c)) {
                mIsMonth = true;
                ms.clear();
                if (c == 'y' || c == 'Y') {
                    sb.append('y');
                }
                else if (c == 'd' || c == 'D') {
                    sb.append('d');
                }
                else {
                    sb.append(c);
                }
            }
            else {
                sb.append(c);
            }
        }
        formatStr = sb.toString();
      //去掉中文单位上的引号
        Pattern date_chinese = Pattern.compile("\"([年月日时分秒]|(上午)|(下午))\"");
		 Matcher matcher = date_chinese.matcher(formatStr);
		 List<Integer> m = new ArrayList<>();
		while(matcher.find()){
				  m.add(matcher.start());
				  m.add(matcher.end()-1);
			  }
		char[] cs = formatStr.toCharArray();
		StringBuilder sbL=new StringBuilder();
		 for(int j=0; j<cs.length; j++) {
			 if(m.contains(j)){
				 continue;
			 }
			 sbL.append(cs[j]);
		 }
		 formatStr=sbL.toString();
		//end  
        try {
            return new ExcelStyleDateFormatter(formatStr, dateSymbols);
        } catch(IllegalArgumentException iae) {

            // the pattern could not be parsed correctly,
            // so fall back to the default number format
            return getDefaultFormat(cellValue);
        }

    }

    private String cleanFormatForNumber(String formatStr) {
        StringBuilder sb = new StringBuilder(formatStr);

        if (emulateCsv) {
            // Requested spacers with "_" are replaced by a single space.
            // Full-column-width padding "*" are removed.
            // Not processing fractions at this time. Replace ? with space.
            // This matches CSV output.
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (c == '_' || c == '*' || c == '?') {
                    if (i > 0 && sb.charAt((i - 1)) == '\\') {
                        // It's escaped, don't worry
                        continue;
                    }
                    if (c == '?') {
                        sb.setCharAt(i, ' ');
                    } else if (i < sb.length() - 1) {
                        // Remove the character we're supposed
                        //  to match the space of / pad to the
                        //  column width with
                        if (c == '_') {
                            sb.setCharAt(i + 1, ' ');
                        } else {
                            sb.deleteCharAt(i + 1);
                        }
                        // Remove the character too
                        sb.deleteCharAt(i);
                        i--;
                    }
                }
            }
        } else {
            // If they requested spacers, with "_",
            //  remove those as we don't do spacing
            // If they requested full-column-width
            //  padding, with "*", remove those too
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (c == '_' || c == '*') {
                    if (i > 0 && sb.charAt((i - 1)) == '\\') {
                        // It's escaped, don't worry
                        continue;
                    }
                    if (i < sb.length() - 1) {
                        // Remove the character we're supposed
                        //  to match the space of / pad to the
                        //  column width with
                        sb.deleteCharAt(i + 1);
                    }
                    // Remove the _ too
                    sb.deleteCharAt(i);
                    i--;
                }
            }
        }

        // Now, handle the other aspects like 
        //  quoting and scientific notation
        for(int i = 0; i < sb.length(); i++) {
           char c = sb.charAt(i);
            // remove quotes and back slashes
            if (c == '\\' || c == '"') {
                sb.deleteCharAt(i);
                i--;

            // for scientific/engineering notation
            } else if (c == '+' && i > 0 && sb.charAt(i - 1) == 'E') {
                sb.deleteCharAt(i);
                i--;
            }
        }

        return sb.toString();
    }

    private Format createNumberFormat(String formatStr, double cellValue) {
        final String format = cleanFormatForNumber(formatStr);
        
        try {
            DecimalFormat df = new DecimalFormat(format, decimalSymbols);
            setExcelStyleRoundingMode(df);
            return df;
        } catch(IllegalArgumentException iae) {

            // the pattern could not be parsed correctly,
            // so fall back to the default number format
            return getDefaultFormat(cellValue);
        }
    }

    /**
     * Return true if the double value represents a whole number
     * @param d the double value to check
     * @return <code>true</code> if d is a whole number
     */
    private static boolean isWholeNumber(double d) {
        return d == Math.floor(d);
    }

    /**
     * Returns a default format for a cell.
     * @param cell The cell
     * @return a default format
     */
    public Format getDefaultFormat(Cell cell) {
        return getDefaultFormat(cell.getNumericCellValue());
    }
    private Format getDefaultFormat(double cellValue) {
        localeChangedObervable.checkForLocaleChange();
        
        // for numeric cells try user supplied default
        if (defaultNumFormat != null) {
            return defaultNumFormat;

          // otherwise use general format
        }
        if (isWholeNumber(cellValue)){
            return generalWholeNumFormat;
        }
        return generalDecimalNumFormat;
    }
    
    /**
     * Performs Excel-style date formatting, using the
     *  supplied Date and format
     */
    private String performDateFormatting(Date d, Format dateFormat) {
       if(dateFormat != null) {
          return dateFormat.format(d);
      }
      return d.toString();
    }

    /**
     * Returns the formatted value of an Excel date as a <tt>String</tt> based
     * on the cell's <code>DataFormat</code>. i.e. "Thursday, January 02, 2003"
     * , "01/02/2003" , "02-Jan" , etc.
     *
     * @param cell The cell
     * @return a formatted date string
     */
    private String getFormattedDateString(Cell cell) {
        Format dateFormat = getFormat(cell);
        if(dateFormat instanceof ExcelStyleDateFormatter) {
           // Hint about the raw excel value
           ((ExcelStyleDateFormatter)dateFormat).setDateToBeFormatted(
                 cell.getNumericCellValue()
           );
        }
        Date d = cell.getDateCellValue();
        return performDateFormatting(d, dateFormat);
    }

    /**
     * Returns the formatted value of an Excel number as a <tt>String</tt>
     * based on the cell's <code>DataFormat</code>. Supported formats include
     * currency, percents, decimals, phone number, SSN, etc.:
     * "61.54%", "$100.00", "(800) 555-1234".
     *
     * @param cell The cell
     * @return a formatted number string
     */
    private String getFormattedNumberString(Cell cell) {

        Format numberFormat = getFormat(cell);
        double d = cell.getNumericCellValue();
        if (numberFormat == null) {
            return String.valueOf(d);
        }
        return numberFormat.format(new Double(d));
    }

    /**
     * Formats the given raw cell value, based on the supplied
     *  format index and string, according to excel style rules.
     * @see #formatCellValue(Cell)
     */
    public String formatRawCellContents(double value, int formatIndex, String formatString) {
        return formatRawCellContents(value, formatIndex, formatString, false);
    }
    /**
     * Formats the given raw cell value, based on the supplied
     *  format index and string, according to excel style rules.
     * @see #formatCellValue(Cell)
     */
    public String formatRawCellContents(double value, int formatIndex, String formatString, boolean use1904Windowing) {
        localeChangedObervable.checkForLocaleChange();
        
        // Is it a date?
        if(ExcelDateUtil.isADateFormat(formatIndex,formatString)) {
            if(ExcelDateUtil.isValidExcelDate(value)) {
            	Format dateFormat ;
            	if(ignoreNumFormat){
            		dateFormat = getDateFormat();
            	}else{
            		dateFormat = getFormat(value, formatIndex, formatString);
            	}
                if(dateFormat instanceof ExcelStyleDateFormatter) {
                    // Hint about the raw excel value
                    ((ExcelStyleDateFormatter)dateFormat).setDateToBeFormatted(value);
                }
                Date d = ExcelDateUtil.getJavaDate(value, use1904Windowing);
                return performDateFormatting(d, dateFormat);
            }
            // RK: Invalid dates are 255 #s.
            if (emulateCsv) {
                return invalidDateTimeString;
            }
        }
        
        // else Number
        if(ignoreNumFormat){
        	String re= generalDecimalNumFormat.format(value);
        	int reLength = re.length();
        	/*
        	 * FIXME 在excel中，所能表达的double类型最多15个数字。而在excel底层存储中却可以存储到17？18？位数字，
        	 * 并且（由于excle的bug）？，比如-60.704会在底层存储为-60.7040000000000001，从而读取数据与excel中看到的
        	 * 不一致。顾此处临时解决这个问题
        	 * 
        	 */
        	if(reLength>16){
        		int index=re.indexOf('.');
        		if(re.startsWith("-")){
    				index-=1;
    			}
        		if(index>0&&index<15){
        			
        			BigDecimal decimal = new BigDecimal(value);
            		value =decimal.setScale(15-index, BigDecimal.ROUND_HALF_UP).doubleValue();
            		return generalDecimalNumFormat.format(value);
        		}
        		
        	}
        	return re;
        }
        Format numberFormat = getFormat(value, formatIndex, formatString);
        if (numberFormat == null) {
            return String.valueOf(value);
        }
        
        // When formatting 'value', double to text to BigDecimal produces more
        // accurate results than double to Double in JDK8 (as compared to
        // previous versions). However, if the value contains E notation, this
        // would expand the values, which we do not want, so revert to
        // original method.
        String result;
        final String textValue = NumberToTextConverter.toText(value);
        if (textValue.indexOf('E') > -1) {
            result = numberFormat.format(new Double(value));
        }
        else {
            result = numberFormat.format(new BigDecimal(textValue));
        }
        // Complete scientific notation by adding the missing +.
        if (result.indexOf('E') > -1 && !result.contains("E-")) {
            result = result.replaceFirst("E", "E+");
        }
        return result;
    }

    private Format getDateFormat() {
    	SimpleDateFormat dateFormat = localFormat.get();
    	if (dateFormat==null) {
			
    		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		localFormat.set(dateFormat);
		}
		return dateFormat;
	}

	/**
     * <p>
     * Returns the formatted value of a cell as a <tt>String</tt> regardless
     * of the cell type. If the Excel format pattern cannot be parsed then the
     * cell value will be formatted using a default format.
     * </p>
     * <p>When passed a null or blank cell, this method will return an empty
     * String (""). Formulas in formula type cells will not be evaluated.
     * </p>
     *
     * @param cell The cell
     * @return the formatted cell value as a String
     */
    public String formatCellValue(Cell cell) {
        return formatCellValue(cell, null);
    }

    /**
     * <p>
     * Returns the formatted value of a cell as a <tt>String</tt> regardless
     * of the cell type. If the Excel format pattern cannot be parsed then the
     * cell value will be formatted using a default format.
     * </p>
     * <p>When passed a null or blank cell, this method will return an empty
     * String (""). Formula cells will be evaluated using the given
     * {@link FormulaEvaluator} if the evaluator is non-null. If the
     * evaluator is null, then the formula String will be returned. The caller
     * is responsible for setting the currentRow on the evaluator
     *</p>
     *
     * @param cell The cell (can be null)
     * @param evaluator The FormulaEvaluator (can be null)
     * @return a string value of the cell
     */
    public String formatCellValue(Cell cell, FormulaEvaluator evaluator) {
        localeChangedObervable.checkForLocaleChange();
        
        if (cell == null) {
            return "";
        }

        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_FORMULA) {
            if (evaluator == null) {
                return cell.getCellFormula();
            }
            cellType = evaluator.evaluateFormulaCell(cell);
        }
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC :

                if (ExcelDateUtil.isCellDateFormatted(cell)) {
                    return getFormattedDateString(cell);
                }
                return getFormattedNumberString(cell);

            case Cell.CELL_TYPE_STRING :
                return cell.getRichStringCellValue().getString();

            case Cell.CELL_TYPE_BOOLEAN :
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_BLANK :
                return "";
            case Cell.CELL_TYPE_ERROR:
            	return FormulaError.forInt(cell.getErrorCellValue()).getString();
        }
        throw new RuntimeException("Unexpected celltype (" + cellType + ")");
    }


    /**
     * <p>
     * Sets a default number format to be used when the Excel format cannot be
     * parsed successfully. <b>Note:</b> This is a fall back for when an error
     * occurs while parsing an Excel number format pattern. This will not
     * affect cells with the <em>General</em> format.
     * </p>
     * <p>
     * The value that will be passed to the Format's format method (specified
     * by <code>java.text.Format#format</code>) will be a double value from a
     * numeric cell. Therefore the code in the format method should expect a
     * <code>Number</code> value.
     * </p>
     *
     * @param format A Format instance to be used as a default
     * @see java.text.Format#format
     */
    public void setDefaultNumberFormat(Format format) {
        Iterator<Map.Entry<String,Format>> itr = formats.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry<String,Format> entry = itr.next();
            if (entry.getValue() == generalDecimalNumFormat
                    || entry.getValue() == generalWholeNumFormat) {
                entry.setValue(format);
            }
        }
        defaultNumFormat = format;
    }

    /**
     * Adds a new format to the available formats.
     * <p>
     * The value that will be passed to the Format's format method (specified
     * by <code>java.text.Format#format</code>) will be a double value from a
     * numeric cell. Therefore the code in the format method should expect a
     * <code>Number</code> value.
     * </p>
     * @param excelFormatStr The data format string
     * @param format A Format instance
     */
    public void addFormat(String excelFormatStr, Format format) {
        formats.put(excelFormatStr, format);
    }

    // Some custom formats

    /**
     * @return a <tt>DecimalFormat</tt> with parseIntegerOnly set <code>true</code>
     */
    private static DecimalFormat createIntegerOnlyFormat(String fmt) {
        DecimalFormatSymbols dsf = DecimalFormatSymbols.getInstance(Locale.ROOT);
        DecimalFormat result = new DecimalFormat(fmt, dsf);
        result.setParseIntegerOnly(true);
        return result;
    }
    
    /**
     * Enables excel style rounding mode (round half up) on the 
     *  Decimal Format given.
     */
    public static void setExcelStyleRoundingMode(DecimalFormat format) {
        setExcelStyleRoundingMode(format, RoundingMode.HALF_UP);
    }

    /**
     * Enables custom rounding mode on the given Decimal Format.
     * @param format DecimalFormat
     * @param roundingMode RoundingMode
     */
    public static void setExcelStyleRoundingMode(DecimalFormat format, RoundingMode roundingMode) {
       format.setRoundingMode(roundingMode);
    }

    /**
     * If the Locale has been changed via {@link LocaleUtil#setUserLocale(Locale)} the stored
     * formats need to be refreshed. All formats which aren't originated from DataFormatter
     * itself, i.e. all Formats added via {@link DataFormatter#addFormat(String, Format)} and
     * {@link DataFormatter#setDefaultNumberFormat(Format)}, need to be added again.
     * To notify callers, the returned {@link Observable} should be used.
     * The Object in {@link Observer#update(Observable, Object)} is the new Locale.
     *
     * @return the listener object, where callers can register themself
     */
    public Observable getLocaleChangedObservable() {
        return localeChangedObervable;
    }

    /**
     * Update formats when locale has been changed
     *
     * @param observable usually this is our own Observable instance
     * @param localeObj only reacts on Locale objects
     */
    public void update(Observable observable, Object localeObj) {
        if (!(localeObj instanceof Locale))  return;
        Locale newLocale = (Locale)localeObj;
        if (!localeIsAdapting || newLocale.equals(locale)) return;
        
        locale = newLocale;
        
        dateSymbols = DateFormatSymbols.getInstance(locale);
        decimalSymbols = DecimalFormatSymbols.getInstance(locale);
        generalWholeNumFormat = new DecimalFormat("#", decimalSymbols);
        generalDecimalNumFormat = new DecimalFormat("#.##################", decimalSymbols);

        // init built-in formats

        formats.clear();
        Format zipFormat = ZipPlusFourFormat.instance;
        addFormat("00000\\-0000", zipFormat);
        addFormat("00000-0000", zipFormat);

        Format phoneFormat = PhoneFormat.instance;
        // allow for format string variations
        addFormat("[<=9999999]###\\-####;\\(###\\)\\ ###\\-####", phoneFormat);
        addFormat("[<=9999999]###-####;(###) ###-####", phoneFormat);
        addFormat("###\\-####;\\(###\\)\\ ###\\-####", phoneFormat);
        addFormat("###-####;(###) ###-####", phoneFormat);

        Format ssnFormat = SSNFormat.instance;
        addFormat("000\\-00\\-0000", ssnFormat);
        addFormat("000-00-0000", ssnFormat);
    }



    /**
     * Format class for Excel's SSN format. This class mimics Excel's built-in
     * SSN formatting.
     *
     * @author James May
     */
    @SuppressWarnings("serial")
   private static final class SSNFormat extends Format {
        public static final Format instance = new SSNFormat();
        private static final DecimalFormat df = createIntegerOnlyFormat("000000000");
        private SSNFormat() {
            // enforce singleton
        }

        /** Format a number as an SSN */
        public static String format(Number num) {
            String result = df.format(num);
            StringBuffer sb = new StringBuffer();
            sb.append(result.substring(0, 3)).append('-');
            sb.append(result.substring(3, 5)).append('-');
            sb.append(result.substring(5, 9));
            return sb.toString();
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(format((Number)obj));
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    /**
     * Format class for Excel Zip + 4 format. This class mimics Excel's
     * built-in formatting for Zip + 4.
     * @author James May
     */
    @SuppressWarnings("serial")
   private static final class ZipPlusFourFormat extends Format {
        public static final Format instance = new ZipPlusFourFormat();
        private static final DecimalFormat df = createIntegerOnlyFormat("000000000");
        private ZipPlusFourFormat() {
            // enforce singleton
        }

        /** Format a number as Zip + 4 */
        public static String format(Number num) {
            String result = df.format(num);
            StringBuffer sb = new StringBuffer();
            sb.append(result.substring(0, 5)).append('-');
            sb.append(result.substring(5, 9));
            return sb.toString();
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(format((Number)obj));
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    /**
     * Format class for Excel phone number format. This class mimics Excel's
     * built-in phone number formatting.
     * @author James May
     */
    @SuppressWarnings("serial")
   private static final class PhoneFormat extends Format {
        public static final Format instance = new PhoneFormat();
        private static final DecimalFormat df = createIntegerOnlyFormat("##########");
        private PhoneFormat() {
            // enforce singleton
        }

        /** Format a number as a phone number */
        public static String format(Number num) {
            String result = df.format(num);
            StringBuffer sb = new StringBuffer();
            String seg1, seg2, seg3;
            int len = result.length();
            if (len <= 4) {
                return result;
            }

            seg3 = result.substring(len - 4, len);
            seg2 = result.substring(Math.max(0, len - 7), len - 4);
            seg1 = result.substring(Math.max(0, len - 10), Math.max(0, len - 7));

            if(seg1 != null && seg1.trim().length() > 0) {
                sb.append('(').append(seg1).append(") ");
            }
            if(seg2 != null && seg2.trim().length() > 0) {
                sb.append(seg2).append('-');
            }
            sb.append(seg3);
            return sb.toString();
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(format((Number)obj));
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }
    

    
    
    /**
     * Format class that does nothing and always returns a constant string.
     *
     * This format is used to simulate Excel's handling of a format string
     * of all # when the value is 0. Excel will output "", Java will output "0".
     *
     * @see DataFormatter#createFormat(double, int, String)
     */
    @SuppressWarnings("serial")
   private static final class ConstantStringFormat extends Format {
        private static final DecimalFormat df = createIntegerOnlyFormat("##########");
        private final String str;
        public ConstantStringFormat(String s) {
            str = s;
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(str);
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }




	public boolean isIgnoreNumFormat() {
		return ignoreNumFormat;
	}

	public void setIgnoreNumFormat(boolean ignoreNumFormat) {
		this.ignoreNumFormat = ignoreNumFormat;
	}
    
	


}
