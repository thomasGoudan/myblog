
//DOM加载完成执行方法
$(function () {

    var _pageSize = 5; // 存储用于搜索

    // 根据用户名、页面索引、页面大小获取用户列表
    function getUserName(pageIndex, pageSize) {
        $.ajax({
            url: "/users",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize,
                "name":$("#searchName").val()
            },
            success: function(data){
                $("#mainContainer").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    };

    // 搜索
    $("#searchNameBtn").click(function() {
        getUserName(0, _pageSize);
    });

    //分页
    $("#rightContainer").on("click",".tbpage-item",function () {
        debugger;
        var pageIndex = $(this).attr("pageIndex");
        getUserName(pageIndex, _pageSize);
    });

    //获取表单
    $("#addUser").click(function () {
        $.ajax({
            url:"users/add",
            success:function (data) {
                $("#userFormContainer").html(data);
            },
            error:function (data) {
                toastr.error(data.message);
            }
        })
    });

    //提交表单
    $("#submitEdit").click(function () {
        $.ajax({
            url:"/users",
            type:'POST',
            data:$("#userForm").serialize(),
            success:function (data) {
                $("#userForm")[0].reset();
                if (data.success) {
                    // 从新刷新主界面
                    getUserName(0, _pageSize);
                } else {
                    toastr.error(data.message);
                }
            },
            error:function () {
                toastr.error("添加失败");
            }
        })
    });

    //编辑用户
    $("#rightContainer").on("click","#blog-edit-user",function () {
        debugger;
        $.ajax({
            url:"/users/edit/"+$(this).attr("userId"),
            success:function (data) {
                $("#userFormContainer").html(data);
            },
            error:function () {
                toastr.error("编辑失败!");
            }
        })
    });

    //删除用户
    $("#blog-delete-user").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url:"/users/"+$(this).attr("userId"),
            type:"delete",
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success:function (data) {
                //成功刷新页面
                if (data.success) {
                    // 从新刷新主界面
                    getUserName(0, _pageSize);
                } else {
                    toastr.error(data.message);
                }
            },
            error:function () {
                toastr.error("error!");
            }
        })
    });



})