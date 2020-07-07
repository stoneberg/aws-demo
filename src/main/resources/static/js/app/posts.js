const main = (function($) {

    const init = function() {
        $('#btn-save').on('click', function(e) {
            save();
        });
        $('#btn-update').on('click', function(e) {
            update();
        });
    }

    const save = function() {
        const data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.alert('글이 등록되었습니다.');
            window.location.href="/";
        }).fail(function(error) {
            window.alert(JSON.stringify(error));
        });
    }

    const update = function() {
        const data = {
            title: $('#title').val().trim(),
            content: $('#content').val().trim()
        }

        const id = $('#id').val();
        console.log("saving data=================>", id, data);

        $.ajax({
            type: 'PUT',
            url: `/api/v1/posts/${id}`,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.alert('글이 수정되었습니다.');
            window.location.href="/";
        }).fail(function(error) {
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