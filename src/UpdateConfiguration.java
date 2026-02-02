import java.util.*;

public class UpdateConfiguration {
    public static final List<UpdateStep> FULL_ROUTE = Arrays.asList(
            new UpdateStep(0, 1560, "Sybase 9.0", "Sybase 9.0", false),
            new UpdateStep(1560, 2718, "Sybase 9.0", "Sybase 9.0", false),
            new UpdateStep(2718, 3407, "Sybase 12", "Sybase 9.0", true),
            new UpdateStep(3407, 3856, "Sybase 12", "Sybase 12", false),
            new UpdateStep(3856, 3999, "Sybase 12", "Sybase 12", false),
            new UpdateStep(3999, 4477, "Sybase 16", "Sybase 12", true),
            new UpdateStep(4477, 4773, "Sybase 16", "Sybase 16", false),
            new UpdateStep(4773, 5603, "Sybase 17", "Sybase 16", true),
            new UpdateStep(5603, 5917, "Sybase 17", "Sybase 17", false)
    );

    public static final Map<Integer, Integer> VERSION_TO_ROUTE_INDEX = new HashMap<>();
    public static final Set<Integer> ANCIENT_VERSIONS = Set.of(872, 875, 931, 937, 968, 987);

    static {
        int[] group0 = {1084, 1104, 1140, 1155, 1190, 1198, 1203, 1274, 1289, 1312, 1350, 1356, 1380, 1448, 1533};
        int[] group1 = {1560, 1622, 1673, 1709, 1825, 1883, 1892, 1992, 2611, 2655};
        int[] group2 = {2718, 3252, 3329};
        int[] group3 = {3407, 3561};
        int[] group4 = {3632, 3658, 3856};
        int[] group5 = {3999, 4110, 4202, 4272};
        int[] group6 = {4336, 4477, 4599, 4690};
        int[] group7 = {4773, 4842, 4904, 4965, 5029, 5118, 5199, 5271, 5313, 5393};
        int[] group8 = {5437, 5509, 5603, 5692, 5752, 5810};

        for (int v : group0) VERSION_TO_ROUTE_INDEX.put(v, 0);
        for (int v : group1) VERSION_TO_ROUTE_INDEX.put(v, 1);
        for (int v : group2) VERSION_TO_ROUTE_INDEX.put(v, 2);
        for (int v : group3) VERSION_TO_ROUTE_INDEX.put(v, 3);
        for (int v : group4) VERSION_TO_ROUTE_INDEX.put(v, 4);
        for (int v : group5) VERSION_TO_ROUTE_INDEX.put(v, 5);
        for (int v : group6) VERSION_TO_ROUTE_INDEX.put(v, 6);
        for (int v : group7) VERSION_TO_ROUTE_INDEX.put(v, 7);
        for (int v : group8) VERSION_TO_ROUTE_INDEX.put(v, 8);
    }
}