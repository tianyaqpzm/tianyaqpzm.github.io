py_demo — small LeetCode helper package

This directory contains a small Python project layout for experimenting with
LeetCode solutions and running tests locally.

Quick start

1. Create and activate a virtual environment (recommended):

	# Windows PowerShell
	python -m venv .venv; .\.venv\Scripts\Activate.ps1

2. Install the project in editable mode and test dependencies:

	pip install -e .
	pip install -r requirements.txt

3. Run tests:

	pytest -q

Notes

- The project uses a src/ layout. Tests will pick up the package from src via
  `tests/conftest.py`, so installing is optional but recommended for a realistic
  workflow (`pip install -e .`).
- The virtual environment directory `.venv/` is in `.gitignore` to avoid
  committing environment artifacts.
