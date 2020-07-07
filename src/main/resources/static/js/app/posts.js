const main = (function($) {

    const init = function() {
        // 등록
        $('#btn-save').on('click', function(e) {
            savePost();
        });
        // 수정
        $('#btn-update').on('click', function(e) {
            updatePost();
        });
        // 삭제
        $('#btn-delete').on('click', function(e) {
            deletePost();
        });
    }

    const savePost = function() {
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

    const updatePost = function() {
        const data = {
            title: $('#title').val().trim(),
            content: $('#content').val().trim()
        }

        const id = $('#id').val();

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

    const deletePost = function() {
        const id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: `/api/v1/posts/${id}`,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function() {
            window.alert('글이 삭제되었습니다.');
            window.location.href="/";
        }).fail(function(error) {
            window.alert(JSON.stringify(error));
        });
    };

    $(function() {
       init();
    });

    return {

    }

})(jQuery);