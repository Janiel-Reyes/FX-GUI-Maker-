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
 
 */
public class FXGuiMaker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FXTreeNode root = new FXTreeNode(ComponentType.ANCHORPANE, "");
        FXComponentTree tree = new FXComponentTree(root);
        boolean run = true;
        Scanner scan= new Scanner(System.in);
        String input;
        int inputNumber;
        // TODO code application logic here
         while(run){
            System.out.println("Welcome to the Counterfeit SceneBuilder \n"
                 + "Menu: \n"
                 + "L - Load From File \n"
                 + "P - Print \n"
                 + "C - Cursor to Child \n"
                 + "A - Add Child \n"
                 + "U - Cursor Up \n"
                 + "E - Edit Text \n"
                 + "D - Delete child\n"
                 + "R - Cursor to Root \n"
                 + "S - Save to File \n"
                 + "Q - Quit \n");
            System.out.println("Please Enter an option: ");
           input=scan.nextLine();
           
           if(input.equalsIgnoreCase("L")){
               System.out.println("Enter FileName");
               input= scan.nextLine();
               try{
                  tree=tree.readFromFile(input);
                  
               }
                 catch(FileNotFoundException ex){
                      System.out.println("File not found");
        }
           }     
           else if(input.equalsIgnoreCase("P")){
               tree.printTree(tree.getRoot());
           }
           else if(input.equalsIgnoreCase("C")){
               System.out.println("Please Enter the number of child");
               inputNumber = scan.nextInt();
               tree.cursorToChild(inputNumber);
           }
           else if(input.equalsIgnoreCase("A")){
                System.out.println("Please Enter Component Type \n"
                        + "H-Hbox, V- Vbox, T - TextArea, B-Button, L-Label");
                ComponentType user= null;
                String textAnswer;
                int userAnswer;
                boolean valid = true;
                while(valid){
                    input= scan.nextLine();
                    input.toUpperCase();
                switch(input){
                    case "H": 
                    case "h":
                        user= ComponentType.HBOX;
                        valid = false;
                        break;
                    case "V":
                    case "v":
                        user= ComponentType.VBOX;
                        valid = false;
                        break;
                    case "T":
                    case "t":
                        user= ComponentType.TEXTAREA;
                        valid = false;
                        break;
                    case "B":
                    case "b":
                        user= ComponentType.BUTTON;
                        valid = false;
                        break;
                    case "L":
                    case "l":
                        user = ComponentType.LABEL;
                        valid = false;
                        break;
                    default : 
                        System.out.println("Invalid Choice Please Choose Correct Letter");
                        
                        break;
                
                }
                
                }
                 System.out.println("Please enter Text");
                 textAnswer= scan.nextLine();
                 System.out.println("Please Enter Index");
                 userAnswer= scan.nextInt();
                 FXTreeNode temp = new FXTreeNode(user,textAnswer);
                 tree.addChild(userAnswer, temp);
                  System.out.println("Child has been added");
           }
           else if(input.equalsIgnoreCase("U")){
               tree.cursorToParent();
           }
           else if(input.equalsIgnoreCase("E")){
                System.out.println("Please Enter the text you want");
                String answer= scan.nextLine();
               tree.setTextAtCursor(answer);
           }
           else if(input.equalsIgnoreCase("D")){
               System.out.println("Enter Index");
               tree.deleteChild(scan.nextInt());
           }
           else if(input.equalsIgnoreCase("R")){
               tree.cursorToRoot();
           }
           else if(input.equalsIgnoreCase("S")){
               System.out.println("Enter file name to be saved");
               String filename = scan.nextLine();
               try{
               tree.writeToFile(tree,filename);
               }
               catch(FileNotFoundException ex){
                      System.out.println("File not found");
        }
               }
           else if(input.equalsIgnoreCase("Q")){
               System.out.println("Goodbye");
               run =false;
               System.exit(0);
           }
           else{
               System.out.println("Enter a Valid letter");
           }
           

       }
    }
    
}
