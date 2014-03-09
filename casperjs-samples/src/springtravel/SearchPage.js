function SearchPage() {

  this.checkPage = function () {
    casper.then(function () {
      casper.test.assertUrlMatch('hotels/search', 'Is on search page');
    });
  };

  this.checkThatBookingsAreDisplayed = function() {
    casper.then(function () {
      casper.test.assertTextExists('Current Hotel Bookings', 'bookings title are displayed');
      casper.test.assertExists('#bookings > table > tbody > tr', 'bookings are displayed');
    });
  };

  this.fillSearchForm = function(searchTerms) {
    casper.then(function () {
      this.fill('form[id="searchCriteria"]', {
        'searchString': searchTerms
        }, false);
    });
  };

  this.submitSearchForm = function() {
    casper.then(function () {
      this.click('form[id="searchCriteria"] button[type="submit"]');
    });
  };
}