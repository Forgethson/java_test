function delFruit(fid) {
    if (confirm('是否确认删除？')) {
        // 向del.do 标签对应的DelServlet 发送请求，且携带有下面的参数
        window.location.href = 'fruit.do?fid=' + fid + '&operate=del';
    }
}

function page(pageNo) {
    window.location.href = "fruit.do?pageNo=" + pageNo;
}