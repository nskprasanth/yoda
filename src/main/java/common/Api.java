package common;

import java.lang.reflect.Type;
import java.util.Map;

public interface Api {

    String getName();

    String getUrlBase();

    RequestType getRequestType();

    Type getResponseType();

    Map<String, String> getHeaderParams();

    Map<String, String> getRequestParams();

}
