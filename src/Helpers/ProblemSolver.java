package Helpers;

import java.io.*;
import java.util.*;

public abstract class ProblemSolver {
    String inputFilePath;
    String outputFilePath;

    public ProblemSolver() {
        String packageName = this.getClass().getPackage().getName().replace('.', '/');
        String baseDir = "src/" + packageName;
        this.inputFilePath = baseDir + "/input.txt";
        this.outputFilePath = baseDir + "/output.txt";
    }

    public void readInput(){
        File inputFile = new File(this.inputFilePath);
        File outputFile = new File(this.outputFilePath);
        

        try (Scanner scanner = new Scanner(inputFile); PrintStream out = new PrintStream(new FileOutputStream(outputFile))) {
            // Redirect System.out to output.txt
            System.setOut(out);

            while (scanner.hasNextLine()) {
                String[] inputLine = Arrays.stream(scanner.nextLine().trim().split("&"))
                    .map(String::trim)
                    .toArray(String[]::new);
                this.processParameters(inputLine);
                System.out.println("***********************************");
            }

        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
    }

    public abstract void processParameters(String[] args);
}
