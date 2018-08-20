package tk.mybatis.simple.model;

import java.util.Date;

/**
 * 創建信息
 * @author E-Kunt
 *
 */
public class CreateInfo {
	/**
	 * 創建人
	 */
	private String createBy;
	/**
	 * 創建時間
	 */
	private Date createTime;
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
