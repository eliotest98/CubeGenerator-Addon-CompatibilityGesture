package io.eliotesta98.CGACG.Modules.McMMO;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

public class McMMoUtils {

    private static McMMOPlayer getPlayerProfile(Player player) {
        return UserManager.getPlayer(player);
    }

    public static void setBlockState(Block block) {
        BlockState blockState = block.getState();
        mcMMO.getPlaceStore().setFalse(blockState);
    }

    public static void addExp(Block block, McMMOPlayer mcMMOPlayer) {
        BlockState blockState = block.getState();
        ExperienceAPI.addXpFromBlock(blockState, mcMMOPlayer);
    }

    public static boolean removeState(Player player, Block block) {
        McMMOPlayer mcMMOPlayer = getPlayerProfile(player);
        // Check if the profile is loaded
        if (mcMMOPlayer == null) {
            /* Remove metadata from placed watched blocks */
            setBlockState(block);
            return false;
        }
        addExp(block,mcMMOPlayer);
        return true;
    }

}
