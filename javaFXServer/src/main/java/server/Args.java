package server;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names="--port")
    int port;

    @Parameter(names="--db-properties")
    String proper;
}
