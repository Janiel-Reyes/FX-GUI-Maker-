/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017hw5;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Kyle
 */
public class FXComponentTree {
    private FXTreeNode root;
    private FXTreeNode cursor; 
    
    public FXComponentTree(FXTreeNode root){
        this.root = root;
        cursor = root;
    }
    public void cursorToRoot(){
        cursor = root;
    }
    ;
    //     Removes the child at the specified index of the FXComponentTree, as well as all of its children.
    public void deleteChild(int index){
        cursor.setChildren(null, index);
    }
    //    Adds the given node to the corresponding index of the children array.
    //Should throw an exception if adding the node at the specified index makes a hole in the array.
    public void addChild(int index, FXTreeNode node){
        if(cursor.getChild(index)==null){
        cursor.setChildren(node, index);
        //Sets parent of child
        cursor.getChild(index).setParent(cursor);
        cursor.getChild(index).setDepth(cursor.getDepth()+1);
        String temp ="";
        temp+= cursor.getWriteHelpVar()+"-"+index;
        
        cursor.getChild(index).setWriteHelpVar(temp);
        
        }
        cursorToChild(index);
        System.out.println("Child has been added");
        }
    /*
    Sets the current node's text to the specified text.
    */
    public void setTextAtCursor(String text){
        cursor.setText(text);
        System.out.println("Text Edited");
    }
    /*
    Moves cursor to the child node of the cursor at index
    */
    public void cursorToChild(int index){
        if(cursor.getChild(index)!= null){
        cursor= cursor.getChild(index);
        System.out.println("Cursor moved to "+ cursor.getType().toString()+" "+cursor.getText());
        }
        else{
            System.out.println("No child exists there");
        }
        
       
    }
    //Moves the cursor to the parent of node
    public void cursorToParent(){
        if(cursor.getParent() != null){
        cursor = cursor.getParent();
        System.out.println("Cursor moved to "+ cursor.getType().toString()+" "+cursor.getText());
        }
        else{
            System.out.println("You are at the root!");
        }    
    }
    /*
    Generates the FXComponentTRee based on the file name
    */
    public static FXComponentTree readFromFile(String filename)throws FileNotFoundException{
            Scanner scan= new Scanner(System.in);
            File file = new File(filename);
            scan = new Scanner(file);
            
            FXComponentTree temp = null;
            ComponentType tempo=null;
            FXTreeNode node;
            while(scan.hasNextLine()){
                //Scans the line
                String line = scan.nextLine();
                //Gets the nodes and numbers 0-0-0 
                String beforeSpace = line.substring(0,line.indexOf(" ")+1);    
                //Gets the Component type and text
                String afterSpace = line.substring(line.indexOf(" ")+1);
                //Checks these because these dont have text after it 
                 if(afterSpace.equalsIgnoreCase("VBOX")){
                     tempo= ComponentType.VBOX;
                 }else if(afterSpace.equalsIgnoreCase("HBOX")){
                     tempo= ComponentType.HBOX;
                 }else if(afterSpace.equalsIgnoreCase("ANCHORPANE")){
                     tempo= ComponentType.ANCHORPANE;
                 }
                 
                 String component = afterSpace.substring(0,afterSpace.indexOf(" ")+1);
                 component = component.replace(" ", "");
                    
                        // Finds the value of the component
                    switch(component){
                        case "LABEL":
                            tempo = ComponentType.LABEL;
                            break;
                        case "BUTTON":
                            tempo = ComponentType.BUTTON;
                            break;
                        case "TEXTAREA":
                            tempo = ComponentType.TEXTAREA;
                            break;
                        case "ANCHORPANE":
                            tempo = ComponentType.ANCHORPANE;
                            break;
                        case "HBOX":
                            tempo = ComponentType.HBOX;
                            break;
                        case "VBOX":
                            tempo = ComponentType.VBOX;
                            break;    
                    
                    }
                    if(tempo == null){
                        System.out.println("NO ENUM");
                        return null;
                    }
                    else
                        System.out.println(tempo.toString());
                 //Creates the node from the after space
                    node = new FXTreeNode(tempo, afterSpace.substring(afterSpace.indexOf(" ")+1));
                //The first root node has no dash so if it does it's not the root
                if(beforeSpace.contains("-")){
                    //Splits the 0-0-0 to values[0]
                String[] values = beforeSpace.split("-");
                int i = 1 ;
               //Makes the cursor go to the end of the branch it wants to reach
                while(i< values.length-1){
                    temp.cursorToChild(Integer.parseInt(values[i].replace(" ", "")));
                    i++;
                    
                }
                    //Adds the child to the tree 
                    // Note addchild checks to see if the child is null if it is then it adds this node if not it does nothing
                    //replace is there well because it was giving a 0 with a space in it and causing errors
                    temp.addChild(Integer.parseInt(values[i].replace(" ", "")),node);
                    temp.cursorToRoot();
                }
                else{
                    //Root
                    node = new FXTreeNode(tempo, "");
                    
                    temp = new FXComponentTree(node);
                    System.out.println("Root created");
                    
                }
            }
            temp.cursorToRoot();
            return temp;
    }
    /*
    Generates a text file that reflects the structure of the FXComponentTree.
    */
    public static void writeToFile(FXComponentTree tree, String filename)throws FileNotFoundException{
        File Fileright = new File(filename);
        PrintWriter pw = new PrintWriter(Fileright);
        pw.print(tree.traverseTree(tree.getRoot()));
        pw.close();
    }
     public String traverseTree(FXTreeNode node){
         String answer ="";
         if(node ==null){
             return answer;
         }   
         if(node.getParent()==null){
             answer+="0 "+node.toString()+"\r\n";
         
         }
         else{
            
             answer+= " "+node.toString()+"\r\n" ;
         }
         if(node.hasChildren()){
             
             int childIndex=0;
             
             while(childIndex< node.maxChildren){
                 
                 if(node.getChild(childIndex)!=null){
                 answer+= node.getWriteHelpVar()+"-"+childIndex;
                 answer+= traverseTree(node.getChild(childIndex));
                 
                 
                 
             }                     
         childIndex++;
             }
         }
         return answer;
        }
    /*
    Extra Credit
    */
    public static void exportToFXML(FXComponentTree tree){
        
    }
    public void printTree(FXTreeNode root){
        if(root ==null){
            return;
        }
        if(root == cursor){
            System.out.print("====>");
        }
        if(root.getParent() == null){
            System.out.println(root.toString());
        }
        else{
            for(int i = 0; i<root.getDepth(); i++){
               System.out.print("\t");
           }
        System.out.println("+---"+ root.toString());
        
        }
        if(root.hasChildren())
        {
           
            int childCount = 0;
        while(childCount < root.maxChildren){
            
            printTree(root.getChild(childCount));
            childCount++;
            
        
        }
        }
    }
    public FXTreeNode getRoot() {
        return root;
    }

    public FXTreeNode getCursor() {
        return cursor;
    }
    
    
    
}
