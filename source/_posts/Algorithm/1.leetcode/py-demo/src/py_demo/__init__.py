"""py_demo package shim.

This package exposes implementations used by the tests. It re-exports
selected implementations that currently live in `src/leetcode`.
"""
from .word_search import Solution  # re-export for compatibility with tests

__all__ = ["Solution"]
