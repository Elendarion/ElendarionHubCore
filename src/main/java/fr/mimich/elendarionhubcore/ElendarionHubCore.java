package fr.mimich.elendarionhubcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fr.mimich.elendarionhubcore.commands.*;
import fr.mimich.elendarionhubcore.inventorys.ServerInfosInventory;
import fr.mimich.elendarionhubcore.inventorys.ServerSelectorInventory;
import fr.mimich.elendarionhubcore.listerners.*;
import fr.mrmicky.fastinv.FastInvManager;
import net.jitse.npclib.NPCLib;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ElendarionHubCore extends JavaPlugin {

    private NPCLib npcLib;
    private SuperConfig superConfig;
    private ServerTeleport serverTeleport;

    private JoinItem joinItem;
    private Spawn spawn;
    private Show show;
    private CustomHelp customHelp;
    private NPC npc;

    private InventoryUtil inventoryUtil;
    private ServerSelectorInventory serverSelectorInventory;
    private ServerInfosInventory serverInfosInventory;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.npcLib = new NPCLib(this);
        FastInvManager.register(this);
        this.superConfig = new SuperConfig(this);
        this.serverTeleport = new ServerTeleport(this);

        this.joinItem = new JoinItem(this);
        this.spawn = new Spawn(this);
        this.show = new Show(this);
        this.customHelp = new CustomHelp(this);
        this.npc = new NPC(this);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ScoreBoardListener(this), this);
        pm.registerEvents(new SpawnVoidListener(this), this);
        pm.registerEvents(new UtilsListeners(this), this);
        pm.registerEvents(new WelcomeListener(this), this);
        pm.registerEvents(new PermissionsRemoverListener(this), this);
        pm.registerEvents(new JoinListener(this), this);
        pm.registerEvents(new JoinItemListener(this), this);
        pm.registerEvents(new ShowListener(this), this);
        pm.registerEvents(new NPCListener(this), this);
        pm.registerEvents(new SuperGunListener(this), this);
        getCommand("help").setExecutor(new CustomHelpCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("ping").setExecutor(new PingComand(this));
        getCommand("show").setExecutor(new ShowCommand(this));
        getCommand("infos").setExecutor(new InfosCommand(this));

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new SuperGunListener(this));
        protocolManager.addPacketListener(new ShowListener(this));

        this.inventoryUtil = new InventoryUtil(this);
        this.serverInfosInventory = new ServerInfosInventory(this);
        this.serverSelectorInventory = new ServerSelectorInventory(this);
    }


    public String convertColorCodes(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public NPCLib getNpcLib() {
        return npcLib;
    }

    public SuperConfig getSuperConfig() {
        return superConfig;
    }

    public ServerTeleport getServerTeleport() {
        return serverTeleport;
    }

    public JoinItem getJoinItem() {
        return joinItem;
    }

    public Spawn getSpawn() {
        return spawn;
    }

    public Show getShow() {
        return show;
    }

    public CustomHelp getCustomHelp() {
        return customHelp;
    }

    public NPC getNpc() {
        return npc;
    }

    public InventoryUtil getInventoryUtil() {
        return inventoryUtil;
    }

    public ServerSelectorInventory getServerSelectInventory() {
        return serverSelectorInventory;
    }

    public ServerInfosInventory getInfosInventory() {
        return serverInfosInventory;
    }
}
