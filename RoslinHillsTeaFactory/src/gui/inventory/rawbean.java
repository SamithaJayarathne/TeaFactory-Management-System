/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.inventory;

/**
 *
 * @author Sahan
 */
public class rawbean {
   
    private String id;
    private String name;
    private String material;

    public rawbean(String id, String name,  String material ) {
        this.id = id;
        this.name = name;
        this.material = material;
    }

    // Getters
    public String getid() { return id; }
    public String getname() { return name; }
    public String getmaterial() { return material; }
}
