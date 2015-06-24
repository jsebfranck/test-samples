casper.options.viewportSize = {width: 1200, height: 760};
casper.on('page.error', function(msg, trace) {
   this.echo('Error: ' + msg, 'ERROR');
   for(var i = 0; i < trace.length; i++) {
       var step = trace[i];
       this.echo('   ' + step.file + ' (line ' + step.line + ')', 'ERROR');
   }
});

var environment = casper.cli.get('env');

if (!environment) {
	console.log('Usage: "casperjs test --env=<env> <scenario.js>"');
	casper.exit(1);
}

console.log('Loading the ' + environment + ' config.');

var environmentConfig = require('./env/' + environment + '.js');
module.exports = environmentConfig;
