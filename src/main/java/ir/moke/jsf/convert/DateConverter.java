package ir.moke.jsf.convert;

import org.apache.johnzon.mapper.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<Date> {

    @Override
    public String toString(Date date) {
        return date.toString();
    }

    @Override
    public Date fromString(String s) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat();
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
