function forward(url){
	$("#content").load(url);
}

$(function() {
	common.ajax({
			type: "post",
			url: "/web/getNickname",
			error: function(request) {
				alert("Connection error");
			},
			success: function(data) {
				$("#nickname").html(data);
			}
		});
});
