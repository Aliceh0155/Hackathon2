 package org.example.programs;

import org.example.data.CPU;
import org.example.data.Disk;
import org.example.data.RAM;
    public class Programs {

        private String name;

        public String getName() {
            return name;
        }

        public CPU getCpu() {
            return cpu;
        }

        public RAM getRam() {
            return ram;
        }

        public Disk getDisk() {
            return disk;
        }

        private CPU cpu;
        private RAM ram;
        private Disk disk;


        public Programs(String name, CPU cpu, RAM ram, Disk disk) {
            this.name = name;
            this.cpu = cpu;
            this.ram = ram;
            this.disk = disk;
        }


        public static Programs[] createSamplePrograms() {
            Programs[] samplePrograms = new Programs[3];

            CPU cpu1 = new CPU(2.5, 4);
            RAM ram1 = new RAM(8);
            Disk disk1 = new Disk(100, 20, 120);
            samplePrograms[0] = new Programs("Program 1", cpu1, ram1, disk1);

            CPU cpu2 = new CPU(3.0, 8);
            RAM ram2 = new RAM(16);
            Disk disk2 = new Disk(200, 50, 250);
            samplePrograms[1] = new Programs("Program 2", cpu2, ram2, disk2);

            CPU cpu3 = new CPU(2.2, 2);
            RAM ram3 = new RAM(4);
            Disk disk3 = new Disk(50, 10, 60);
            samplePrograms[2] = new Programs("Program 3", cpu3, ram3, disk3);

            return samplePrograms;
        }
    }

