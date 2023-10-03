package io.eliotesta98.CGACG.Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class DebugUtils {

    private ArrayList<String> lines = new ArrayList<String>();

    public DebugUtils(ArrayList<String> lines) {
        this.lines = lines;
    }

    public DebugUtils() {

    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public ArrayList<String> getLines() {
        return this.lines;
    }

    public String getLine(int index) {
        return this.lines.get(index);
    }

    public void setLine(String newLine, int index) {
        this.lines.set(index, newLine);
    }

    public void addLine(String newLine) {
        this.lines.add(newLine);
    }

    public void removeLine(int index) {
        this.lines.remove(index);
    }

    public int getSize() {
        return this.lines.size();
    }

    public boolean containLine(String line) {
        return this.lines.contains(line);
    }

    public void removeAll() {
        this.lines.removeAll(this.lines);
    }

    public void debug(String type) {
        String debug = " Debug CubeGenerator for " + type;
        Bukkit.getServer().getConsoleSender().sendMessage(" ");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + debug);
        Bukkit.getServer().getConsoleSender().sendMessage(" ");
        for (int i = 0; i < this.getSize(); i++) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + this.getLine(i));
        }
        Bukkit.getServer().getConsoleSender().sendMessage(" ");
        debug = " Close Debug CubeGenerator for " + type;
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + debug);
        Bukkit.getServer().getConsoleSender().sendMessage(" ");
        removeAll();
    }

}
