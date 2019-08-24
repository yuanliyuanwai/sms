package cn.edu.bit.sms.common.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.edu.bit.sms.common.util.TimeUtil;

@MappedSuperclass
public abstract class BaseEntity {

    /**
     * id
     */
    protected Long id;

    /**
     * 创建时间
     */
    private Calendar createTime;

    /**
     * 更新时间
     */
    private Calendar updateTime;

    /**
     * 创建人
     */
    private String createUserNo;

    /**
     * 更新人
     */
    private String updateUserNo;

    protected BaseEntity() {
        this.createTime = Calendar.getInstance();
        this.updateTime = Calendar.getInstance();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "create_time", nullable = false)
    @JsonFormat(pattern = TimeUtil.YYYY_MM_DD_HH_MM_SS, timezone = "GMT+8")
    public Calendar getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time", nullable = false)
    @JsonFormat(pattern = TimeUtil.YYYY_MM_DD_HH_MM_SS, timezone = "GMT+8")
    public Calendar getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "create_user_no")
    public String getCreateUserNo() {
        return createUserNo;
    }

    public void setCreateUserNo(String createUserNo) {
        this.createUserNo = createUserNo;
    }

    @Column(name = "update_user_no")
    public String getUpdateUserNo() {
        return updateUserNo;
    }

    public void setUpdateUserNo(String updateUserNo) {
        this.updateUserNo = updateUserNo;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
    
}
