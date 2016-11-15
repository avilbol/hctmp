describe('Demostración de protractor', function() {
  var firstValue = element(by.model('firstValue'));
  var operationSelect = element(by.model('operation'));
  var secondValue = element(by.model('secondValue'));
  var operateButton = element(by.name('submit'));
  var result = element(by.model('vm.result'));

  function setSelection(select, option) {
    select.click();
    browser.waitForAngular();
    element.all( by.css('.md-select-menu-container.md-active md-option[value="'+option+'"]')).click();
    browser.waitForAngular();
  }

  function add(a, b) {
    firstValue.sendKeys(a);
    setSelection(operationSelect,"Suma");
    secondValue.sendKeys(b);
    operateButton.click();
  }

  beforeEach(function() {
    browser.get('http://localhost:3000/#/protractor');
  });

  it('Debe tener un título', function() {
    expect(browser.getTitle()).toEqual('HalloCasa');
  });

  it('Debe sumar 2 y 3', function() {
    add(2, 3);
    expect(result.getAttribute('value')).toEqual('5');
  });

});
