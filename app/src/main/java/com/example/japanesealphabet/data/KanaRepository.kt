package com.example.japanesealphabet.data

enum class KanaScript(val title: String, val subtitle: String) {
    HIRAGANA("Hiragana", "Dung cho tu thuan Nhat va ngu phap"),
    KATAKANA("Katakana", "Dung cho tu muon va nhan manh")
}

enum class KanaCategory(val title: String, val description: String) {
    BASIC("Co ban", "47 ky tu nen tang"),
    DAKUTEN("Dakuten", "Am bien doi voi dau hai cham va tron"),
    YOUON("Youon", "Am ghep nho nhu kya, shu, cho")
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
        KanaItem("あ", "a", "A", "asa - buoi sang", KanaCategory.BASIC),
        KanaItem("い", "i", "A", "inu - con cho", KanaCategory.BASIC),
        KanaItem("う", "u", "A", "umi - bien", KanaCategory.BASIC),
        KanaItem("え", "e", "A", "eki - nha ga", KanaCategory.BASIC),
        KanaItem("お", "o", "A", "ocha - tra", KanaCategory.BASIC),
        KanaItem("か", "ka", "K", "kasa - o", KanaCategory.BASIC),
        KanaItem("き", "ki", "K", "kita - phia bac", KanaCategory.BASIC),
        KanaItem("く", "ku", "K", "kumo - may", KanaCategory.BASIC),
        KanaItem("け", "ke", "K", "kemuri - khoi", KanaCategory.BASIC),
        KanaItem("こ", "ko", "K", "koe - giong noi", KanaCategory.BASIC),
        KanaItem("さ", "sa", "S", "sakana - ca", KanaCategory.BASIC),
        KanaItem("し", "shi", "S", "shio - muoi", KanaCategory.BASIC),
        KanaItem("す", "su", "S", "sushi - sushi", KanaCategory.BASIC),
        KanaItem("せ", "se", "S", "sekai - the gioi", KanaCategory.BASIC),
        KanaItem("そ", "so", "S", "sora - bau troi", KanaCategory.BASIC),
        KanaItem("た", "ta", "T", "tamago - trung", KanaCategory.BASIC),
        KanaItem("ち", "chi", "T", "chizu - ban do", KanaCategory.BASIC),
        KanaItem("つ", "tsu", "T", "tsuki - mat trang", KanaCategory.BASIC),
        KanaItem("て", "te", "T", "te - ban tay", KanaCategory.BASIC),
        KanaItem("と", "to", "T", "tori - chim", KanaCategory.BASIC),
        KanaItem("な", "na", "N", "natsu - mua he", KanaCategory.BASIC),
        KanaItem("に", "ni", "N", "niku - thit", KanaCategory.BASIC),
        KanaItem("ぬ", "nu", "N", "inu - con cho", KanaCategory.BASIC),
        KanaItem("ね", "ne", "N", "neko - meo", KanaCategory.BASIC),
        KanaItem("の", "no", "N", "nori - rong bien", KanaCategory.BASIC),
        KanaItem("は", "ha", "H", "hana - hoa", KanaCategory.BASIC),
        KanaItem("ひ", "hi", "H", "hito - nguoi", KanaCategory.BASIC),
        KanaItem("ふ", "fu", "H", "fune - thuyen", KanaCategory.BASIC),
        KanaItem("へ", "he", "H", "heya - can phong", KanaCategory.BASIC),
        KanaItem("ほ", "ho", "H", "hoshi - ngoi sao", KanaCategory.BASIC),
        KanaItem("ま", "ma", "M", "mado - cua so", KanaCategory.BASIC),
        KanaItem("み", "mi", "M", "mizu - nuoc", KanaCategory.BASIC),
        KanaItem("む", "mu", "M", "mushi - con trung", KanaCategory.BASIC),
        KanaItem("め", "me", "M", "me - mat", KanaCategory.BASIC),
        KanaItem("も", "mo", "M", "mori - rung", KanaCategory.BASIC),
        KanaItem("や", "ya", "Y", "yama - nui", KanaCategory.BASIC),
        KanaItem("ゆ", "yu", "Y", "yuki - tuyet", KanaCategory.BASIC),
        KanaItem("よ", "yo", "Y", "yoru - dem", KanaCategory.BASIC),
        KanaItem("ら", "ra", "R", "raion - su tu", KanaCategory.BASIC),
        KanaItem("り", "ri", "R", "ringo - tao", KanaCategory.BASIC),
        KanaItem("る", "ru", "R", "rusu - vang nha", KanaCategory.BASIC),
        KanaItem("れ", "re", "R", "rekishi - lich su", KanaCategory.BASIC),
        KanaItem("ろ", "ro", "R", "rosoku - nen", KanaCategory.BASIC),
        KanaItem("わ", "wa", "W", "wani - ca sau", KanaCategory.BASIC),
        KanaItem("を", "wo", "W", "o - tro tu tan ngu", KanaCategory.BASIC),
        KanaItem("ん", "n", "N'", "hon - sach", KanaCategory.BASIC)
    )

    private val hiraganaDakuten = listOf(
        KanaItem("が", "ga", "G", "gakkou - truong hoc", KanaCategory.DAKUTEN),
        KanaItem("ぎ", "gi", "G", "ginkou - ngan hang", KanaCategory.DAKUTEN),
        KanaItem("ぐ", "gu", "G", "guai - cu den", KanaCategory.DAKUTEN),
        KanaItem("げ", "ge", "G", "genki - khoe", KanaCategory.DAKUTEN),
        KanaItem("ご", "go", "G", "gohan - com", KanaCategory.DAKUTEN),
        KanaItem("ざ", "za", "Z", "zasshi - tap chi", KanaCategory.DAKUTEN),
        KanaItem("じ", "ji", "Z", "jikan - thoi gian", KanaCategory.DAKUTEN),
        KanaItem("ず", "zu", "Z", "mizu - nuoc", KanaCategory.DAKUTEN),
        KanaItem("ぜ", "ze", "Z", "zenbu - tat ca", KanaCategory.DAKUTEN),
        KanaItem("ぞ", "zo", "Z", "zousan - con voi", KanaCategory.DAKUTEN),
        KanaItem("だ", "da", "D", "daigaku - dai hoc", KanaCategory.DAKUTEN),
        KanaItem("ぢ", "ji", "D", "chijimu - co lai", KanaCategory.DAKUTEN),
        KanaItem("づ", "zu", "D", "tsuzuku - tiep tuc", KanaCategory.DAKUTEN),
        KanaItem("で", "de", "D", "densha - tau dien", KanaCategory.DAKUTEN),
        KanaItem("ど", "do", "D", "doa - cua", KanaCategory.DAKUTEN),
        KanaItem("ば", "ba", "B", "banana - chuoi", KanaCategory.DAKUTEN),
        KanaItem("び", "bi", "B", "biru - toa nha", KanaCategory.DAKUTEN),
        KanaItem("ぶ", "bu", "B", "buta - lon", KanaCategory.DAKUTEN),
        KanaItem("べ", "be", "B", "benri - tien loi", KanaCategory.DAKUTEN),
        KanaItem("ぼ", "bo", "B", "boushi - mu", KanaCategory.DAKUTEN),
        KanaItem("ぱ", "pa", "P", "pan - banh mi", KanaCategory.DAKUTEN),
        KanaItem("ぴ", "pi", "P", "piano - dan piano", KanaCategory.DAKUTEN),
        KanaItem("ぷ", "pu", "P", "purin - banh flan", KanaCategory.DAKUTEN),
        KanaItem("ぺ", "pe", "P", "pen - but", KanaCategory.DAKUTEN),
        KanaItem("ぽ", "po", "P", "poteto - khoai tay", KanaCategory.DAKUTEN)
    )

    private val hiraganaYouon = listOf(
        KanaItem("きゃ", "kya", "KY", "kyaku - khach", KanaCategory.YOUON),
        KanaItem("きゅ", "kyu", "KY", "kyuuri - dua leo", KanaCategory.YOUON),
        KanaItem("きょ", "kyo", "KY", "kyou - hom nay", KanaCategory.YOUON),
        KanaItem("しゃ", "sha", "SH", "shashin - anh", KanaCategory.YOUON),
        KanaItem("しゅ", "shu", "SH", "shukudai - bai tap", KanaCategory.YOUON),
        KanaItem("しょ", "sho", "SH", "shokudou - nha an", KanaCategory.YOUON),
        KanaItem("ちゃ", "cha", "CH", "cha - tra", KanaCategory.YOUON),
        KanaItem("ちゅ", "chu", "CH", "chuumon - goi mon", KanaCategory.YOUON),
        KanaItem("ちょ", "cho", "CH", "chotto - mot chut", KanaCategory.YOUON),
        KanaItem("にゃ", "nya", "NY", "nyanko - meo", KanaCategory.YOUON),
        KanaItem("にゅ", "nyu", "NY", "nyuusu - tin tuc", KanaCategory.YOUON),
        KanaItem("にょ", "nyo", "NY", "nyorai - Nhu Lai", KanaCategory.YOUON),
        KanaItem("ひゃ", "hya", "HY", "hyaku - mot tram", KanaCategory.YOUON),
        KanaItem("ひゅ", "hyu", "HY", "hyuuman - con nguoi", KanaCategory.YOUON),
        KanaItem("ひょ", "hyo", "HY", "hyou - bang bieu", KanaCategory.YOUON),
        KanaItem("みゃ", "mya", "MY", "myaku - mach", KanaCategory.YOUON),
        KanaItem("みゅ", "myu", "MY", "myuujikku - am nhac", KanaCategory.YOUON),
        KanaItem("みょ", "myo", "MY", "myouji - ho", KanaCategory.YOUON),
        KanaItem("りゃ", "rya", "RY", "ryakusu - luoc bo", KanaCategory.YOUON),
        KanaItem("りゅ", "ryu", "RY", "ryuugaku - du hoc", KanaCategory.YOUON),
        KanaItem("りょ", "ryo", "RY", "ryokou - du lich", KanaCategory.YOUON)
    )

    private val katakanaBasic = listOf(
        KanaItem("ア", "a", "A", "aisu - kem", KanaCategory.BASIC),
        KanaItem("イ", "i", "A", "inu - cho", KanaCategory.BASIC),
        KanaItem("ウ", "u", "A", "uni - nhim bien", KanaCategory.BASIC),
        KanaItem("エ", "e", "A", "ea - khong khi", KanaCategory.BASIC),
        KanaItem("オ", "o", "A", "orenji - cam", KanaCategory.BASIC),
        KanaItem("カ", "ka", "K", "kamera - may anh", KanaCategory.BASIC),
        KanaItem("キ", "ki", "K", "kiwi - kiwi", KanaCategory.BASIC),
        KanaItem("ク", "ku", "K", "kurasu - lop hoc", KanaCategory.BASIC),
        KanaItem("ケ", "ke", "K", "keeki - banh ngot", KanaCategory.BASIC),
        KanaItem("コ", "ko", "K", "koohii - ca phe", KanaCategory.BASIC),
        KanaItem("サ", "sa", "S", "sarada - salad", KanaCategory.BASIC),
        KanaItem("シ", "shi", "S", "shatsu - ao so mi", KanaCategory.BASIC),
        KanaItem("ス", "su", "S", "suupu - sup", KanaCategory.BASIC),
        KanaItem("セ", "se", "S", "seetaa - ao len", KanaCategory.BASIC),
        KanaItem("ソ", "so", "S", "sofa - sofa", KanaCategory.BASIC),
        KanaItem("タ", "ta", "T", "takushii - taxi", KanaCategory.BASIC),
        KanaItem("チ", "chi", "T", "chiizu - pho mai", KanaCategory.BASIC),
        KanaItem("ツ", "tsu", "T", "tsuaa - chuyen tour", KanaCategory.BASIC),
        KanaItem("テ", "te", "T", "tesuto - bai kiem tra", KanaCategory.BASIC),
        KanaItem("ト", "to", "T", "tomato - ca chua", KanaCategory.BASIC),
        KanaItem("ナ", "na", "N", "naifu - dao", KanaCategory.BASIC),
        KanaItem("ニ", "ni", "N", "nyuusu - tin tuc", KanaCategory.BASIC),
        KanaItem("ヌ", "nu", "N", "nuudoru - mi", KanaCategory.BASIC),
        KanaItem("ネ", "ne", "N", "nekutai - ca vat", KanaCategory.BASIC),
        KanaItem("ノ", "no", "N", "nooto - vo", KanaCategory.BASIC),
        KanaItem("ハ", "ha", "H", "hanbaagaa - burger", KanaCategory.BASIC),
        KanaItem("ヒ", "hi", "H", "hinto - goi y", KanaCategory.BASIC),
        KanaItem("フ", "fu", "H", "furuutsu - trai cay", KanaCategory.BASIC),
        KanaItem("ヘ", "he", "H", "herumetto - mu bao hiem", KanaCategory.BASIC),
        KanaItem("ホ", "ho", "H", "hoteru - khach san", KanaCategory.BASIC),
        KanaItem("マ", "ma", "M", "masuku - khau trang", KanaCategory.BASIC),
        KanaItem("ミ", "mi", "M", "miruku - sua", KanaCategory.BASIC),
        KanaItem("ム", "mu", "M", "muubii - phim", KanaCategory.BASIC),
        KanaItem("メ", "me", "M", "memo - ghi chu", KanaCategory.BASIC),
        KanaItem("モ", "mo", "M", "moderu - nguoi mau", KanaCategory.BASIC),
        KanaItem("ヤ", "ya", "Y", "yakuruto - Yakult", KanaCategory.BASIC),
        KanaItem("ユ", "yu", "Y", "yunifoomu - dong phuc", KanaCategory.BASIC),
        KanaItem("ヨ", "yo", "Y", "yoga - yoga", KanaCategory.BASIC),
        KanaItem("ラ", "ra", "R", "rajio - radio", KanaCategory.BASIC),
        KanaItem("リ", "ri", "R", "rizumu - nhip dieu", KanaCategory.BASIC),
        KanaItem("ル", "ru", "R", "ruuru - luat le", KanaCategory.BASIC),
        KanaItem("レ", "re", "R", "remon - chanh", KanaCategory.BASIC),
        KanaItem("ロ", "ro", "R", "robotto - robot", KanaCategory.BASIC),
        KanaItem("ワ", "wa", "W", "wain - ruou vang", KanaCategory.BASIC),
        KanaItem("ヲ", "wo", "W", "wo - rat hiem", KanaCategory.BASIC),
        KanaItem("ン", "n", "N'", "pan - banh mi", KanaCategory.BASIC)
    )

    private val katakanaDakuten = listOf(
        KanaItem("ガ", "ga", "G", "gaaru - co gai", KanaCategory.DAKUTEN),
        KanaItem("ギ", "gi", "G", "gitaa - guitar", KanaCategory.DAKUTEN),
        KanaItem("グ", "gu", "G", "guramu - gram", KanaCategory.DAKUTEN),
        KanaItem("ゲ", "ge", "G", "geemu - game", KanaCategory.DAKUTEN),
        KanaItem("ゴ", "go", "G", "gooru - goal", KanaCategory.DAKUTEN),
        KanaItem("ザ", "za", "Z", "zasshi - tap chi", KanaCategory.DAKUTEN),
        KanaItem("ジ", "ji", "Z", "jyuusu - nuoc ep", KanaCategory.DAKUTEN),
        KanaItem("ズ", "zu", "Z", "zuumu - zoom", KanaCategory.DAKUTEN),
        KanaItem("ゼ", "ze", "Z", "zero - so khong", KanaCategory.DAKUTEN),
        KanaItem("ゾ", "zo", "Z", "zoo - so thu", KanaCategory.DAKUTEN),
        KanaItem("ダ", "da", "D", "daiya - kim cuong", KanaCategory.DAKUTEN),
        KanaItem("ヂ", "ji", "D", "rare in modern katakana", KanaCategory.DAKUTEN),
        KanaItem("ヅ", "zu", "D", "rare in modern katakana", KanaCategory.DAKUTEN),
        KanaItem("デ", "de", "D", "deeto - hen ho", KanaCategory.DAKUTEN),
        KanaItem("ド", "do", "D", "doa - cua", KanaCategory.DAKUTEN),
        KanaItem("バ", "ba", "B", "basu - xe buyt", KanaCategory.DAKUTEN),
        KanaItem("ビ", "bi", "B", "biiru - bia", KanaCategory.DAKUTEN),
        KanaItem("ブ", "bu", "B", "burashi - ban chai", KanaCategory.DAKUTEN),
        KanaItem("ベ", "be", "B", "beddo - giuong", KanaCategory.DAKUTEN),
        KanaItem("ボ", "bo", "B", "borupen - but bi", KanaCategory.DAKUTEN),
        KanaItem("パ", "pa", "P", "pasupooto - ho chieu", KanaCategory.DAKUTEN),
        KanaItem("ピ", "pi", "P", "piza - pizza", KanaCategory.DAKUTEN),
        KanaItem("プ", "pu", "P", "puro - chuyen nghiep", KanaCategory.DAKUTEN),
        KanaItem("ペ", "pe", "P", "pen - but", KanaCategory.DAKUTEN),
        KanaItem("ポ", "po", "P", "poketto - tui ao", KanaCategory.DAKUTEN)
    )

    private val katakanaYouon = listOf(
        KanaItem("キャ", "kya", "KY", "kyanpu - cam trai", KanaCategory.YOUON),
        KanaItem("キュ", "kyu", "KY", "kyuuto - de thuong", KanaCategory.YOUON),
        KanaItem("キョ", "kyo", "KY", "kyori - khoang cach", KanaCategory.YOUON),
        KanaItem("シャ", "sha", "SH", "shanpuu - dau goi", KanaCategory.YOUON),
        KanaItem("シュ", "shu", "SH", "shuuzu - giay", KanaCategory.YOUON),
        KanaItem("ショ", "sho", "SH", "shoppu - cua hang", KanaCategory.YOUON),
        KanaItem("チャ", "cha", "CH", "chansu - co hoi", KanaCategory.YOUON),
        KanaItem("チュ", "chu", "CH", "chuubu - trung tam", KanaCategory.YOUON),
        KanaItem("チョ", "cho", "CH", "chooko - socola", KanaCategory.YOUON),
        KanaItem("ニャ", "nya", "NY", "nyansu - meo anime", KanaCategory.YOUON),
        KanaItem("ニュ", "nyu", "NY", "nyuusu - tin tuc", KanaCategory.YOUON),
        KanaItem("ニョ", "nyo", "NY", "nyoron - mem mai", KanaCategory.YOUON),
        KanaItem("ヒャ", "hya", "HY", "hyaku - mot tram", KanaCategory.YOUON),
        KanaItem("ヒュ", "hyu", "HY", "hyuuman - human", KanaCategory.YOUON),
        KanaItem("ヒョ", "hyo", "HY", "hyou - bang", KanaCategory.YOUON),
        KanaItem("ミャ", "mya", "MY", "myaku - mach", KanaCategory.YOUON),
        KanaItem("ミュ", "myu", "MY", "myuujiamu - bao tang", KanaCategory.YOUON),
        KanaItem("ミョ", "myo", "MY", "myouji - ho", KanaCategory.YOUON),
        KanaItem("リャ", "rya", "RY", "ryaku - tom tat", KanaCategory.YOUON),
        KanaItem("リュ", "ryu", "RY", "ryuukou - xu huong", KanaCategory.YOUON),
        KanaItem("リョ", "ryo", "RY", "ryokou - du lich", KanaCategory.YOUON)
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
