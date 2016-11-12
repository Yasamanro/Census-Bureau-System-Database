package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Yasaman
 * Date: 1/10/15 AD
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */

public class Info {

    private String Name;
    private int ID;
    private int Birthday;
    private String Gender;
    private int FatherID;
    private int MotherID;
    boolean isAlive;
    private DoublyLinkedList<Info> Children;

    public Info (String name,int ID,int Birthday,String Gender,int FID , int MID , DoublyLinkedList Children){

        this.Name = name;
        this.ID = ID;
        this.Birthday = Birthday;
        this.Gender = Gender;
        this.FatherID = FID;
        this.MotherID = MID;
        this.Children = Children;
    }

    public Info (String name,int ID,int Birthday,String Gender,int FID, int MID) {
        this.Name = name;
        this.ID = ID;
        this.Birthday = Birthday;
        this.Gender = Gender;
        this.FatherID = FID;
        this.MotherID = MID;
        this.Children = new DoublyLinkedList<Info>();
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public int getBirthday() {
        return Birthday;
    }

    public int getFatherID() {
        return FatherID;
    }

    public int getMotherID() {
        return MotherID;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public DoublyLinkedList getChildren() {
        return Children;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setBirthday(int birthday) {
        Birthday = birthday;
    }

    public void setFatherID(int fatherID) {
        FatherID = fatherID;
    }

    public void setMotherID(int motherID) {
        MotherID = motherID;
    }

    public void setChildren(DoublyLinkedList<Info> children) {
        Children = children;
    }

    public void addChild(Info child){
        DoublyLinkedList.Node<Info> node = new DoublyLinkedList.Node<Info>(child, null, null);
        Children.addFirst(node);
    }
}
