<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.dimine.${modelPath ?lower_case}.dao.${actionName ?cap_first}Dao" >
	<!-- Result Map-->
	<resultMap id="BaseResultMap" type="com.dimine.${modelPath ?lower_case}.entity.${actionName ?cap_first}Entity" >
		<#list filed as filed>
		<result column="${filed.filedName ?lower_case}" property="${filed.filedName ?lower_case}"/>
		</#list>
	</resultMap>
		   
	<!-- ${actionName ?lower_case} table all fields -->
	<sql id="Base_Column_List" >
		<#list filed as filed>
		${filed.filedName ?lower_case}<#if filed_has_next>, </#if>
		</#list>
	</sql>	   
	   
	<!-- 查询条件 -->
	<sql id="Base_Where">
		WHERE 1=1
		<trim suffixOverrides="," >
			<if test="param != null and param != ''" >
				<#if isRequirement?exists>
				and 
				<#list isRequirement as isRequirement>
				${isRequirement.filedName ?lower_case} like '%${'$'}${'{'}param${'}'}%' <#if isRequirement_has_next> or </#if>
				</#list>
				</#if>
			</if>
			<if test="query != null and query != ''" >
				${'$'}${'{'}query${'}'}
			</if>
		</trim>
	</sql>

	<!-- 查询${title}信息列表 -->
	<select id="selectByList" resultMap="BaseResultMap" parameterType="Object">
		SELECT
		<include refid="Base_Column_List"/>
		FROM  ${tableName} 
		<include refid="Base_Where"/>
		<if test="pager.orderCondition != null and pager.orderCondition != ''" >
		${'$'}${'{'}pager.orderCondition${'}'}
		</if>
		<if test="pager.mysqlQueryCondition != null and pager.mysqlQueryCondition != ''" >
		${'$'}${'{'}pager.mysqlQueryCondition${'}'}
		</if>
	</select>
	
	<!-- 查询${title}信息列表总数-->
	<select id="selectByCount" resultType="java.lang.Integer" parameterType="Object">
		SELECT count(1) FROM ${tableName} 
		<include refid="Base_Where"/>
	</select>
	
	<!-- 根据id查询 ${title}信息 -->
	<select id="selectById" resultMap="BaseResultMap" parameterType="Object">
		SELECT <include refid="Base_Column_List" /> 
		FROM ${tableName} 
		<#if primaryKey?exists>
		WHERE 
		<#list primaryKey as primaryKey>		
		${primaryKey ?lower_case} = ${'#'}${'{'}id${'}'} <#if primaryKey_has_next> AND </#if>
		</#list>
		</#if>
	</select>

	<!-- 插入记录 -->
	<insert id="insert" parameterType="Object" >
		INSERT INTO ${tableName}(
		<#list filed as filed>
		${filed.filedName ?lower_case}<#if filed_has_next>, </#if>
		</#list>
		)
		VALUES(		
		<#list filed as filed>
		${'#'}${'{'}${filed.filedName ?lower_case}${'}'}<#if filed_has_next>, </#if>
		</#list>
		)
	</insert>
	<!-- 根据id，修改记录-->  
	<update id="update" parameterType="Object" >
		UPDATE ${tableName} 
		SET 		
		<#list filed as filed>
		${filed.filedName ?lower_case} = ${'#'}${'{'}${filed.filedName ?lower_case}${'}'}<#if filed_has_next>, </#if>
		</#list>
		<#if primaryKey?exists>
		WHERE 
		<#list primaryKey as primaryKey>		
		${primaryKey ?lower_case} = ${'#'}${'{'}${primaryKey ?lower_case}${'}'} <#if primaryKey_has_next> AND </#if>		
		</#list>
		</#if>
	</update>	
	<!-- 删除记录 -->
	<delete id="delete" parameterType="Object">
		DELETE FROM ${tableName}		
		<#if primaryKey?exists>
		WHERE 
		<#list primaryKey as primaryKey>		
		${primaryKey ?lower_case} = ${'#'}${'{'}${primaryKey ?lower_case}${'}'} <#if primaryKey_has_next> AND </#if>		
		</#list>
		</#if>
	</delete>
	
</mapper>   