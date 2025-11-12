package edu.grinnell.csc207.lootgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";
    
    public static void main(String[] args) throws IOException {
        System.out.println("This program kills monsters and generates loot!");
        
        //Getting data from monstats.txt
        FileReader fileMonstats = new FileReader("data/small/monstats.txt");
        BufferedReader buffMonstats = new BufferedReader(fileMonstats);
        int linesMonstats = 0; // the number of lines in the file monstats.txt
        while(buffMonstats.readLine()!=null){
            linesMonstats++;
        }
        buffMonstats.close();

        //Getting random line from monstats.txt and reading class and TC
        buffMonstats = new BufferedReader(new FileReader("data/small/monstats.txt"));
        Random random = new Random();
        int rand = random.nextInt(linesMonstats);
        int currentLine = 0;
        String lineMonstats = null;
        String classMonstats = null;
        String treasureClass = null;

        while((lineMonstats = buffMonstats.readLine()) != null){
            if(currentLine == rand){
                classMonstats = lineMonstats.split(" ")[0];
                //find the level index (the index of last number and divide the line with split)
                //everything after is a treasure class
                treasureClass = lineMonstats.replaceFirst(".*?\\d+\\s+", "");
                break;
            }
            else{
                currentLine++;
            }
        }
        buffMonstats.close();
        System.out.println("class:" + classMonstats);
        System.out.println("treasure class:" + treasureClass);
        //System.out.println("random line number:" + rand);
        //System.out.println("lineMonstats:" + lineMonstats);
        buffMonstats.close();
    }
}
