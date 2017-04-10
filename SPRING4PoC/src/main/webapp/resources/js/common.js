var escapeHTMLTag = {
		'&': '&#38;'
		, '<': '&#60;'
		, '>': '&#62;'
		, '"': '&#34;'
		, "'": '&#39;'
		, '/': '&#x2F;'
		, '`': '&#x60;'
		, '=': '&#x3D;'
		, '#': '&#35;'
		, '(': '&#40;'
		, ')': '&#41;'
		, ';': '&#59;'
};

function escapeHTML(str) {
	
	return String(str).replace(/[&<>"'`=\/]/g, function(s) {
		
		return escapeHTMLTag[s];
	
	});

}
