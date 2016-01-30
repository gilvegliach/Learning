function endsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) !== -1;
}

var fs = require('fs')

fs.readdir(process.argv[2], function (err, list) {
	if (err) throw err;
	for (var i = 0; i < list.length; i++) {
		if (endsWith(list[i], "." + process.argv[3])) {
			console.log(list[i])
		}
	}
})