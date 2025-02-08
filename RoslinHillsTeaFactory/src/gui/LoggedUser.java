/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

public class LoggedUser {
    
    private static String fname;
    private static String lname;
    private static String email;
    private static String department;

    /**
     * @return the fname
     */
    public static String getFname() {
        return fname;
    }

    /**
     * @param aFname the fname to set
     */
    public static void setFname(String aFname) {
        fname = aFname;
    }

    /**
     * @return the lname
     */
    public static String getLname() {
        return lname;
    }

    /**
     * @param aLname the lname to set
     */
    public static void setLname(String aLname) {
        lname = aLname;
    }

    /**
     * @return the email
     */
    public static String getEmail() {
        return email;
    }

    /**
     * @param aEmail the email to set
     */
    public static void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * @return the department
     */
    public static String getDepartment() {
        return department;
    }

    /**
     * @param aDepartment the department to set
     */
    public static void setDepartment(String aDepartment) {
        department = aDepartment;
    }
    
}
