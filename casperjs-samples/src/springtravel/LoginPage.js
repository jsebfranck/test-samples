function LoginPage() {

  this.startOnLoginPage = function () {
    casper.start('http://localhost:8080/login');
  };

  this.checkIsOnLoginPage = function () {
    casper.then(function () {
      casper.test.assertExists('form[name="f"]', 'Is on login page');
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