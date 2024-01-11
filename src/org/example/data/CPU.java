package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class CPU {

    private double frequency;
    private int cores;

    public CPU(double frequency, int cores) {
        this.frequency = frequency;
        this.cores = cores;
    }


    public double getFrequency() {
        return frequency;
    }


    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }


    public int getCores() {
        return cores;
    }


    public void setCores(int cores) {
        this.cores = cores;
    }

    /*
     * Lägg scriptet i exec argumentet för att se cpu info
     * Ni kan prova dessa i era terminaler
     * Sök gärna upp om de kanske finns något annat script ni kan nytja.
     * cmd(windows) terminal(mac)
     *
     * Windows
     * wmic cpu get caption, deviceid, name, numberofcores, maxclockspeed, status
     *
     * Mac
     * sysctl machdep.cpu
     *
     * Linux
     * lscpu
     *
     * */

    public static CPU getCPUinfo() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("sysctl machdep.cpu");
        process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        double frequency = 0.0;
        int cores = 0;

        while ((line = reader.readLine()) != null) {

            if (line.contains("machdep.cpu.brand_string")) {
                // Extract the CPU brand string, which includes the frequency
                String[] parts = line.trim().split(":");
                if (parts.length >= 2) {
                    String brandString = parts[1].trim();
                    // Extract frequency from the brand string
                    frequency = extractFrequencyFromBrandString(brandString);
                }
            } else if (line.contains("machdep.cpu.core_count")) {
                // Extract the number of cores
                String[] parts = line.trim().split(":");
                if (parts.length >= 2) {
                    String coresStr = parts[1].trim();
                    cores = Integer.parseInt(coresStr);
                }
            }
        }

        reader.close();
        return new CPU(frequency, cores);
    }

    private static double extractFrequencyFromBrandString(String brandString) {
        // Extract frequency from the brand string
        String[] words = brandString.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (words[i].endsWith("GHz") || words[i].endsWith("MHz")) {
                try {
                    return Double.parseDouble(words[i].replaceAll("[^\\d.]", ""));
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // Handle parsing error
                }
            }
        }
        return 0.0; // Return default if frequency is not found
    }
}