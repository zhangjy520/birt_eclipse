

$(function () {
    
});


function f_initDatatable() {
    $('.table tbody').on("mouseenter mouseleave", "tr", function () {
        $(this).toggleClass('hovered');
    }).on('click', "tr", function () {
        $(this).toggleClass('selected');//选中行高亮
        if ($(this).find("input:checkbox").eq(0).prop("checked")) {
            $(this).find("input:checkbox").eq(0).removeAttr("checked");
        } else {
            $(this).find("input:checkbox").eq(0).prop("checked", true);
        }
    });
}

function f_initdatetimepicker() {
    if ($('.datepicker').length > 0) {
        $('.datepicker').datetimepicker({
            language: 'cn',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
    }
}


//加载框
function sysLoading(msg) {
    if (typeof (layer) != 'undefined') {
        layer.closeAll();
        if (msg == undefined || msg == null || msg == '') {
            msg = '加载中...'
        }
        layer.msg(msg, { icon: 16, time: 0, shade: 0.1 });
    }
}

//提示框
function sysAlert(msg, fun) {
    if (typeof (layer) != 'undefined') {
        layer.closeAll();
        if (typeof fun == 'function') {
            layer.alert(msg, fun);
        } else {
            layer.alert(msg);
        }
    }
}

//确认框
function sysConfirm(msg, fun) {
    if (typeof (layer) != 'undefined') {
        layer.closeAll();
        if (typeof fun == 'function') {
            layer.confirm(msg, fun);
        }
    }
}
//关闭弹出框
function closeAlert() {
    if (typeof (layer) != 'undefined') {
        layer.closeAll();
    }
}


function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}



//日期格式转换
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };

    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }

    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }

    return format;
}

//异步获取json的日期格式化：去掉/Date( )/
function convertTime(jsonTime, format) {
    var date = new Date(parseInt(jsonTime.replace("/Date(", "").replace(")/", ""), 10));
    var formatDate = date.format(format);
    return formatDate;
}