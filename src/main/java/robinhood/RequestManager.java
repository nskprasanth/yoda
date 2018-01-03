package robinhood;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import robinhood.apis.Api;

import java.io.UnsupportedEncodingException;
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
            default:
                break;
        }

        if (request == null) {
            return null;
        }

        try {
            HttpResponse<JsonNode> response = request.asJson();

            Gson gson = new Gson();
            result = gson.fromJson(response.getBody().toString(), api.getReturnType());

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

        return request;
    }

    private HttpRequest buildPostRequest(Api api) {
        HttpRequestWithBody request = Unirest.post(api.getUrlBase());
        Map<String, String> headers = api.getHeaderParams();
        for (String k : headers.keySet()) {
            request.header(k, headers.get(k));
        }

        try {
            request.body(api.getRequestParamsAsString());
        } catch (UnsupportedEncodingException e) {
            // TODO: throw custom exception
            e.printStackTrace();
        }
        return request;
    }
}
