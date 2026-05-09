package com.example.japanesealphabet.data

enum class KanaScript(val title: String, val subtitle: String) {
    HIRAGANA("Hiragana", "Used for native Japanese words and grammar"),
    KATAKANA("Katakana", "Used for loanwords and emphasis")
}

enum class KanaCategory(val title: String, val description: String) {
    BASIC("Basic", "Foundation kana set"),
    DAKUTEN("Dakuten", "Voiced and semi-voiced sounds"),
    YOUON("Youon", "Contracted sounds like kya, shu, cho")
}

data class KanaItem(
    val symbol: String,
    val romaji: String,
    val row: String,
    val example: String,
    val category: KanaCategory
)

object KanaRepository {
    private val hiraganaBasic = listOf(
        KanaItem("あ", "a", "A", "asa - morning", KanaCategory.BASIC),
        KanaItem("い", "i", "A", "inu - dog", KanaCategory.BASIC),
        KanaItem("う", "u", "A", "umi - sea", KanaCategory.BASIC),
        KanaItem("え", "e", "A", "eki - station", KanaCategory.BASIC),
        KanaItem("お", "o", "A", "ocha - tea", KanaCategory.BASIC),
        KanaItem("か", "ka", "K", "kasa - umbrella", KanaCategory.BASIC),
        KanaItem("き", "ki", "K", "kita - north", KanaCategory.BASIC),
        KanaItem("く", "ku", "K", "kumo - cloud", KanaCategory.BASIC),
        KanaItem("け", "ke", "K", "kemuri - smoke", KanaCategory.BASIC),
        KanaItem("こ", "ko", "K", "koe - voice", KanaCategory.BASIC),
        KanaItem("さ", "sa", "S", "sakana - fish", KanaCategory.BASIC),
        KanaItem("し", "shi", "S", "shio - salt", KanaCategory.BASIC),
        KanaItem("す", "su", "S", "sushi - sushi", KanaCategory.BASIC),
        KanaItem("せ", "se", "S", "sekai - world", KanaCategory.BASIC),
        KanaItem("そ", "so", "S", "sora - sky", KanaCategory.BASIC),
        KanaItem("た", "ta", "T", "tamago - egg", KanaCategory.BASIC),
        KanaItem("ち", "chi", "T", "chizu - map", KanaCategory.BASIC),
        KanaItem("つ", "tsu", "T", "tsuki - moon", KanaCategory.BASIC),
        KanaItem("て", "te", "T", "te - hand", KanaCategory.BASIC),
        KanaItem("と", "to", "T", "tori - bird", KanaCategory.BASIC),
        KanaItem("な", "na", "N", "natsu - summer", KanaCategory.BASIC),
        KanaItem("に", "ni", "N", "niku - meat", KanaCategory.BASIC),
        KanaItem("ぬ", "nu", "N", "inu - dog", KanaCategory.BASIC),
        KanaItem("ね", "ne", "N", "neko - cat", KanaCategory.BASIC),
        KanaItem("の", "no", "N", "nori - seaweed", KanaCategory.BASIC),
        KanaItem("は", "ha", "H", "hana - flower", KanaCategory.BASIC),
        KanaItem("ひ", "hi", "H", "hito - person", KanaCategory.BASIC),
        KanaItem("ふ", "fu", "H", "fune - boat", KanaCategory.BASIC),
        KanaItem("へ", "he", "H", "heya - room", KanaCategory.BASIC),
        KanaItem("ほ", "ho", "H", "hoshi - star", KanaCategory.BASIC),
        KanaItem("ま", "ma", "M", "mado - window", KanaCategory.BASIC),
        KanaItem("み", "mi", "M", "mizu - water", KanaCategory.BASIC),
        KanaItem("む", "mu", "M", "mushi - insect", KanaCategory.BASIC),
        KanaItem("め", "me", "M", "me - eye", KanaCategory.BASIC),
        KanaItem("も", "mo", "M", "mori - forest", KanaCategory.BASIC),
        KanaItem("や", "ya", "Y", "yama - mountain", KanaCategory.BASIC),
        KanaItem("ゆ", "yu", "Y", "yuki - snow", KanaCategory.BASIC),
        KanaItem("よ", "yo", "Y", "yoru - night", KanaCategory.BASIC),
        KanaItem("ら", "ra", "R", "raion - lion", KanaCategory.BASIC),
        KanaItem("り", "ri", "R", "ringo - apple", KanaCategory.BASIC),
        KanaItem("る", "ru", "R", "rusu - away from home", KanaCategory.BASIC),
        KanaItem("れ", "re", "R", "rekishi - history", KanaCategory.BASIC),
        KanaItem("ろ", "ro", "R", "rousoku - candle", KanaCategory.BASIC),
        KanaItem("わ", "wa", "W", "wani - crocodile", KanaCategory.BASIC),
        KanaItem("を", "wo", "W", "wo - object marker", KanaCategory.BASIC),
        KanaItem("ん", "n", "N'", "hon - book", KanaCategory.BASIC)
    )

