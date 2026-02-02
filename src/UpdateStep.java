
    public class UpdateStep {
        final int fromVersion;
        final int toVersion;
        final String sybaseVersion;
        final String dsnVersion;
        final boolean requiresNewSybase;

        public UpdateStep(int from, int to, String sybase, String dsn, boolean requiresNew) {
            this.fromVersion = from;
            this.toVersion = to;
            this.sybaseVersion = sybase;
            this.dsnVersion = dsn;
            this.requiresNewSybase = requiresNew;
        }
    }

