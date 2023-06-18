#!/usr/bin/env bash
# Benchmark using ab - run it from project root
ab -p ab/watches.json -T 'application/json' -n 100 -c 4 -t 120 http://localhost:8080/checkout