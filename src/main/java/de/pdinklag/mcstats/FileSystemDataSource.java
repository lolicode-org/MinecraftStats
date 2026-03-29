package de.pdinklag.mcstats;

import java.nio.file.Path;

/**
 * Describes a MinecraftStats data source on the local file system.
 */
public class FileSystemDataSource implements DataSource {
    private static final String ADVANCEMENTS_PATH_NAME = "advancements";
    private static final String STATS_PATH_NAME = "stats";

    private final Path serverPath;
    private final String worldName;
    private final String customStatsPath;
    private final String customAdvancementsPath;

    /**
     * Constructs a data source.
     * 
     * @param serverPath
     * @param worldName
     */
    public FileSystemDataSource(Path serverPath, String worldName) {
        this(serverPath, worldName, null, null);
    }

    /**
     * Constructs a data source with custom paths.
     * 
     * @param serverPath
     * @param worldName
     * @param customStatsPath custom path for stats (relative to world), or null for default
     * @param customAdvancementsPath custom path for advancements (relative to world), or null for default
     */
    public FileSystemDataSource(Path serverPath, String worldName, String customStatsPath, String customAdvancementsPath) {
        this.serverPath = serverPath;
        this.worldName = worldName;
        this.customStatsPath = customStatsPath;
        this.customAdvancementsPath = customAdvancementsPath;
    }

    @Override
    public Path getServerPath() {
        return serverPath;
    }

    @Override
    public Path getPlayerStatsPath() {
        String statsPathName = customStatsPath != null ? customStatsPath : STATS_PATH_NAME;
        return serverPath.resolve(worldName).resolve(statsPathName);
    }

    @Override
    public Path getPlayerAdvancementsPath() {
        String advancementsPathName = customAdvancementsPath != null ? customAdvancementsPath : ADVANCEMENTS_PATH_NAME;
        return serverPath.resolve(worldName).resolve(advancementsPathName);
    }
}
