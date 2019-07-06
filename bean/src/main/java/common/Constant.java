package common;

/**
 * @author jinzhicheng
 * @date 2019/5/27 10:49
 */
public class Constant {

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;

    /**
     * 1愿景，2使命，3价值观，4战略，5社会责任，6社会影响力  7绩效指标
     *
     * @return
     */
    public static String collegeFunctionType(int type) {
        switch (type) {
            case ONE:
                return "愿景";
            case TWO:
                return "使命";
            case THREE:
                return "价值观";
            case FOUR:
                return "战略";
            case FIVE:
                return "社会责任";
            case SIX:
                return "社会影响力";
            case SEVEN:
                return "绩效指标";
            default:
                return "其他";
        }
    }

}
