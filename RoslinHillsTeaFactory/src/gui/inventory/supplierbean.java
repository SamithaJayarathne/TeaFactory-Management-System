/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.inventory;

/**
 *
 * @author Sahan
 */
public class supplierbean {
    private String nic;
    private String firstName;
   
    private String mobile;
    private String email;

    private String city;

    public supplierbean(String nic, String firstName,  String mobile, String email, String city) {
        this.nic = nic;
        this.firstName = firstName;
       
        this.mobile = mobile;
        this.email = email;
       
        this.city = city;
    }

    // Getters
    public String getNic() { return nic; }
    public String getFirstName() { return firstName; }
  
    public String getMobile() { return mobile; }
    public String getEmail() { return email; }
   
    public String getCity() { return city; }
}
