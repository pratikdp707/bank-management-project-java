package org.managebank;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader();
        fileReader.readFile(args[0]);
    }
}