    private val hiraganaDakuten = listOf(
        KanaItem("が", "ga", "G", "gakkou - school", KanaCategory.DAKUTEN),
        KanaItem("ぎ", "gi", "G", "ginkou - bank", KanaCategory.DAKUTEN),
        KanaItem("ぐ", "gu", "G", "guai - rubble", KanaCategory.DAKUTEN),
        KanaItem("げ", "ge", "G", "genki - healthy", KanaCategory.DAKUTEN),
        KanaItem("ご", "go", "G", "gohan - meal", KanaCategory.DAKUTEN),
        KanaItem("ざ", "za", "Z", "zasshi - magazine", KanaCategory.DAKUTEN),
        KanaItem("じ", "ji", "Z", "jikan - time", KanaCategory.DAKUTEN),
        KanaItem("ず", "zu", "Z", "mizu - water", KanaCategory.DAKUTEN),
        KanaItem("ぜ", "ze", "Z", "zenbu - all", KanaCategory.DAKUTEN),
        KanaItem("ぞ", "zo", "Z", "zou - elephant", KanaCategory.DAKUTEN),
        KanaItem("だ", "da", "D", "daigaku - university", KanaCategory.DAKUTEN),
        KanaItem("ぢ", "ji", "D", "chijimu - shrink", KanaCategory.DAKUTEN),
        KanaItem("づ", "zu", "D", "tsuzuku - continue", KanaCategory.DAKUTEN),
        KanaItem("で", "de", "D", "densha - train", KanaCategory.DAKUTEN),
        KanaItem("ど", "do", "D", "doa - door", KanaCategory.DAKUTEN),
        KanaItem("ば", "ba", "B", "banana - banana", KanaCategory.DAKUTEN),
        KanaItem("び", "bi", "B", "biru - building", KanaCategory.DAKUTEN),
        KanaItem("ぶ", "bu", "B", "buta - pig", KanaCategory.DAKUTEN),
        KanaItem("べ", "be", "B", "benri - convenient", KanaCategory.DAKUTEN),
        KanaItem("ぼ", "bo", "B", "boushi - hat", KanaCategory.DAKUTEN),
        KanaItem("ぱ", "pa", "P", "pan - bread", KanaCategory.DAKUTEN),
        KanaItem("ぴ", "pi", "P", "piano - piano", KanaCategory.DAKUTEN),
        KanaItem("ぷ", "pu", "P", "purin - pudding", KanaCategory.DAKUTEN),
        KanaItem("ぺ", "pe", "P", "pen - pen", KanaCategory.DAKUTEN),
        KanaItem("ぽ", "po", "P", "poteto - potato", KanaCategory.DAKUTEN)
    )

