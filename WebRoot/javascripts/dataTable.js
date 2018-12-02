/*
function MyDataTable(id,options){
	options.processing = true;
	options.serverSide = true;
	options.searching = false;
	options.paging = true;
	//options.url = 'test/testMyBatis.do';
	return $('#'+id).DataTable({
		"oLanguage" : {
			"sInfoFiltered": ""
		},
		"iDisplayLength" : 10,
		"info": false,
		"processing": options.processing,
        "serverSide": options.serverSide,
        "searching": options.searching,
        "paging" : options.paging,
        "bLengthChange" : false,
        "columns" : [
            { data: "applyId" },
            { data: "applyStatus" },
            { data: "applyId" }
        ],
		ajax : {
			url : options.url,
			
			type : 'POST'
			
			dataSrc :function(data ){
				console.log(data);
				if(data.result==false){
					toastr["error"](data.msg+'!');
					return [];
				}else{
					return data.data;
				}
			}
			
		}
	});
}*/

//modify zh 20180206   
function MyDataTable(id,options){
	options.processing = true;
	options.serverSide = true;
	options.searching = false;
	options.paging = true;
	return $('#'+id).DataTable({
		"oLanguage" : {
			"sInfoFiltered": "",
			"sZeroRecords": "没有查询到相关数据",
			"oPaginate": {  
					"sPrevious": "上一页",  
					"sNext": "下一页",  
			}  
		},
		"destroy":true,//解决重新加载表格内容问题
		"iDisplayLength" : 10,
		"info": false,
		"processing": options.processing,
        "serverSide": options.serverSide,
        "searching": options.searching,
        "paging" : options.paging,
        "bLengthChange" : false,
        "columns" : options.columns,
		ajax : {
			url : options.url,
			type : 'POST',
			data: options.data,
			dataSrc :function(data ){
				console.log(data);
				if(data.result==false){
					toastr["error"](data.msg+'!');
					return [];
				}else{
					return data.data;
				}
			}
		}
	});
}