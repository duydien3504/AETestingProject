package utils;

import com.poiji.bind.Poiji;
import java.io.File;
import java.util.List;

public class ExcelUtils {
    public static <T> List<T> getTestData(String fileName, Class<T> clazz) {
        String filePath = "src/main/resources/testdata/" + fileName;
        return Poiji.fromExcel(new File(filePath), clazz);
    }
}
