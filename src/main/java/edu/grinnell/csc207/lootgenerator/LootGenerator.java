package edu.grinnell.csc207.lootgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.grinnell.csc207.lootgenerator.Monstats;
import edu.grinnell.csc207.lootgenerator.TreasureClass;
import edu.grinnell.csc207.lootgenerator.Armor;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    public static String generateSuffixOrPrefix(String file) throws IOException {
        FileReader fileSuffix = new FileReader("data/small/" + file);
        BufferedReader buffSuffix = new BufferedReader(fileSuffix);
        int totalLinesSuffix = 0;
        String lineSuffix = null;
        while ((lineSuffix = buffSuffix.readLine()) != null) {
            totalLinesSuffix++;
        }
        buffSuffix.close();
        // reopen buffSuffix
        buffSuffix = new BufferedReader(new FileReader("data/small/" + file));
        Random random = new Random();
        int randomSuffixLine = random.nextInt(totalLinesSuffix);
        int currentLineNumberSuffix = 0;
        while ((lineSuffix = buffSuffix.readLine()) != null) {
            if (currentLineNumberSuffix == randomSuffixLine) {
                break;
            } else {
                currentLineNumberSuffix++;
            }
        }
        return lineSuffix;
    }

    // PARSHING FUNCTIONS
    /**
     * Transforms input from the text file monstats.txt into List of Monstats data
     * structure
     * 
     * @return the list populated with Monstats data structure (contains monster
     *         name and treasure class)
     */
    public static List<Monstats> parseMonstats() throws IOException {

        // Getting data from monstats.txt
        FileReader fileMonstats = new FileReader("data/small/monstats.txt");
        BufferedReader buffMonstats = new BufferedReader(fileMonstats);

        // populate listMonstats
        List<Monstats> listMonstats = new ArrayList<>();
        String lineMonstats = null;
        while ((lineMonstats = buffMonstats.readLine()) != null) {
            String[] monststsElements = lineMonstats.split("\\t");
            listMonstats.add(new Monstats(monststsElements[0], monststsElements[3]));
        }
        buffMonstats.close();
        return listMonstats;

    }

    /**
     * Transforms input from the text file TreasureClassEx.txt into a Map
     * 
     * @return a map where the key is a treasure class and value is TreasureClass
     *         data structure(contains treasure class as a key and array of possible
     *         drops as a value)
     */
    public static Map<String, TreasureClass> parseTreasureClass() throws IOException {

        // Getting data from TreasureClassEx.txt
        FileReader fileTC = new FileReader("data/small/TreasureClassEx.txt");
        BufferedReader buffTC = new BufferedReader(fileTC);

        // populate Map
        Map<String, TreasureClass> treasureClassMap = new HashMap<>();
        String lineTC = null;
        while ((lineTC = buffTC.readLine()) != null) {
            String[] tcElements = lineTC.split("\\t");
            String tcName = tcElements[0];
            String[] items = Arrays.copyOfRange(tcElements, 1, tcElements.length);
            treasureClassMap.put(tcName, new TreasureClass(tcName, items));
        }
        buffTC.close();
        return treasureClassMap;
    }

    /**
     * Transforms input from the text file armor.txt into a Map
     * 
     * @return a map where the key is an armor name and value is Armor
     *         data structure(contains armor name, minac, and maxac)
     * @throws IOException
     */
    public static Map<String, Armor> parseArmor() throws IOException {

        // Getting data from armor.txt
        FileReader fileDefence = new FileReader("data/small/armor.txt");
        BufferedReader buffDefence = new BufferedReader(fileDefence);

        // populate Map
        Map<String, Armor> armorMap = new HashMap<>();
        String lineDefence = null;
        while ((lineDefence = buffDefence.readLine()) != null) {
            String[] armorElements = lineDefence.split("\\t");
            String armorName = armorElements[0];
            int minac = Integer.parseInt(armorElements[1]);
            int maxac = Integer.parseInt(armorElements[2]);
            armorMap.put(armorName, new Armor(armorName, minac, maxac));
        }
        buffDefence.close();
        return armorMap;
    }

    /**
     * Transforms input from the text file MagicSuffix.txt into a Map
     * 
     * @return a map where the key is a magic suffix name and value is MagicSuffix
     *         data structure(contains suffix name, mod1code, min1code, max1code)
     * @throws IOException
     */
    public static Map<String, MagicSuffix> parseMagicSuffix() throws IOException {
        // Getting data from MagicSuffix.txt
        FileReader fileSuffix = new FileReader("data/small/MagicSuffix.txt");
        BufferedReader buffSuffix = new BufferedReader(fileSuffix);

        // populate Map
        Map<String, MagicSuffix> magicSuffixMap = new HashMap<>();
        String lineSuffix = null;
        while ((lineSuffix = buffSuffix.readLine()) != null) {
            String[] suffixElements = lineSuffix.split("\\t");
            String nameSuffix = suffixElements[0];
            String mod1code = suffixElements[1];
            int min1code = Integer.parseInt(suffixElements[2]);
            int max1code = Integer.parseInt(suffixElements[3]);
            magicSuffixMap.put(nameSuffix, new MagicSuffix(nameSuffix, mod1code, min1code, max1code));
        }
        buffSuffix.close();
        return magicSuffixMap;
    }

    /**
     * Transforms input from the text file MagicPrefix.txt into a Map
     * 
     * @return a map where the key is a magic prefix name and value is MagicPrefix
     *         data structure(contains prefix name, mod1code, min1code, max1code)
     * @throws IOException
     */
    public static Map<String, MagicPrefix> parseMagicPrefix() throws IOException {
        // Getting data from MagicPrefix.txt
        FileReader filePrefix = new FileReader("data/small/MagicPrefix.txt");
        BufferedReader buffPrefix = new BufferedReader(filePrefix);

        // populate Map
        Map<String, MagicPrefix> magicPrefixMap = new HashMap<>();
        String linePrefix = null;
        while ((linePrefix = buffPrefix.readLine()) != null) {
            String[] prefixElements = linePrefix.split("\\t");
            String namePrefix = prefixElements[0];
            String mod1code = prefixElements[1];
            int min1code = Integer.parseInt(prefixElements[2]);
            int max1code = Integer.parseInt(prefixElements[3]);
            magicPrefixMap.put(namePrefix, new MagicPrefix(namePrefix, mod1code, min1code, max1code));
        }
        buffPrefix.close();
        return magicPrefixMap;
    }

    // ALGORITHM FUNCTIONS
    /**
     * Picks a random monster to fight
     * 
     * @param monstats List of possible monsters presented as a Monstats data
     *                 structure
     *                 (contains monster name and treasure class)
     * @return a random monster to fight
     */
    private static Monstats pickMonster(List<Monstats> monstats) {
        Random random = new Random();
        int rand = random.nextInt(monstats.size());
        return monstats.get(rand);
    }

    /**
     * Picks the random drop based on the treasure class
     * 
     * @param treasureClasses a map that maps treasure class to the TreasureClass
     *                        data structure, that contains
     *                        a treasure class and possible drop items
     * @param monsterTC       treasure class of a monster that we fight
     * @return the random drop based on the treasure class
     */
    private static String fetchTreasureClass(Map<String, TreasureClass> treasureClasses,
            String monsterTC) {

        // Randomly choose one of three drops.
        Random random = new Random();
        int randOfThree = random.nextInt(3);
        String selectedDrop = treasureClasses.get(monsterTC).getItems()[randOfThree];

        // find if the selectedDrop a TC
        boolean isTC = false;
        for (String element : treasureClasses.keySet()) {
            if (element.equals(selectedDrop)) {
                isTC = true;
                break;
            }
        }

        // if selectedDrop is another treasure class, recurse; if not, return
        if (isTC) {
            return fetchTreasureClass(treasureClasses, selectedDrop);
        } else {
            return selectedDrop;
        }

    }

    /**
     * Generates base item dropped by the monster
     * 
     * @param treasureClasses a map that maps treasure class to the TreasureClass
     *                        data structure, that contains
     *                        a treasure class and possible drop items
     * @param selectedDrop    the treasure class of the monster
     * @return
     */
    private static String generateBaseItem(Map<String, TreasureClass> treasureClasses, String selectedDrop) {
        return fetchTreasureClass(treasureClasses, selectedDrop);
    }

    /**
     * Generates base stats for a base item
     * 
     * @param armorMap a map that maps armor name to Armor data structure (contains
     *                 armor name, minac, and maxac)
     * @param baseItem the name of the base item
     * @return the generated base stats for the base item
     */
    private static int generateBaseStats(Map<String, Armor> armorMap, String baseItem) {
        Random random = new Random();
        int baseStats = 0;
        if (armorMap.containsKey(baseItem)) {
            Armor armor = armorMap.get(baseItem);
            baseStats = random.nextInt(armor.minac, armor.maxac + 1);
        }
        return baseStats;
    }

    /**
     * Generates suffix and prefix for the base item
     * 
     * @param suffixMap a map that maps suffix name to MagicSuffix data structure
     *                  (contains suffix name, mod1code, mod1min, mod1max)
     * @param prefixMap a map that maps prefix name to MagicPrefix data structure
     *                  (contains prefix name, mod1code, mod1min, mod1max)
     * @return an array containing prefix name, suffix name, prefix stats, and
     *         suffix stats
     * @throws IOException
     */
    private static String[] generateAffix(
            Map<String, MagicSuffix> suffixMap,
            Map<String, MagicPrefix> prefixMap) throws IOException {

        Random random = new Random();
        String prefixName = "";
        String suffixName = "";
        String prefixStat = "";
        String suffixStat = "";

        // Generate prefix
        if (random.nextInt(2) == 1) {
            String linePrefix = generateSuffixOrPrefix("MagicPrefix.txt");

            // Split on tab
            String[] parts = linePrefix.split("\\t");
            String prefixRand = parts[0];
            MagicPrefix magicPrefix = prefixMap.get(prefixRand);

            int valuePrefix = random.nextInt(magicPrefix.mod1min, magicPrefix.mod1max + 1);
            prefixName = prefixRand;
            prefixStat = valuePrefix + " " + magicPrefix.mod1code;
        }

        // Generate suffix
        if (random.nextInt(2) == 1) {
            String lineSuffix = generateSuffixOrPrefix("MagicSuffix.txt");

            // Split on tab
            String[] parts = lineSuffix.split("\\t");
            String suffixRand = parts[0];
            MagicSuffix magicSuffix = suffixMap.get(suffixRand);
            int valueSuffix = random.nextInt(magicSuffix.mod1min, magicSuffix.mod1max + 1);
            suffixName = suffixRand;
            suffixStat = valueSuffix + " " + magicSuffix.mod1code;
        }
        return new String[] { prefixName, suffixName, prefixStat, suffixStat };
    }

    /**
     * Generates the final loot output
     * 
     * @param monster   the monster fought
     * @param findDrop  the item dropped by the monster
     * @param affixes   an array containing prefix name, suffix name, prefix stats,
     *                  and suffix stats
     * @param baseStats the base stats for the item dropped
     */
    private static void generateLoot(Monstats monster, String findDrop, String[] affixes, int baseStats) {
        System.out.println("--------------------------------------------------");
        System.out.println("Fighting " + monster.getName() + "...");
        System.out.println("You have slaid " + monster.getName() + "!");
        System.out.println(monster.getName() + " dropped:");
        System.out.println("");

        // Full name
        String fullName = findDrop;
        String prefix = affixes[0];
        String suffix = affixes[1];
        String statisticTextPrefix = affixes[2];
        String statisticTextSuffix = affixes[3];
        if (suffix != "" && prefix != "") {
            fullName = prefix + " " + findDrop + " " + suffix;
        } else if (suffix != "" && prefix == "") {
            fullName = findDrop + " " + suffix;
        } else if (suffix == "" && prefix != "") {
            fullName = prefix + " " + findDrop;
        }

        System.out.println(fullName);
        System.out.println("Defense: " + baseStats);
        if (suffix != "") {
            System.out.println(statisticTextSuffix);
        }
        if (prefix != "") {
            System.out.println(statisticTextPrefix);
        }
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) throws IOException {

        // Pick random monster
        List<Monstats> listMonstats = parseMonstats();
        Monstats monster = pickMonster(listMonstats);
        System.out.println("Random monster: " + monster.getName());
        System.out.println("Monster treasure class: " + monster.getTreasureClass());

        // Find treasure class drop
        Map<String, TreasureClass> treasureClassMap = parseTreasureClass();
        String findDrop = generateBaseItem(treasureClassMap, monster.getTreasureClass());
        System.out.println("Item dropped: " + findDrop);

        // Compute base stats for a base item
        Map<String, Armor> armorMap = parseArmor();
        int baseStats = generateBaseStats(armorMap, findDrop);
        System.out.println("Stats for item dropped: " + baseStats);

        // Generating suffix and prefix
        Map<String, MagicSuffix> suffixMap = parseMagicSuffix();
        Map<String, MagicPrefix> prefixMap = parseMagicPrefix();
        String[] affixes = generateAffix(suffixMap, prefixMap);

        generateLoot(monster, findDrop, affixes, baseStats);
    }
}
