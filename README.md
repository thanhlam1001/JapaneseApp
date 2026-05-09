# Kana Master

Ung dung Android de hoc bang chu cai Hiragana va Katakana bang Kotlin + Jetpack Compose.

## Tinh nang

- Chuyen doi giua `Hiragana` va `Katakana`
- Hoc theo 3 nhom: `Co ban`, `Dakuten`, `Youon`
- Hoc tung ky tu theo dang the
- Xem toan bo danh sach ky tu de nhay nhanh
- Quiz theo nhom dang hoc de luyen nho romaji va mat chu
- Giao dien toi uu cho dien thoai

## Cau truc

- `app/src/main/java/com/example/japanesealphabet/data/KanaRepository.kt`: du lieu kana va phan nhom
- `app/src/main/java/com/example/japanesealphabet/ui/KanaLearningApp.kt`: giao dien hoc, bo loc nhom va quiz
- `app/src/main/java/com/example/japanesealphabet/MainActivity.kt`: diem vao app

## Mo project

1. Mo thu muc nay bang Android Studio.
2. Cho Gradle sync dependencies.
3. Chay app tren emulator hoac may Android.

## Cach build nhanh nhat neu may khong chay duoc Android Studio

Cach nhanh nhat la build tren GitHub Actions, khong can cai Android Studio, JDK hay Android SDK tren may.

1. Tao repo moi tren GitHub.
2. Day toan bo thu muc project nay len repo do.
3. Mo tab `Actions` tren GitHub.
4. Chon workflow `Android Build`.
5. Bam `Run workflow`.
6. Doi workflow chay xong, tai file `app-debug-apk` ve.
7. Giai nen artifact va lay file `app-debug.apk`.

Neu ban co dien thoai Android va da bat cho cai file APK tu nguon ngoai, ban co the copy `app-debug.apk` vao may va cai truc tiep.

## Luu y

- Workspace hien tai khong co `gradle` hay `gradlew`, nen chua the build/verify truc tiep trong terminal nay.
- Neu ban muon, minh co the tiep tuc bo sung audio phat am, viet tay theo net, luu tien do hoc hoac flashcard ngau nhien.
