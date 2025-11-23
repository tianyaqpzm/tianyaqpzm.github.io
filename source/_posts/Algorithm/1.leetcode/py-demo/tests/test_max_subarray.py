import pytest

from py_demo.max_subarray import Solution


@pytest.mark.parametrize("nums,expected", [
    ([1], 1),
    ([-2,1,-3,4,-1,2,1,-5,4], 6),  # example: [4,-1,2,1]
    ([-1], -1),
    ([0, 0, 0], 0),
])
def test_max_subarray(nums, expected):
    sol = Solution()
    assert sol.maxSubArray(nums) == expected


def test_empty_input_raises():
    sol = Solution()
    with pytest.raises(ValueError):
        sol.maxSubArray([])
