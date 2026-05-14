package models;

import com.poiji.annotation.ExcelCellName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationData {

    @ExcelCellName("ID")
    public String id;

    @ExcelCellName("Name")
    public String name;

    @ExcelCellName("Email")
    public String email;

    @ExcelCellName("Password")
    public String password;

    @ExcelCellName("First Name")
    public String firstName;

    @ExcelCellName("Company")
    public String company;

    @ExcelCellName("Last Name")
    public String lastName;

    @ExcelCellName("Address")
    public String address;

    @ExcelCellName("Country")
    public String country;

    @ExcelCellName("State")
    public String state;

    @ExcelCellName("City")
    public String city;

    @ExcelCellName("Zipcode")
    public String zipcode;

    @ExcelCellName("Mobile Number")
    public String mobileNumber;

    @ExcelCellName("Address 2")
    public String address2;
}