    private val hiraganaYouon = listOf(
        KanaItem("きゃ", "kya", "KY", "kyaku - guest", KanaCategory.YOUON),
        KanaItem("きゅ", "kyu", "KY", "kyuuri - cucumber", KanaCategory.YOUON),
        KanaItem("きょ", "kyo", "KY", "kyou - today", KanaCategory.YOUON),
        KanaItem("しゃ", "sha", "SH", "shashin - photo", KanaCategory.YOUON),
        KanaItem("しゅ", "shu", "SH", "shukudai - homework", KanaCategory.YOUON),
        KanaItem("しょ", "sho", "SH", "shokudou - cafeteria", KanaCategory.YOUON),
        KanaItem("ちゃ", "cha", "CH", "cha - tea", KanaCategory.YOUON),
        KanaItem("ちゅ", "chu", "CH", "chuumon - order", KanaCategory.YOUON),
        KanaItem("ちょ", "cho", "CH", "chotto - a little", KanaCategory.YOUON),
        KanaItem("にゃ", "nya", "NY", "nyanko - kitty", KanaCategory.YOUON),
        KanaItem("にゅ", "nyu", "NY", "nyuusu - news", KanaCategory.YOUON),
        KanaItem("にょ", "nyo", "NY", "nyorai - tathagata", KanaCategory.YOUON),
        KanaItem("ひゃ", "hya", "HY", "hyaku - one hundred", KanaCategory.YOUON),
        KanaItem("ひゅ", "hyu", "HY", "hyuuman - human", KanaCategory.YOUON),
        KanaItem("ひょ", "hyo", "HY", "hyou - table", KanaCategory.YOUON),
        KanaItem("みゃ", "mya", "MY", "myaku - pulse", KanaCategory.YOUON),
        KanaItem("みゅ", "myu", "MY", "myuujikku - music", KanaCategory.YOUON),
        KanaItem("みょ", "myo", "MY", "myouji - surname", KanaCategory.YOUON),
        KanaItem("りゃ", "rya", "RY", "ryakusu - abbreviate", KanaCategory.YOUON),
        KanaItem("りゅ", "ryu", "RY", "ryuugaku - study abroad", KanaCategory.YOUON),
        KanaItem("りょ", "ryo", "RY", "ryokou - travel", KanaCategory.YOUON)
    )

    private val katakanaBasic = listOf(
        KanaItem("ア", "a", "A", "aisu - ice cream", KanaCategory.BASIC),
        KanaItem("イ", "i", "A", "inu - dog", KanaCategory.BASIC),
        KanaItem("ウ", "u", "A", "uni - sea urchin", KanaCategory.BASIC),
        KanaItem("エ", "e", "A", "ea - air", KanaCategory.BASIC),
        KanaItem("オ", "o", "A", "orenji - orange", KanaCategory.BASIC),
        KanaItem("カ", "ka", "K", "kamera - camera", KanaCategory.BASIC),
        KanaItem("キ", "ki", "K", "kiwi - kiwi", KanaCategory.BASIC),
        KanaItem("ク", "ku", "K", "kurasu - class", KanaCategory.BASIC),
        KanaItem("ケ", "ke", "K", "keeki - cake", KanaCategory.BASIC),
        KanaItem("コ", "ko", "K", "koohii - coffee", KanaCategory.BASIC),
        KanaItem("サ", "sa", "S", "sarada - salad", KanaCategory.BASIC),
        KanaItem("シ", "shi", "S", "shatsu - shirt", KanaCategory.BASIC),
        KanaItem("ス", "su", "S", "suupu - soup", KanaCategory.BASIC),
        KanaItem("セ", "se", "S", "seetaa - sweater", KanaCategory.BASIC),
        KanaItem("ソ", "so", "S", "sofa - sofa", KanaCategory.BASIC),
        KanaItem("タ", "ta", "T", "takushii - taxi", KanaCategory.BASIC),
        KanaItem("チ", "chi", "T", "chiizu - cheese", KanaCategory.BASIC),
        KanaItem("ツ", "tsu", "T", "tsuaa - tour", KanaCategory.BASIC),
        KanaItem("テ", "te", "T", "tesuto - test", KanaCategory.BASIC),
        KanaItem("ト", "to", "T", "tomato - tomato", KanaCategory.BASIC),
        KanaItem("ナ", "na", "N", "naifu - knife", KanaCategory.BASIC),
        KanaItem("ニ", "ni", "N", "nyuusu - news", KanaCategory.BASIC),
        KanaItem("ヌ", "nu", "N", "nuudoru - noodles", KanaCategory.BASIC),
        KanaItem("ネ", "ne", "N", "nekutai - necktie", KanaCategory.BASIC),
        KanaItem("ノ", "no", "N", "nooto - notebook", KanaCategory.BASIC),
        KanaItem("ハ", "ha", "H", "hanbaagaa - hamburger", KanaCategory.BASIC),
        KanaItem("ヒ", "hi", "H", "hinto - hint", KanaCategory.BASIC),
        KanaItem("フ", "fu", "H", "furuutsu - fruit", KanaCategory.BASIC),
        KanaItem("ヘ", "he", "H", "herumetto - helmet", KanaCategory.BASIC),
        KanaItem("ホ", "ho", "H", "hoteru - hotel", KanaCategory.BASIC),
        KanaItem("マ", "ma", "M", "masuku - mask", KanaCategory.BASIC),
        KanaItem("ミ", "mi", "M", "miruku - milk", KanaCategory.BASIC),
        KanaItem("ム", "mu", "M", "muubii - movie", KanaCategory.BASIC),
        KanaItem("メ", "me", "M", "memo - memo", KanaCategory.BASIC),
        KanaItem("モ", "mo", "M", "moderu - model", KanaCategory.BASIC),
        KanaItem("ヤ", "ya", "Y", "yakuruto - Yakult", KanaCategory.BASIC),
        KanaItem("ユ", "yu", "Y", "yunifoomu - uniform", KanaCategory.BASIC),
        KanaItem("ヨ", "yo", "Y", "yoga - yoga", KanaCategory.BASIC),
        KanaItem("ラ", "ra", "R", "rajio - radio", KanaCategory.BASIC),
        KanaItem("リ", "ri", "R", "rizumu - rhythm", KanaCategory.BASIC),
        KanaItem("ル", "ru", "R", "ruuru - rules", KanaCategory.BASIC),
        KanaItem("レ", "re", "R", "remon - lemon", KanaCategory.BASIC),
        KanaItem("ロ", "ro", "R", "robotto - robot", KanaCategory.BASIC),
        KanaItem("ワ", "wa", "W", "wain - wine", KanaCategory.BASIC),
        KanaItem("ヲ", "wo", "W", "wo - rare in modern Japanese", KanaCategory.BASIC),
        KanaItem("ン", "n", "N'", "pan - bread", KanaCategory.BASIC)
    )

