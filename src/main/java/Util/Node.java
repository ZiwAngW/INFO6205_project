package Util;

public class Node {
    private String ChineseChar;
    private byte[] byteArray;
    public Node(String word) {
        ChineseChar=word;
        byteArray = ChineseUtil.toByteArray(word);
    }
    public Node(){
        ChineseChar=null;
        byteArray =null;
    }
    public void setNode(String word){
        ChineseChar=word;
        byteArray = ChineseUtil.toByteArray(word);
    }
    public String getChineseChar(){
        return ChineseChar;
    }
    public byte[] getByteArray(){
        return byteArray;
    }
}
