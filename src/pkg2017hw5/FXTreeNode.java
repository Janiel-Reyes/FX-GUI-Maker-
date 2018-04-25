/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017hw5;

/**
 *
 * @author Kyle
 */
public class FXTreeNode {
    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    private int depth = 0;
    private String writeHelpVar= "0";
    final int maxChildren = 10;
    public FXTreeNode(ComponentType type, String text){
        if(type == ComponentType.HBOX || type == ComponentType.VBOX ||type == ComponentType.ANCHORPANE )
            this.text = "";
        else{
            this.text = text;
        }
        this.type = type;
        children= new FXTreeNode[maxChildren];
    }
    
    //Setters
    
    public void setWriteHelpVar(String writeHelpVar) {
        this.writeHelpVar = writeHelpVar;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public void setParent(FXTreeNode parent) {
        this.parent = parent;
    }

    public void setChildren(FXTreeNode child, int index) {
       children[index]= child;
    }
   public boolean hasChildren(){
       boolean temp = false;
       for(int i = 0; i< children.length; i++){
           if(children[i] != null){
               temp =true;
           }
       }
       return temp;
   }

    
    // Getters
   
    public String getWriteHelpVar() {
        return writeHelpVar;
    }

    public int getDepth() {
        return depth;
    }

    public String getText() {
        return text;
    }

    public ComponentType getType() {
        return type;
    }

    public FXTreeNode getParent() {
        return parent;
    }

    public FXTreeNode getChild(int index) {
        return children[index];
    }
    public String toString(){
        String temp ="";
        temp+= type.toString() + " "+ text;
        
        return temp ;
    }
    
}
