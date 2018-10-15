package com.lcy.cssm.support.user.po;

import com.mcilife.zlnsh.common.base.po.BasePO;
import com.mcilife.zlnsh.common.base.po.OldBasePO;

import java.util.Date;

public class UserLikes extends BasePO {

	private Integer likeId;

	private Integer userId;

	private Integer moduleType;

	private Integer statisticsType;

	private Date createDate;

	private Date updateDate;

	public Integer getLikeId() {
		return likeId;
	}

	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getModuleType() {
		return moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}

	public Integer getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(Integer statisticsType) {
		this.statisticsType = statisticsType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}