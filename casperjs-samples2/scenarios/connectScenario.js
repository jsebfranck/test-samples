var connectionPage = require('../pages/ConnectionPage');

casper.test.begin('Should connect then disconnect', function(test) {
   connectionPage.connect();

   casper.run(function() {
	   test.done();
   });
});

/*
To disconnect :

casper.then(function() {
	this.wait(1000);
	phantom.clearCookies();
});

*/