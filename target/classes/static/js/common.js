$("select").select2({
    theme: "bootstrap-5",
    containerCssClass: "select2--small", // For Select2 v4.0
    selectionCssClass: "select2--small", // For Select2 v4.1
    dropdownCssClass: "select2--small",
    placeholder: "Select a state",
    allowClear: true,
    closeOnSelect: true,
});

function ufn_select2_getData(vUrl, element, vPlaceH, vChangeVal, onSelect) {
    $(element).empty();
    $.ajax({
        url: vUrl,
        dataType: "json",
        contentType: "application/json; UTF-8;",
        success: function (data) {
            //data = JSON.parse(data);
            $(element).prepend('<option selected=""></option>').select2({
                allowClear: true,
                theme: "bootstrap-5",
                placeholder: vPlaceH,
                data: data,
            });
            $(element).val(vChangeVal).trigger('change');
        }
    });
    $(element).on('select2:select', function (e) {
        onSelect();
    });

}

// 그리드ID, GET 주소, 폼 아이디
function G_load(VGrid, vUrl, vFormId) {

    var params = $("#" + vFormId).serialize();
    $.ajax({
        url: vUrl,
        method: "get",
        contentType: "application/json; UTF-8;",
        dataType: "JSON",
        data: params,
        success: function (result) {
            VGrid.resetData(result);
            console.log("성공");
        }
        // , beforeSend: function () {
        //     //(이미지 보여주기 처리)
        //     $('.btn_s').attr("disabled", true);
        //     VGrid.clear();
        // }
        // , complete: function (result) {
        //     //(이미지 감추기 처리)
        //     $.each(result.responseJSON, function (index, item) {
        //         if (item.cont_stat == "계약종료") {
        //             VGrid.addCellClassName(index, 'cont_stat', 'custom-txt-color');
        //         }
        //     });
        //     $('.btn_s').attr("disabled", false);
        // }
        , error: function (e) {
            //조회 실패일 때 처리
        }
    });
}