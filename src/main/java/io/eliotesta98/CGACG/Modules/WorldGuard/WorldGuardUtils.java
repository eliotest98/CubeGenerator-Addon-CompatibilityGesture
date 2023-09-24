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
import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class WorldGuardUtils {

    private static final ArrayList<String> regionFlags = Main.instance.getConfigGestion().getRegionFlags();
    private static final boolean debug = Main.instance.getConfigGestion().getDebug().get("Compatibility");
    private static final DebugUtils debugUtils = new DebugUtils();

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
        if (debug) {
            debugUtils.addLine("Region Creation");
            debugUtils.addLine("Informations:");
            debugUtils.addLine("World: " + world.getName());
            debugUtils.addLine("Region Name: " + regionName);
            debugUtils.debug("WorldGuard compatibility");
        }
        RegionManager regionManager = getRegionManager(world);
        ProtectedRegion protectedRegion = new ProtectedCuboidRegion(regionName, angle1, angle2);

        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        for (String flag : regionFlags) {
            String[] split = flag.split(":");
            try {
                StateFlag stateFlag = (StateFlag) registry.get(split[0]);
                protectedRegion.setFlag(stateFlag, StateFlag.State.valueOf(split[1]));
            } catch (NullPointerException e) {
                Bukkit.getServer().getConsoleSender().sendMessage("&cError with a flag set in region! This flag &6" + split[0] + "6c not exist!");
                e.printStackTrace();
            }
        }

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
