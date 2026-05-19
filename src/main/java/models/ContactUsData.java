package models;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactUsData {
    @ExcelCellName("Test Case ID")
    public String id;

    @ExcelCellName("Name")
    public String name;

    @ExcelCellName("Email")
    public String email;

    @ExcelCellName("Subject")
    public String subject;

    @ExcelCellName("Message")
    public String message;

    @ExcelCellName("File Upload")
    public String fileUpload;

    @ExcelCellName("Expected Result")
    public String expectedResult;
}
