package com.company;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Yasaman
 * Date: 1/14/15 AD
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */

public class Parser {

    static int male = 0;
    static int female = 0;

    public void findrel1(AVL<Integer, Info> AVLID, int findRelID) {

        Node findrel = AVLID.find(findRelID);              // Get input Node
        Info relInfo = (Info) findrel.getInfo();           // Get input Info


        int dadsRel = relInfo.getFatherID();            // Get Father ID
        Node dad = AVLID.find(dadsRel);                 // Get Father Node

        Info dadInfo = null;
        System.out.println("Father:");
        if (dad != null) {
            dadInfo = (Info) dad.getInfo();             // Get Father Info

            if (dadInfo.isAlive())
                System.out.println(dadInfo.getName());
        }

        int mothersRel = relInfo.getMotherID();            // Get Father ID
        Node mother = AVLID.find(mothersRel);              // Get Father Node
        Info momInfo = null;
        System.out.println("Mother:");
        if (mother != null) {
            momInfo = (Info) mother.getInfo();             // Get Father Info
            if (momInfo.isAlive())
                System.out.println(momInfo.getName());
        }

        AVL<String, Info> Brothers = new AVL<String, Info>();
        AVL<String, Info> Sisters = new AVL<String, Info>();
        AVL<String, Info> Children = new AVL<String, Info>();

        if (momInfo != null) {
            // Find Mother's Children
            Iterator<Info> momit = momInfo.getChildren().iterator();
            while (momit.hasNext()) {
                Info motherChildInfo = momit.next();
                int childID = motherChildInfo.getID();

                Node childNode = AVLID.find(childID);
                Info childInfo = (Info) childNode.getInfo();
                String childGender = childInfo.getGender();

                Node<String, Info> nodeToAdd = new Node<String, Info>(childInfo.getName(), childInfo);
                if (childGender.compareTo("male") == 0) {
                    Brothers.insert(nodeToAdd);
                } else if (childGender.compareTo("female") == 0) {     // If The Child is Female --> Add To Female AVL Child
                    Sisters.insert(nodeToAdd);
                }
            }
        }

        if (dadInfo != null) {

            // Find Father's Children
            Iterator<Info> dadit = dadInfo.getChildren().iterator();
            while (dadit.hasNext()) {
                Info fatherChildInfo = dadit.next();
                int childID = fatherChildInfo.getID();

                Node childNode = AVLID.find(childID);
                Info childInfo = (Info) childNode.getInfo();
                String childGender = childInfo.getGender();

                Node<String, Info> nodeToAdd = new Node<String, Info>(childInfo.getName(), childInfo);
                if (childGender.compareTo("male") == 0) {
                    Brothers.insert(nodeToAdd);
                } else if (childGender.compareTo("female") == 0) {     // If The Child is Female --> Add To Female AVL Child
                    Sisters.insert(nodeToAdd);
                }
            }
        }

        Brothers.delete(relInfo.getName());
        Sisters.delete(relInfo.getName());

        System.out.println("Brothers:");
        Brothers.InorderTraversalList();
        System.out.println("Sisters:");
        Sisters.InorderTraversalList();

        Iterator<Info> childrenIT = relInfo.getChildren().iterator();
        while (childrenIT.hasNext()) {
            int ChildID = childrenIT.next().getID();
            Node childNode = AVLID.find(ChildID);
            Info childInfo = (Info) childNode.getInfo();

            Node<String, Info> nodeToAdd = new Node<String, Info>(childInfo.getName(), childInfo);

            Children.insert(nodeToAdd);
        }

        System.out.println("Children:");
        Children.InorderTraversalList();
    }

    public void findrel2(AVL<Integer, Info> AVLID, int findRelID) {

        Node findrel = AVLID.find(findRelID);              // Get input Node
        Info relInfo = (Info) findrel.getInfo();           // Get input Info


        DoublyLinkedList<Info> AuntsUncs = grandparents(AVLID, relInfo);
        grandchildren(relInfo);
        DoublyLinkedList<Info> AuntsUncsChildren = auntsUncles(AuntsUncs, relInfo);
        causins(AuntsUncsChildren);
    }

