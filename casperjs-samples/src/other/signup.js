casper.test.begin('Basic signup', 9, function suite(test) {

    var startUrl = "http://localhost/signup";

    casper.start(startUrl, function() {
        test.assertTitle("Bienvenue sur Viadeo");
        test.assertExists('form#registration', "signup form is found");

        this.fill('form#registration', {
            firstName: "toto"
        }, true);
    });

    casper.then(function() {
        test.assertTitle("Bienvenue sur Viadeo");
        test.assertExists('form#registration', "signup form is found");
        test.assertExists('.control-group [name=firstName]', "firstName is correct");
        test.assertExists('.error [name=lastName]', "lastName is in error");
        test.assertExists('.error [name=email]', "email is in error");
        test.assertExists('.error [name=password]', "password is in error");
        test.assertExists('.error [name=birthYear]', "birthYear is in error");
    });

    casper.run(function() {
        test.done();
    });
});
