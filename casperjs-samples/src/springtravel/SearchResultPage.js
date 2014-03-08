function SearchResultPage() {

  this.checkIsOnSearchResultPage = function () {
    casper.then(function () {
      casper.test.assertUrlMatch('hotels?searchString=', 'Is on search result page');
    });
  };

  this.checkThatResultsAreDisplayed = function(expectedCount) {
    casper.then(function () {
      casper.test.assertElementCount('#hotelResults > table > tbody > tr', expectedCount, expectedCount + ' hotels have been found');
    });
  };
}