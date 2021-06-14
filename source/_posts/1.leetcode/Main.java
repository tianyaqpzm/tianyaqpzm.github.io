import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String reg = "(intl\\.huaweicloud\\.com)|(\\.huaweicloud\\.com/intl/)";
        Pattern pat = Pattern.compile(reg);
        Matcher matcher = pat.matcher("http://console-intl.huaweicloud.com/intl/test.html");
        Matcher matcher2 = pat.matcher("http://console.huaweicloud.com/intl/test.html");
        Matcher matcher3 = pat.matcher("http://console-intl.huaweicloud.com/intl/test.html");
        Matcher matcher4 = pat.matcher("http://console-intl.huiiaweicloud.com/intl.//./test.html");

        if (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
        }
        if (matcher2.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
        }
        if (matcher3.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
        }
        if (matcher4.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
        }
    }
}