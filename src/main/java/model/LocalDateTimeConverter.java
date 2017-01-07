package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.sql2o.converters.Converter;
import org.sql2o.converters.ConverterException;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    @Override
    public LocalDateTime convert(Object val) throws ConverterException {
        if (val == null) {
            return null;
        }

        if (val instanceof LocalDateTime) {
            return (LocalDateTime) val;
        }

        if (val instanceof Timestamp) {
            return ((Timestamp) val).toLocalDateTime();
        }

        if (val instanceof String) {
            return LocalDateTime.parse((String) val);
        }

        throw new ConverterException("Cannot convert type " + val.getClass() + " to " + LocalDateTime.class);
    }

    @Override
    public Object toDatabaseParam(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }

}
