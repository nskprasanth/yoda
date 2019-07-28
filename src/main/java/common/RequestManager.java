package common;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestManager {

    private static RequestManager manager = null;

    public static RequestManager getInstance() {
        if (manager == null) {
            manager = new RequestManager();
        }

        return manager;
    }

    public <T> T callAPI(Api api) {

        T result = null;

        HttpRequest request = null;
        switch (api.getRequestType()) {
            case GET:
                request = buildGetRequest(api);
                break;
            case POST:
                request = buildPostRequest(api);
                break;
        }

        try {
            HttpResponse<JsonNode> response = request.asJson();

            // TODO: remove after testing
            System.out.println(response.getBody().toString());

            Gson gson = new Gson();
            result = gson.fromJson(response.getBody().toString(), api.getResponseType());

        } catch (UnirestException e) {
            // TODO: throw custom exception
            e.printStackTrace();
        }

        return result;
    }

    private HttpRequest buildGetRequest(Api api) {
        GetRequest request = Unirest.get(api.getUrlBase());
        Map<String, String> headers = api.getHeaderParams();
        for (String k : headers.keySet()) {
            request.header(k, headers.get(k));
        }

        Map<String, String> requestParams = api.getRequestParams();
        for (String k : requestParams.keySet()) {
            request.queryString(k, requestParams.get(k));
        }

        return request;
    }

    private HttpRequest buildPostRequest(Api api) {
        HttpRequestWithBody request = Unirest.post(api.getUrlBase());
        Map<String, String> headers = api.getHeaderParams();
        for (String k : headers.keySet()) {
            request.header(k, headers.get(k));
        }

        request.body(getRequestParamsAsString(api.getRequestParams()));
        return request;
    }

    private String getRequestParamsAsString(Map<String, String> requestParams) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String k : requestParams.keySet()) {
            sb.append(first ? "" : "&");
            sb.append(URLEncoder.encode(k, StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(
                    requestParams.get(k), StandardCharsets.UTF_8));
            first = false;
        }
        return sb.toString();
    }

}
