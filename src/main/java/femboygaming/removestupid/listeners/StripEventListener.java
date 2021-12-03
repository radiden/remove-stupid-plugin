package femboygaming.removestupid.listeners;

import femboygaming.removestupid.RemoveStupid;

import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class StripEventListener implements Listener {
    public StripEventListener(RemoveStupid plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            Material blockType = block.getType();
            Player player = event.getPlayer();
            ItemStack heldItem = player.getInventory().getItemInMainHand();

            if (blockType == Material.DIRT_PATH && block.isPreferredTool(heldItem)) {
                block.setType(Material.DIRT);
                player.playSound(block.getLocation(), Sound.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            else if (Tag.LOGS.isTagged(blockType) && block.getBlockData().getAsString().startsWith("minecraft:stripped") && block.isPreferredTool(heldItem)) {
                block.setType(Material.getMaterial(block.getType().name().replace("STRIPPED_", "")));
                player.playSound(block.getLocation(), Sound.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }
    }
}