    private val katakanaDakuten = listOf(
        KanaItem("ガ", "ga", "G", "gaaru - girl", KanaCategory.DAKUTEN),
        KanaItem("ギ", "gi", "G", "gitaa - guitar", KanaCategory.DAKUTEN),
        KanaItem("グ", "gu", "G", "guramu - gram", KanaCategory.DAKUTEN),
        KanaItem("ゲ", "ge", "G", "geemu - game", KanaCategory.DAKUTEN),
        KanaItem("ゴ", "go", "G", "gooru - goal", KanaCategory.DAKUTEN),
        KanaItem("ザ", "za", "Z", "zasshi - magazine", KanaCategory.DAKUTEN),
        KanaItem("ジ", "ji", "Z", "jyuusu - juice", KanaCategory.DAKUTEN),
        KanaItem("ズ", "zu", "Z", "zuumu - zoom", KanaCategory.DAKUTEN),
        KanaItem("ゼ", "ze", "Z", "zero - zero", KanaCategory.DAKUTEN),
        KanaItem("ゾ", "zo", "Z", "zoo - zoo", KanaCategory.DAKUTEN),
        KanaItem("ダ", "da", "D", "daiya - diamond", KanaCategory.DAKUTEN),
        KanaItem("ヂ", "ji", "D", "rare in modern katakana", KanaCategory.DAKUTEN),
        KanaItem("ヅ", "zu", "D", "rare in modern katakana", KanaCategory.DAKUTEN),
        KanaItem("デ", "de", "D", "deeto - date", KanaCategory.DAKUTEN),
        KanaItem("ド", "do", "D", "doa - door", KanaCategory.DAKUTEN),
        KanaItem("バ", "ba", "B", "basu - bus", KanaCategory.DAKUTEN),
        KanaItem("ビ", "bi", "B", "biiru - beer", KanaCategory.DAKUTEN),
        KanaItem("ブ", "bu", "B", "burashi - brush", KanaCategory.DAKUTEN),
        KanaItem("ベ", "be", "B", "beddo - bed", KanaCategory.DAKUTEN),
        KanaItem("ボ", "bo", "B", "borupen - ballpoint pen", KanaCategory.DAKUTEN),
        KanaItem("パ", "pa", "P", "pasupooto - passport", KanaCategory.DAKUTEN),
        KanaItem("ピ", "pi", "P", "piza - pizza", KanaCategory.DAKUTEN),
        KanaItem("プ", "pu", "P", "puro - professional", KanaCategory.DAKUTEN),
        KanaItem("ペ", "pe", "P", "pen - pen", KanaCategory.DAKUTEN),
        KanaItem("ポ", "po", "P", "poketto - pocket", KanaCategory.DAKUTEN)
    )

