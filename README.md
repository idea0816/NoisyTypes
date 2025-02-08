# NoisyTypes

2025.02.05  雖然在網路上有找到許多不管是免費或付費的鍵盤敲擊聲的程式、但還是想要自己從頭寫一個看看囉！

2025.02.06  
1.  使用 JNativeHook（全域鍵盤監聽，適用於背景執行）。
2.  我在 [Freesound](http://example.com/ "Title") 中申請免費會員、並下載了一個鍵盤敲擊聲。

在 IntelliJ IDEA 中：
1.	右鍵 src/main/resources → Mark Directory as → Resources Root（標記為資源根目錄）。
2.	確保 sounds/my_sound.wav 出現在 target/classes/sounds/（當編譯）。

為什麼不能用 new File("resources/sounds/my_sound.wav")？
1.  resources 目錄在打包後（如 JAR 檔案）不會以檔案系統存在，而是內嵌在 Classpath 裡。
2.  getResourceAsStream() 可以確保即使你的應用程式被打包成 JAR 也能正常讀取資源。