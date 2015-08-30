package hiro20v.lang;

class TestConstants {

    public static int U_MINUS = -1;// 範囲外

    public static int U0000 = 0x0000;// BMP始

    public static int U0001 = 0x0001;//

    public static int U0030 = 0x0030;// '0'
    public static int U0041 = 0x0041;// 'A'

    public static int UFE00 = 0xfe00;// VARIATION_SELECTORS始
    public static int UFE0F = 0xfe0f;// VARIATION_SELECTORS終

    public static int UFFFF = 0xffff;// BMP終

    public static int U10000 = 0x10000;// サロゲート始

    public static int UE0100 = 0xe0100;// VARIATION_SELECTORS_SUPPLEMENT始
    public static int UE01EF = 0xe01ef;// VARIATION_SELECTORS_SUPPLEMENT終

    public static int U1F3FB = 0x1f3fb;// EMOJI MODIFIER FITZPATRICK始
    public static int U1F3FF = 0x1f3ff;// EMOJI MODIFIER FITZPATRICK終

    public static int U10FFFF = 0x10ffff;// サロゲート終

    public static int U110000 = 0x110000;// 範囲外

    //

    public static String SAMPLE_U0041 = "A";
    public static String SAMPLE_U0301 = "\u0301";
    public static String SAMPLE_U0041_U0301 = SAMPLE_U0041 + SAMPLE_U0301;

    public static String SAMPLE_U00C1 = "Á";

    public static String SAMPLE_U3071 = "ぱ";

    public static String SAMPLE_U9089 = "邉";
    public static String SAMPLE_UE010F = "\uDB40\uDD0F";
    public static String SAMPLE_U9089_UE010F = SAMPLE_U9089 + SAMPLE_UE010F;

    public static String SAMPLE_U2229 = "∩";
    public static String SAMPLE_UFE00 = "\uFE00";
    public static String SAMPLE_U2229_UFE00 = SAMPLE_U2229 + SAMPLE_UFE00;

    public static String SAMPLE_UFB03 = "ﬃ";

    public static String SAMPLE_U0066 = "f";
    public static String SAMPLE_U0069 = "i";
    public static String SAMPLE_U0066_U0066_U0069 = SAMPLE_U0066 + SAMPLE_U0066 + SAMPLE_U0069;

    public static String SAMPLE_U611B = "愛";

    public static String SAMPLE_1F1EF = "\uD83C\uDDEF";// J
    public static String SAMPLE_1F1F5 = "\uD83C\uDDF5";// P
    public static String SAMPLE_1F1EF_1F1F5 = SAMPLE_1F1EF + SAMPLE_1F1F5;

    public static String SAMPLE_U306F = "は";
    public static String SAMPLE_U309A = "\u309A";
    public static String SAMPLE_U306F_U309A = SAMPLE_U306F + SAMPLE_U309A;

    public static String SAMPLE_U2F993 = "\ud87e\udd93";// "花";

    public static String SAMPLE_UE0001 = "\uDB40\uDC01"; // lang
    public static String SAMPLE_UE006A = "\uDB40\uDC6A"; // J
    public static String SAMPLE_UE0061 = "\uDB40\uDC61"; // A
    public static String SAMPLE_U5E73 = "平"; //
    public static String SAMPLE_UE007F = "\uDB40\uDC7F"; // cancel
    public static String SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F = SAMPLE_UE0001 + SAMPLE_UE006A + SAMPLE_UE0061 + SAMPLE_U5E73 + SAMPLE_UE007F;

    //

    public static String SAMPLE_U1F6AB = "🚫";

    public static String SAMPLE_U82B1 = "\u82b1";// "花";
    public static String SAMPLE_U82B2 = "\u82b2";// "芲";

    public static String SAMPLE_UE0100 = "\uDB40\uDD00";
    public static String SAMPLE_UE0101 = "\uDB40\uDD01";
    public static String SAMPLE_UE0110 = "\uDB40\uDD10";

    //

    public static String SAMPLE_STRING_VARIATION = "芦田さんは芦\uDB40\uDD01屋のお嬢様だ";
}
