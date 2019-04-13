package base;

/**
 * Stores configuration constants for the test suite
 */
public final class Config {

    public static final String BASE_URL = "https://trello.com";
    public static final String API_URL = "https://api.trello.com";
    public static final int TIMEOUT_SECONDS = 30;

    // Trello account configuration
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String API_KEY = "KEY";
    public static final String API_TOKEN = "TOKEN";

    public static final String PROJECT_PATH = ""; // path to the project in the filesystem
    public static final String RESOURCE_LOCATION = PROJECT_PATH + "src\\test\\resources\\";
    public static final String PATH_TO_IIMAGE = RESOURCE_LOCATION + "sunflower.jpg";
    public static final String PATH_TO_UNICODE_IIMAGE = RESOURCE_LOCATION + "觻۩센 䋽軍휴x�𘦄枢g񮗎焖xɌ䒹`̍ꥷ疽Ǧ󣬯-āỒ򪜒櫿ۨ퐠󵥖@.jpg";

    private Config() {}
}