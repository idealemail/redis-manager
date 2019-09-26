package com.newegg.ec.redis.plugin.alert.service.impl;

import com.newegg.ec.redis.plugin.alert.dao.IAlertRecordDao;
import com.newegg.ec.redis.plugin.alert.entity.AlertRecord;
import com.newegg.ec.redis.plugin.alert.service.IAlertRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Jay.H.Zou
 * @date 2019/8/31
 */
public class AlertRecordService implements IAlertRecordService {

    private static final Logger logger = LoggerFactory.getLogger(AlertRecordService.class);

    @Autowired
    private IAlertRecordDao alertRecordDao;

    @Override
    public List<AlertRecord> getAlertRecordByClusterId(int clusterId) {
        try {
            return alertRecordDao.selectAlertRecordByClusterId(clusterId);
        } catch (Exception e) {
            logger.error("Get alert record by cluster id failed, cluster id = " + clusterId, e);
            return null;
        }
    }

    @Override
    public boolean addAlertRecord(List<AlertRecord> alertRecordList) {
        try {
            int row = alertRecordDao.insertAlertRecord(alertRecordList);
            return row > 0;
        } catch (Exception e) {
            logger.error("Add alert record  failed, " + alertRecordList, e);
            return false;
        }
    }

    @Override
    public boolean deleteAlertRecordByIds(List<Integer> recordIdList) {
        try {
            alertRecordDao.deleteAlertRecordByIds(recordIdList);
            return true;
        } catch (Exception e) {
            logger.error("Delete alert record by ids failed, alert record ids = " + recordIdList, e);
            return false;
        }
    }

    @Override
    public boolean deleteAlertRecordByTime(Timestamp earliestTime) {
        try {
            alertRecordDao.deleteAlertRecordByTime(earliestTime);
            return true;
        } catch (Exception e) {
            logger.error("Delete alert record by earliest time failed", e);
            return false;
        }
    }

    @Override
    public Integer getAlertNumber() {
        return 9;
    }
}