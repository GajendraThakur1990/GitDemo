package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {

        if (req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\thaku\\IdeaProjects\\APIFramework\\src\\test\\java\\Resources\\Global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }

    public String getJsonPath(Response response, String key){

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        System.out.println(js.get(key).toString());
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        return js.get(key).toString();
    }


}
