package fr.mimich.elendarionhubcore.utils;

import fr.mimich.elendarionhubcore.ElendarionHubCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SuperConfig {

    private ElendarionHubCore main;

    public SuperConfig(ElendarionHubCore main) {
        this.main = main;
    }

    public enum TextType {
        MESSAGE,
        ITEM,
        INVENTORY,
        HELP,
        NPC,
        SCOREBOARD
    }

    private String getConfigString(String stringAddress) {
        return this.main.getConfig().getString(stringAddress);
    }

    private List<String> getConfigStringList(String stringListAddress) {
        return this.main.getConfig().getStringList(stringListAddress);
    }

    public String addressBuilder(TextType textType, String textAddress) {
        String builder = "";
        if (textType == null) {
            builder = textAddress;
        } else {
            for (TextType type : TextType.values()) {
                if (type.equals(textType)) {
                    builder = textType.toString().toLowerCase() + "." + textAddress;
                }
            }
        }
        return builder;
    }

    private String placeHolders(Map<String, String> placeHolders, String text) {
        if (!placeHolders.isEmpty())
            for (Map.Entry<String, String> entry : placeHolders.entrySet()) {
                text = text.replaceAll(entry.getKey(), entry.getValue());
            }
        return text;
    }

    public String getConfigText(TextType textType, String textAddress) {
        return getConfigText(textType, textAddress, Collections.emptyMap());
    }

    public String getConfigText(TextType textType, String textAddress, Map<String, String> placeHolders) {
        return this.main.convertColorCodes(this.placeHolders(placeHolders, this.getConfigString(addressBuilder(textType, textAddress))));
    }

    public List<String> getConfigTextList(TextType textType, String textListAddress) {
        return getConfigTextList(textType, textListAddress, Collections.emptyMap());
    }

    public List<String> getConfigTextList(TextType textType, String textListAddress, Map<String, String> placeHolders) {
        List<String> list = this.getConfigStringList(addressBuilder(textType, textListAddress)),
                finalList = new ArrayList<>();
        for (String element : list) {
            finalList.add(this.main.convertColorCodes(this.placeHolders(placeHolders, element)));
        }
        return finalList;
    }

}
