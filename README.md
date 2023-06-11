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

## Improvements