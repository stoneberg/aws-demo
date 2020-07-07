var main = (function($) {

    var init = function() {
        $('#btn-save').on('click', function(e) {
            console.log("saving posts=================>", $);
            save();
        });
    }

    var save = function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        console.log("saving data=================>", data);

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.alert('글이 등록되었습니다.');
            window.location.href="/";
        }).fail(function() {
            window.alert(JSON.stringify(error));
        });
    }

    $(function() {
       init();
    });

    return {
        save: save
    }

})(jQuery);