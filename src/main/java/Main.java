import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements NativeKeyListener {
    public static void main(String[] args) {
        try {
            // 關閉 JNativeHook 的日誌輸出，避免過多訊息
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);

            GlobalScreen.registerNativeHook(); // 註冊全域鍵盤監聽
            GlobalScreen.addNativeKeyListener(new Main());

            System.out.println("鍵盤監聽已啟動，請嘗試按下鍵盤按鍵...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        // Toolkit.getDefaultToolkit().beep(); // 發出 Beep 聲
        playSound("sounds/194797.wav"); // 修改為你的音效檔案
    }

    public void playSound(String soundFile) {
        try {
            // 使用 ClassLoader 取得資源
            InputStream audioSrc = Main.class.getClassLoader().getResourceAsStream(soundFile);
            if (audioSrc == null) {
                throw new IllegalArgumentException("音效檔案未找到: " + soundFile);
            }

            // 轉換成 AudioInputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {}

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}
}
