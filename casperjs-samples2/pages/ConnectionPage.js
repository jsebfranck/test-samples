var config = require('../config/Config'),
  utils = require('../utils/Utils');

var selectors = {

};

exports.connect = function() {
	casper.start(config.url);

	utils.capture('ConnectionPage.connected');
};
