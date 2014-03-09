function BookingListingPage() {

  this.checkPage = function () {
    casper.then(function () {
      casper.test.assertUrlMatch('hotels/search', 'Is on booking listing page');
    });
  };

  this.checkThatBookingsAreDisplayed = function() {
    casper.then(function () {
      casper.test.assertTextExists('Current Hotel Bookings', 'bookings title are displayed');
      casper.test.assertExists('#bookings > table > tbody > tr', 'bookings are displayed');
    });
  };
}