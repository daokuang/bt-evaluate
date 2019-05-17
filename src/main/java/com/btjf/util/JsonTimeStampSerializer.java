package com.btjf.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yj on 2019/5/17.
 */
public class JsonTimeStampSerializer extends JsonSerializer<Date> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void serialize(final Date timestamp, final JsonGenerator gen, final SerializerProvider provider) throws IOException,
            JsonProcessingException {
        String value = dateFormat.format(timestamp);
        gen.writeString(value);
    }
}
