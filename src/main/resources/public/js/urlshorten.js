function sendPost() {
	console.log("Value entered in the field " , $("#urlField").val());
    $.ajax({
        type: "POST",
        url: "/rest/url",
        data: $("#urlField").val(),
        contentType: 'text/plain',
        success: function(data, textStatus, request){
        	console.log(request.getResponseHeader('id'));
            $("#link1").text("http://localhost:9000/rest/url/"+request.getResponseHeader('id'));
            $.ajax({
                type: "GET",
                url: "/rest/url/"+request.getResponseHeader('id'),
                success: function (data) {
                	  $("a").attr("href",data.url);
                }
            });
       },
       error: function (request, textStatus, errorThrown) {
            alert("Some thing went wrong, make sure the URL is valid URL. And also check for any error in the application console");
       }
    },
    );
}
$(document).on({
    ajaxStart: function(){
        $("body").addClass("loading");
    },
    ajaxStop: function(){
        $("body").removeClass("loading");
    }
});