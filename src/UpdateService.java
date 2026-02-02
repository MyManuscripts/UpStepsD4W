import java.util.List;

public class UpdateService {
    public static String buildRouteText(int startIndex, int currentVersion) {
        StringBuilder sb = new StringBuilder();
        List<UpdateStep> route = UpdateConfiguration.FULL_ROUTE;

        for (int i = startIndex; i < route.size(); i++) {
            UpdateStep step = route.get(i);
            int from = (i == startIndex) ? currentVersion : step.fromVersion;

            sb.append(String.format(" Обновить с версии %d -> %d (%s)\n", from, step.toVersion, step.sybaseVersion));
            sb.append(String.format(" Создайте DSN на версии %s.\n", step.dsnVersion));
            if (step.requiresNewSybase) {
                sb.append(" Не забудьте установить ").append(step.sybaseVersion).append(" перед обновлением.\n");
            }
            sb.append("------------------------------------------------------\n");
        }
        return sb.toString();
    }

    public static boolean isAncientVersion(int version) {
        return UpdateConfiguration.ANCIENT_VERSIONS.contains(version);
    }

    public static boolean isTooNewVersion(int version) {
        return version > 5917;
    }

    public static Integer getRouteStartIndex(int version) {
        return UpdateConfiguration.VERSION_TO_ROUTE_INDEX.get(version);
    }
}