    public DoublyLinkedList<Info> grandparents(AVL<Integer, Info> AVLID, Info relInfo) {

        int momID = relInfo.getMotherID();
        int dadID = relInfo.getFatherID();

        Node mom = AVLID.find(momID);
        Node dad = AVLID.find(dadID);

        Info momInfo = (Info) mom.getInfo();
        Info dadInfo = (Info) dad.getInfo();

        int mmomID = momInfo.getMotherID();
        int dmomID = momInfo.getFatherID();
        int mdadID = dadInfo.getMotherID();
        int ddadID = dadInfo.getFatherID();

        Node mmom = AVLID.find(mmomID);
        Node dmom = AVLID.find(dmomID);
        Node mdad = AVLID.find(mdadID);
        Node ddad = AVLID.find(ddadID);

        Info mmomInfo = (Info) mmom.getInfo();
        Info dmomInfo = (Info) dmom.getInfo();
        Info mdadInfo = (Info) mdad.getInfo();
        Info ddadInfo = (Info) ddad.getInfo();

        DoublyLinkedList<Info> mmomschildren = mmomInfo.getChildren();
        DoublyLinkedList<Info> dmomschildren = dmomInfo.getChildren();
        DoublyLinkedList<Info> mdadchildren = mdadInfo.getChildren();
        DoublyLinkedList<Info> ddadschildren = ddadInfo.getChildren();

        Iterator<Info> mmomschit = mmomschildren.iterator();
        Iterator<Info> dmomschit = dmomschildren.iterator();
        Iterator<Info> mdadschit = mdadchildren.iterator();
        Iterator<Info> ddadschit = ddadschildren.iterator();


        DoublyLinkedList<Info> relatives = new DoublyLinkedList<Info>();

        while (mmomschit.hasNext()) {
            Info relative = mmomschit.next();
            DoublyLinkedList.Node<Info> nodeToAdd = new DoublyLinkedList.Node<Info>(relative, null, null);
            relatives.addFirst(nodeToAdd);
        }
        while (dmomschit.hasNext()) {
            Info relative = dmomschit.next();
            DoublyLinkedList.Node<Info> nodeToAdd = new DoublyLinkedList.Node<Info>(relative, null, null);
            relatives.addFirst(nodeToAdd);
        }
        while (mdadschit.hasNext()) {
            Info relative = mdadschit.next();
            DoublyLinkedList.Node<Info> nodeToAdd = new DoublyLinkedList.Node<Info>(relative, null, null);
            relatives.addFirst(nodeToAdd);
        }
        while (ddadschit.hasNext()) {
            Info relative = ddadschit.next();
            DoublyLinkedList.Node<Info> nodeToAdd = new DoublyLinkedList.Node<Info>(relative, null, null);
            relatives.addFirst(nodeToAdd);
        }

        AVL<String, Info> grandFathers = new AVL<String, Info>();
        AVL<String, Info> grandMothers = new AVL<String, Info>();

        if (mmomInfo.getGender().compareTo("male") == 0) {
            grandFathers.insert(new Node<String, Info>(mmomInfo.getName(), mmomInfo));
        } else if (mmomInfo.getGender().compareTo("female") == 0) {
            grandMothers.insert(new Node<String, Info>(mmomInfo.getName(), mmomInfo));
        }

        if (dmomInfo.getGender().compareTo("male") == 0) {
            grandFathers.insert(new Node<String, Info>(dmomInfo.getName(), dmomInfo));
        } else if (dmomInfo.getGender().compareTo("female") == 0) {
            grandMothers.insert(new Node<String, Info>(dmomInfo.getName(), dmomInfo));
        }

        if (mdadInfo.getGender().compareTo("male") == 0) {
            grandFathers.insert(new Node<String, Info>(mdadInfo.getName(), mdadInfo));
        } else if (mdadInfo.getGender().compareTo("female") == 0) {
            grandMothers.insert(new Node<String, Info>(mdadInfo.getName(), mdadInfo));
        }

        if (ddadInfo.getGender().compareTo("male") == 0) {
            grandFathers.insert(new Node<String, Info>(ddadInfo.getName(), ddadInfo));
        } else if (ddadInfo.getGender().compareTo("female") == 0) {
            grandMothers.insert(new Node<String, Info>(ddadInfo.getName(), ddadInfo));
        }

        System.out.println("Grandfathers:");
        grandFathers.InorderTraversalList();

        System.out.println("GrandMothers:");
        grandMothers.InorderTraversalList();

        return relatives;
    }

