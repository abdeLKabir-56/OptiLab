import { Builder, By, until } from 'selenium-webdriver';
import { expect } from 'chai';

(async () => {
  const options = new chrome.Options();
  options.addArguments(
    '--headless',          // Run headlessly
    '--disable-gpu',       // Disable GPU for headless mode
    '--no-sandbox',        // Avoid issues with sandboxing in CI
    '--disable-dev-shm-usage'  // Avoid shared memory issues in CI
  );
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
