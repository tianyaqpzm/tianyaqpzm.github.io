import pytest

from py_demo.word_search import Solution


@pytest.mark.parametrize("board,word,expected", [
    (
        [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
        "ABCCED",
        True,
    ),
    (
        [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
        "SEE",
        True,
    ),
    (
        [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
        "ABCB",
        False,
    ),
    ([["a"]], "a", True),
    ([["a"]], "b", False),
    ([["A","A"]], "AA", True),
    ([["A","A"]], "AAA", False),
    ([["A","B"],["C","D"]], "", True),
])
def test_exist(board, word, expected):
    sol = Solution()
    assert sol.exist(board, word) == expected


def test_path_cannot_reuse_cell():
    # Ensure the algorithm doesn't reuse the same cell twice
    board = [["A","B"],["C","D"]]
    sol = Solution()
    # Word requires reusing the same 'A' cell twice which is not allowed
    assert sol.exist(board, "AA") is False
