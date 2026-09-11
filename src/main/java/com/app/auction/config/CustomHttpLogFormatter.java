package com.app.auction.config;

import java.io.IOException;
import java.util.Map;

import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.json.JsonHttpLogFormatter;

final class CustomHttpLogFormatter implements HttpLogFormatter {

    private final JsonHttpLogFormatter jsonFormatter;

    CustomHttpLogFormatter(final JsonHttpLogFormatter jsonFormatter) {
        this.jsonFormatter = jsonFormatter;
    }

    @Override
    public String format(Precorrelation precorrelation, HttpRequest request) throws IOException {
        Map<String, Object> content = jsonFormatter.prepare(precorrelation, request);
     
        content.remove("remote");
        content.remove("origin");
        content.remove("protocol");
        content.remove("method");
        content.remove("uri");
        content.remove("host");
        content.remove("scheme");
        content.remove("port");
        return jsonFormatter.format(content) + "";

    }

    @Override
    public String format(Correlation correlation, HttpResponse response) throws IOException {
        Map<String, Object> content = jsonFormatter.prepare(correlation, response);
        content.remove("origin");
        content.remove("protocol");
        return jsonFormatter.format(content) + "";

    }

}