import 'jest-preset-angular/setup-jest';
jest.mock('@primeng/themes/aura', () => {
  return {}; // Mock the Aura theme module to avoid import errors
});
