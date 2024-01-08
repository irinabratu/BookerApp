package api;

import java.util.HashMap;

public class HeaderApi {

    private final static String validContentType = "application/json";
    private final static String invalidContentType = "application/xml";
    private final static String validAccept = "application/json";
    private final static String invalidAccept = "application/xml";
    private final static String validAuth = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    private final static String invalidAuth = "Basic 1234=";

    public static HashMap<String,String> headerBuilder(String contentType, String accept, String auth, String token) {
        HashMap<String,String> headerBuilder = new HashMap<>();

        switch(contentType) {
            case "valid":
                headerBuilder.put("Content-Type",validContentType);
                break;
            case "invalid":
                headerBuilder.put("Content-Type",invalidContentType);
                break;
            case "missing":
                break;
            default:
                System.out.println("contentType value not expected");
        }

        switch(accept) {
            case "valid":
                headerBuilder.put("Accept",validAccept);
                break;
            case "invalid":
                headerBuilder.put("Accept",invalidAccept);
                break;
            case "missing":
                break;
            default:
                System.out.println("accept value not expected");
        }

        switch(auth) {
            case "valid":
                headerBuilder.put("Authorization",validAuth);
                break;
            case "invalid":
                headerBuilder.put("Authorization",invalidAuth);
                break;
            case "missing":
                break;
            default:
                System.out.println("auth value not expected");
        }

        if(token!=null){
            headerBuilder.put("Cookie","token=" + token);
        }

        return headerBuilder;
    }
}
