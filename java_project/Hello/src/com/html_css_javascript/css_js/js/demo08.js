window.onload=function(){
	updateZJ();
	//当页面加载完成，我们需要绑定各种事件
	//根据id获取到表格
	var fruitTbl =  document.getElementById("tbl_fruit");
	//获取表格中的所有的行
	var rows = fruitTbl.rows ;
	for(var i = 1 ; i<rows.length-1 ; i++){
		var tr = rows[i];
		//1.绑定鼠标悬浮以及离开时设置背景颜色事件
		tr.onmouseover=showBGColor;
		tr.onmouseout=clearBGColor;
		//获取tr这一行的所有单元格
		var cells = tr.cells;
		var priceTD = cells[1];
		//2.绑定鼠标悬浮在单价单元格变手势的事件
		priceTD.onmouseover = showHand ;
		//3.绑定鼠标点击单价单元格的事件
		priceTD.onclick=editPrice;
	}

}

//当鼠标点击单价单元格时进行价格编辑
function editPrice(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var priceTD = event.srcElement ;
		// 目的是判断当前priceTD有子节点，而且第一个子节点是文本节点
        // 节点类型：TextNode对应的是3  ElementNode对应的是1
		if(priceTD.firstChild && priceTD.firstChild.nodeType==3){
			//innerText 表示获取当前节点的内部文本
			var oldPrice = priceTD.innerText ;
			//innerHTML 表示设置当前节点的内部HTML
			priceTD.innerHTML = "<input type='text' size='4'/>";
			// <td><input type='text' size='4'/></td>
			var input = priceTD.firstChild;
			// 当html的标签为input标签时（上面已经将标签改为input了）
			if(input.tagName == "INPUT"){
				input.value = oldPrice ;
				//选中输入框内部的文本（同时也自动进入到输入框焦点）
				input.select();
				//4.绑定输入框失去焦点事件 , 失去焦点，更新单价
				input.onblur=updatePrice ;
			}
		}
		
	}
}

function updatePrice(){
	if(event && event.srcElement && event.srcElement.tagName=="INPUT"){
	    // input是td节点的子节点（文本类型，表示了单元格的具体内容）
		var input = event.srcElement ;
		// 将input父节点（td）的文本以及背景颜色改变一下
		input.parentElement.style.color = "red";
		input.parentElement.style.backgroundColor = "gold";
		var newPrice = input.value ;
		//input节点的父节点是td
		var priceTD = input.parentElement ;
		priceTD.innerText = newPrice ;

		//更新当前行的小计这一个格子的值
		//传入函数的实参是td的父元素，即tr（一行）
		updateXJ(priceTD.parentElement);

	}
}

//更新指定行的小计
function updateXJ(tr){
	if(tr && tr.tagName=="TR"){
	    // 得到一行的单元格数组
		var tds = tr.cells;
		var price = tds[1].innerText ;
		var count = tds[2].innerText ;
		//innerText获取到的值的类型是字符串类型，因此需要类型转换，才能进行数学运算
		var xj = parseInt(price) * parseInt(count);
		tds[3].innerText = xj ;
		//更新最后一个行-总计
		updateZJ();
	}
}

//更新总计
function updateZJ(){
	var fruitTbl = document.getElementById("tbl_fruit");
	var rows = fruitTbl.rows ;
	var sum = 0 ;
	for(var i = 1; i<rows.length-1 ; i++){
		var tr = rows[i];
		var xj = parseInt(tr.cells[3].innerText);
        //NaN    not a number 不是一个数字
		sum = sum + xj ;
	}
	rows[rows.length-1].cells[1].innerText = sum ;
}

//当鼠标悬浮时，显示背景颜色
function showBGColor(){
	//event : 当前发生的事件
	//event.srcElement : 事件源
	//alert(event.srcElement);
	//alert(event.srcElement.tagName);	--> TD
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		//td.parentElement 表示获取td的父元素 -> TR
		var tr = td.parentElement ;
		//如果想要通过js代码设置某节点的样式，则需要加上 .style
		tr.style.backgroundColor = "mediumslateblue" ;

		//tr.cells表示获取这个tr中的所有的单元格
		var tds = tr.cells;
		for(var i = 0 ; i<tds.length ; i++){
			tds[i].style.color="white";
		}
	}
}

//当鼠标离开时，恢复原始样式
function clearBGColor(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		var tr = td.parentElement ;
		tr.style.backgroundColor="transparent";
		var tds = tr.cells;
		for(var i = 0 ; i<tds.length ; i++){
			tds[i].style.color="threeddarkshadow";
			tds[i].style.backgroundColor="transparent";
		}
	}
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		//cursor : 光标
		td.style.cursor="hand";
	}

}