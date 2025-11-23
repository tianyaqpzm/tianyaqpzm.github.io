"""Compatibility shim for the original code living under `src/leetcode`.

This module re-exports the Solution implementation so tests can import
`py_demo.word_search.Solution`.
"""
from leetcode.word_search import Solution

__all__ = ["Solution"]
