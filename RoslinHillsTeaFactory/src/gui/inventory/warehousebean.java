/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.inventory;

/**
 *
 * @author Sahan
 */
public class warehousebean {
   
    private String id;
    private String name;
    private String address;
     private String capacity;
    private String  manager;
    private String city;

    public warehousebean(String id, String name,  String address ,  String capacity ,  String manager ,  String city ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.manager = manager;
        this.city= city;
    }

    // Getters
    public String getid() { return id; }
    public String getname() { return name; }
    public String getaddress() { return address; }
    public String getcapacity() { return capacity; }
    public String getmanager() { return manager; }
    public String getcity() { return city; }
}
