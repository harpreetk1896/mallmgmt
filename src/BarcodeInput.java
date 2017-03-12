/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishab
 */
public class BarcodeInput {
    static String inp = null;
    static boolean has_input = false;
    
    static void setInput(String input)
    {
        inp = input;
        has_input=true;
    }
    
    static boolean hasInput()
    {
        return has_input;
    }
    
    static String getInput()
    {
        has_input=false;
        return inp;
    }
}
