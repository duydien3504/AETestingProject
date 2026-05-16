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
public class LoginData {
    @ExcelCellName("ID")
    public String id;

    @ExcelCellName("Email Address")
    public String emailAddress;

    @ExcelCellName("Password")
    public String password;
}
