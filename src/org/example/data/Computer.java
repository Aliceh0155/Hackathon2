package org.example.data;

import org.example.data.CPU;
import org.example.data.Disk;
import org.example.data.RAM;

public class Computer {
    private Disk disk;
    private CPU cpu;
    private RAM ram;

    public Computer(Disk disk, CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.disk = disk;
        this.ram = ram;
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
}

