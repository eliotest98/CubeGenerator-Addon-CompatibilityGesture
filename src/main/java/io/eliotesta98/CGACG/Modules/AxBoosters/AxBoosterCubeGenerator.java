package io.eliotesta98.CGACG.Modules.AxBoosters;

import com.artillexstudios.axboosters.boosters.BoosterManager;
import com.artillexstudios.axboosters.hooks.booster.BoosterHook;
import com.artillexstudios.axboosters.libs.kyori.adventure.key.Key;
import com.artillexstudios.axboosters.users.User;
import com.artillexstudios.axboosters.users.UserList;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.SellEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class AxBoosterCubeGenerator implements Listener, BoosterHook {

    @Override
    public Key getKey() {
        return Key.key("cubegenerator", "sell");
    }

    @Override
    public Material getIcon() {
        return Material.EXPERIENCE_BOTTLE;
    }

    // you should set persistent to true, otherwise AxBoosters will unregister it after a reload
    @Override
    public boolean isPersistent() {
        return true;
    }

    @EventHandler
    public void onEvent(@NotNull SellEvent event) {
        double original = event.getPrice();
        Player player = event.getSeller();

        User user = UserList.getUser(player);
        if (user == null) {
            return;
        }

        float boost = user.getBoost(this);
        if (boost == 1.0f) {
            return;
        }

        // this is the important part, make sure to use the BoosterManager#multiply method
        double boosted = BoosterManager.multiply(boost, original);
        if (boosted == original) return;

        event.setPrice(boosted);
    }

}
