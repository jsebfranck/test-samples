

casper.test.begin('When I connect I should see my bookings', function (test) {

  var scenarioUrl = 'http://localhost:8080/login';
  casper.start(scenarioUrl);

  casper.then(function () {
    test.assertExists('form[name="f"]', 'Is on login page');
  });

  casper.then(function () {
    this.fill('form[name="f"]', {
      'j_username': 'scott',
      'j_password': 'rochester'
      }, false);
  });

  casper.then(function () {
    this.click('form[name="f"] button[type="submit"]', 'Login submit button clicked');
  });

  casper.then(function () {
    test.assertUrlMatch('hotels/search', 'Is on search page');
  });

  casper.then(function () {
    test.assertTextExists('Current Hotel Bookings', 'bookings title are displayed');
    test.assertExists('#bookings > table > tbody > tr', 'bookings are displayed');
  });

  casper.run(function () {
    test.done();
  });
});

casper.test.begin('When I connect myself and search hotels in Atlanta Then should find three hotels', function (test) {

  var scenarioUrl = 'http://localhost:8080/login';
  casper.start(scenarioUrl);

  casper.then(function () {
    test.assertExists('form[name="f"]', 'Is on login page');
  });

  casper.then(function () {
    this.fill('form[name="f"]', {
      'j_username': 'scott',
      'j_password': 'rochester'
      }, false);
  });

  casper.then(function () {
    this.click('form[name="f"] button[type="submit"]', 'Login submit button clicked');
  });

  casper.then(function () {
    test.assertUrlMatch('hotels/search', 'Is on search page');
  });

  casper.then(function () {
    this.fill('form[id="searchCriteria"]', {
      'searchString': 'Atlanta'
      }, false);
  });

  casper.then(function () {
    this.click('form[id="searchCriteria"] button[type="submit"]');
  });

  casper.then(function () {
    test.assertUrlMatch('hotels?searchString=Atlanta', 'Is on search result page');
  });

  casper.then(function () {
    test.assertElementCount('#hotelResults > table > tbody > tr', 3, '3 hotels have been found');
  });

  casper.run(function () {
    test.done();
  });
});
