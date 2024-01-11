package org.example;
import java.io.IOException;

import org.example.data.CPU;
import org.example.data.Computer;
import org.example.data.Disk;
import org.example.data.RAM;
import org.example.programs.Programs;

public class Main {

    public static void main(String[] args) throws Exception {

        try {
            // Get CPU information
            CPU cpu = CPU.getCPUinfo();

            // Get RAM information
            RAM ram = RAM.getRAMinfo();

            // Get Disk information
            Disk disk = Disk.getDiskInfo();

            // Create an instance of Computer
            Computer myComputer = new Computer(disk, cpu, ram);

            // Access and print information
            System.out.println("CPU Frequency: " + myComputer.getCpu().getFrequency() + " GHz, Cores: " + myComputer.getCpu().getCores());
            System.out.println("RAM Memory: " + myComputer.getRam().getMemory() + " GB");
            System.out.println("Disk Free Space: " + myComputer.getDisk().getFree() + " GB, Used Space: " + myComputer.getDisk().getUsed() + " GB, Total Space: " + myComputer.getDisk().getTotal()+ " GB");



            // Create sample programs
            Programs[] samplePrograms = Programs.createSamplePrograms();

            // Compare computer hardware with each program
            for (Programs program : samplePrograms) {
                if (compareHardware(myComputer, program)) {
                    System.out.println(program.getName() + " matches the computer's hardware!");
                } else {
                    System.out.println(program.getName() + " does not match the computer's hardware.");
                }
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static boolean compareHardware(Computer computer, Programs program) {
        // Compare individual attributes
        return computer.getCpu().getFrequency() == program.getCpu().getFrequency() &&
                computer.getCpu().getCores() == program.getCpu().getCores() &&
                computer.getRam().getMemory() == program.getRam().getMemory() &&
                computer.getDisk().getFree() == program.getDisk().getFree() &&
                computer.getDisk().getUsed() == program.getDisk().getUsed() &&
                computer.getDisk().getTotal() == program.getDisk().getTotal();
    }

}

