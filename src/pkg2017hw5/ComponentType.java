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
public enum ComponentType {
    BUTTON{
        @Override
        public String toString(){
            return "Button";
        }
    },
    LABEL{
        @Override
        public String toString(){
            return "Label";
        }
    }, 
    TEXTAREA{@Override
    public String toString(){
        return "TextArea";
        }
    },
    HBOX{
    @Override
    public String toString(){
        return "Hbox";
        }
    }, 
    VBOX{
        @Override
    public String toString(){
        return "Vbox";
    }
    
    }, 
    ANCHORPANE{
        @Override
    public String toString(){
        return "AnchorPane";
        }
    }
        
    }

