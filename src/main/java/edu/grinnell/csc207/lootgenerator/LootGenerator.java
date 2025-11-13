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
    
    /**
     * Returns the random drop based on the treasure class
     * @param treasureClass the treasure class of a monster
     * @param treasureClassArr array populated with all possible treasure classes
     * @return the random drop based on the treasure class
     * @throws IOException
     */
    public static String findDrop(String treasureClass, String[] treasureClassArr) throws IOException{
        FileReader fileTC = new FileReader("data/small/TreasureClassEx.txt");
        BufferedReader buffTC = new BufferedReader(fileTC);
        String lineTC = null;
        String dropsLine = null;
            while((lineTC = buffTC.readLine()) != null){
            if(lineTC.startsWith(treasureClass)){
                dropsLine = lineTC.substring(treasureClass.length()); //get everything after the TC name
                dropsLine = dropsLine.replaceFirst("\\s+", ""); //get rid of the spaces at the beginning
                String[] dropsArr = dropsLine.split("\t");

                //Randomly choose one of three drops.
                Random random = new Random();
                int randOfThree = random.nextInt(3);
                String selectedDrop = dropsArr[randOfThree];

                //find if the selectedDrop a TC
                boolean isTC = false;
                for (String element : treasureClassArr) {
                    if (element.equals(selectedDrop)) {
                        isTC = true;
                        break;
                    }
                }

                //if selectedDrop is another treasure class, recurse; if not, return
                if (isTC) {
                    return findDrop(selectedDrop, treasureClassArr);
                } else {
                    return selectedDrop;
                }
                
            }
        }
        //return treasureClass;
        buffTC.close();
        return "treasureClass doesn't exist";
    }

    public static String generateSuffixOrPrefix(String file) throws IOException{
            FileReader fileSuffix = new FileReader("data/small/" + file);
            BufferedReader buffSuffix = new BufferedReader(fileSuffix);
             int totalLinesSuffix = 0;
             String lineSuffix = null;
             while((lineSuffix = buffSuffix.readLine())!= null){
                totalLinesSuffix++;
             }
             buffSuffix.close();
             //reopen buffSuffix
             buffSuffix = new BufferedReader(new FileReader("data/small/" + file));
             Random random = new Random();
             int randomSuffixLine = random.nextInt(totalLinesSuffix);
             int currentLineNumberSuffix = 0;
             while ((lineSuffix = buffSuffix.readLine()) != null) {
                if (currentLineNumberSuffix == randomSuffixLine) {
                    break;
                }
                else{
                    currentLineNumberSuffix++;
                }
            } 
            return lineSuffix;
    }
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
                classMonstats = lineMonstats.split("\\t")[0];
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
        buffMonstats.close();

        //Create array that contains treasure classes
        FileReader fileTC = new FileReader("data/small/TreasureClassEx.txt");
        BufferedReader buffTC = new BufferedReader(fileTC);
        String lineTC = null;
        int countLines = 0;
        int linesTreasureClassEx = 0;

        //Find number of lines in TreasureClassEx
        while(buffTC.readLine()!=null){
            linesTreasureClassEx++;
        }
        buffTC.close();

        //Array of treasure classes
        String[] treasureClassArr = new String[linesTreasureClassEx];
        buffTC = new BufferedReader(new FileReader("data/small/TreasureClassEx.txt"));
         while((lineTC = buffTC.readLine()) != null){
            treasureClassArr[countLines] = lineTC.replaceFirst("\\t.*", "");
            countLines++;
        }
        //Look up the monster's TC in TreasureClassEx.txt.
        String findDrop = findDrop(treasureClass, treasureClassArr);
        //The base item that we finally choose is the randomly generated drop from our monster!
        buffTC.close();

        //Computing base stats for a base item
        BufferedReader buffDefence = new BufferedReader(new FileReader("data/small/armor.txt"));
        String lineDefence = null;
        int defence = 0;
        while((lineDefence = buffDefence.readLine()) != null){
            if(lineDefence.startsWith(findDrop)){
                int minDefence = Integer.parseInt(lineDefence.split("\\t")[1]);
                int maxDefence = Integer.parseInt(lineDefence.split("\\t")[2]);
                defence = random.nextInt(minDefence, maxDefence+1);
            }
        }

        //Generating suffix and prefix
        int randSuffix = random.nextInt(2);
        int randPrefix = random.nextInt(2);
        String prefix = "";
        String suffix = "";
        int valueSuffix = 0;
        String statisticTextSuffix = null;
        if(randSuffix == 1){
            String lineSuffix = generateSuffixOrPrefix("MagicSuffix.txt");
            //extract suffix, statistic text, and value
             suffix = lineSuffix.replaceFirst("\\t.*", "");
             statisticTextSuffix = lineSuffix.split("\\t")[1];
             int minSuffix = Integer.parseInt(lineSuffix.split("\\t")[2]);
             int maxSuffix = Integer.parseInt(lineSuffix.split("\\t")[3]);
             valueSuffix = random.nextInt(minSuffix, maxSuffix+1);
        }

        int valuePrefix = 0;
        String statisticTextPrefix = null;
        if(randPrefix == 1){
            String linePrefix = generateSuffixOrPrefix("MagicPrefix.txt");
            //extract suffix, statistic text, and value
             prefix = linePrefix.replaceFirst("\\t.*", "");
             statisticTextPrefix = linePrefix.split("\\t")[1];
             int minPrefix = Integer.parseInt(linePrefix.split("\\t")[2]);
             int maxPrefix = Integer.parseInt(linePrefix.split("\\t")[3]);
             valuePrefix = random.nextInt(minPrefix, maxPrefix+1);
        }
        String fullName = findDrop;
        if(valueSuffix!=0 && valuePrefix!=0){
            fullName = prefix + " " + findDrop + " " + suffix;
        }
        else if(valueSuffix!=0 && valuePrefix==0){
            fullName = findDrop + " " + suffix;
        }
        else if(valueSuffix==0 && valuePrefix!=0){
            fullName = prefix + " " + findDrop;
        }

        //Output
        System.out.println("--------------------------------------------------");
        System.out.println("Fighting " + classMonstats + "...");
        System.out.println("You have slaid " + classMonstats + "!");
        System.out.println(classMonstats + " dropped:");
        System.out.println("");
        System.out.println(fullName);
        System.out.println("Defense: " + defence);
        if(valueSuffix!=0){
            System.out.println(valueSuffix + " " + statisticTextSuffix);
        }
        if(valuePrefix!=0){
            System.out.println(valuePrefix + " " + statisticTextPrefix);
        }
        System.out.println("--------------------------------------------------");
}
}
