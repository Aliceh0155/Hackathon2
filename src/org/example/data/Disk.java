package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Disk {

    private int total;
    private int used;
    private int free;

    public Disk(int total, int used, int free) {
        this.total = total;
        this.used = used;
        this.free = free;
    }



    public int getTotal() {
        return total;
    }



    public void setTotal(int total) {
        this.total = total;
    }



    public int getUsed() {
        return used;
    }



    public void setUsed(int used) {
        this.used = used;
    }



    public int getFree() {
        return free;
    }



    public void setFree(int free) {
        this.free = free;
    }

    public static Disk getDiskInfo() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("df -h");
        process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        // Skip the header line
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            // Assuming a specific format of the output, you need to parse and set values accordingly
            String[] parts = line.trim().split("\\s+");
            if (parts.length >= 5) {
                String totalStr = parts[1].replaceAll("[^\\d]", "");
                String usedStr = parts[2].replaceAll("[^\\d]", "");
                String freeStr = parts[3].replaceAll("[^\\d]", "");

                // Check if the strings are not empty after removing non-numeric characters
                if (!totalStr.isEmpty() && !usedStr.isEmpty() && !freeStr.isEmpty()) {
                    int total = Integer.parseInt(totalStr);
                    int used = Integer.parseInt(usedStr);
                    int free = Integer.parseInt(freeStr);

                    return new Disk(total, used, free);
                }
            }
        }

        // If no valid result is found, return a default Disk object
        return new Disk(0, 0, 0);
    }
}