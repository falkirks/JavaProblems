package com.falkirks;

public class Box {
    String charToPrint;
    int width;
    int height;
    public Box(String _charToPrint, int _width, int _height) {
        width = _width;
        height = _height;
        charToPrint = "";
        for (int i = 0; i < width; i++){
            charToPrint += _charToPrint;
        }
    }
    public void doLinePrint(){
        if(height > 0){
            System.out.println(charToPrint);
            height--;
            doLinePrint();
        }
    }
}
