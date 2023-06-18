#!/usr/bin/env bash
# Deploy app to k8 minikube
set -e

log() {
  local m="${1}"
  local ts=$(date -u +%Y-%m-%d'T'%H:%M:%S) # osx
  echo "${ts} INFO ${m}"
}

tool_check() {
  local tools=("$@")

  for t in "${tools[@]}"; do
    echo "-> $t"
    command -v "${t}" >/dev/null 2>&1 || {
      echo "${t} is required but not found" >&2
      exit 1
    }
  done
}

validate_k8_spec() {
  local d="${1}"
  tool_check kubeval
  kubeval "${d}" >/dev/null 2>&1 || {
    exit 1
  }
}

K8_PROFILE=checkout
K8_DEPLOYMENT=k8/app-deployment.yaml

log "Build"
# shellcheck disable=SC2046
eval $(minikube docker-env -p "${K8_PROFILE}")
./gradlew app:jibDockerBuild

log "Validate spec"
validate_k8_spec "${K8_DEPLOYMENT}"

log "Deploy"
kubectl apply -f "${K8_DEPLOYMENT}"

log "
-------
D O N E
-------

🐝 To verify:

kubectl port-forward

Run benchmark tests:
ab/ab-benchmark.sh

Check logs:
kubectl log l='app=checkout-app' -f
"