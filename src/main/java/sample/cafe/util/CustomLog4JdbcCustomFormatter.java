package sample.cafe.util;

import net.sf.log4jdbc.log.SpyLogDelegator;
import net.sf.log4jdbc.sql.Spy;
import net.sf.log4jdbc.sql.resultsetcollector.ResultSetCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class CustomLog4JdbcCustomFormatter  implements SpyLogDelegator {
    
    
    private static final Logger sqlLogger = LoggerFactory.getLogger("jdbc.sqlonly");
    private static final Pattern pattern = Pattern.compile("\\n[\\t\\s]*\\n");
    
    @Override
    public boolean isJdbcLoggingEnabled() {
        return true;
    }
    
    @Override
    public void sqlOccurred(Spy spy, String methodCall, String rawSql) {
        
        String cleanedSql = pattern.matcher(rawSql).replaceAll("\n");
        String mapperId = JpaQueryContextHolder.get();
        if (mapperId != null) {
            cleanedSql = "/* " + mapperId + " */\n" + cleanedSql;
            JpaQueryContextHolder.clear();
        }
        
        sqlLogger.info("[SQL] {} :::\n{}", methodCall, cleanedSql);
    }
    
    @Override
    public void sqlTimingOccurred(Spy spy, long execTime, String methodCall, String rawSql) {
    
    }
    
    @Override
    public void exceptionOccured(Spy spy, String methodCall, Exception e, String sql, long execTime) {
        sqlLogger.error("SQL ERROR [{}] :::\n{}", execTime, sql, e);
    }
    
    @Override public void methodReturned(Spy spy, String returnMsg, String methodCall) {
    
    }
    @Override public void constructorReturned(Spy spy, String constructorMsg) {
    
    }
    @Override public void connectionOpened(Spy spy, long execTime) {
    
    }
    @Override public void connectionClosed(Spy spy, long execTime) {
    
    }
    @Override public void connectionAborted(Spy spy, long execTime) {
    
    }
    @Override public void debug(String msg) {
    
    }
    @Override public boolean isResultSetCollectionEnabled() {
        return false;
    }
    @Override public boolean isResultSetCollectionEnabledWithUnreadValueFillIn() {
        return false;
    }
    @Override public void resultSetCollected(ResultSetCollector resultSetCollector) {
    
    }
}