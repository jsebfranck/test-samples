function LoginPage() {

  this.startOnLoginPage = function () {
    casper.echo("base url is : " + casper.cli.options.baseUrl);
    casper.start(casper.cli.options.baseUrl + '/login');
  };

  this.checkPage = function () {
    casper.then(function () {
      casper.test.assertUrlMatch('login', 'Is on login page');
      casper.test.assertExists('form[name="f"]', 'Login page form has been found');
    });
  };

  this.fillForm = function (username, password) {
    casper.then(function () {
      this.fill('form[name="f"]', {
        'j_username': username,
        'j_password': password
      }, false);
    });
  };

  this.submitForm = function () {
    casper.then(function () {
      this.click('form[name="f"] button[type="submit"]', 'Login submit button clicked');
    });
  };
}