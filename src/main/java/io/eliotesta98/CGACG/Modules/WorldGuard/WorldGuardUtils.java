package io.eliotesta98.CGACG.Modules.WorldGuard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.World;

public class WorldGuardUtils {

    private static final StateFlag.State allow = StateFlag.State.ALLOW;

    public static RegionManager getRegionManager(World world) {
        // prendo il container della regione
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        // prendo le regions del mondo
        return container.get(BukkitAdapter.adapt(world));
    }

    public static BlockVector3 getVectorFromLocation(Location location) {
        return BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static void createRegion(World world, String regionName, BlockVector3 angle1, BlockVector3 angle2) {
        RegionManager regionManager = getRegionManager(world);
        ProtectedRegion protectedRegion = new ProtectedCuboidRegion(regionName, angle1, angle2);

        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        StateFlag mine = (StateFlag) registry.get("RevEnchants-Mine");
        StateFlag explosive = (StateFlag) registry.get("RevEnchants-Explosive");
        StateFlag jackHammer = (StateFlag) registry.get("RevEnchants-JackHammer");
        StateFlag thorHammer = (StateFlag) registry.get("RevEnchants-ThorHammer");
        StateFlag laser = (StateFlag) registry.get("RevEnchants-Laser");
        StateFlag metheorShower = (StateFlag) registry.get("RevEnchants-MeteorShower");
        StateFlag bigBang = (StateFlag) registry.get("RevEnchants-BigBang");
        protectedRegion.setFlag(mine, allow);
        protectedRegion.setFlag(explosive, allow);
        protectedRegion.setFlag(jackHammer, allow);
        protectedRegion.setFlag(thorHammer, allow);
        protectedRegion.setFlag(laser, allow);
        protectedRegion.setFlag(metheorShower, allow);
        protectedRegion.setFlag(bigBang, allow);

        regionManager.addRegion(protectedRegion);
        try {
            regionManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    public static void removeRegion(World world, String regionName) {
        RegionManager regionManager = getRegionManager(world);
        regionManager.removeRegion(regionName);
        try {
            regionManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }
}
