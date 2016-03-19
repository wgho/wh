package com.yslm.model.goods;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yslm.util.JsonDateSerializer;


@Entity(name="goods")
public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	/**
	 * 宝贝名称
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * 宝贝类目
	 */
	@Column(name = "cid")
	private String cid;
	
	/**
	 * 店铺类目
	 */
	@Column(name = "seller_cids")
	private String seller_cids;
	
	/**
	 * 新旧程度
	 */
	@Column(name = "stuff_status")
	private String stuff_status;

	/**
	 * 省
	 */
	@Column(name = "location_state")
	private String location_state;
	
	/**
	 * 市
	 */
	@Column(name = "location_city")
	private String location_city;
	
	/**
	 * 出售方式
	 */
	@Column(name = "item_type")
	private String item_type;

	/**
	 * 价格
	 */
	@Column(name = "price")
	private String price;
	
	/**
	 * 加价幅度
	 */
	@Column(name = "auction_increment")
	private String auction_increment;
	
	/**
	 * 宝贝数量
	 */
	@Column(name = "num")
	private String num;
	
	/**
	 * 有效期
	 */
	@Column(name = "valid_thru")
	private String valid_thru;
	
	/**
	 * 运费承担
	 */
	@Column(name = "freight_payer")
	private String freight_payer;
	
	/**
	 * 平邮
	 */
	@Column(name = "post_fee")
	private String post_fee;

	/**
	 * ems
	 */
	@Column(name = "ems_fee")
	private String ems_fee;
	
	/**
	 * 快递
	 */
	@Column(name = "express_fee")
	private String express_fee;
	
	/**
	 * 发票
	 */
	@Column(name = "has_invoice")
	private String has_invoice;
	
	/**
	 * 保修
	 */
	@Column(name = "has_warranty")
	private String has_warranty;
	
	/**
	 * 放入仓库
	 */
	@Column(name = "approve_status")
	private String approve_status;
	
	/**
	 * 橱窗推荐
	 */
	@Column(name = "has_showcase")
	private String has_showcase;
	
	/**
	 * 开始时间
	 */
	@Column(name = "list_time")
	private String list_time;
	
	/**
	 * 宝贝描述
	 */
	@Column(name = "description",columnDefinition="TEXT")
	private String description;
	
	/**
	 * 宝贝属性
	 */
	@Column(name = "cateProps",columnDefinition="TEXT")
	private String cateProps;
	
	/**
	 * 邮费模版id
	 */
	@Column(name = "postage_id")
	private String postage_id;
	
	/**
	 * 会员打折
	 */
	@Column(name="has_discount")
	private String has_discount;
	
	/**
	 * 修改时间
	 */
	@Column(name = "modified")
	private String modified;
	
	/**
	 * 上传状态
	 */
	@Column(name = "upload_fail_msg")
	private String upload_fail_msg;
	
	/**
	 * 图片状态
	 */
	@Column(name="picture_status")
	private String picture_status;
	
	/**
	 * 返点比例
	 */
	@Column(name = "auction_point")
	private String auction_point;
	
	/**
	 * 新图片
	 */
	@Column(name = "picture",columnDefinition="TEXT")
	private String picture;
	
	/**
	 * 视频
	 */
	@Column(name = "video")
	private String video;

	/**
	 * 销售属性组合
	 */
	@Column(name = "skuProps",columnDefinition="TEXT")
	private String skuProps;

	/**
	 * 用户输入ID串
	 */
	@Column(name = "inputPids")
	private String inputPids;
	
	/**
	 * 用户输入名-值对 
	 * （对应进价表的货号）
	 */
	@Column(name = "inputValues")
	private String inputValues;

	/**
	 * 商家编码
	 */
	@Column(name = "outer_id")
	private String outer_id;
	
	/**
	 * 销售属性别名
	 */
	@Column(name = "propAlias")
	private String propAlias;

	/**
	 * 代充类型
	 */
	@Column(name = "auto_fill")
	private String auto_fill;
	
	/**
	 * 数字ID
	 */
	@Column(name = "num_id")
	private String num_id;

	/**
	 * 本地ID
	 */
	@Column(name = "local_cid")
	private String local_cid;
	
	/**
	 * 宝贝分类
	 */
	@Column(name = "navigation_type")
	private String navigation_type;

	/**
	 * 用户名称
	 */
	@Column(name = "user_name")
	private String user_name;
	
	/**
	 * 宝贝状态
	 */
	@Column(name = "syncStatus")
	private String syncStatus;

	/**
	 * 闪电发货
	 */
	@Column(name = "is_lighting_consigment")
	private String is_lighting_consigment;
	
	/**
	 * 新品
	 */
	@Column(name = "is_xinpin")
	private String is_xinpin;

	/**
	 * 食品专项
	 */
	@Column(name = "foodparame")
	private String foodparame;
	/**
	 * 尺码库
	 */
	@Column(name = "features")
	private String features;

	/**
	 * 采购地
	 */
	@Column(name = "buyareatype")
	private String buyareatype;
	/**
	 * 库存类型
	 */
	@Column(name = "global_stock_type")
	private String global_stock_type;

	/**
	 * 国家地区
	 */
	@Column(name = "global_stock_country")
	private String global_stock_country;
	
	/**
	 * 库存计数
	 */
	@Column(name = "sub_stock_type")
	private String sub_stock_type;

	/**
	 * 物流体积
	 */
	@Column(name = "item_size")
	private String item_size;

	/**
	 * 物流重量
	 */
	@Column(name = "item_weight")
	private String item_weight;
	
	/**
	 * 退换货承诺
	 */
	@Column(name = "sell_promise")
	private String sell_promise;

	/**
	 * 定制工具
	 */
	@Column(name = "custom_design_flag")
	private String custom_design_flag;
	
	/**
	 * 无线详情
	 */
	@Column(name = "wireless_desc")
	private String wireless_desc;
	
	/**
	 * 商品条形码
	 */
	@Column(name = "barcode")
	private String barcode;

	/**
	 * sku 条形码
	 */
	@Column(name = "sku_barcode")
	private String sku_barcode;
	

	/**
	 * 7天退货
	 */
	@Column(name = "newprepay")
	private String newprepay;
	
	/**
	 * 宝贝卖点
	 */
	@Column(name = "subtitle")
	private String subtitle;

	/**
	 * 属性值备注
	 */
	@Column(name = "cpv_memo")
	private String cpv_memo;
	
	/**
	 * 自定义属性值
	 */
	@Column(name = "input_custom_cpv")
	private String input_custom_cpv;
	
	/**
	 * 商品资质
	 */
	@Column(name = "qualification")
	private String qualification;

	/**
	 * 增加商品资质
	 */
	@Column(name = "add_qualification")
	private String add_qualification;
	
	/**
	 * 关联线下服务
	 */
	@Column(name = "o2o_bind_service")
	private String o2o_bind_service;
	
	/**
	 * 进价
	 */
	@Column(name = "inprice")
	private String inprice;
	
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSeller_cids() {
		return seller_cids;
	}

	public void setSeller_cids(String seller_cids) {
		this.seller_cids = seller_cids;
	}

	public String getStuff_status() {
		return stuff_status;
	}

	public void setStuff_status(String stuff_status) {
		this.stuff_status = stuff_status;
	}

	public String getLocation_state() {
		return location_state;
	}

	public void setLocation_state(String location_state) {
		this.location_state = location_state;
	}

	public String getLocation_city() {
		return location_city;
	}

	public void setLocation_city(String location_city) {
		this.location_city = location_city;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAuction_increment() {
		return auction_increment;
	}

	public void setAuction_increment(String auction_increment) {
		this.auction_increment = auction_increment;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getValid_thru() {
		return valid_thru;
	}

	public void setValid_thru(String valid_thru) {
		this.valid_thru = valid_thru;
	}

	public String getFreight_payer() {
		return freight_payer;
	}

	public void setFreight_payer(String freight_payer) {
		this.freight_payer = freight_payer;
	}

	public String getPost_fee() {
		return post_fee;
	}

	public void setPost_fee(String post_fee) {
		this.post_fee = post_fee;
	}

	public String getEms_fee() {
		return ems_fee;
	}

	public void setEms_fee(String ems_fee) {
		this.ems_fee = ems_fee;
	}

	public String getExpress_fee() {
		return express_fee;
	}

	public void setExpress_fee(String express_fee) {
		this.express_fee = express_fee;
	}

	public String getHas_invoice() {
		return has_invoice;
	}

	public void setHas_invoice(String has_invoice) {
		this.has_invoice = has_invoice;
	}

	public String getHas_warranty() {
		return has_warranty;
	}

	public void setHas_warranty(String has_warranty) {
		this.has_warranty = has_warranty;
	}

	public String getApprove_status() {
		return approve_status;
	}

	public void setApprove_status(String approve_status) {
		this.approve_status = approve_status;
	}

	public String getHas_showcase() {
		return has_showcase;
	}

	public void setHas_showcase(String has_showcase) {
		this.has_showcase = has_showcase;
	}

	public String getList_time() {
		return list_time;
	}

	public void setList_time(String list_time) {
		this.list_time = list_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCateProps() {
		return cateProps;
	}

	public void setCateProps(String cateProps) {
		this.cateProps = cateProps;
	}

	public String getPostage_id() {
		return postage_id;
	}

	public void setPostage_id(String postage_id) {
		this.postage_id = postage_id;
	}

	public String getHas_discount() {
		return has_discount;
	}

	public void setHas_discount(String has_discount) {
		this.has_discount = has_discount;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getUpload_fail_msg() {
		return upload_fail_msg;
	}

	public void setUpload_fail_msg(String upload_fail_msg) {
		this.upload_fail_msg = upload_fail_msg;
	}

	public String getPicture_status() {
		return picture_status;
	}

	public void setPicture_status(String picture_status) {
		this.picture_status = picture_status;
	}

	public String getAuction_point() {
		return auction_point;
	}

	public void setAuction_point(String auction_point) {
		this.auction_point = auction_point;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getSkuProps() {
		return skuProps;
	}

	public void setSkuProps(String skuProps) {
		this.skuProps = skuProps;
	}

	public String getInputPids() {
		return inputPids;
	}

	public void setInputPids(String inputPids) {
		this.inputPids = inputPids;
	}

	public String getInputValues() {
		return inputValues;
	}

	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}

	public String getOuter_id() {
		return outer_id;
	}

	public void setOuter_id(String outer_id) {
		this.outer_id = outer_id;
	}

	public String getPropAlias() {
		return propAlias;
	}

	public void setPropAlias(String propAlias) {
		this.propAlias = propAlias;
	}

	public String getAuto_fill() {
		return auto_fill;
	}

	public void setAuto_fill(String auto_fill) {
		this.auto_fill = auto_fill;
	}

	public String getNum_id() {
		return num_id;
	}

	public void setNum_id(String num_id) {
		this.num_id = num_id;
	}

	public String getLocal_cid() {
		return local_cid;
	}

	public void setLocal_cid(String local_cid) {
		this.local_cid = local_cid;
	}

	public String getNavigation_type() {
		return navigation_type;
	}

	public void setNavigation_type(String navigation_type) {
		this.navigation_type = navigation_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getIs_lighting_consigment() {
		return is_lighting_consigment;
	}

	public void setIs_lighting_consigment(String is_lighting_consigment) {
		this.is_lighting_consigment = is_lighting_consigment;
	}

	public String getIs_xinpin() {
		return is_xinpin;
	}

	public void setIs_xinpin(String is_xinpin) {
		this.is_xinpin = is_xinpin;
	}

	public String getFoodparame() {
		return foodparame;
	}

	public void setFoodparame(String foodparame) {
		this.foodparame = foodparame;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getBuyareatype() {
		return buyareatype;
	}

	public void setBuyareatype(String buyareatype) {
		this.buyareatype = buyareatype;
	}

	public String getGlobal_stock_type() {
		return global_stock_type;
	}

	public void setGlobal_stock_type(String global_stock_type) {
		this.global_stock_type = global_stock_type;
	}

	public String getGlobal_stock_country() {
		return global_stock_country;
	}

	public void setGlobal_stock_country(String global_stock_country) {
		this.global_stock_country = global_stock_country;
	}

	public String getSub_stock_type() {
		return sub_stock_type;
	}

	public void setSub_stock_type(String sub_stock_type) {
		this.sub_stock_type = sub_stock_type;
	}

	public String getItem_size() {
		return item_size;
	}

	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}

	public String getItem_weight() {
		return item_weight;
	}

	public void setItem_weight(String item_weight) {
		this.item_weight = item_weight;
	}

	public String getSell_promise() {
		return sell_promise;
	}

	public void setSell_promise(String sell_promise) {
		this.sell_promise = sell_promise;
	}

	public String getCustom_design_flag() {
		return custom_design_flag;
	}

	public void setCustom_design_flag(String custom_design_flag) {
		this.custom_design_flag = custom_design_flag;
	}

	public String getWireless_desc() {
		return wireless_desc;
	}

	public void setWireless_desc(String wireless_desc) {
		this.wireless_desc = wireless_desc;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSku_barcode() {
		return sku_barcode;
	}

	public void setSku_barcode(String sku_barcode) {
		this.sku_barcode = sku_barcode;
	}

	public String getNewprepay() {
		return newprepay;
	}

	public void setNewprepay(String newprepay) {
		this.newprepay = newprepay;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getCpv_memo() {
		return cpv_memo;
	}

	public void setCpv_memo(String cpv_memo) {
		this.cpv_memo = cpv_memo;
	}

	public String getInput_custom_cpv() {
		return input_custom_cpv;
	}

	public void setInput_custom_cpv(String input_custom_cpv) {
		this.input_custom_cpv = input_custom_cpv;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getAdd_qualification() {
		return add_qualification;
	}

	public void setAdd_qualification(String add_qualification) {
		this.add_qualification = add_qualification;
	}

	public String getO2o_bind_service() {
		return o2o_bind_service;
	}

	public void setO2o_bind_service(String o2o_bind_service) {
		this.o2o_bind_service = o2o_bind_service;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	
		
}
 