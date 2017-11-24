$(function() {
	dataArr = [ [ {
		field : 'id',
		title : 'id',
		width : 30,
		hidden : true
	}, {
		field : 'name',
		title : '角色名',
		width : 30,
		formatter : function(v) {
			return "<a href='javascript:showAss();void(0);'>" + v + "</a>";
		}
	} ] ];
});
