<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="${actionName ?lower_case}_info" >
	<form id="${actionName ?lower_case}_form" method="post" class="form-horizontal">
		<#list primaryKey as primaryKey>
		<input type="hidden" id="${primaryKey ?lower_case}" name="${primaryKey ?lower_case}"/>
		</#list>
		
		<#list code as code>
		<div class="form-group">
			${code}
		</div>
		</#list>
		
	</form>
</div>