package de.pdinklag.mcstats;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A player filter that excludes players based on name prefix or suffix patterns.
 */
public class PlayerNamePatternFilter implements PlayerFilter {
    private final ArrayList<String> excludedPrefixes = new ArrayList<>();
    private final ArrayList<String> excludedSuffixes = new ArrayList<>();

    /**
     * Constructs an empty filter.
     */
    public PlayerNamePatternFilter() {
    }

    /**
     * Adds a name prefix to exclude.
     * @param prefix the prefix to exclude.
     */
    public void addPrefix(String prefix) {
        excludedPrefixes.add(prefix);
    }

    /**
     * Adds name prefixes to exclude.
     * @param prefixes the prefixes to exclude.
     */
    public void addAllPrefixes(Collection<String> prefixes) {
        excludedPrefixes.addAll(prefixes);
    }

    /**
     * Adds a name suffix to exclude.
     * @param suffix the suffix to exclude.
     */
    public void addSuffix(String suffix) {
        excludedSuffixes.add(suffix);
    }

    /**
     * Adds name suffixes to exclude.
     * @param suffixes the suffixes to exclude.
     */
    public void addAllSuffixes(Collection<String> suffixes) {
        excludedSuffixes.addAll(suffixes);
    }

    @Override
    public boolean filter(Player player) {
        String name = player.getProfile().getName();
        if (name == null || name.isEmpty()) {
            return true; // Keep players without names (they'll be filtered elsewhere if needed)
        }

        // Check if name starts with any excluded prefix
        for (String prefix : excludedPrefixes) {
            if (name.startsWith(prefix)) {
                return false;
            }
        }

        // Check if name ends with any excluded suffix
        for (String suffix : excludedSuffixes) {
            if (name.endsWith(suffix)) {
                return false;
            }
        }

        return true;
    }
}