    private val katakanaYouon = listOf(
        KanaItem("キャ", "kya", "KY", "kyanpu - camp", KanaCategory.YOUON),
        KanaItem("キュ", "kyu", "KY", "kyuuto - cute", KanaCategory.YOUON),
        KanaItem("キョ", "kyo", "KY", "kyori - distance", KanaCategory.YOUON),
        KanaItem("シャ", "sha", "SH", "shanpuu - shampoo", KanaCategory.YOUON),
        KanaItem("シュ", "shu", "SH", "shuuzu - shoes", KanaCategory.YOUON),
        KanaItem("ショ", "sho", "SH", "shoppu - shop", KanaCategory.YOUON),
        KanaItem("チャ", "cha", "CH", "chansu - chance", KanaCategory.YOUON),
        KanaItem("チュ", "chu", "CH", "chuubu - central region", KanaCategory.YOUON),
        KanaItem("チョ", "cho", "CH", "chooko - chocolate", KanaCategory.YOUON),
        KanaItem("ニャ", "nya", "NY", "nyansu - cat-like sound", KanaCategory.YOUON),
        KanaItem("ニュ", "nyu", "NY", "nyuusu - news", KanaCategory.YOUON),
        KanaItem("ニョ", "nyo", "NY", "nyoron - soft bend", KanaCategory.YOUON),
        KanaItem("ヒャ", "hya", "HY", "hyaku - one hundred", KanaCategory.YOUON),
        KanaItem("ヒュ", "hyu", "HY", "hyuuman - human", KanaCategory.YOUON),
        KanaItem("ヒョ", "hyo", "HY", "hyou - chart", KanaCategory.YOUON),
        KanaItem("ミャ", "mya", "MY", "myaku - pulse", KanaCategory.YOUON),
        KanaItem("ミュ", "myu", "MY", "myuujiamu - museum", KanaCategory.YOUON),
        KanaItem("ミョ", "myo", "MY", "myouji - surname", KanaCategory.YOUON),
        KanaItem("リャ", "rya", "RY", "ryaku - abbreviation", KanaCategory.YOUON),
        KanaItem("リュ", "ryu", "RY", "ryuukou - trend", KanaCategory.YOUON),
        KanaItem("リョ", "ryo", "RY", "ryokou - travel", KanaCategory.YOUON)
    )

    private val kanaMap = mapOf(
        KanaScript.HIRAGANA to mapOf(
            KanaCategory.BASIC to hiraganaBasic,
            KanaCategory.DAKUTEN to hiraganaDakuten,
            KanaCategory.YOUON to hiraganaYouon
        ),
        KanaScript.KATAKANA to mapOf(
            KanaCategory.BASIC to katakanaBasic,
            KanaCategory.DAKUTEN to katakanaDakuten,
            KanaCategory.YOUON to katakanaYouon
        )
    )

    fun byScript(script: KanaScript): List<KanaItem> =
        KanaCategory.entries.flatMap { byScriptAndCategory(script, it) }

    fun byScriptAndCategory(script: KanaScript, category: KanaCategory): List<KanaItem> =
        kanaMap[script]?.get(category).orEmpty()

    fun availableCategories(script: KanaScript): List<KanaCategory> =
        KanaCategory.entries.filter { byScriptAndCategory(script, it).isNotEmpty() }
}