    public void grandchildren(Info relInfo) {

        DoublyLinkedList<Info> children = relInfo.getChildren();
        DoublyLinkedList<Info> gChildren = new DoublyLinkedList<Info>();

        Iterator<Info> childrenit = children.iterator();
        while (childrenit.hasNext()) {
            Info childInfo = childrenit.next();
            DoublyLinkedList<Info> childsChildren = childInfo.getChildren();

            Iterator<Info> chchildrenit = childsChildren.iterator();
            while (chchildrenit.hasNext()) {
                Info chchildsInfo = chchildrenit.next();
                DoublyLinkedList.Node<Info> grandchild = new DoublyLinkedList.Node<Info>(chchildsInfo, null, null);
                gChildren.addFirst(grandchild);
            }
        }

        AVL<String, Info> grandchildren = new AVL<String, Info>();
        Iterator<Info> gChit = gChildren.iterator();
        while (gChit.hasNext()) {
            Info gChInfo = gChit.next();
            String gChName = gChInfo.getName();
            Node grandchild = new Node(gChName, gChInfo);
            grandchildren.insert(grandchild);
        }

        System.out.println("Grandchildren:");
        grandchildren.InorderTraversalList();
    }

    public DoublyLinkedList<Info> auntsUncles(DoublyLinkedList<Info> AuntsUnces, Info relInfo) {

        Iterator<Info> it = AuntsUnces.iterator();
        AVL<String, Info> aunts = new AVL<String, Info>();
        AVL<String, Info> uncs = new AVL<String, Info>();

        DoublyLinkedList<Info> ans = new DoublyLinkedList<Info>();
        Iterator<Info> childit = AuntsUnces.iterator();
        while (childit.hasNext()){
            Info auInf = childit.next();
            if (auInf.getID() == relInfo.getFatherID() || auInf.getID() == relInfo.getMotherID())
                continue;
            DoublyLinkedList<Info> auchildren = auInf.getChildren();
            Iterator<Info> auchildrenit = auchildren.iterator();

            while (auchildrenit.hasNext()){
                Info causin = auchildrenit.next();
                DoublyLinkedList.Node<Info> node = new DoublyLinkedList.Node<Info>(causin, null, null);
                ans.addFirst(node);
            }

            String nodesname = auInf.getName();
            Node<String, Info> AVLNode = new Node<String, Info>(nodesname, auInf);

            if (auInf.getGender().compareTo("male") == 0) {
                uncs.insert(AVLNode);
            } else if (auInf.getGender().compareTo("female") == 0) {
                aunts.insert(AVLNode);
            }
        }


        System.out.println("Uncles:");
        uncs.InorderTraversalList();
        System.out.println("Aunts:");
        aunts.InorderTraversalList();

        return ans;
    }

    public void causins (DoublyLinkedList<Info> auntuncschildren ) {

        Iterator<Info> it = auntuncschildren.iterator();
        AVL<String, Info> causins = new AVL<String, Info>();

        while (it.hasNext()) {
            Info node = it.next();
            String nodesname = node.getName();
            Node<String, Info> AVLNode = new Node<String, Info>(nodesname, node);
            causins.insert(AVLNode);
        }

        System.out.println("Cousins:");
        causins.InorderTraversalList();
    }


