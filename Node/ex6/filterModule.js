module.exports = function (dirName, extension, callback) {
	var fs = require('fs')
	var path = require('path')
	fs.readdir(dirName, function (err, list) {
		if (err) {
			callback(err);
		} else {
			var filtered = []
			list.forEach(function (elem, index, array) {
				var ext = "." + extension
				if (path.extname(elem) === ext) {
					filtered.push(elem)
				}
			})
			callback(null, filtered)
	    }
	})
}