package com.dimine.customer.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 客户尺寸信息类
 * 
 * @author dimine 2019-09-01 15:10:45
 * 
 */
public class Dz_customer_size_configEntity extends BaseEntity {

	/** id */
	private String id = "";
	/** 登录客户的id */
	private String customer_id = "";
	/** 姓名 */
	private String name = "";
	/** 性别 */
	private String sex = "";
	/** 年龄 */
	private String year = "";
	/** 身高（cm） */
	private String height = "";
	/** 体重（公斤） */
	private String weight = "";
	/** 腰围（cm） */
	private String waist = "";
	/** 胸围（cm） */
	private String bust = "";
	/** 衣长（cm） */
	private String clothes_length = "";
	/** 臂长（cm） */
	private String arm_length = "";
	/** 臂围（cm） */
	private String arm_circum = "";
	/** 肩宽（cm） */
	private String shoulder_width = "";
	/** 尺寸类型配置内容 */
	private String content = "";
	/** 图示-正面照 */
	private String headlight_url = "";
	/** 图示-侧面照 */
	private String side_shot_url = "";
	/** 图示-背面照 */
	private String back_photo_url = "";
	/** 上传的图片 */
	private String imgs_url = "";
	/** 是否设置为默认尺寸 */
	private String is_default = "";
	/** 备注（特殊说明栏） */
	private String remarks = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getWaist() {
		return waist;
	}
	public void setWaist(String waist) {
		this.waist = waist;
	}
	public String getBust() {
		return bust;
	}
	public void setBust(String bust) {
		this.bust = bust;
	}
	public String getClothes_length() {
		return clothes_length;
	}
	public void setClothes_length(String clothes_length) {
		this.clothes_length = clothes_length;
	}
	public String getArm_length() {
		return arm_length;
	}
	public void setArm_length(String arm_length) {
		this.arm_length = arm_length;
	}
	public String getArm_circum() {
		return arm_circum;
	}
	public void setArm_circum(String arm_circum) {
		this.arm_circum = arm_circum;
	}
	public String getShoulder_width() {
		return shoulder_width;
	}
	public void setShoulder_width(String shoulder_width) {
		this.shoulder_width = shoulder_width;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHeadlight_url() {
		return headlight_url;
	}
	public void setHeadlight_url(String headlight_url) {
		this.headlight_url = headlight_url;
	}
	public String getSide_shot_url() {
		return side_shot_url;
	}
	public void setSide_shot_url(String side_shot_url) {
		this.side_shot_url = side_shot_url;
	}
	public String getBack_photo_url() {
		return back_photo_url;
	}
	public void setBack_photo_url(String back_photo_url) {
		this.back_photo_url = back_photo_url;
	}
	public String getImgs_url() {
		return imgs_url;
	}
	public void setImgs_url(String imgs_url) {
		this.imgs_url = imgs_url;
	}
	public String getIs_default() {
		return is_default;
	}
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
