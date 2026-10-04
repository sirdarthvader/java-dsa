#!/usr/bin/env bash
set -euo pipefail

if [[ $# -ne 1 ]]; then
    echo "Usage: ./run_java.sh <RelativePathToSource.java>" >&2
    exit 2
fi

FILE=$1
if [[ ! -f "$FILE" ]]; then
    echo "Java source file not found: $FILE" >&2
    exit 2
fi

BASENAME=$(basename "$FILE" .java)
PKG=$(awk '/^[[:space:]]*package[[:space:]]+/ { sub(/^[[:space:]]*package[[:space:]]+/, ""); sub(/[[:space:]]*;.*/, ""); print; exit }' "$FILE")
BUILD_DIR=$(mktemp -d)
trap 'rm -rf "$BUILD_DIR"' EXIT

javac -d "$BUILD_DIR" "$FILE"
if [[ -n "$PKG" ]]; then
    java -ea -cp "$BUILD_DIR" "$PKG.$BASENAME"
else
    java -ea -cp "$BUILD_DIR" "$BASENAME"
fi