    public void run() {


        AVL<Integer, Info> AVLID = new AVL<Integer, Info>();                 // AVL Tree With IDs as Keys
        AVL<Integer, Info> AVLBDay = new AVL<Integer, Info>();                 // AVL Tree With Birthdays as Keys
        AVL<String, Info> AVLName = new AVL<String, Info>();                 // AVL Tree With Names as Keys

        Scanner input = new Scanner(System.in);

        while (true) {                                         // The While Goes on Forever Until User Enteres Exit ! :D

            String inputstr = input.nextLine();

            if (inputstr.compareTo("exit") == 0) {             // "Exit" Entered ? End Program !
                break;
            } else if (inputstr.compareTo("add") == 0) {              // Add New Entry to All Three Trees !

                // Gather Entry Information From User:
                String name = input.nextLine();                  // Get Name
                int ID = input.nextInt();                        // Get ID

                int birthday = input.nextInt();                  // Get Birthday
                input.nextLine();

                String gender = input.nextLine();                // Get Gender
                if (gender.compareTo("male") == 0) {           // if Gender is Male
                    male++;
                } else if (gender.compareTo("female") == 0) {   // if Gender is Female
                    female++;
                }

                int FatherID = input.nextInt();                 // Get FatherID
                int MotherID = input.nextInt();                 // Get MotherID

                Info info;

                if (AVLID.find(ID) != null) { // We Have This ID , We Just Have to Edit it !
                    Node current = AVLID.find(ID);
                    info = (Info) current.getInfo();
                    info.setName(name);
                    info.setBirthday(birthday);
                    info.setGender(gender);
                    info.setFatherID(FatherID);
                    info.setMotherID(MotherID);
                    info.setAlive(true);

                    Node<Integer, Info> nodeBDay = new Node<Integer, Info>(birthday, info);
                    Node<String, Info> nodeName = new Node<String, Info>(name, info);

                    AVLBDay.insert(nodeBDay);
                    AVLName.insert(nodeName);
                } else {
                    // Make The Info
                    info = new Info(name, ID, birthday, gender, FatherID, MotherID);


                    info.setAlive(true);

                    // Make The Node for AVLID
                    Node<Integer, Info> nodeID = new Node<Integer, Info>(ID, info);

                    // Make The Node For AVLBday
                    Node<Integer, Info> nodeBDay = new Node<Integer, Info>(birthday, info);

                    // Make Node For AVLName
                    Node<String, Info> nodeName = new Node<String, Info>(name, info);

                    // Call Insert Method For AVLID , AVLBday & AVLName
                    AVLID.insert(nodeID);
                    AVLBDay.insert(nodeBDay);
                    AVLName.insert(nodeName);
                }

                Info dadInfo = null;
                Info momInfo = null;

                if (AVLID.find(MotherID) == null) { // No Mother , We Should Make an Empty Node For it
                    momInfo = new Info("", MotherID, 0, "", 0, 0);

                    momInfo.setAlive(false);
                    Node<Integer, Info> momID = new Node<Integer, Info>(MotherID, momInfo);
                    // Node<Integer, Info> momBDay = new Node<Integer, Info>(birthday, momInfo);
                    // Node<String, Info> momName = new Node<String, Info>(name, momInfo);

                    AVLID.insert(momID);
                    // AVLBDay.insert(momBDay);
                    // AVLName.insert(momName);

                } else {
                    momInfo = (Info) AVLID.find(MotherID).getInfo();
                }

                if (AVLID.find(FatherID) == null) { // No Mother , We Should Make an Empty Node For it
                    dadInfo = new Info("", FatherID, 0, "", 0, 0);
                    dadInfo.setAlive(false);
                    Node<Integer, Info> dadID = new Node<Integer, Info>(FatherID, dadInfo);
                    // Node<Integer, Info> dadBDay = new Node<Integer, Info>(birthday, dadInfo);
                    // Node<String, Info> dadName = new Node<String, Info>(name, dadInfo);

                    AVLID.insert(dadID);
                    // AVLBDay.insert(dadBDay);
                    // AVLName.insert(dadName);

                } else {
                    dadInfo = (Info) AVLID.find(FatherID).getInfo();
                }

                dadInfo.addChild(info);
                momInfo.addChild(info);
                input.nextLine();
            } else if (inputstr.compareTo("delete") == 0) {            // We Have To Delete From 3 Different Trees

                int deleteID = input.nextInt();                        // Get The Deleting Node ID From User
                input.nextLine();

                Node deletingNode = AVLID.find(deleteID);              // Find The Node We Want To Delete
                Info deleteingNodeInfo = (Info) deletingNode.getInfo();

                if (deleteingNodeInfo != null)
                    deleteingNodeInfo.setAlive(false);
            } else if (inputstr.compareTo("rename") == 0) {

                int inputID = input.nextInt();                        // Get ID From User
                input.nextLine();
                String newName = input.nextLine();                    // Get New Name From User

                Node editingNode = AVLID.find(inputID);               // Find The Node We Want To Rename
                Info editingNodeInfo = (Info) editingNode.getInfo();

                if (editingNodeInfo != null) {

                    AVLName.delete(editingNodeInfo.getName());         // Delete The Node
                    editingNodeInfo.setName(newName);                     // Change The Name


                    Node<String, Info> newNode = new Node<String, Info>(editingNodeInfo.getName(), editingNodeInfo);
                    AVLName.insert(newNode);                              // Make a New Node and insert
                }
            } else if (inputstr.compareTo("findbyname") == 0) {      // Find a Person in AVLName

                String inputName = input.nextLine();

                Info info = (Info) AVLName.find(inputName).getInfo();

                if (info != null && info.isAlive()) {
                    System.out.println(info.getName());
                    System.out.println(info.getID());
                    System.out.println(info.getBirthday());
                    System.out.println(info.getGender());
                    System.out.println(info.getFatherID());
                    System.out.println(info.getMotherID());

                } else {
                    System.out.println("not found");
                }
            } else if (inputstr.compareTo("findbyid") == 0) {        // Find a Person in AVLID

                int inputID = input.nextInt();
                input.nextLine();

                Info info = (Info) AVLID.find(inputID).getInfo();

                if (info != null && info.isAlive()) {
                    System.out.println(info.getName());
                    System.out.println(info.getID());
                    System.out.println(info.getBirthday());
                    System.out.println(info.getGender());
                    System.out.println(info.getFatherID());
                    System.out.println(info.getMotherID());
                } else {
                    System.out.println("not found");
                }

            } else if (inputstr.compareTo("findrel1") == 0) {

                int findRelID = input.nextInt();
                input.nextLine();

                findrel1(AVLID, findRelID);


            } else if (inputstr.compareTo("findrel2") == 0) {

                int findRelID = input.nextInt();
                input.nextLine();

                findrel2(AVLID, findRelID);

            } else if (inputstr.compareTo("findbybirthrange") == 0) {

                int startDate = input.nextInt();
                input.nextLine();
                int endDate = input.nextInt();
                input.nextLine();

                BST<String, Info> ansName = AVLBDay.findByRange(startDate, endDate);
                if (ansName.size != 0) {
                    ansName.InorderTraversalList();
                } else
                    System.out.println("not found");
            } else if (inputstr.compareTo("percent") == 0) {

                String inputPercent = input.nextLine();              // Decide Which Gender The User Wants to Check
                double ans = 0;

                if ((male + female) == 0) {                          // Avoid Division By Zero
                    System.out.println("0");
                } else if (inputPercent.compareTo("female") == 0) {  // female entered
                    ans = (female * 100.0) / (female + male);
                } else if (inputPercent.compareTo("male") == 0) {    // male entered
                    ans = (male * 100.0) / (female + male);
                }

                System.out.println(ans);

            } else if (inputstr.compareTo("list") == 0) {

                AVLName.InorderTraversalList();
            }

        }
    }
}
