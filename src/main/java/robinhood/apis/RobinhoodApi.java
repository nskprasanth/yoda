package robinhood.apis;

import robinhood.ConfigManager;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractApi {

    private final String name;
    private String urlBase;
    private RequestType requestType;

    private Type responseType;

    private Map<String, String> headerParams = new HashMap<>();
    private Map<String,String> requestParams = new HashMap<>();

    private boolean tokenRequired = false;

    private final ConfigManager config = ConfigManager.getInstance();

    protected AbstractApi(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Type getResponseType() {
        return responseType;
    }

    public Map<String,String> getHeaderParams() {
        return headerParams;
    }

    public Map<String,String> getRequestParams() {
        return requestParams;
    }

    protected void setUrlBase(String uri) {
        this.urlBase = uri;
    }

    protected void setRequestType(RequestType type) {
        this.requestType = type;
    }

    protected void setResponseType(Type type) {
        this.responseType = type;
    }

    public void setHeaderParams(String k, String v) {
        headerParams.put(k, v);
    }

    public void setRequestParams(String k, String v) {
        requestParams.put(k, v);
    }

    public void setTokenRequired() {
        tokenRequired = true;
    }

    public boolean requireAuthToken() {
        return tokenRequired;
    }

    public String getRequestParamsAsString() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String k : requestParams.keySet()) {
            sb.append(first ? "" : "&");
            sb.append(URLEncoder.encode(k,"UTF-8")).append("=").append(URLEncoder.encode(requestParams.get(k),"UTF-8"));
            first = false;
        }
        return sb.toString();
    }
}
