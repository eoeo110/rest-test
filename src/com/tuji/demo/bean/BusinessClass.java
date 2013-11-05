package com.tuji.demo.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "MWT_OM_CLS")
public class BusinessClass {
	public BusinessClass() {
		super();

	  /*  this.cls_name="新类型";
	    this.isStructureFlag = "F";
	    this.isObsoleteFlag = "F";
	    this.baseClassID = "4AEAF74C-8493-436A-A1CE-827BD1084ADA";
	    this.baseClassName = "标准对象";
	   

	    
	    this.tableName = "MWT_UD_";
	    this.tableSchema = "MW_APP";
	    this.tableSpace = "MWS_APP";*/
	}
	
    public String toString() {

        return this.cls_name+"**";

    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableSpace() {
		return tableSpace;
	}

	public void setTableSpace(String tableSpace) {
		this.tableSpace = tableSpace;
	}

	public String getIsStructureFlag() {
		return isStructureFlag;
	}

	public void setIsStructureFlag(String isStructureFlag) {
		this.isStructureFlag = isStructureFlag;
	}

	public String getCls_name() {
		return cls_name;
	}

	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}

	private String id;//cls_id
	
	

	
	private String tableSchema;//例如mw_sys
	private String tableName;//
	private String tableSpace;
	
	private String isStructureFlag;//是否是结构对象
	private String cls_name;//表名称
	
	
	
	

}
