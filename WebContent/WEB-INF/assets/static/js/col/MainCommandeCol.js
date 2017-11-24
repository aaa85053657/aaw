$(function() {
	dataArr = [ [ {
		field : 'id',
		title : $.i18n.prop('mCommande.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'codeId',
		title : $.i18n.prop('mCommande.obj.codeID'),
		width : 30,
		hidden : true
	}, {
		field : 'externalCode',
		title : $.i18n.prop('mCommande.obj.externalCode'),
		width : 30
	}, {
		field : 'type',
		title : $.i18n.prop('mCommande.obj.type'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.name;
			}
			return "";
		}
	}, {
		field : 'col1',
		title : $.i18n.prop('mCommande.obj.col1'),
		width : 30
	}, {
		field : 'orderStatus',
		title : $.i18n.prop('mCommande.obj.col5'),
		width : 30,
		formatter : function(v, row, index) {
			var col6 = row.col6;
			var col4 = row.col4;
			var col2 = row.col2;
			var col7 = row.col7;
			if (col6 == 7 || col6 == 8) {
				return formatterStatus(col6);
			}
			if (v == 1 || v == 2 || v == 3) {
				return formatterStatus(v);
			}
			if (v == 4 && col6 == 5) {
				return formatterStatus(v) + col2;
			}
			if (col6 == 6 && col7 != null) {
				return col7 + formatterStatus(col4) + col2;
			}
			if (col7 == null) {
				return $.i18n.prop('mCommande.state.s12') + col2;
			}
			return "";
		}
	}, {
		field : 'col8',
		title : $.i18n.prop('mCommande.obj.timeUpdate'),
		width : 30
	}, {
		field : 'col4',
		title : $.i18n.prop('mCommande.obj.col4'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return formatterStatus(v);
			}
			return "";
		},
		hidden : true
	}, {
		field : 'col6',
		title : $.i18n.prop('mCommande.obj.col6'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return formatterStatus(v);
			}
			return "";
		},
		hidden : true
	}, {
		field : 'col2',
		title : $.i18n.prop('mCommande.obj.col2'),
		width : 30,
		hidden : true
	}, {
		field : 'priority',
		title : $.i18n.prop('mCommande.obj.priority'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.name;
			}
			return "";
		}
	}, {
		field : 'col3',
		title : $.i18n.prop('mCommande.obj.col3'),
		width : 30
	}, {
		field : 'customer',
		title : $.i18n.prop('mCommande.obj.customer'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.firstName + "." + v.middleName + "." + v.lastName;
			}
			return "";
		}
	}, {
		field : 'address',
		title : $.i18n.prop('mCommande.obj.address'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.address;
			}
			return "";
		}
	}, {
		field : 'seller',
		title : $.i18n.prop('mCommande.obj.seller'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.name;
			}
			return "";
		},
		hidden : true
	}, {
		field : 'timeCreation',
		title : $.i18n.prop('mCommande.obj.timeCreation'),
		width : 30,
		hidden : true
	}, {
		field : 'timeModification',
		title : $.i18n.prop('mCommande.obj.timeModification'),
		width : 30,
		hidden : true
	}, {
		field : 'timeDelete',
		title : $.i18n.prop('mCommande.obj.timeDelete'),
		width : 30,
		hidden : true
	}, {
		field : 'fid',
		title : $.i18n.prop('mCommande.obj.franch'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.name;
			}
			return "";
		},
		hidden : true
	} ] ];

	detailArr = [ [
			{
				field : 'componentName',
				title : $.i18n.prop('slaveCommande.detail.componentName'),
				width : 50
			},
			{
				field : 'attrName',
				title : $.i18n.prop('slaveCommande.detail.attrName'),
				width : 50
			},
			{
				field : 'componentID',
				title : $.i18n.prop('slaveCommande.detail.componentID'),
				width : 50,
				hidden : true,
				formatter : function(v, r, index) {
					return "<input type='hidden' name='configs[" + index
							+ "].component.id' value='" + v + "'/>";
				}
			},
			{
				field : 'elementLeft',
				title : $.i18n.prop('slaveCommande.detail.elementLeft'),
				width : 100,
				formatter : function(v, row, index) {
					if (v.length > 0) {
						var str = JSON.stringify(v);
						return "<input name='configs["
								+ index
								+ "].elementLeft.id' class='easyui-combobox eleSel' data-options='required:true,editable : true,data:"
								+ str + "' />";
					}
					return "";
				}
			},
			{
				field : 'elementRight',
				title : $.i18n.prop('slaveCommande.detail.elementRight'),
				width : 100,
				formatter : function(v, row, index) {
					if (v.length > 0) {
						var str = JSON.stringify(v);
						return "<input name='configs["
								+ index
								+ "].elementRight.id' class='easyui-combobox eleSel' data-options='required:true,editable : true,data:"
								+ str + "' />";
					}
					return "";
				}
			} ] ];

	detailArr2 = [ [
			{
				field : 'componentName',
				title : $.i18n.prop('slaveCommande.detail.componentName'),
				width : 50
			},
			{
				field : 'attrName',
				title : $.i18n.prop('slaveCommande.detail.attrName'),
				width : 50
			},
			{
				field : 'attrNameId',
				title : $.i18n.prop('slaveCommande.detail.attrName'),
				width : 50,
				hidden : true,
				formatter : function(v, r, index) {
					return "<input type='hidden' name='configs2[" + index
							+ "].attribute.id' value='" + v + "'/>";
				}
			},
			{
				field : 'componentID',
				title : $.i18n.prop('slaveCommande.detail.componentID'),
				width : 50,
				hidden : true,
				formatter : function(v, r, index) {
					return "<input type='hidden' name='configs2[" + index
							+ "].component.id' value='" + v + "'/>";
				}
			},
			{
				field : 'elementLeft',
				title : $.i18n.prop('slaveCommande.detail.elementLeft'),
				width : 100,
				formatter : function(v, row, index) {
					return "<input name='configs2[" + index
							+ "].elementLeft' class='easyui-textbox' />";
				}
			},
			{
				field : 'elementRight',
				title : $.i18n.prop('slaveCommande.detail.elementRight'),
				width : 100,
				formatter : function(v, row, index) {
					return "<input name='configs2[" + index
							+ "].elementRight' class='easyui-textbox' />";
				}
			} ] ];

	cfgArr = [ [
			{
				field : 'id',
				title : $.i18n.prop('mCommande.cfg.id'),
				width : 30,
				hidden : true
			},
			{
				field : 'codeId',
				title : $.i18n.prop('mCommande.cfg.codeId'),
				width : 50
			},
			{
				field : 'model',
				title : $.i18n.prop('mCommande.cfg.model'),
				width : 50,
				formatter : function(v, r, index) {
					if (v) {
						return v.name;
					}
					return "";
				}
			},
			{
				field : 'comments',
				title : $.i18n.prop('mCommande.cfg.comments'),
				width : 100
			},
			{
				field : 'sss',
				title : $.i18n.prop('mCommande.cfg.del'),
				width : 60,
				formatter : function(value, row, index) {
					return "<a href='javascript: deleSingleSide("
							+ row.id
							+ ");void(0);'>"
							+ $.i18n.prop('mCommande.cfg.del')
							+ "</a>&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='printDialog(\""
							+ row.codeId
							+ "\",\""
							+ row.mainCommande.codeId
							+ "\",\""
							+ row.id
							+ "\")'>打印标签</a>&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='printLable(\""
							+ row.codeId + "\",\"" + row.mainCommande.codeId
							+ "\",\"" + row.id + "\")'>打印内容</a>"
				}
			} ] ];

	slaveDArr = [ [ {
		field : 'id',
		title : $.i18n.prop('slaveD.arr.id'),
		width : 30,
		hidden : true
	}, {
		field : 'slaveCommande',
		title : $.i18n.prop('slaveD.arr.model.id'),
		width : 50,
		formatter : function(v, r, index) {
			if (v) {
				return v.model.name;
			}
			return "";
		}
	}, {
		field : 'component',
		title : $.i18n.prop('slaveD.arr.component'),
		width : 50,
		formatter : function(v, r, index) {
			if (v) {
				return v.name;
			}
			return "";
		}
	}, {
		field : 'attrName',
		title : $.i18n.prop('slaveD.arr.attrName'),
		width : 50,
		formatter : function(v, r, index) {
			var attrName = "";
			if (r.elementLeft) {
				attrName = r.elementLeft.attribute.name;
			} else if (r.elementRight) {
				attrName = r.elementRight.attribute.name;
			}
			return attrName;
		}
	}, {
		field : 'elementLeft',
		title : $.i18n.prop('slaveD.arr.elementLeft'),
		width : 50,
		formatter : function(v, r, index) {
			if (v) {
				return v.name;
			}
			return "";
		}
	}, {
		field : 'elementRight',
		title : $.i18n.prop('slaveD.arr.elementRight'),
		width : 50,
		formatter : function(v, r, index) {
			if (v) {
				return v.name;
			}
			return "";
		}
	} ] ];

	slaveDArr2 = [ [ {
		field : 'id',
		title : $.i18n.prop('slaveD.arr.id'),
		width : 30,
		hidden : true
	}, {
		field : 'slaveCommande',
		title : $.i18n.prop('slaveD.arr.model.id'),
		width : 50,
		formatter : function(v, r, index) {
			if (v) {
				return v.model.name;
			}
			return "";
		}
	}, {
		field : 'component',
		title : $.i18n.prop('slaveD.arr.component'),
		width : 50,
		formatter : function(v, r, index) {
			if (v) {
				return v.name;
			}
			return "";
		}
	}, {
		field : 'attribute',
		title : $.i18n.prop('slaveD.arr.attrName'),
		width : 50,
		formatter : function(v, r, index) {
			return v.name;
		}
	}, {
		field : 'elementLeft',
		title : $.i18n.prop('slaveD.arr.elementLeft'),
		width : 50
	}, {
		field : 'elementRight',
		title : $.i18n.prop('slaveD.arr.elementRight'),
		width : 50
	} ] ];

	tmpArr = [ [
			{
				field : 'mid',
				title : $.i18n.prop('cst.obj.mid'),
				width : 30,
				hidden : true
			},
			{
				field : 'id',
				title : $.i18n.prop('cst.obj.id'),
				width : 30,
				hidden : true
			},
			{
				field : 'parentId',
				title : $.i18n.prop('cst.obj.parentId'),
				width : 30,
				hidden : true
			},
			{
				field : 'nextId',
				title : $.i18n.prop('cst.obj.nextId'),
				width : 30,
				hidden : true
			},
			{
				field : 'previousId',
				title : $.i18n.prop('cst.obj.previousId'),
				width : 30,
				hidden : true
			},
			{
				field : 'name',
				title : $.i18n.prop('cst.obj.name'),
				width : 50,
				formatter : function(v, node) {
					if (node.children) {
						v += '&nbsp;<span style=\'color:blue\'>('
								+ node.children.length + ')</span>';
					}
					return v;
				}
			},
			{
				field : 'isSendMail',
				title : $.i18n.prop('cst.obj.isSendMail'),
				width : 30,
				formatter : function(v) {
					return v > 0 ? $.i18n.prop('cst.obj.isSendMail.y') : $.i18n
							.prop('cst.obj.isSendMail.n');
				}
			}, {
				field : 'comments',
				title : $.i18n.prop('cst.obj.comments'),
				width : 30
			}, {
				field : 'hasChildren',
				title : $.i18n.prop('cst.obj.hasChildren'),
				width : 30
			}, {
				field : 'status',
				title : $.i18n.prop('cst.obj.opr'),
				width : 30,
				formatter : function(value, row) {
					return cbbV(value, stCbb);
				},
				editor : {
					type : 'combobox',
					options : {
						valueField : 'id',
						textField : 'text',
						data : stCbb,
						required : true
					}
				}
			} ] ];

	custArr = [ [ {
		field : 'id',
		title : $.i18n.prop('customer.obj.id'),
		width : 30
	}, {
		field : 'firstName',
		title : $.i18n.prop('customer.obj.firstName'),
		width : 30
	}, {
		field : 'middleName',
		title : $.i18n.prop('customer.obj.middleName'),
		width : 30
	}, {
		field : 'lastName',
		title : $.i18n.prop('customer.obj.lastName'),
		width : 30
	}, {
		field : 'sex',
		title : $.i18n.prop('customer.obj.sex'),
		width : 30,
		formatter : sexFormat
	}, {
		field : 'email',
		title : $.i18n.prop('customer.obj.email'),
		width : 30
	}, {
		field : 'fax',
		title : $.i18n.prop('customer.obj.fax'),
		width : 30
	}, {
		field : 'ctName',
		title : $.i18n.prop('customer.obj.type'),
		width : 30
	}, {
		field : 'icName',
		title : $.i18n.prop('customer.obj.use'),
		width : 30
	} ] ];
});
