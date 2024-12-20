package com.relaxingleg.commands;

import org.apache.commons.codec.language.bm.Lang;

public enum Language {
    Afar("aa", "Afar"),
    Abkhazian("ab", "Abkhazian"),
    Afrikaans("af", "Afrikaans"),
    Akan("ak", "Akan"),
    Amharic("am", "Amharic"),
    Arabic("ar", "Arabic"),
    Assamese("as", "Assamese"),
    Aymara("ay", "Aymara"),
    Azerbaijani("az", "Azerbaijani"),
    Bashkir("ba", "Bashkir"),
    Belarusian("be", "Belarusian"),
    Bulgarian("bg", "Bulgarian"),
    Bihari("bh", "Bihari"),
    Bislama("bi", "Bislama"),
    Bengali("bn", "Bengali"),
    Tibetan("bo", "Tibetan"),
    Breton("br", "Breton"),
    Bosnian("bs", "Bosnian"),
    Buginese("bug", "Buginese"),
    Catalan("ca", "Catalan"),
    Cebuano("ceb", "Cebuano"),
    Cherokee("chr", "Cherokee"),
    Corsican("co", "Corsican"),
    Seselwa("crs", "Seselwa"),
    Czech("cs", "Czech"),
    Welsh("cy", "Welsh"),
    Danish("da", "Danish"),
    German("de", "German"),
    Dhivehi("dv", "Dhivehi"),
    Dzongkha("dz", "Dzongkha"),
    Egyptian("egy", "Egyptian"),
    Greek("el", "Greek"),
    English("en", "English"),
    Esperanto("eo", "Esperanto"),
    Spanish("es", "Spanish"),
    Estonian("et", "Estonian"),
    Basque("eu", "Basque"),
    Persian("fa", "Persian"),
    Finnish("fi", "Finnish"),
    Fijian("fj", "Fijian"),
    Faroese("fo", "Faroese"),
    French("fr", "French"),
    Frisian("fy", "Frisian"),
    Irish("ga", "Irish"),
    ScotsGaelic("gd", "Scots Gaelic"),
    Galician("gl", "Galician"),
    Guarani("gn", "Guarani"),
    Gothic("got", "Gothic"),
    Gujarati("gu", "Gujarati"),
    Manx("gv", "Manx"),
    Hausa("ha", "Hausa"),
    Hawaiian("haw", "Hawaiian"),
    Hindi("hi", "Hindi"),
    Hmong("hmn", "Hmong"),
    Croatian("hr", "Croatian"),
    HaitianCreole("ht", "Haitian Creole"),
    Hungarian("hu", "Hungarian"),
    Armenian("hy", "Armenian"),
    Interlingua("ia", "Interlingua"),
    Indonesian("id", "Indonesian"),
    Interlingue("ie", "Interlingue"),
    Igbo("ig", "Igbo"),
    Inupiak("ik", "Inupiak"),
    Icelandic("is", "Icelandic"),
    Italian("it", "Italian"),
    Inuktitut("iu", "Inuktitut"),
    Hebrew("iw", "Hebrew"),
    Japanese("ja", "Japanese"),
    Javanese("jw", "Javanese"),
    Georgian("ka", "Georgian"),
    Khasi("kha", "Khasi"),
    Kazakh("kk", "Kazakh"),
    Greenlandic("kl", "Greenlandic"),
    Khmer("km", "Khmer"),
    Kannada("kn", "Kannada"),
    Korean("ko", "Korean"),
    Kashmiri("ks", "Kashmiri"),
    Kurdish("ku", "Kurdish"),
    Kyrgyz("ky", "Kyrgyz"),
    Latin("la", "Latin"),
    Luxembourgish("lb", "Luxembourgish"),
    Ganda("lg", "Ganda"),
    Limbu("lif", "Limbu"),
    Lingala("ln", "Lingala"),
    Laothian("lo", "Laothian"),
    Lithuanian("lt", "Lithuanian"),
    Latvian("lv", "Latvian"),
    MauritianCreole("mfe", "Mauritian Creole"),
    Malagasy("mg", "Malagasy"),
    Maori("mi", "Maori"),
    Macedonian("mk", "Macedonian"),
    Malayalam("ml", "Malayalam"),
    Mongolian("mn", "Mongolian"),
    Marathi("mr", "Marathi"),
    Malay("ms", "Malay"),
    Maltese("mt", "Maltese"),
    Burmese("my", "Burmese"),
    Nauru("na", "Nauru"),
    Nepali("ne", "Nepali"),
    Dutch("nl", "Dutch"),
    Norwegian("no", "Norwegian"),
    Ndebele("nr", "Ndebele"),
    Pedi("nso", "Pedi"),
    Nyanja("ny", "Nyanja"),
    Occitan("oc", "Occitan"),
    Oromo("om", "Oromo"),
    Oriya("or", "Oriya"),
    Punjabi("pa", "Punjabi"),
    Polish("pl", "Polish"),
    Pashto("ps", "Pashto"),
    Portuguese("pt", "Portuguese"),
    Quechua("qu", "Quechua"),
    RhaetoRomance("rm", "Rhaeto Romance"),
    Rundi("rn", "Rundi"),
    Romanian("ro", "Romanian"),
    Russian("ru", "Russian"),
    Kinyarwanda("rw", "Kinyarwanda"),
    Sanskrit("sa", "Sanskrit"),
    Scots("sco", "Scots"),
    Sindhi("sd", "Sindhi"),
    Sango("sg", "Sango"),
    Sinhalese("si", "Sinhalese"),
    Slovak("sk", "Slovak"),
    Slovenian("sl", "Slovenian"),
    Samoan("sm", "Samoan"),
    Shona("sn", "Shona"),
    Somali("so", "Somali"),
    Albanian("sq", "Albanian"),
    Serbian("sr", "Serbian"),
    Siswant("ss", "Siswant"),
    Sesotho("st", "Sesotho"),
    Sundanese("su", "Sundanese"),
    Swedish("sv", "Swedish"),
    Swahili("sw", "Swahili"),
    Syriac("syr", "Syriac"),
    Tamil("ta", "Tamil"),
    Telugu("te", "Telugu"),
    Tajik("tg", "Tajik"),
    Thai("th", "Thai"),
    Tigrinya("ti", "Tigrinya"),
    Turkmen("tk", "Turkmen"),
    Tagalog("tl", "Tagalog"),
    Klingon("tlh", "Klingon"),
    Tswana("tn", "Tswana"),
    Tonga("to", "Tonga"),
    Turkish("tr", "Turkish"),
    Tsonga("ts", "Tsonga"),
    Tatar("tt", "Tatar"),
    Uighur("ug", "Uighur"),
    Ukrainian("uk", "Ukrainian"),
    Urdu("ur", "Urdu"),
    Uzbek("uz", "Uzbek"),
    Venda("ve", "Venda"),
    Vietnamese("vi", "Vietnamese"),
    Volapuk("vo", "Volapuk"),
    WarayPhilippines("war", "Waray Philippines"),
    Wolof("wo", "Wolof"),
    Xhosa("xh", "Xhosa"),
    Yiddish("yi", "Yiddish"),
    Yoruba("yo", "Yoruba"),
    Zhuang("za", "Zhuang"),
    ChineseSimplified("zh", "Chinese Simplified"),
    ChineseTraditional("zh-Hant", "Chinese Traditional"),
    Zulu("zu", "Zulu");

    String code;
    String language;

    Language(String code, String language) {
        this.code = code;
        this.language = language;
    }

    public static String obtenerValorPorClave(String clave) {
        for (Language idioma : Language.values()) {
            if (idioma.code.equals(clave)) {
                return idioma.language;
            }
        }
        return null;
    }
}
