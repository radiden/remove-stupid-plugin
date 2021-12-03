package femboygaming.removestupid;

import femboygaming.removestupid.listeners.StripEventListener;

import org.bukkit.plugin.java.JavaPlugin;
public final class RemoveStupid extends JavaPlugin {
    @Override
    public void onEnable() {
        new StripEventListener(this);
    }
}
