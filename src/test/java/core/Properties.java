package core;

import lombok.Getter;

@Getter
public class Properties {

    public static String LOGIN_URL = Config.properties.getProperty("login.url");

}
