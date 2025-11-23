"""Test configuration.

Ensure tests can import packages from `src/` when running pytest without installing the
package into the environment. This keeps test runs simple for local development.
"""
import os
import sys

# Insert project's src/ at front of path so imports like `py_demo.*` resolve.
ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
SRC = os.path.join(ROOT, "src")
if SRC not in sys.path:
    sys.path.insert(0, SRC)
