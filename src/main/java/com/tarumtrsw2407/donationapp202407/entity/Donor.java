package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.DonorType;
import java.util.Date;

/**
 *
 * @author Wong Xiao Zhe
 */
public interface Donor {
    String getId();
    DonorType getType();
    String getName();
    Date getExistDate();
    String getHomeRegion();
    void setId(String id);
    void setType(DonorType type);
    void setName(String first,String last);
    void setExistDate(Date date);
    void setHomeRegion(String region);
}
