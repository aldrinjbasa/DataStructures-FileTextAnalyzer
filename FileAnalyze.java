import java.util.*;
import java.io.*;


public class App {
    public static ArrayList<File> listOfFiles = new ArrayList<File>();

    public static void main(String[] args) throws Exception {
        Stack stack = new Stack();
        if (args.length == 0) {
            //File self =
                    new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()); //EXPERIMENTAL
            File self = new File("D:\\IdeaProjects\\Prog1\\src\\main\\java\\edu\\sdsu\\cs\\App.java");
            FileWriter outputFile = new FileWriter("App.java.stats", true);
            PrintWriter printOutputIntoFile = new PrintWriter(outputFile);
            listOfFiles.add(self);
            printOutputIntoFile.println(longestLineLength(listOfFiles));
            printOutputIntoFile.println(averageLineLength(listOfFiles));
            printOutputIntoFile.println(uniqueTokensCaseSensitive(listOfFiles));
            printOutputIntoFile.println(uniqueTokensCaseInsensitive(listOfFiles));
            printOutputIntoFile.println(numberOfTotalTokens(listOfFiles));
            printOutputIntoFile.println(mostUsedToken(listOfFiles));
            printOutputIntoFile.println(mostUsedTokenFrequency(listOfFiles));
            printOutputIntoFile.println(tenMostUsedTokenFrequency(listOfFiles));
            printOutputIntoFile.println(tenLeastUsedTokenFrequency(listOfFiles));
            printOutputIntoFile.flush();
        } else if (args.length > 0) {
            FileWriter outputFile = new FileWriter("App.java.stats", true);
            PrintWriter printOutputIntoFile = new PrintWriter(outputFile);
            printOutputIntoFile.println(longestLineLength(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(averageLineLength(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(uniqueTokensCaseSensitive(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(uniqueTokensCaseInsensitive(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(numberOfTotalTokens(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(mostUsedToken(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(mostUsedTokenFrequency(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(tenMostUsedTokenFrequency(retrieveFiles(args[0], listOfFiles)));
            listOfFiles.clear();
            printOutputIntoFile.println(tenLeastUsedTokenFrequency(retrieveFiles(args[0], listOfFiles)));
            printOutputIntoFile.flush();

        }

    }

    private static ArrayList<File> retrieveFiles(String inputDirectory, ArrayList<File> fileList) throws IOException { //LEFT OFF HERE
        File directory = new File(inputDirectory);
        File[] directoryFiles = directory.listFiles();

        for (int numberOfFiles = 0; numberOfFiles < directoryFiles.length; numberOfFiles++) {
            if (directoryFiles[numberOfFiles].isFile()) {
                fileList.add(directoryFiles[numberOfFiles].getCanonicalFile());
            } else if (directoryFiles[numberOfFiles].isDirectory()) {
                retrieveFiles(directoryFiles[numberOfFiles].getCanonicalPath(), fileList);
            }

        }
        return fileList;
    }

    private static String longestLineLength(ArrayList<File> file) throws IOException {
        int longestLength = 0;
        int currentLength = 0;
        int amountOfFiles = 0;
        String currentLine = "";
        String outputToFile = "";
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(currentLine);
                if (stringBuffer.toString().length() > longestLength) {
                    longestLength = stringBuffer.toString().length();
                }
                stringBuffer.delete(0, stringBuffer.length() - 1);
            }
            System.out.println("Longest Length for File " + (amountOfFiles + 1) + ": " + longestLength);
            outputToFile = ("Longest Length for File " + (amountOfFiles + 1) + ": " + longestLength + "\n");
            return outputToFile;
        }
        return outputToFile;
    }

    private static String averageLineLength(ArrayList<File> file) throws IOException {
        int currentLength = 0;
        int averageLength = 0;
        int amountOfFiles = 0;
        String currentLine = "";
        String outputToFile = "";
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            int lineCount = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(currentLine);
                stringBuffer.append("\n");
                lineCount = lineCount + 1;
            }
            averageLength = ((stringBuffer.toString().length() / lineCount));
            System.out.println("Average Line Length for File " + (amountOfFiles + 1) + ": " + averageLength);
            outputToFile = ("Average Line Length for File " + (amountOfFiles + 1) + ": " + averageLength + "\n");
            return outputToFile;
        }
        return outputToFile;
    }

    private static String uniqueTokensCaseSensitive(ArrayList<File> file) throws IOException {
        int uniqueTokens = 0;
        int amountOfFiles = 0;
        String currentLine = "";
        String outputToFile = "";
        ArrayList<String> tokenList = new ArrayList<String>();
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer uniqueTokenFinder = new StringBuffer();
            tokenList.clear();
            int wordSlot = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(currentLine);
                //Start Tokenizing to add to Arraylist: uniqueTokenFinder
                for (int charSlot = 0; charSlot <= stringBuffer.length(); charSlot++) { //Start Checking Characters to find tokens
                    if (currentLine.isEmpty()) {
                        //Do Nothing
                    } else if (charSlot == stringBuffer.length()) {
                        if (tokenList.size() > 0) {
                            for (int compareIndex = 0; compareIndex < tokenList.size(); compareIndex++) {
                                if (uniqueTokenFinder.equals(tokenList.get(compareIndex))) {
                                    uniqueTokenFinder = new StringBuffer();
                                }
                            }
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0 && !uniqueTokenFinder.toString().isEmpty()) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();
                        }
                    } else if (stringBuffer.charAt(charSlot) != 32) {
                        uniqueTokenFinder.append(stringBuffer.charAt(charSlot));
                    } else if (stringBuffer.charAt(charSlot) == 32) {
                        if (tokenList.size() > 0) {
                            for (int compareIndex = 0; compareIndex < tokenList.size(); compareIndex++) { //For every token in the arraylist
                                if (uniqueTokenFinder.toString().equals(tokenList.get(compareIndex))) {
                                    uniqueTokenFinder = new StringBuffer();
                                }
                            }
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer(); //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                        }
                    }
                }
            }
            uniqueTokens = tokenList.size();
            System.out.println("Amount of Case Sensitive Unique Space De-lineated Tokens in File " + (amountOfFiles + 1) + ": " + uniqueTokens);
            outputToFile = ("Amount of Case Sensitive Unique Space De-lineated Tokens in File " + (amountOfFiles + 1) + ": " + uniqueTokens + "\n");
            return outputToFile;
        }
        return outputToFile;
    }

    private static String uniqueTokensCaseInsensitive(ArrayList<File> file) throws IOException {
        int uniqueTokens = 0;
        int amountOfFiles = 0;
        String currentLine = "";
        ArrayList<String> tokenList = new ArrayList<String>();
        String outputToFile = "";
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer uniqueTokenFinder = new StringBuffer();
            tokenList.clear();
            int wordSlot = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(currentLine);
                //Start Tokenizing to add to Arraylist: uniqueTokenFinder
                for (int charSlot = 0; charSlot <= stringBuffer.length(); charSlot++) { //Start Checking Characters to find tokens
                    if (currentLine.isEmpty()) {
                        //Do Nothing
                    } else if (charSlot == stringBuffer.length()) {
                        if (tokenList.size() > 0) {
                            for (int compareIndex = 0; compareIndex < tokenList.size(); compareIndex++) {
                                if (uniqueTokenFinder.toString().equalsIgnoreCase(tokenList.get(compareIndex))) {
                                    uniqueTokenFinder = new StringBuffer();
                                }
                            }
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0 && !uniqueTokenFinder.toString().isEmpty()) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();
                        }
                    } else if (stringBuffer.charAt(charSlot) != 32) {
                        uniqueTokenFinder.append(stringBuffer.charAt(charSlot));
                    } else if (stringBuffer.charAt(charSlot) == 32) {
                        if (tokenList.size() > 0) {
                            for (int compareIndex = 0; compareIndex < tokenList.size(); compareIndex++) { //For every token in the arraylist
                                if (uniqueTokenFinder.toString().equalsIgnoreCase(tokenList.get(compareIndex))) {
                                    uniqueTokenFinder = new StringBuffer();
                                }
                            }
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer(); //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                        }
                    }
                }
            }
            uniqueTokens = tokenList.size();
            System.out.println("Amount of Case Insensitive Unique Space De-lineated Tokens in File " + (amountOfFiles + 1) + ": " + uniqueTokens);
            outputToFile = ("Amount of Case Insensitive Unique Space De-lineated Tokens in File " + (amountOfFiles + 1) + ": " + uniqueTokens + "\n");
            return outputToFile;
        }
        return outputToFile;
    }

    private static String numberOfTotalTokens(ArrayList<File> file) throws IOException {
        int uniqueTokens = 0;
        int amountOfFiles = 0;
        String currentLine = "";
        ArrayList<String> tokenList = new ArrayList<String>();
        String outputToFile = "";
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer uniqueTokenFinder = new StringBuffer();
            tokenList.clear();
            int wordSlot = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(currentLine);
                //Start Tokenizing to add to Arraylist: uniqueTokenFinder
                for (int charSlot = 0; charSlot <= stringBuffer.length(); charSlot++) { //Start Checking Characters to find tokens
                    if (currentLine.isEmpty()) {
                        //Do Nothing
                    } else if (charSlot == stringBuffer.length()) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0 && !uniqueTokenFinder.toString().isEmpty()) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();
                        }
                    } else if (stringBuffer.charAt(charSlot) != 32) {
                        uniqueTokenFinder.append(stringBuffer.charAt(charSlot));
                    } else if (stringBuffer.charAt(charSlot) == 32) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer(); //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                        }
                    }
                }
            }
            uniqueTokens = tokenList.size();
            System.out.println("Total Space De-lineated Tokens in File " + (amountOfFiles + 1) + ": " + uniqueTokens);
            outputToFile = ("Total Space De-lineated Tokens in File " + (amountOfFiles + 1) + ": " + uniqueTokens + "\n");
            return outputToFile;
        }
        return outputToFile;
    }

    private static String mostUsedToken(ArrayList<File> file) throws IOException {
        int amountOfFiles = 0;
        int currentFrequentTokenFrequency = 0;
        int tokenFrequency = 0;
        String currentLine = "";
        String outputToFile = "";
        int mostFrequentTokenFrequency = 0;
        ArrayList<String> tokenList = new ArrayList<String>();
        ArrayList<Integer> tokenFrequencyList = new ArrayList<Integer>();
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer uniqueTokenFinder = new StringBuffer();
            StringBuffer mostFrequentToken = new StringBuffer();
            int wordSlot = 0;
            tokenList.clear();
            tokenFrequencyList.clear();
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(currentLine);
                //Start Tokenizing to add to Arraylist: uniqueTokenFinder
                for (int charSlot = 0; charSlot <= stringBuffer.length(); charSlot++) { //Start Checking Characters to find tokens
                    if (currentLine.isEmpty()) {
                        //Do Nothing
                    } else if (charSlot == stringBuffer.length()) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0 && !uniqueTokenFinder.toString().isEmpty()) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();
                        }
                    } else if (stringBuffer.charAt(charSlot) != 32) {
                        uniqueTokenFinder.append(stringBuffer.charAt(charSlot));
                    } else if (stringBuffer.charAt(charSlot) == 32) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer(); //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                        }
                    }
                }
            }
            for (int tokenSlot = 0; tokenSlot < tokenList.size(); tokenSlot++) {
                for (int tokenCompare = 0; tokenCompare < tokenList.size(); tokenCompare++) {
                    if (tokenList.get(tokenSlot).equals(tokenList.get(tokenCompare))) {
                        currentFrequentTokenFrequency = currentFrequentTokenFrequency + 1;
                    }
                }
                tokenFrequencyList.add(tokenSlot, currentFrequentTokenFrequency);
                currentFrequentTokenFrequency = 0; //Reset
            }
            tokenFrequency = tokenFrequencyList.size();
            for (int frequencySlot = 0; frequencySlot < tokenFrequencyList.size(); frequencySlot++) {
                if (tokenFrequencyList.get(frequencySlot) > mostFrequentTokenFrequency) {
                    mostFrequentTokenFrequency = tokenFrequencyList.get(frequencySlot);
                    mostFrequentToken = new StringBuffer();
                    mostFrequentToken.append(tokenList.get(frequencySlot));
                }
            }
            System.out.println("The most frequent token in File " + (amountOfFiles + 1) + ": " + mostFrequentToken);
            outputToFile = ("The most frequent token in File " + (amountOfFiles + 1) + ": " + mostFrequentToken);
            mostFrequentTokenFrequency = 0;
        }
        return outputToFile;
    }

    private static String mostUsedTokenFrequency(ArrayList<File> file) throws IOException {
        int amountOfFiles = 0;
        int currentFrequentTokenFrequency = 0;
        int tokenFrequency = 0;
        String currentLine = "";
        String outputToFile = "";
        ArrayList<String> tokenList = new ArrayList<String>();
        ArrayList<Integer> tokenFrequencyList = new ArrayList<Integer>();
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer uniqueTokenFinder = new StringBuffer();
            StringBuffer mostFrequentToken = new StringBuffer();
            int wordSlot = 0;
            int mostFrequentTokenFrequency = 0;
            tokenList.clear();
            tokenFrequencyList.clear();
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(currentLine);
                //Start Tokenizing to add to Arraylist: uniqueTokenFinder
                for (int charSlot = 0; charSlot <= stringBuffer.length(); charSlot++) { //Start Checking Characters to find tokens
                    if (currentLine.isEmpty()) {
                        //Do Nothing
                    } else if (charSlot == stringBuffer.length()) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0 && !uniqueTokenFinder.toString().isEmpty()) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();
                        }
                    } else if (stringBuffer.charAt(charSlot) != 32) {
                        uniqueTokenFinder.append(stringBuffer.charAt(charSlot));
                    } else if (stringBuffer.charAt(charSlot) == 32) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer(); //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                        }
                    }
                }
            }
            for (int tokenSlot = 0; tokenSlot < tokenList.size(); tokenSlot++) {
                for (int tokenCompare = 0; tokenCompare < tokenList.size(); tokenCompare++) {
                    if (tokenList.get(tokenSlot).equals(tokenList.get(tokenCompare))) {
                        currentFrequentTokenFrequency = currentFrequentTokenFrequency + 1;
                    }
                }
                tokenFrequencyList.add(tokenSlot, currentFrequentTokenFrequency);
                currentFrequentTokenFrequency = 0; //Reset
            }
            tokenFrequency = tokenFrequencyList.size();
            for (int frequencySlot = 0; frequencySlot < tokenFrequencyList.size(); frequencySlot++) {
                if (tokenFrequencyList.get(frequencySlot) > mostFrequentTokenFrequency) {
                    mostFrequentTokenFrequency = tokenFrequencyList.get(frequencySlot);
                    mostFrequentToken = new StringBuffer();
                    mostFrequentToken.append(tokenList.get(frequencySlot));
                }
            }
            System.out.println("Count of most frequent token in File " + (amountOfFiles + 1) + ": " + mostFrequentTokenFrequency);
            outputToFile = ("Count of most frequent token in File " + (amountOfFiles + 1) + ": " + mostFrequentTokenFrequency);
            return outputToFile;
        }
        return outputToFile;
    }

    private static String tenMostUsedTokenFrequency(ArrayList<File> file) throws IOException {
        int amountOfFiles = 0;
        int currentFrequentTokenFrequency = 0;
        int tokenFrequency = 0;
        String currentLine = "";
        StringBuffer outputToFile = new StringBuffer();
        ArrayList<String> tokenList = new ArrayList<String>();
        ArrayList<Integer> tokenFrequencyList = new ArrayList<Integer>();
        ArrayList<String> tenTokensList = new ArrayList<String>();
        ArrayList<Integer> tenFrequencyList = new ArrayList<Integer>();
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer uniqueTokenFinder = new StringBuffer();
            StringBuffer mostFrequentToken = new StringBuffer();
            int wordSlot = 0;
            int mostFrequentTokenFrequency = 0;
            tokenList.clear();
            tokenFrequencyList.clear();
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(currentLine);
                //Start Tokenizing to add to Arraylist: uniqueTokenFinder
                for (int charSlot = 0; charSlot <= stringBuffer.length(); charSlot++) { //Start Checking Characters to find tokens
                    if (currentLine.isEmpty()) {
                        //Do Nothing
                    } else if (charSlot == stringBuffer.length()) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0 && !uniqueTokenFinder.toString().isEmpty()) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();
                        }
                    } else if (stringBuffer.charAt(charSlot) != 32) {
                        uniqueTokenFinder.append(stringBuffer.charAt(charSlot));
                    } else if (stringBuffer.charAt(charSlot) == 32) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer(); //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                        }
                    }
                }
            }
            for (int tokenSlot = 0; tokenSlot < tokenList.size(); tokenSlot++) {
                for (int tokenCompare = 0; tokenCompare < tokenList.size(); tokenCompare++) {
                    if (tokenList.get(tokenSlot).equals(tokenList.get(tokenCompare))) {
                        currentFrequentTokenFrequency = currentFrequentTokenFrequency + 1;
                    }
                }
                tokenFrequencyList.add(tokenSlot, currentFrequentTokenFrequency);
                currentFrequentTokenFrequency = 0; //Reset
            }
            tokenFrequency = tokenFrequencyList.size();
            for (int frequencySlot = 0; frequencySlot < tokenFrequencyList.size(); frequencySlot++) {
                if (tokenFrequencyList.get(frequencySlot) > mostFrequentTokenFrequency) {
                    mostFrequentTokenFrequency = tokenFrequencyList.get(frequencySlot);
                    mostFrequentToken = new StringBuffer();
                    mostFrequentToken.append(tokenList.get(frequencySlot));
                }
            }
            for (int tokenSlot = 0; tokenSlot < 10 && tokenSlot < tokenList.size(); tokenSlot++) {
                tenTokensList.add(tokenList.get(tokenSlot));
                tenFrequencyList.add(tokenFrequencyList.get(tokenSlot));
            }
            for (int tokenSlot = 0; tokenSlot < tenTokensList.size(); tokenSlot++) {
                for (int tokenCompare = 10; tokenCompare >= 10 && tokenCompare < tokenList.size(); tokenCompare++) {
                    if (tenFrequencyList.get(tokenSlot) > tokenFrequencyList.get(tokenCompare)) {
                        //Do Nothing
                    } else if (tenTokensList.contains(tokenList.get(tokenCompare))) {
                        //Do Nothing
                    } else if (tokenFrequencyList.get(tokenSlot) < tokenFrequencyList.get(tokenCompare)) {
                        tenFrequencyList.set(tokenSlot, tokenFrequencyList.get(tokenCompare));
                        tenTokensList.set(tokenSlot, tokenList.get(tokenCompare));
                    }
                }
            }
            outputToFile.append("Ten Most Frequent Tokens in File: \n");
            System.out.println("Ten Most Frequent Tokens in File: " + (amountOfFiles + 1) + ":");
            for (int printList = 0; printList < tenTokensList.size(); printList++) {
                System.out.println(tenTokensList.get(printList) + " with a count of " + tenFrequencyList.get(printList));
                outputToFile.append(tenTokensList.get(printList) + " with a count of " + tenFrequencyList.get(printList) + "\n");
            }
            tenTokensList.clear();
            tenFrequencyList.clear();
        }
        return outputToFile.toString();
    }

    private static String tenLeastUsedTokenFrequency(ArrayList<File> file) throws IOException {
        int amountOfFiles = 0;
        int currentFrequentTokenFrequency = 0;
        int tokenFrequency = 0;
        String currentLine = "";
        StringBuffer outputToFile = new StringBuffer();
        ArrayList<String> tokenList = new ArrayList<String>();
        ArrayList<Integer> tokenFrequencyList = new ArrayList<Integer>();
        ArrayList<String> tenTokensList = new ArrayList<String>();
        ArrayList<Integer> tenFrequencyList = new ArrayList<Integer>();
        for (amountOfFiles = 0; amountOfFiles < listOfFiles.size(); amountOfFiles++) {
            FileReader fileReader = new FileReader(listOfFiles.get(amountOfFiles));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer uniqueTokenFinder = new StringBuffer();
            StringBuffer mostFrequentToken = new StringBuffer();
            int wordSlot = 0;
            int mostFrequentTokenFrequency = 0;
            tokenList.clear();
            tokenFrequencyList.clear();
            tenFrequencyList.clear();
            tenTokensList.clear();
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(currentLine);
                //Start Tokenizing to add to Arraylist: uniqueTokenFinder
                for (int charSlot = 0; charSlot <= stringBuffer.length(); charSlot++) { //Start Checking Characters to find tokens
                    if (currentLine.isEmpty()) {
                        //Do Nothing
                    } else if (charSlot == stringBuffer.length()) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0 && !uniqueTokenFinder.toString().isEmpty()) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();
                        }
                    } else if (stringBuffer.charAt(charSlot) != 32) {
                        uniqueTokenFinder.append(stringBuffer.charAt(charSlot));
                    } else if (stringBuffer.charAt(charSlot) == 32) {
                        if (tokenList.size() > 0) {
                            if (uniqueTokenFinder.length() != 0) {
                                tokenList.add(wordSlot, uniqueTokenFinder.toString());
                                uniqueTokenFinder = new StringBuffer(); //clear the tokenizer
                            }
                        } else if (tokenList.size() == 0) {
                            tokenList.add(wordSlot, uniqueTokenFinder.toString());
                            uniqueTokenFinder = new StringBuffer();    //clear the tokenizer
                        }
                    }
                }
            }
            for (int tokenSlot = 0; tokenSlot < tokenList.size(); tokenSlot++) {
                for (int tokenCompare = 0; tokenCompare < tokenList.size(); tokenCompare++) {
                    if (tokenList.get(tokenSlot).equals(tokenList.get(tokenCompare))) {
                        currentFrequentTokenFrequency = currentFrequentTokenFrequency + 1;
                    }
                }
                tokenFrequencyList.add(tokenSlot, currentFrequentTokenFrequency);
                currentFrequentTokenFrequency = 0; //Reset
            }
            tokenFrequency = tokenFrequencyList.size();
            for (int frequencySlot = 0; frequencySlot < tokenFrequencyList.size(); frequencySlot++) {
                if (tokenFrequencyList.get(frequencySlot) > mostFrequentTokenFrequency) {
                    mostFrequentTokenFrequency = tokenFrequencyList.get(frequencySlot);
                    mostFrequentToken = new StringBuffer();
                    mostFrequentToken.append(tokenList.get(frequencySlot));
                }
            }
            for (int tokenSlot = 0; tokenSlot < 10 && tokenSlot < tokenList.size(); tokenSlot++) {
                tenTokensList.add(tokenList.get(tokenSlot));
                tenFrequencyList.add(tokenFrequencyList.get(tokenSlot));
            }
            for (int tokenSlot = 0; tokenSlot < tenTokensList.size(); tokenSlot++) {
                for (int tokenCompare = 10; tokenCompare >= 10 && tokenCompare < tokenList.size(); tokenCompare++) {
                    if (tenFrequencyList.get(tokenSlot) < tokenFrequencyList.get(tokenCompare)) {
                        //Do Nothing
                    } else if (tenTokensList.contains(tokenList.get(tokenCompare))) {
                        //Do Nothing
                    } else if (tokenFrequencyList.get(tokenSlot) > tokenFrequencyList.get(tokenCompare)) {
                        tenFrequencyList.set(tokenSlot, tokenFrequencyList.get(tokenCompare));
                        tenTokensList.set(tokenSlot, tokenList.get(tokenCompare));
                    }
                }
            }

            outputToFile.append("Ten Least Frequent Tokens in File: \n");
            System.out.println("Ten Least Frequent Tokens in File: " + (amountOfFiles + 1) + ":");
            for (int printList = 0; printList < tenTokensList.size(); printList++) {
                System.out.println(tenTokensList.get(printList) + " with a count of " + tenFrequencyList.get(printList));
                outputToFile.append(tenTokensList.get(printList) + " with a count of " + tenFrequencyList.get(printList) + "\n");
            }
            tenTokensList.clear();
            tenFrequencyList.clear();
        }
        return outputToFile.toString();
    }
}
