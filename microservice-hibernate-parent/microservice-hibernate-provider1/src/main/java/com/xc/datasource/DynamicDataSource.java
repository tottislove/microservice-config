package com.xc.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * Created by xiongying on 16/8/2.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
//        logger.debug(new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(),
//                "DynamicDataSource determineCurrentLookupKey,readOnly=" + HandleDataSource.getDataSource()).toJson());
        Boolean readOnly = HandleDataSource.getDataSource();
        if (readOnly == null)
            return DataSourceType.WRITE;
        return readOnly ? DataSourceType.READ : DataSourceType.WRITE;
    }
}
