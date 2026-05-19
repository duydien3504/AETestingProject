package utils;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class FileUtils {
    public static void uploadFile(Page page, String selector, String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            page.setInputFiles(selector, Paths.get(filePath));
        }
    }
}
