package com.cnitsec.mirana.cloud.events.domain;

import java.util.Date;


/**
 * @ClassName: Classify
 * @Version:1.0
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 李玉志 email:394023466@qq.com
 * @date 开发时间: 2017年8月31日 下午4:16:16
 *
 */

public class EventsClassify {


    private String id;

    private String zhuti;
    // 客体
    private String keti;
    // 客体类型
    private String ketiType;
    // 操作描述
    private String describe;
    // 操作时间
    private Date operTime;

    private long taskId;
    private String typeKey;

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getZhuti() {
        return zhuti;
    }

    public void setZhuti(String zhuti) {
        this.zhuti = zhuti;
    }

    public String getKeti() {
        return keti;
    }

    public void setKeti(String keti) {
        this.keti = keti;
    }

    public String getKetiType() {
        return ketiType;
    }

    public void setKetiType(String ketiType) {
        this.ketiType = ketiType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.cnitsec.mirana.persistence.Identifiable#getId()
     */
    public String getId() {

        return this.id;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.cnitsec.mirana.persistence.Identifiable#setId(java.lang.Object)
     */
    public void setId(String id) {
        this.id = id;
    }


}
