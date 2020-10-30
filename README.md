# generator-web

> mybatis文件在线自动生成器-让机械无味的mybatis文件编写工作一去不返
> 随时随地 - 方便快捷

厌倦了一成不变的mybatis文件的编写工作？快来使用这个在线工具吧！
填写正确的配置即可一秒生成`entity、mapper、service、controller`文件。既然要偷懒，就要做到极致。

快来下载试用吧！

我们只要在`application.properties`里面如下配置：

```properties
#数据库连接配置
datasource.url=jdbc:mysql://172.xxx.xxx.xxx:3306/rmp?characterEncoding=utf-8
datasource.username=xxx
datasource.password=xxx
datasource.driver-class-name=com.mysql.jdbc.Driver

#生成代码地址!
targetProjectPath=E:\\code

#包名
mybatis.controller=com.lvshen.rmp.controller
mybatis.service=com.lvshen.rmp.service
mybatis.serviceImpl=com.lvshen.rmp.service.impl
mybatis.entity=com.lvshen.rmp.entity
mybatis.mapper=com.lvshen.rmp.mapper


#mapper文件的位置
mybatis.resources=com.lvshen.rmp.mapper

#配置表->实体
mybatis.table=rmp3_return_replacement_apply
mybatis.classname=ReturnReplacementApply

```

生成的代码效果如下：

## Entity

```java
package com.lvshen.rmp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zoomlion.rmp.entity.RmpBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description  rmp3_return_replacement_apply:质量退换、商务退换申请表
 * @author   wujialv
 * @date   2020-10-30
 */
@Table(name = "rmp3_return_replacement_apply")
@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnReplacementApply extends RmpBaseEntity implements Serializable {
    /**
     * id:
     */
    @ApiModelProperty(value = "")
    @Column(name = "id")
    private String id;

    ...

    /**
     * first_audited_time:初次审批时间
     */
    @ApiModelProperty(value = "初次审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "first_audited_time")
    private Date firstAuditedTime;

   ...

    /**
     * update_date:修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * is_active:
     */
    @ApiModelProperty(value = "")
    @Column(name = "is_active")
    private String isActive;

    private static final long serialVersionUID = 1L;
}
```

## Mapper

```java
/**
* Created by Mybatis Generator on 2020/10/30
*/
@Repository
public interface ReturnReplacementApplyMapper extends BaseMapper<ReturnReplacementApply> {
}
```

**xml文件**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lvshen.rmp.mapper.ReturnReplacementApplyMapper">
  <resultMap id="BaseResultMap" type="com.lvshen.rmp.entity.ReturnReplacementApply">
    <id column="id" jdbcType="VARCHAR" property="id" />
    <result column="material_number" jdbcType="VARCHAR" property="materialNumber" />
    <result column="new_machine_material" jdbcType="VARCHAR" property="newMachineMaterial" />
    <result column="entry_method" jdbcType="VARCHAR" property="entryMethod" />
    <result column="return_replacement_annex" jdbcType="VARCHAR" property="returnReplacementAnnex" />
    <result column="apply_status" jdbcType="VARCHAR" property="applyStatus" />
    <result column="first_auditor" jdbcType="VARCHAR" property="firstAuditor" />
    <result column="first_audited_time" jdbcType="TIMESTAMP" property="firstAuditedTime" />
    <result column="first_audited_remark" jdbcType="VARCHAR" property="firstAuditedRemark" />
    <result column="second_auditor" jdbcType="VARCHAR" property="secondAuditor" />
    <result column="second_audited_time" jdbcType="TIMESTAMP" property="secondAuditedTime" />
    <result column="second_audited_remark" jdbcType="VARCHAR" property="secondAuditedRemark" />
    <result column="type" jdbcType="VARCHAR" property="type" />
    <result column="create_by" jdbcType="VARCHAR" property="createBy" />
    <result column="create_date" jdbcType="TIMESTAMP" property="createDate" />
    <result column="update_by" jdbcType="VARCHAR" property="updateBy" />
    <result column="update_date" jdbcType="TIMESTAMP" property="updateDate" />
    <result column="is_active" jdbcType="CHAR" property="isActive" />
  </resultMap>
  <sql id="Base_Column_List">
    id, material_number, new_machine_material, entry_method, return_replacement_annex, 
    apply_status, first_auditor, first_audited_time, first_audited_remark, second_auditor, 
    second_audited_time, second_audited_remark, type, create_by, create_date, update_by, 
    update_date, is_active
  </sql>
  ...
```

## Service

```java
public interface ReturnReplacementApplyService extends BaseService<ReturnReplacementApply> {
}
```

**impl**类

```java
@Service("returnReplacementApplyService")
public class ReturnReplacementApplyServiceImpl extends BaseServiceImpl<ReturnReplacementApply> implements ReturnReplacementApplyService {
    @Autowired
    private ReturnReplacementApplyMapper returnReplacementApplyMapper;

    @Override
    public BaseMapper<ReturnReplacementApply> getBaseMapper() {
        return returnReplacementApplyMapper;
    }

    @Override
    public Object getExample() {
        return new ReturnReplacementApplyExample();
    }
}
```

## Controller

```java
@RestController
@RequestMapping("/returnReplacementApply")
@Api(tags = "ReturnReplacementApplyController", description = "ReturnReplacementApplyController")
public class ReturnReplacementApplyController implements BaseController {
    @Autowired
    private ReturnReplacementApplyService returnReplacementApplyService;

    @Override
    public BaseService<ReturnReplacementApply> getService() {
        return returnReplacementApplyService;
    }
}
```

以上代码都是工具自动生成的哦！

> **Created by wujialv**