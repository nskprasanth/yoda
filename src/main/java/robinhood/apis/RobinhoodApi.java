package robinhood.apis;

import common.Api;
import common.RequestType;
import robinhood.config.ConfigManager;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class RobinhoodApi implements Api {

    private final ConfigManager config = ConfigManager.getInstance();

    private final String name;

    private String urlBase;
    private RequestType requestType;
    private Type responseType;

    private Map<String, String> headerParams = new HashMap<>();
    private Map<String, String> requestParams = new HashMap<>();

    protected RobinhoodApi(String name) {
        this.name = name;
    }

    protected void setAuthToken() {
        setHeaderParams("Authorization", "Bearer " + config.getAuthToken());
    }

    public String getName() {
        return name;
    }

    public String getUrlBase() {
        return urlBase;
    }

    protected void setUrlBase(String uri) {
        this.urlBase = uri;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    protected void setRequestType(RequestType type) {
        this.requestType = type;
    }

    public Type getResponseType() {
        return responseType;
    }

    protected void setResponseType(Type type) {
        this.responseType = type;
    }

    public Map<String, String> getHeaderParams() {
        return headerParams;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setHeaderParams(String k, String v) {
        headerParams.put(k, v);
    }

    public void setRequestParams(String k, String v) {
        requestParams.put(k, v);
    }



}
