$(function () {
    $("#uploadForm").submit(upload);
});

function upload() {
    $.ajax({
        url: "http://upload-z1.qiniup.com",
        method: "post",
        processData: false,
        contentType: false,
        data: new FormData($("#uploadForm")[0]),
        // 七牛云服务器的响应，data的格式已经是JOSN了，无需data = $.parseJSON(data);
        success: function (data) {
            if (data && data.code == 0) {
                // 更新头像访问路径
                $.post(
                    CONTEXT_PATH + "/user/header/url",
                    {"fileName": $("input[name='key']").val()},
                    // 本地服务器响应
                    function (data) {
                        data = $.parseJSON(data); // 转成JSON
                        if (data.code == 0) {
                            alert(data.msg);
                            //刷新页面
                            setTimeout(function () {
                                window.location.reload();
                            }, 2000);
                        } else {
                            alert(data.msg);
                        }
                    }
                );
            } else {
                alert("上传失败!");
            }
        }
    });
    // 时间到此为止，不用再向下执行了
    return false;
}