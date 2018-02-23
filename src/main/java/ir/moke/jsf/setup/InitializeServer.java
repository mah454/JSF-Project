package ir.moke.jsf.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Singleton
@Startup
public class InitializeServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializeServer.class);

    @Resource(name = "None-JTA-Connection")
    private DataSource dataSource;

    @PostConstruct
    public void init() {

    }

    @PreDestroy
    public void shutdown() {
        LOGGER.warn("Server Shutdown .");
    }
}
