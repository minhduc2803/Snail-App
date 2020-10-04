package vn.zalopay.ducnm8.config;

public class APIPath {
  private static final String PUBLIC_API = "/api/public/";
  private static final String PROTECTED = "/api/protected/";
  public static final String ECHO = PUBLIC_API + "echo";
  public static final String EXAMPLE = PUBLIC_API + "example";

  public static final String LOGIN = PUBLIC_API + "login";
  public static final String REGISTER = PUBLIC_API + "register";

  public static final String LIST_CONVERSATION = PROTECTED + "list-conversation";
  public static final String LIST_CHAT = PROTECTED + "list-chat";
  public static final String LIST_USER = PROTECTED + "list-user";
}
