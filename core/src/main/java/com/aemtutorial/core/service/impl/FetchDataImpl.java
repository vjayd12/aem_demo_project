package com.aemtutorial.core.service.impl;

import com.aemtutorial.core.ocd.DomainAPIConfiguration;
import com.aemtutorial.core.service.FetchData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Designate(ocd = DomainAPIConfiguration.class)
@Component(service = FetchData.class, immediate = true)
public class FetchDataImpl implements FetchData {
    private String apiEndpoint;


    private static final Logger log = LoggerFactory.getLogger(FetchDataImpl.class);


    @Override
    public JsonObject getCusotmerData() {
        String jsonData = apiCall();

        if (StringUtils.isBlank(jsonData)) {
            log.error("Received empty or invalid JSON data.");
            return null;
        }
        try {
            JsonElement jsonElement = JsonParser.parseString(jsonData);

            if (jsonElement.isJsonObject()) {
                return jsonElement.getAsJsonObject();
            }

            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                log.info("JSON Array: {}", jsonArray);
                if (jsonArray.size() > 0) {
                    JsonObject firstElement = jsonArray.get(0).getAsJsonObject();
                    return firstElement;
                } else {
                    log.error("The JSON array is empty.");
                }
            }
        } catch (Exception e) {
            log.error("Error while parsing the response as JSON", e);
        }

        return null;
    }
    @Modified
    @Activate
    public void activate(DomainAPIConfiguration config) {
        apiEndpoint = config.bffEndpoint();
        JsonObject customerData = getCusotmerData();
        log.info("API EndPoint: {}", apiEndpoint);

        if (customerData != null) {
            log.info("Customer Data: {}", customerData.toString());
        } else {
            log.error("Customer data is null or empty.");
        }
    }

    private String apiCall() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        String output = StringUtils.EMPTY;
        try {
            HttpGet request = new HttpGet(apiEndpoint);
            request.setHeader("accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                return "error";
            }
            HttpEntity httpEntity = response.getEntity();
            output = EntityUtils.toString(httpEntity);
            log.info("Response body: output{}", output);

        } catch (IOException e) {
            log.info("Response body: {}", e.getMessage());
        }
        return output;
    }
}
