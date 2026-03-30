#!/bin/zsh

set -euo pipefail

find . -name "*.java" -print0 | xargs -0 javac
