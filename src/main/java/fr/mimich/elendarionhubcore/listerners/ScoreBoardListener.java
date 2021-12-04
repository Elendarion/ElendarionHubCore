package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.utils.SuperConfig;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScoreBoardListener implements Listener {

    private ElendarionHubCore main;

    private Set<FastBoard> boards;

    public ScoreBoardListener(ElendarionHubCore main) {
        this.main = main;
        this.boards = new HashSet<>();
    }

    private void loadBoards() {
        FastBoard board;
        Map<String, String> placeHolders = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            placeHolders.put("%player-name%", player.getName());
            placeHolders.put("%player-number%", String.valueOf(Bukkit.getOnlinePlayers().size()));
            placeHolders.put("%max-player%", String.valueOf(Bukkit.getMaxPlayers()));
            board = new FastBoard(player);
            board.updateTitle(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.SCOREBOARD, "title"));
            board.updateLines(this.main.getSuperConfig().getConfigTextList(SuperConfig.TextType.SCOREBOARD, "lines", placeHolders));
            this.boards.add(board);
            placeHolders.clear();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        loadBoards();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.boards.forEach(FastBoard::delete);
        this.boards.clear();
        loadBoards();
    }
}
