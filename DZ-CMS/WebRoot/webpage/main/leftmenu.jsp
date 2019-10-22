<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- #section:basics/sidebar -->
<div id="sidebar" class="sidebar                  responsive">
	<script type="text/javascript">
	try {
		ace.settings.check('sidebar', 'fixed')
	} catch (e) {
	}
	function functionOne(obj){
		var shortcut = obj.id;
		$.ajax({
			url: rootpath+'/manager/sys/func/toshortcut.action',
			cache: false,
			type:'post',
			data:{},
			success: function(data){
				 var array = data.split(",");
					if(shortcut=="shortcut1"&&array.length>=1){
						window.location.href=rootpath+"/main/adminindex.jsp#dimine/"+array[0].trim();
					}else if(shortcut=="shortcut2"&&array.length>=2){
						window.location.href=rootpath+"/main/adminindex.jsp#dimine/"+array[1].trim();
					}else if(shortcut=="shortcut3"&&array.length>=3){
						window.location.href=rootpath+"/main/adminindex.jsp#dimine/"+array[2].trim();
					}else if(shortcut=="shortcut4"&&array.length==4){
						window.location.href=rootpath+"/main/adminindex.jsp#dimine/"+array[3].trim();
					}else{
						console.log('error');
					}
					//console.info("shortcut="+shortcut+",array.length="+array.length);
					//console.info(array);
			},
			   error: function(data) {
				  console.log('error');
			   }
			});
	}
</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts" style="display:none;">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success" id="shortcut1" onclick="functionOne(this)">
				<i class="ace-icon fa fa-signal"></i>
			</button>
			<button class="btn btn-info"  id="shortcut2" onclick="functionOne(this)">
				<i class="ace-icon fa fa-pencil"></i>
			</button>
			<!-- #section:basics/sidebar.layout.shortcuts -->
			<button class="btn btn-warning"  id="shortcut3" onclick="functionOne(this)">
				<i class="ace-icon fa fa-users"></i>
			</button>
			<button class="btn btn-danger"  id="shortcut4" onclick="functionOne(this)">
				<i class="ace-icon fa fa-cogs"></i>
			</button>
			<!-- /section:basics/sidebar.layout.shortcuts -->
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>
			<span class="btn btn-info"></span>
			<span class="btn btn-warning"></span>
			<span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- /.sidebar-shortcuts -->
    <div class="nav-search" id="nav-search" style="display:none">
		<form class="form-search">
			<span class="input-icon">
				<input type="text" placeholder="搜索 ..." class="typeahead scrollable nav-search-input "  autocomplete="off"   />
				<i class="ace-icon fa fa-search nav-search-icon"></i>
			</span>
			<div style="border-width:1px;" id="auto_div"></div>
		</form>
	</div><!-- /.nav-search -->
	<ul class="nav nav-list" id="nav-nav-list-func"></ul>
	<!-- /.nav-list -->

	<!-- #section:basics/sidebar.layout.minimize -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<!-- /section:basics/sidebar.layout.minimize -->
	<script type="text/javascript">
	try {
		ace.settings.check('sidebar', 'collapsed');
	} catch (e) {}
</script>
</div>

<!-- /section:basics/sidebar -->