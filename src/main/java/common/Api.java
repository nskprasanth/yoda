package instruments;

import robinhood.ConfigManager;
import robinhood.apis.RequestType;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public interface Api {

    String getName();

    String getUrlBase();

    RequestType getRequestType();

    Type getResponseType();

    Map<String, String> getHeaderParams();

    Map<String, String> getRequestParams();

}
