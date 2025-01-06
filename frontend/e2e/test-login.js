import { Builder, By, until } from 'selenium-webdriver';
import { expect } from 'chai';

 
(async () => {
  const options = new Options();
  options.addArguments(
    '--headless',        // Run Chrome in headless mode
    '--disable-gpu',     // Disable GPU acceleration
    '--no-sandbox',      // Disable sandboxing (needed in CI environments)
    '--disable-dev-shm-usage',  // Avoid issues with shared memory
    '--remote-debugging-port=9222' // Open a debugging port (optional)
  );

  const driver = await new Builder()
    .forBrowser('chrome')
    .setChromeOptions(options)
    .build();
  await driver.get('http://localhost:4200');

  const signInButton = await driver.findElement(By.id('sign'));
  await signInButton.click();

  const formElement = await driver.wait(
    until.elementLocated(By.id('loginForm')), // Wait for the form element by ID
    10000 // 10 seconds timeout
  );

  // Check if the form is visible
  const isFormVisible = await formElement.isDisplayed();
  expect(isFormVisible).to.be.true;
  console.log('Login form is visible: ', isFormVisible);

  await driver.quit();
})();
