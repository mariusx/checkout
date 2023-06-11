# eCom - Checkout

The app exposes one endpoint for checkout of Watches.  
It will take a list of Watch id's and calculate the total price, deducting any discounts applicable.

## Getting started

### How to build

```
./gradlew clean build
```

### How to run

This will start the app and make the api accessible on `http//localhost:8080`

```
./gradlew bootRun
```

## How to test

```bash
curl -X 'POST' \
  'http://localhost:8080/checkout' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '["001","002","003"]'
```

## Approach
- Initial setup
- Add checkout endpoint and mock response
- Impl checkout partially without discount
- Solved discount
- Added data to Watch and Discount repos
- Fix integration test
- ... stopping now

## Improvements
Code and build:
- Input validation
- More (negative) tests
- Refactoring
- Build pipeline
- Add code analysis, code coverage, dependency check to build

UseCase: 
- Maybe it should be possible to sell other items than Watches?
- Support for decimals?
- Extend response with more information?