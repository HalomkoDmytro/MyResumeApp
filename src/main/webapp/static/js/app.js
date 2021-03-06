// // --- load photo ---
// $(document).on("click", ".browse", function () {
//     var file = $(this).parents().find(".file");
//     file.trigger("click");
// });
// $('input[type="file"]').change(function (e) {
//     var fileName = e.target.files[0].name;
//     $("#file").val(fileName);
//
//     var reader = new FileReader();
//     reader.onload = function (e) {
//         // get loaded data and render thumbnail.
//         document.getElementById("preview").src = e.target.result;
//     };
//     // read the image file as a data URL.
//     reader.readAsDataURL(this.files[0]);
// });
// // --- END load photo ---

var resume = {

    alert: function (message) {
        alert(message);
    },

    moreProfiles: function (searchQuery) {
        var page = parseInt($('#profileContainer').attr('data-profile-number')) + 1;
        var total = parseInt($('#profileContainer').attr('data-profile-total'));
        if (page >= total) {
            return;
        }
        var url = '/fragment/more?page=' + page;
        if (searchQuery != undefined && searchQuery.trim() != '') {
            url += '&query=' + searchQuery;
        }

        $('#loadMoreContainer').css('display', 'none');
        $('#loadMoreIndicator').css('display', 'block');
        $.ajax({
            url: url,
            success: function (data) {
                $('#loadMoreIndicator').css('display', 'none');
                $('#profileContainer').append(data);
                $('#profileContainer').attr('data-profile-number', page);
                if (page >= total - 1) {
                    $('#loadMoreIndicator').remove();
                    $('#loadMoreContainer').remove();
                } else {
                    $('#loadMoreContainer').css('display', 'block');
                }
            },
            error: function (data) {
                resume.showErrorDialog(messages.errorAjax);
            }
        });
    },

    showErrorDialog: function (message) {
        alert(message);
    },

    ui: {

        template: null,

        getTemplate: function () {
            if (resume.ui.template == null) {
                var source = $("#ui-block-template").html();
                resume.ui.template = Handlebars.compile(source);
            }
            return resume.ui.template;
        },

        addBlock: function () {
            var template = resume.ui.getTemplate();
            var container = $('#ui-block-container');
            var blockIndex = container.find('.ui-item').length;
            var context = {
                blockindex: blockIndex
            }
            container.append(template(context));

        },

        removeBlock: function (index) {
            var container = $('#ui-block-container');
            var length = container.find('.ui-item').length;
            $('#ui-item-' + index).remove();
            resume.ui.replaceWithLast(index, length);
        },

        replaceWithLast: function (index, length) {
            var lastOldIndex = length - 1;
            var lastElement = '#ui-item-' + lastOldIndex;
            var container = $(lastElement);

            $(lastElement).attr('id', 'ui-item-' + index);
            var itemName = "items[" + lastOldIndex + "]";
            var allListElements = $('[name^=\"' + itemName + '\"]');

            for(var i = 0; i < allListElements.length; i++) {
                var name = allListElements[i].getAttribute('name');
                var posStart = name.indexOf('[') + 1;
                var posEnd = name.indexOf(']');
                var newAttrName = name.substring(0, posStart) + index + name.substring(posEnd, name.length);
                allListElements[i].setAttribute('name', newAttrName);
            }
        }
    }
}
