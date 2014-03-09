
phantom.page.injectJs('LoginPage.js');
phantom.page.injectJs('SearchPage.js');
phantom.page.injectJs('SearchResultPage.js');

var baseUrl = 'http://localhost:8080/';
var loginPage = new LoginPage();
var searchPage = new SearchPage();
var searchResultPage = new SearchResultPage();

casper.test.begin('When I connect myself I should see my bookings', function (test) {
  loginPage.startOnLoginPage();
  loginPage.checkPage();
  loginPage.fillForm('scott', 'rochester');
  loginPage.submitForm();

  searchPage.checkPage();
  searchPage.checkThatBookingsAreDisplayed();

  casper.run(function () {
    test.done();
  });
});

casper.test.begin('When I connect myself and search hotels in Atlanta Then should find three hotels', function (test) {
  loginPage.startOnLoginPage();
  loginPage.checkPage();
  loginPage.fillForm('scott', 'rochester');
  loginPage.submitForm();

  searchPage.checkPage();
  searchPage.fillSearchForm('Atlanta');
  searchPage.submitSearchForm();

  searchResultPage.checkPage();
  searchResultPage.checkThatResultsAreDisplayed(3);

  casper.run(function () {
    test.done();
  });
});