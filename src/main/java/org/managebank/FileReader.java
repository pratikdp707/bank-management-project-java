package org.managebank;

import java.io.File;
import java.util.Scanner;

public class FileReader {

    AccountDriver accountDriver;

    public FileReader(){
        accountDriver = new AccountDriver();
    }

    public void readFile(String fileName) throws Exception{
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        while(scan.hasNextLine())
            accountDriver.acceptInput(scan.nextLine());
    }
}
