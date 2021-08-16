import org.apache.commons.lang.StringUtils;
/**
 *
 * 移除字符串中空格并给出提示，没移除也给出提示。
 */
public class Test {
    public static String removeBlank(String str, String logInfo) {
        String strTemp = str;
        String logTemp = logInfo;
        int strLen = logTemp.length();
        int i;
        for (i = 0; i < strLen; i++) {
            if (Character.isWhitespace(strTemp.charAt(i)) == true) {
                System.out.println(logTemp + "字符串：" + strTemp + "有空格，已经自动去除了空格");
                strTemp = strTemp.replaceAll("\\s*", "");
                System.out.println(logTemp + "去除空格后的字符串：" + strTemp);
                break;
            }
        }
        if (i == strLen) {
            System.out.println(logTemp + "字符串：" + strTemp + "没有空格");
        }
        return strTemp;
    }
    public static void main(String[] args) {
        String strTest = "asdas";
        if (StringUtils.isNotBlank(str)) {
            str = removeBlank(str, "roleGroupId");
        }
    }
}